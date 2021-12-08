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
import java.sql.SQLException;

/**
 *
 * @author Nguyen Thanh Long
 */
public class User {

    private final String name;
    private final String username;
    private String password;
    private final int userId;
    private int status;

    /**
     * Constructor of user.
     *
     * @param name
     * @param userId
     * @param username
     * @param status
     */
    public User(String name, int userId, String username, int status) {
        this.userId = userId;
        this.username = username;
        this.status = status;
        this.name = name;
    }

    /**
     * Get name of user.
     *
     * @return String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get status of user. 0 is available 1 is busy (renting)
     *
     * @return integer
     */
    public int getStatus() {
        return status;
    }

    /**
     * Get id of user.
     *
     * @return integer
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Update status of user. Call after user rent or return bike success.
     *
     * @param value
     * @return
     * @throws SQLException
     */
    public boolean updateUserStatus(int value) throws SQLException {
        String sqlSetStatusUser = "UPDATE user SET status = '" + value + "' WHERE user_id = '" + MainEntry.user.getUserId() + "'";
        int numberResult = MainEntry.database.update(sqlSetStatusUser);
        this.status = value;
        return numberResult > 0;
    }
}
