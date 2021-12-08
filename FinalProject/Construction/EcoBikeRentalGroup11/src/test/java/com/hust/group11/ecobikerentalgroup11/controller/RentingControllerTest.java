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
public class RentingControllerTest {

    private RentingController rentingController;

    public RentingControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        rentingController = new RentingController();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getDateFormat method, of class RentingController.
     *
     * @param startTimeString
     * @param expResult
     * @throws java.lang.Exception
     */
    // @ParameterizedTest
    // @CsvSource({
    //     "2020-12-23 15:22:51,Wed Dec 23 15:22:51 KRAT 2020",
    //     "2008-12-23 10:20:23,Tue Dec 23 10:20:23 KRAT 2008",
    //     "2011-12-23 11:12:24,Fri Dec 23 11:12:24 KRAT 2011",
    // })
    // public void testGetDateFormat(String startTimeString, String expResult) throws Exception {
    //     System.out.println("getDateFormat");
    //     Date result = rentingController.getDateFormat(startTimeString);
    //     assertEquals(expResult, result.toString());
    // }

    /**
     * Test of calculatePayment method, of class RentingController.
     */
    @Test
    public void testCalculatePayment() {
        System.out.println("calculatePayment");
        long timeMinute = 0L;
        RentingController instance = new RentingController();
        int expResult = 0;
        double result = rentingController.calculateBikeRental(timeMinute);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeRentingFormat method, of class RentingController.
     *
     * @param rentingTime
     * @param rentingTimeFormat
     */
    @ParameterizedTest
    @CsvSource({
        "102898,0:1:42",
        "105899,0:1:45",
        "106899,0:1:46",})
    public void testGetTimeRentingFormat(long rentingTime, String rentingTimeFormat) {
        System.out.println("getTimeRentingFormat");
        String result = rentingController.getTimeRentingFormat(rentingTime);
        assertEquals(rentingTimeFormat, result);
    }

    /**
     * Test of getAmountPaymentFormat method, of class RentingController.
     */
    @Test
    public void testGetAmountPaymentFormat() {
        System.out.println("getAmountPaymentFormat");
        String result = rentingController.getAmountPaymentFormat(12300.0d);
        assertEquals("$12.300 (VND)", result);
    }

}
