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
package com.hust.group11.ecobikerentalgroup11.entity;

import com.google.gson.JsonObject;

/**
 *
 * @author Nguyen Thanh Long
 */
public interface PaymentInterface {

    /**
     * Abtract method pay
     *
     * @param amount
     * @return JsonObject
     */
    public JsonObject pay(double amount);

    /**
     * Abtract method refund
     *
     * @param amount
     * @return JsonObject
     */
    public JsonObject refund(double amount);
}
