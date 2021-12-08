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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Thanh Long
 */
public class DockingPoint {

    private int lockId, status, stationId;
    private String barCode;
    public ArrayList<DockingPoint> arrayDockingPoint;

    /**
     * Constructor 1 of Docking Point. get list docking point.
     *
     * @throws SQLException
     */
    public DockingPoint() throws SQLException {
        System.out.println("get dock ing point");
        String sqlString = "select * from docking_lock";
        System.out.println("get dock ing point 1");
        ResultSet result = MainEntry.database.query(sqlString);
        System.out.println("get dock ing point 2");
        this.arrayDockingPoint = new ArrayList<>();
        while (result.next()) {
            DockingPoint dp = new DockingPoint(result.getInt("lock_id"),
                    result.getInt("status"), result.getInt("station_id"), result.getString("bar_code"));
            this.arrayDockingPoint.add(dp);
        }
    }

    /**
     * Constructor 2 of Docking Point. initial information for Docking Point.
     *
     * @param lockId
     * @param status
     * @param stationId
     * @param barCode
     */
    public DockingPoint(int lockId, int status, int stationId, String barCode) {
        this.lockId = lockId;
        this.status = status;
        this.stationId = stationId;
        this.barCode = barCode;
    }

    /**
     * Get List Docking point.
     *
     * @return ArrayList.
     */
    public ArrayList<DockingPoint> getArrayDockingPoint() {
        System.out.println("return docking point");
        return this.arrayDockingPoint;
    }

    /**
     * Get lock id of docking point.
     *
     * @return integer.
     */
    public int getLockId() {
        return lockId;
    }

    /**
     * get status of docking point. 0: is has no bike. 1: has bike.
     *
     * @return integer.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Get station id of docking point.
     *
     * @return integer.
     */
    public int getStationId() {
        return stationId;
    }

    /**
     * Get bar code of docking point
     *
     * @return String.
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * Update status of docking point. call after rent bike or return bike
     * success.
     *
     * @param status
     * @return
     * @throws SQLException
     */
    public boolean updateDockStatus(int status) throws SQLException {
        String sqlSetStatusDockingPoint = "UPDATE docking_lock SET status = '" + status + "' WHERE lock_id = '" + this.getLockId() + "'";
        int numberResult = MainEntry.database.update(sqlSetStatusDockingPoint);
        if (numberResult > 0) {
            this.status = status;
        }
        return numberResult > 0;
    }
}
