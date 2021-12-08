/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hust.group11.ecobikerentalgroup11.controller;
import com.google.zxing.FormatException;
import com.hust.group11.ecobikerentalgroup11.DataBase;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import java.io.IOException;
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
public class RentBikeControllerTest {

    private RentBikeController rentBikeController;

    public RentBikeControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {
        MainEntry.database = new DataBase();
        System.out.println("------BEFORE ALL----");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("------AFTER ALL----");
    }

    @BeforeEach
    public void setUp() throws SQLException {
        System.out.println("------BEFORE EACH----");
        rentBikeController = new RentBikeController();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("------AFTER EACH----");
    }

    /**
     * Test of calculateDeposit method, of class RentBikeController.
     *
     * @throws java.lang.Exception
     */
    /**
     * Test of payDeposit method, of class RentBikeController.
     * @param valueBike
     * @param deposit
     * @throws java.lang.Exception
     */
    @ParameterizedTest
    @CsvSource({
        "-100, -40",
    })
    public void testCalculateDeposit(double valueBike, double deposit) throws Exception {
        System.out.println(" Test calculateDeposit method");
        assertEquals(40, rentBikeController.calculateDeposit(100));
        assertThrows(IOException.class, () -> rentBikeController.calculateDeposit(valueBike));

    }   
    @ParameterizedTest
    @CsvSource({
        "tn_dock_01",
        "tn_dock_02",
        "bk_dock_01",
        "ys_dock_01"
    })
    public void testCheckBarCodeTrue(String barCode) throws IOException, SQLException, FormatException {
        System.out.println("showInfoDeposit");
        assertTrue(rentBikeController.handleCheckBarCode(barCode) != null);
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
        assertFalse(rentBikeController.handleCheckBarCode(barCode) != null);
    }

}
