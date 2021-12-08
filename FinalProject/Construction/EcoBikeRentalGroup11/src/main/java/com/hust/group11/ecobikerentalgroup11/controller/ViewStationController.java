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

import com.hust.group11.ecobikerentalgroup11.Constants;
import com.hust.group11.ecobikerentalgroup11.entity.Station;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class ViewStationController {

    /**
     * Show list station
     *
     * @param model
     * @param arrStation
     * @throws java.sql.SQLException
     */
    public void processViewStation(DefaultTableModel model, ArrayList<Station> arrStation) throws SQLException {
        model.fireTableDataChanged();
        for (int i = 0; i < arrStation.size(); i++) {
            Station s = arrStation.get(i);
            model.addRow(new Object[]{i + 1, s.getStationName(), 100, s.getAddress(), s.getNumBikeAvailable()});
        }
    }

    public int getNumberBikeOfStation(int stationId) throws SQLException {
        String sqlGetBikeAvailable = "select * from bike where station_id='" + stationId + "' and status='0'";
        int numbikeAvailable = MainEntry.database.getRow(sqlGetBikeAvailable);
        return numbikeAvailable;
    }

    public int getNumberDockingPointOfStation(int stationId) throws SQLException {
        String sqlGetDockingPoint = "select * from docking_lock where station_id='" + stationId + "'";
        int numDockingPoint = MainEntry.database.getRow(sqlGetDockingPoint);
        return numDockingPoint;
    }

    /**
     * View list bike of station
     *
     * @param model
     * @param arrBike
     */
    public void processViewBikeStation(DefaultTableModel model, ArrayList<Bike> arrBike) {
        for (int i = 0; i < arrBike.size(); i++) {
            Bike b = arrBike.get(i);
            int userStatus = MainEntry.user.getStatus();
            String status = b.getStatus() == Constants.BIKE_AVAIL && userStatus == Constants.USER_AVAIL
                    || userStatus == Constants.USER_RENTING && b.getStatus() == Constants.BIKE_RENTED ? "Available" : "Busy";
            String type = b.getType();
            if (b.getType().equals("Electric Bike")) {
                model.addRow(new Object[]{i + 1, b.getLicensePlate(), b.getModel(), b.getType(), b.getBattery() + " %", status, b.getValue() + Constants.MONEY_UNIT});
            } else {
                model.addRow(new Object[]{i + 1, b.getLicensePlate(), b.getModel(), b.getType(), "", status, b.getValue() + Constants.MONEY_UNIT});
            }
        }
        model.fireTableDataChanged();
    }

}
