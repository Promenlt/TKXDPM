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

import com.hust.group11.ecobikerentalgroup11.Constants;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nguyen Thanh Long
 * @createdAt 04/12/2020
 */
public class RentingController implements FeeInterface{

    /**
     *
     * @param startTimeString
     * @throws ParseException
     * @return time start
     */
    public Date getDateFormat(String startTimeString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = formatter.parse(startTimeString);
        System.out.println("renting time -- reting time format: " + startTimeString + ">>>>>" + startTime.toString());
        return startTime;
    }

    /**
     * Get date format now
     *
     * @return
     */
    public String getDateNowFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }


    /**
     * Get formatted renting time.
     *
     * @param rentingTime
     * @return String time format hours : minutes : seconds
     */
    public String getTimeRentingFormat(long rentingTime) {
        long hour = rentingTime / (1000 * 60 * 60);
        long minutes = (rentingTime / (1000 * 60)) % 60;
        long seconds = (rentingTime / (1000)) % 60;
        String timeRentingFormat = Long.toString(hour) + ":" + Long.toString(minutes) + ":" + Long.toString(seconds);
        return timeRentingFormat;

    }

    /**
     * Get formatted money
     *
     * @param amountPayment
     * @return price comma format
     */
    public String getAmountPaymentFormat(double amountPayment) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(amountPayment);
        String sub = moneyString.substring(0, moneyString.length() - 3).replace(',', '.');
        String amountPaymentFormat = sub + Constants.MONEY_UNIT;
        return amountPaymentFormat;
    }

    @Override
    public double calculateBikeRental(long timeRental) {
         double amountPayment;
        if (timeRental <= 10) {
            amountPayment = 0;
        } else {
            if (timeRental <= 30) {
                amountPayment = Constants.PRICE_30MINUTE_FIRST;
            } else {
                timeRental -= 30;
                double round = Math.ceil((double) timeRental / 15.0);
                amountPayment = Constants.PRICE_30MINUTE_FIRST + round * Constants.PRICE_15MINUTE_AFTER;
            }
        }
        return amountPayment;
    }
}
