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

import com.google.zxing.FormatException;
import com.hust.group11.ecobikerentalgroup11.Constants;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import com.hust.group11.ecobikerentalgroup11.entity.DockingPoint;
import com.hust.group11.ecobikerentalgroup11.entity.Transaction;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.boundary.PaymentScreen;
import com.hust.group11.ecobikerentalgroup11.boundary.ReturnBikeScreen;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Thanh Long
 */
public class ReturnBikeController {

    /**
     * Show information bike request to rent
     *
     * @param bike
     * @param transaction
     * @param paymentInfoTable
     * @return
     * @throws Exception
     */
    public double showInfoReturn(Bike bike, Transaction transaction, JTable paymentInfoTable) throws Exception {
        DefaultTableModel model = (DefaultTableModel) paymentInfoTable.getModel();
        double amount = calculateAmountReturn(transaction);
        model.addRow(new Object[]{"Transaction Id", transaction.getPaymentId()});
        model.addRow(new Object[]{"Bike Model", bike.getModel()});
        model.addRow(new Object[]{"Bike Value", bike.getValue() + Constants.MONEY_UNIT});
        model.addRow(new Object[]{"Time Start", transaction.getStartTime()});
        model.addRow(new Object[]{"End Start", transaction.getEndTime()});
        model.addRow(new Object[]{"Time Rental", transaction.getTimeRental()});
        model.addRow(new Object[]{"Deposit", transaction.getDeposit() + Constants.MONEY_UNIT});
        model.addRow(new Object[]{"Bike Retal", transaction.getBikeRental() + Constants.MONEY_UNIT});
        model.addRow(new Object[]{"Have To Pay", amount + Constants.MONEY_UNIT});
        return amount;
    }
    
    /**
     * 
     * @param transaction
     * @return 
     */
    public double calculateAmountReturn(Transaction transaction) {
        double amount = transaction.getBikeRental();
        if (transaction.getBikeTransaction().getType().equals(Constants.ELECTRIC_BIKE_TYPE)) {
            amount = (amount * Constants.ELECTRIC_PRICE);
        }
        return amount - transaction.getDeposit();
    }

    /**
     * Read and validate QR Code
     *
     * @param barCode
     * @return DockingPoint
     * @throws SQLException
     * @throws FormatException
     * @throws IOException
     */
    public DockingPoint handleCheckBarCode(String barCode) throws SQLException, FormatException, IOException {
        ArrayList<DockingPoint> arrayDockingPoints = new DockingPoint().getArrayDockingPoint();
        boolean isSuccess = false;
        for (DockingPoint dockingPoint : arrayDockingPoints) {
            if (barCode == null ? dockingPoint.getBarCode() == null : barCode.equals(dockingPoint.getBarCode())) {
                isSuccess = true;
                return dockingPoint;
            }
        }
        return null;
    }

    /**
     * Process return bike request
     *
     * @param transaction
     * @param bike
     * @param dockingPoint
     * @param screen
     */
    public void processReturnBike(Transaction transaction, Bike bike, DockingPoint dockingPoint, JFrame screen) {
        try {
            MainEntry.move(screen, new PaymentScreen(transaction, dockingPoint, screen));
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBikeScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReturnBikeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
