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
import com.hust.group11.ecobikerentalgroup11.controller.ViewStationController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Hai Long
 */
public class Station {

    private int stationId, stationArea, numBikeAvailable, numberDockingPoint;
    private String address, locationCode, stationName;
    public ArrayList<Station> arrayStation;
    private ViewStationController viewStationController;

    /**
     * Constructor 1 of station. get list station in database.
     */
    public Station() {
        try {
            this.arrayStation = new ArrayList<>();
            String sqlString = "select * from station";
            ResultSet result = MainEntry.database.query(sqlString);
            while (result.next()) {
                Station station = new Station(result.getInt("station_id"), result.getInt("area"),
                        result.getString("address"), result.getString("location_code"), result.getString("station_name"));
                this.arrayStation.add(station);
            }
        } catch (SQLException e) {
            System.out.println("Error get list station: " + e);
        }
    }

    /**
     * Constructor 2. Init info for station.
     *
     * @param stationId
     * @param stationArea
     * @param address
     * @param locationCode
     * @param stationName
     * @throws SQLException
     */
    public Station(int stationId, int stationArea, String address, String locationCode, String stationName) throws SQLException {
        this.viewStationController = new ViewStationController();
        this.stationId = stationId;
        this.stationArea = stationArea;
        this.numBikeAvailable = viewStationController.getNumberBikeOfStation(stationId);
        this.numberDockingPoint = viewStationController.getNumberDockingPointOfStation(stationId);
        this.address = address;
        this.locationCode = locationCode;
        this.stationName = stationName;
    }

    /**
     * Get list station.
     *
     * @return ArrayList
     */
    public ArrayList<Station> getArrayStation() {
        return this.arrayStation;
    }

    /**
     * Get station id.
     *
     * @return integer
     */
    public int getStationId() {
        return stationId;
    }

    /**
     * Get station area.
     *
     * @return int.
     */
    public int getStationArea() {
        return stationArea;
    }

    /**
     * Get Number bike available in station.
     *
     * @return integer
     */
    public int getNumBikeAvailable() {
        return numBikeAvailable;
    }

    /**
     * Get number docking point in station.
     *
     * @return integer.
     */
    public int getNumberDockingPoint() {
        return numberDockingPoint;
    }

    /**
     * Get address of station.
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get name of station.
     *
     * @return String
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Get List bike of station.
     *
     * @return ArrayList.
     */
    public ArrayList<Bike> getArrayBike() {
        ArrayList<Bike> arrBike = new ArrayList<>();
        try {
            String sqlString = "select * from bike join bike_type on bike.type_id = bike_type.type_id where station_id = '" + this.getStationId() + "'";
            System.out.println("qr: " + sqlString);
            ResultSet result = MainEntry.database.query(sqlString);
            while (result.next()) {
                Bike bike = new Bike(result.getString("license_plate"), result.getInt("bike_id"), result.getInt("station_id"),
                        result.getInt("lock_id"), result.getInt("value"), result.getString("model"), result.getString("brand"),
                        result.getString("image"), result.getInt("status"), result.getString("type"), result.getInt("battery"));
                arrBike.add(bike);
            }
        } catch (SQLException e) {
            System.out.println("Error get list bike of station: " + e);
        }
        return arrBike;
    }

}
