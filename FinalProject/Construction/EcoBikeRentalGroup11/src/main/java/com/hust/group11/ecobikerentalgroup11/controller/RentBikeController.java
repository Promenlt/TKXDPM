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
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.boundary.PaymentScreen;
import com.hust.group11.ecobikerentalgroup11.boundary.RentBikeScreen;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Thanh Long
 */
public class RentBikeController {

    public void processRequest(JFrame backScreen, Bike bike, DockingPoint dockingPoint) {
        try {
            MainEntry.move(backScreen, new PaymentScreen(bike, dockingPoint, backScreen));
        } catch (SQLException ex) {
            Logger.getLogger(RentBikeScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RentBikeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param model
     * @param bike
     */
    public void showInfoBike(DefaultTableModel model, Bike bike) {
        model.addRow(new Object[]{"Bike type", bike.getType()});
        model.addRow(new Object[]{"Brand", bike.getBrand()});
        model.addRow(new Object[]{"Model", bike.getModel()});
        model.addRow(new Object[]{"Value", bike.getValue()});
        model.addRow(new Object[]{"Status", bike.getStatus() == Constants.BIKE_AVAIL ? "Available" : "Rented"});
        model.addRow(new Object[]{"License Plate", bike.getLicensePlate()});
        String type = bike.getType();
        if (type.equals("Electric Bike")) {
            model.addRow(new Object[]{"Battery", bike.getBattery() + " %"});
        }
    }

    /**
     * Get bike by id (stationId, bikeId..)
     *
     * @param id
     * @param field
     * @return bike
     */
    public Bike getBikeById(int id, String field) {
        try {
            String sqlString = "select * from bike join bike_type on bike.type_id = bike_type.type_id where " + field + "= '" + id + "'";
            ResultSet result = MainEntry.database.query(sqlString);
            while (result.next()) {
                return new Bike(result.getString("license_plate"), result.getInt("bike_id"),
                        result.getInt("station_id"), result.getInt("lock_id"), result.getInt("value"),
                        result.getString("model"), result.getString("brand"), result.getString("image"),
                        result.getInt("status"), result.getString("type"), result.getInt("battery"));
            }
        } catch (SQLException e) {
            System.out.println("ERR get info bike" + e);
        }
        return null;
    }

    /**
     * Read and validate QR Code for user want to rent bike.
     *
     * @param barCode
     * @return
     * @throws SQLException
     * @throws FormatException
     * @throws IOException
     */
    public DockingPoint handleCheckBarCode(String barCode) throws SQLException, FormatException, IOException {
        ArrayList<DockingPoint> arrayDockingPoints = new DockingPoint().getArrayDockingPoint();
        for (DockingPoint dockingPoint : arrayDockingPoints) {
            if (barCode == null ? dockingPoint.getBarCode() == null : barCode.equals(dockingPoint.getBarCode())) {    
                return dockingPoint;
            }
        }
        return null;
    }

    /**
     * show deposit in payment screen
     *
     * @param bike
     * @param paymentInfoTable
     * @return deposit type integer
     * @throws Exception
     */
    /**
     * Calculate deposit by value of bike
     *
     * @param valueOfBike
     * @return deposit type integer
     * @throws Exception
     */
    public double calculateDeposit(double valueOfBike) throws Exception {
        if (valueOfBike < 0) {
            throw new IOException("Value of bike can not be negative");
        }
        return Constants.DEPOSIT_PERCENT * valueOfBike / 100;
    }
}
