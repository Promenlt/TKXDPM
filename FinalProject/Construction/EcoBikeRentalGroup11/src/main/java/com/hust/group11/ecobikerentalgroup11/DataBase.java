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
package com.hust.group11.ecobikerentalgroup11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thanh Long
 */

public class DataBase {
    private static DataBase db;
    private final int PORT = 3306;
    private final String DB_NAME = "tkxdpm_nhom11";
    private final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB_NAME;
    private final String USER = "root";
    private final String PASS = "";
    public Connection connection;

    /**
     * Constructor initial connection
     * @throws SQLException 
     */
    public DataBase() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            this.connection = null;
            JOptionPane.showMessageDialog(null, "DATABASE CONNECT ERROR: " + e.getMessage());
        }
    }
    /**
     * Create instance of database only one time
     * @return Database.
     * @throws SQLException
     * @throws Exception 
     */
    public static DataBase getDataBase() throws SQLException, Exception{
        if (DataBase.db == null) {
            System.out.println("init db");
            DataBase.db = new DataBase();
        } else {
            System.out.println("can not init more db");
            throw new Exception();
        }
        return DataBase.db;
    }

    /**
     * Execute query to database
     * @param sqlString
     * @return
     * @throws SQLException 
     */
    public ResultSet query(String sqlString) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlString);
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATABASE QUERY ERROR: " + e.getMessage());
        }
        return null;
    }

    /**
     * Execute update to database
     * @param sqlString
     * @return
     * @throws SQLException 
     */
    public int update(String sqlString) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(sqlString);
            int update = statement.executeUpdate();
            return update;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DATABASE UPDATE ERROR: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Get length of query to database
     * @param sqlString
     * @return integer is number row.
     * @throws SQLException 
     */
    public int getRow(String sqlString) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sqlString);
        int rowcount = 0;
        while (result.next()) {
            rowcount++;
        }
        return rowcount;
    }

    /**
     * Execute insert to database
     * @param sqlString
     * @return
     * @throws SQLException 
     */
    public int insert(String sqlString) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlString, Statement.RETURN_GENERATED_KEYS);
        ResultSet result = statement.getGeneratedKeys();
        result.next();
        int numberResult = result.getInt(1);
        return numberResult;
    }
}
