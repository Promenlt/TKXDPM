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
import com.hust.group11.ecobikerentalgroup11.DataBase;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import com.hust.group11.ecobikerentalgroup11.entity.DockingPoint;
import com.hust.group11.ecobikerentalgroup11.entity.Transaction;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Nguyen Thanh Long
 */
public class ReturnBikeControllerTest {

    ReturnBikeController returnBikeController;

    public ReturnBikeControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {
        MainEntry.database = new DataBase();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        returnBikeController = new ReturnBikeController();

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calculateAmountReturn method, of class ReturnBikeController.
     * @param deposit
     * @param bikeRental
     * @param bikeId
     * @param expResult
     */
    @ParameterizedTest
    @CsvSource({
        "200, 500, 1, 300", //single bike
        "200, 500, 4, 300", //double bike
        "200, 500, 2, 550", //electric bike
    })
    public void testCalculateAmountReturn(double deposit, double bikeRental, int bikeId, double expResult) {
        System.out.println("calculateAmountReturn");
        Transaction transaction = new Transaction(bikeId, 4, 0, deposit, bikeRental);
        double result = returnBikeController.calculateAmountReturn(transaction);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of handleCheckBarCode method, of class ReturnBikeController.
     *
     * @param barCode
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     * @throws com.google.zxing.FormatException
     */
    @ParameterizedTest
    @CsvSource({
        "tn_dock_01",
        "tn_dock_02",
        "bk_dock_01",
        "ys_dock_01"
    })
    public void testCheckBarCodeTrue(String barCode) throws IOException, SQLException, FormatException {
        System.out.println("showInfoDeposit");
        assertTrue(returnBikeController.handleCheckBarCode(barCode) != null);
    }

    @ParameterizedTest
    @CsvSource({
        "tn_dock_03",
        "tn_dock_04",
        "bk_dock_05",
        "ys_dock_06"
    })
    public void testCheckBarCodeFail(String barCode) throws IOException, SQLException, FormatException {
        System.out.println("showInfoDeposit");
        assertFalse(returnBikeController.handleCheckBarCode(barCode) != null);
    }

    /**
     * Test of processReturnBike method, of class ReturnBikeController.
     */
    @Test
    public void testProcessReturnBike() {
        System.out.println("processReturnBike");
        Transaction transaction = null;
        Bike bike = null;
        DockingPoint dockingPoint = null;
        JFrame screen = null;
        returnBikeController.processReturnBike(transaction, bike, dockingPoint, screen);
    }

}
