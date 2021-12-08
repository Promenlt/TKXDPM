/* 
 * --------------------ECOBIKE RENTAL 1.0.1--------------------
 *
 * Design and Software Construction 20201
 *
 * Copyright (C) 2020 by Group 11
 *
 * Nguyen Thanh Long
 * Nguyen Hai Long
 * Nguyen Cong Luat
 * UN LyAn
 *
 * This software is created for academic purposes only. Not for
 * commercial purposes. We do not guarantee maintenance issues.
 *
 * ------------------------------------------------------------
 */
package com.hust.group11.ecobikerentalgroup11.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hust.group11.ecobikerentalgroup11.Constants;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import com.hust.group11.ecobikerentalgroup11.entity.DockingPoint;
import com.hust.group11.ecobikerentalgroup11.entity.PaymentMethod;
import com.hust.group11.ecobikerentalgroup11.entity.Transaction;
import com.hust.group11.ecobikerentalgroup11.entity.Interbank;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thanh Long
 */
public class PaymentController {

    private final Gson gson;

    /**
     *
     * @throws SQLException
     */
    public PaymentController() throws SQLException {
        this.gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    public int createNewPaymentMethod(String nameMethod, String cardCode, String nameOwner, String cvvCode, String dateExpired) {
        int result = 0;
        try {
            String sqlString = "Insert into payment_method values (null, '" + nameMethod + "', '" + cardCode + "', '" + nameOwner + "', '" + cvvCode + "', '" + dateExpired + "')";
            result = MainEntry.database.insert(sqlString);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Set payment method for payment screen.
     *
     * @param arrPaymentMethod
     * @param depositComboBox
     */
    public void setPaymentMethod(ArrayList<PaymentMethod> arrPaymentMethod, JComboBox<String> depositComboBox) {
        try {
            String sqlString = "select * from payment_method";
            ResultSet result = MainEntry.database.query(sqlString);
            int size = MainEntry.database.getRow(sqlString);
            String[] nameMethod = new String[size];
            int i = 0;
            while (result.next()) {
                PaymentMethod paymentMethod = new Interbank(result.getString("method_name"),
                        result.getString("card_code"), result.getString("owner"), result.getString("cvv_code"),
                        result.getString("date_expire"));
                arrPaymentMethod.add(paymentMethod);
                nameMethod[i] = result.getString("method_name");
                i++;
            }
            depositComboBox.setModel(new DefaultComboBoxModel(nameMethod));
        } catch (SQLException e) {
            System.out.println("Error show payment method: " + e);
        }
    }

    /**
     * Payment handle for return event
     *
     * @param paymentMethod
     * @param amountDeposit
     * @param bike
     * @param dockingPoint
     * @param transaction
     * @return
     * @throws ParseException
     */
    public Transaction processRentPayment(PaymentMethod paymentMethod, double amountDeposit, Bike bike, DockingPoint dockingPoint, Transaction transaction) throws ParseException {
        try {
            JsonObject paymentResponse = paymentMethod.pay(amountDeposit);
            System.out.println("response interbank: " + paymentResponse.toString());
            JOptionPane.showMessageDialog(null, getError(paymentResponse.get("errorCode").getAsInt()));
            if (paymentResponse.get("errorCode").getAsInt() == 0) {
                boolean insertTransaction = transaction.insertTransaction(bike.getBikeId(), amountDeposit,
                        paymentResponse.get("transaction").getAsJsonObject().get("transactionId").getAsString(),
                        Constants.TRANSACTION_RENTING);
                if (updateStatus(bike, dockingPoint, "Rented", insertTransaction)) {
                    transaction = Transaction.getTransaction();
                    if (transaction != null) {
                        return transaction;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Payment handle for return event.
     *
     * @param paymentMethod
     * @param amount
     * @param bike
     * @param dockingPoint
     * @param transaction
     * @return
     */
    public boolean processReturnPayment(PaymentMethod paymentMethod, double amount, Bike bike, DockingPoint dockingPoint, Transaction transaction) {
        String Command = amount > 0 ? Constants.PAY_COMMAND : Constants.REFUND_COMMAND;
        try {
            JsonObject paymentResponse = paymentMethod.refund(Math.abs(amount));
            System.out.println("response interbank return :" + paymentResponse.toString());
            JOptionPane.showMessageDialog(null, getError(paymentResponse.get("errorCode").getAsInt()));
            if (paymentResponse.get("errorCode").getAsInt() == 0) {
                if (updateStatus(bike, dockingPoint, "Returned", transaction.updateTransaction(Constants.TRANSACTION_SAVED, amount))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update status bike, user, docking point
     *
     * @param bike
     * @param dockingPoint
     * @param status
     * @param insertPayment
     * @return true if update status success, if one of them fail, it will fail.
     * @throws SQLException
     */
    public boolean updateStatus(Bike bike, DockingPoint dockingPoint, String status, boolean insertPayment) throws SQLException {
        int dockStatus = 1, bikeStatus = 1, userStatus = 1;
        if (status.equals("Returned")) {
            dockStatus = Constants.DOCK_HAS_BIKE;
            bikeStatus = Constants.BIKE_AVAIL;
            userStatus = Constants.USER_AVAIL;
        } else if (status.equals("Rented")) {
            dockStatus = Constants.DOCK_EMPTY;
            bikeStatus = Constants.BIKE_RENTED;
            userStatus = Constants.USER_RENTING;
        }
        boolean updateBike = bike.updateBikeStatus(bikeStatus, dockingPoint);
        boolean updateUser = MainEntry.user.updateUserStatus(userStatus);
        boolean updateDock = dockingPoint.updateDockStatus(dockStatus);
        return updateBike && updateUser && updateDock && insertPayment;
    }

    /**
     * Map error code to string error.
     *
     * @param errorCode
     * @return
     */
    public String getError(int errorCode) {
        for (int i = 0; i < Constants.ERR_CODE.length; i++) {
            if (i == errorCode) {
                return Constants.ERR_CODE[i];
            }
        }
        return "Default Error";
    }

}
