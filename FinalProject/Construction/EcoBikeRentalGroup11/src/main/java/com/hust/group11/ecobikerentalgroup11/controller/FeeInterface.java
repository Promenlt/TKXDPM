/*
 * --------------------ECOBIKE RENTAL 1.0.1--------------------
 *
 * Design and Software Construction 20201
 *
 * Copyright (C) 2021 by Group 11
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

/**
 *
 * @author Nguyen Thanh Long
 */
public interface FeeInterface {
    /**
     * 
     * @param timeRental 
     * @return  
     */
    public double calculateBikeRental(long timeRental);
    
}
