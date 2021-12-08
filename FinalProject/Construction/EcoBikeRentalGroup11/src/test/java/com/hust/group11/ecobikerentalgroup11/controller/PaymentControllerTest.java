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

import com.hust.group11.ecobikerentalgroup11.DataBase;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import com.hust.group11.ecobikerentalgroup11.entity.DockingPoint;
import com.hust.group11.ecobikerentalgroup11.entity.User;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Nguyen Thanh Long
 */
public class PaymentControllerTest {

    private PaymentController instance;

    public PaymentControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {
        MainEntry.database = new DataBase();
        MainEntry.user = new User("Nguyen Thanh Long", 3,  "longnt", 0);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        instance = new PaymentController();
    }

    @AfterEach
    public void tearDown() {
    }


    @ParameterizedTest
    @CsvSource({
        "true, Rented",
        "true, Returned",
        })
    public void testUpdateStatusTrue(boolean insertTransaction, String status ) throws SQLException {
        Bike bike = new Bike("TEST_01", 3, 5, 1000, 0, "TestModel", "TestBrand", "bike_default.jpg", 0, "Single Bike", 0);
        DockingPoint dockingPoint = new DockingPoint(5, 1, 3, "ys_dock_02");
        assertTrue(instance.updateStatus(bike, dockingPoint, status, insertTransaction));
    }
    
     @ParameterizedTest
    @CsvSource({
        "false, Rented",
        "false, Returned",
        })
    public void testUpdateStatusFail(boolean insertTransaction, String status ) throws SQLException {
        Bike bike = new Bike("TEST_01", 3, 5, 1000, 0, "TestModel", "TestBrand", "bike_default.jpg", 0, "Single Bike", 0);
        DockingPoint dockingPoint = new DockingPoint(5, 1, 3, "ys_dock_02");
        assertFalse(instance.updateStatus(bike, dockingPoint, status, insertTransaction));
    }

    /**
     * Test of getError method, of class PaymentController.
     *
     * @param errorCode
     * @param errorString
     */
    @ParameterizedTest
    @CsvSource({
        "0, Transaction Successfully",
        "1, Invalid Card",
        "2, Card Not Enough Balance",
        "3, Internal Server Error",
        "4, Fraudulent Transaction",
        "5, Lack Of Transaction Information",
        "6, Lack Of Version Information",
        "7, Invalid Amount",
        "8, No Internet. Try again",})
    public void testGetError(int errorCode, String errorString) {
        String result = instance.getError(errorCode);
        assertEquals(errorString, result);
    }

//    /**
//     * Test of hashMD5 method, of class PaymentController.
//     *
//     * @param plainText
//     * @param hashCode
//     * @throws java.lang.Exception
//     */
//    @ParameterizedTest
//    @CsvSource({
//        "Nguyen Van Bac, 579a52593fb5dd193ab962dd3dcc94a3",
//        "012ABC@, 4fad60bfdc26b2d0773f2eeeb75d7d35",})
//    public void testHashMD5(String plainText, String hashCode) throws Exception {
//        System.out.println("hashMD5");
//        String result = instance.hashMD5(plainText);
//        assertEquals(hashCode, result);
//    }
//    
}
