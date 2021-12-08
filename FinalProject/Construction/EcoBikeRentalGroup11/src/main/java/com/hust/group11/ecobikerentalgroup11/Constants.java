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

/**
 *
 * @author Nguyen Thanh Long
 */
public class Constants {

    //Price constants
    public static final double DEPOSIT_PERCENT = 40.0;
    public static final int PRICE_30MINUTE_FIRST = 10000;
    public static final int PRICE_15MINUTE_AFTER = 3000;
    public static final String MONEY_UNIT = " (VND)";
    public static final double ELECTRIC_PRICE = 1.5;

    //Status of entities
    public static final int DOCK_EMPTY = 0;
    public static final int DOCK_HAS_BIKE = 1;

    public static final int USER_AVAIL = 0;
    public static final int USER_RENTING = 1;

    public static final int BIKE_AVAIL = 0;
    public static final int BIKE_RENTED = 1;

    public static final int TRANSACTION_RENTING = 0;
    public static final int TRANSACTION_SAVED = 1;

    //Api constants
    public static final String SECRET_KEY = "BgNETdvdQqY=";
    public static final String APP_CODE = "A68Jqvs7stU=";
    public static final String APP_VERSION = "1.0.1";
    public static final String PAY_COMMAND = "pay";
    public static final String REFUND_COMMAND = "refund";
    public static final String API_PAYMENT = "https://ecopark-system-api.herokuapp.com/";
    public static final String[] ERR_CODE
            = {"Transaction Successfully",
                "Invalid Card",
                "Card Not Enough Balance",
                "Internal Server Error",
                "Fraudulent Transaction",
                "Lack Of Transaction Information",
                "Lack Of Version Information",
                "Invalid Amount",
                "No Internet. Try again"};

//type bike constants
    public static final String SINGLE_BIKE_TYPE = "Single Bike";
    public static final String DOUBLE_BIKE_TYPE = "Double Bike";
    public static final String ELECTRIC_BIKE_TYPE = "Electric Bike";
}
