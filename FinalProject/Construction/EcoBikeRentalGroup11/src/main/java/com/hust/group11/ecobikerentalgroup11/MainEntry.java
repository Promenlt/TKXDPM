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

import com.hust.group11.ecobikerentalgroup11.entity.User;
import com.hust.group11.ecobikerentalgroup11.boundary.LoginScreen;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nguyen Thanh Long
 */
public class MainEntry {

    public static DataBase database;
    public static User user;

    /**
     * Move from current screen to next screen
     *
     * @param currScreen current screen
     * @param nextScreen next screen
     */
    public static void move(JFrame currScreen, JFrame nextScreen) {
        nextScreen.setLocation(currScreen.getLocation());
        nextScreen.setVisible(true);
        currScreen.setVisible(false);
    }

    /**
     * Start method of application. application will run this method first all.
     * this method will initial login screen first.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                database = DataBase.getDataBase();
                if (database.connection != null) {
                    LoginScreen loginScreen = new LoginScreen();
                    loginScreen.setVisible(true);
                } 
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(MainEntry.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MainEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
