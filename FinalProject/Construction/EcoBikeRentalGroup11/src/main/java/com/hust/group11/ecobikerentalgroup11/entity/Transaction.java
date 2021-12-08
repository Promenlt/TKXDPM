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
package com.hust.group11.ecobikerentalgroup11.entity;

import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.controller.RentingController;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Thanh Long
 */
public class Transaction {

    private String startTime, endTime, paymentId, timeRental;
    private final int bikeId;
    private final int userId;
    private final int transactionId;
    private final int status;
    double deposit, bikeRental;
    private RentingController rentingController;

    /**
     * Constructor 1 init important info for transaction
     *
     * @param bikeId
     * @param userId
     * @param status
     */
    public Transaction(int bikeId, int userId, int status) {
        this.bikeId = bikeId;
        this.userId = userId;
        this.status = status;
        this.transactionId = 0;
        this.bikeRental = 0.0;
    }

    /**
     * Constructor 2 init fully info fro transaction
     *
     * @param startTime
     * @param endTime
     * @param bikeId
     * @param userId
     * @param transactionId
     * @param status
     * @param deposit
     * @param paymentId
     */
    public Transaction(String startTime, String endTime, int bikeId, int userId, int transactionId,
            int status, double deposit, String paymentId) {
        this.rentingController = new RentingController();
        this.startTime = startTime;
        this.bikeId = bikeId;
        this.userId = userId;
        this.transactionId = transactionId;
        this.status = status;
        this.deposit = deposit;
        this.endTime = endTime;
        this.paymentId = paymentId;
    }

    /**
     * Constructor 3.init info transaction for test
     *
     * @param bikeId
     * @param userId
     * @param status
     * @param deposit
     * @param bikeRental
     */
    public Transaction(int bikeId, int userId, int status, double deposit, double bikeRental) {
        this.bikeId = bikeId;
        this.userId = userId;
        this.status = status;
        this.transactionId = 0;
        this.bikeRental = bikeRental;
        this.deposit = deposit;
    }

    /**
     * Get start time of transaction.
     *
     * @return string
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Get end time of transaction.
     *
     * @return String
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Get bike id of transaction.
     *
     * @return integer
     */
    public int getBikeId() {
        return bikeId;
    }

    /**
     * Get transaction id of transaction.
     *
     * @return integer
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Get bike rental of transaction.
     *
     * @return double.
     */
    public double getBikeRental() {
        return bikeRental;
    }

    /**
     * Get deposit of transaction.
     *
     * @return double
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * Get payment id of transaction
     *
     * @return String
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Get time rental of transaction.
     *
     * @return String
     */
    public String getTimeRental() {
        return timeRental;
    }

    /**
     * Update time of transaction to return bike
     *
     * @param endTime
     * @param amountPayment
     * @param totalTime
     */
    public void refeshTransactionTimeNow(String endTime, double amountPayment, String totalTime) {
        this.endTime = endTime;
        this.bikeRental = amountPayment;
        this.timeRental = totalTime;
    }

    /**
     * Get bike of transaction
     *
     * @return Bike
     */
    public Bike getBikeTransaction() {
        try {
            String sqlString = "select * from bike join bike_type on bike.type_id = bike_type.type_id where bike_id = '" + this.getBikeId() + "'";
            System.out.println("qr: " + sqlString);
            ResultSet result = MainEntry.database.query(sqlString);
            while (result.next()) {
                return new Bike(result.getString("license_plate"), result.getInt("bike_id"), result.getInt("station_id"),
                        result.getInt("lock_id"), result.getInt("value"), result.getString("model"), result.getString("brand"),
                        result.getString("image"), result.getInt("status"), result.getString("type"), result.getInt("battery"));
            }
        } catch (SQLException e) {
            System.out.println("Error get list bike of station: " + e);
        }
        return null;
    }

    /**
     * Insert new transaction to database.
     *
     * @param bikeId
     * @param deposit
     * @param paymentId
     * @param status
     * @return Boolean
     * @throws SQLException
     */
    public boolean insertTransaction(int bikeId, double deposit, String paymentId, int status) throws SQLException {
        String sqlInsertTransaction = "INSERT INTO transaction VALUES (NULL, '" + bikeId + "', '"
                + MainEntry.user.getUserId() + "', current_timestamp(), NULL, '" + status + "', "
                + deposit + ", '" + paymentId + "', '0', '')";
        return MainEntry.database.insert(sqlInsertTransaction) > 0;
    }

    public static Transaction getTransaction() throws SQLException {
        String sqlGetTransaction = "SELECT * FROM transaction WHERE user_id = '" + MainEntry.user.getUserId() + "' and status = '0'";
        ResultSet result = MainEntry.database.query(sqlGetTransaction);
        int numTransaction = MainEntry.database.getRow(sqlGetTransaction);
        if (numTransaction == 1) {
            while (result.next()) {
                System.out.println("pase to double = " + result.getDouble("deposit"));
                return new Transaction(result.getString("start_time"), result.getString("end_time"),
                        result.getInt("bike_id"), result.getInt("user_id"), result.getInt("transaction_id"),
                        result.getInt("status"), result.getDouble("deposit"), result.getString("payment_Id"));
            }
        }
        return null;
    }

    /**
     * Update transaction info in database.
     *
     * @param status
     * @param amount
     * @return Boolean
     * @throws SQLException
     */
    public boolean updateTransaction(int status, double amount) throws SQLException {
        System.out.println("value convert to string : " + amount);
        String sqlInsertTransaction = "UPDATE transaction SET status = '" + status + "', end_time = '" + this.getEndTime()
                + "', bike_rental = " + amount + ", time_rental = '" + this.getTimeRental()
                + "' WHERE transaction_id = '" + this.getTransactionId() + "'";
        int numberResult = MainEntry.database.update(sqlInsertTransaction);
        return numberResult > 0;
    }
}
