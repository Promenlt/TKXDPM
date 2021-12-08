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
import com.hust.group11.ecobikerentalgroup11.boundary.ViewListBikeScreen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thanh Long
 */
public class Bike {

    private final int bikeId;
    private final int stationId;
    private final int lockId;
    private int status;
    private final int battery;
    private final String model;
    private final String type;
    private final String brand;
    private final String image;
    private final String licensePlate;
    private final double value;

    /**
     * Constructor init information for Bike.
     *
     * @param licensePlate
     * @param bikeId
     * @param stationId
     * @param lockId
     * @param value
     * @param model
     * @param brand
     * @param image
     * @param status
     * @param type
     * @param battery
     */
    public Bike(String licensePlate, int bikeId, int stationId, int lockId, double value, String model,
            String brand, String image, int status, String type, int battery) {
        this.bikeId = bikeId;
        this.stationId = stationId;
        this.lockId = lockId;
        this.model = model;
        this.type = type;
        this.brand = brand;
        this.image = image;
        this.status = status;
        this.value = value;
        this.battery = battery;
        this.licensePlate = licensePlate;
    }

    /**
     * Get license plate of bike.
     *
     * @return String.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Get battery of bike.
     *
     * @return integer.
     */
    public int getBattery() {
        return battery;
    }

    /**
     * Get value of bike. use to calculate deposit when rent bike.
     *
     * @return double.
     */
    public double getValue() {
        return value;
    }

    /**
     * Get Image of bike. name file image.
     *
     * @return String.
     */
    public String getImage() {
        return image;
    }

    /**
     * Get status of bike. 0: available. 1: is rented.
     *
     * @return integer.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Get id of bike.
     *
     * @return integer.
     */
    public int getBikeId() {
        return bikeId;
    }

    /**
     * Get station id of bike.
     *
     * @return integer.
     */
    public int getStationId() {
        return stationId;
    }

    /**
     * Get lock id of bike.
     *
     * @return integer
     */
    public int getLockId() {
        return lockId;
    }

    /**
     * get model of bike
     *
     * @return String.
     */
    public String getModel() {
        return model;
    }

    /**
     * Get type of bike.
     *
     * @return String.
     */
    public String getType() {
        return type;
    }

    /**
     * Get brand of bike.
     *
     * @return String.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get Docking point of bike.
     *
     * @return Docking point.
     */
    public DockingPoint getDockingPoint() {
        String sql = "select * from docking_lock where lock_id='" + this.getLockId() + "'";
        ResultSet result;
        try {
            result = MainEntry.database.query(sql);
            DockingPoint dockingPoint = new DockingPoint(0, 0, 0, "");
            while (result.next()) {
                dockingPoint = new DockingPoint(result.getInt("lock_id"),
                        result.getInt("status"), result.getInt("station_id"), result.getString("bar_code"));
            }
            return dockingPoint;
        } catch (SQLException ex) {
            Logger.getLogger(ViewListBikeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Update status bike, docking point , station when bike return in new
     * place.
     *
     * @param status
     * @param dockingPoint
     * @return true if update success else return false.
     * @throws SQLException
     */
    public boolean updateBikeStatus(int status, DockingPoint dockingPoint) throws SQLException {
        String sqlSetStatusBike = "UPDATE bike SET status = '" + status + "', lock_id = '" + dockingPoint.getLockId()
                + "', station_id = '" + dockingPoint.getStationId() + "' WHERE bike_id = '" + this.getBikeId() + "'";
        int numberResult = MainEntry.database.update(sqlSetStatusBike);
        if (numberResult > 0) {
            this.status = status;
        }
        return numberResult > 0;
    }

}
