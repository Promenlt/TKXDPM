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
package com.hust.group11.ecobikerentalgroup11.entity;

import com.google.gson.JsonObject;

/**
 *
 * @author Nguyen Thanh Long
 */
public class PaymentMethod implements PaymentInterface {

    private final String nameMethod;
    private final String cardCode;
    private final String nameOwner;
    private final String cvvCode;
    private final String dateExpired;

    /**
     * Constructor for payment method. 
     * initial information for payment method.
     *
     * @param nameMethod
     * @param cardCode
     * @param nameOwner
     * @param cvvCode
     * @param dateExpired
     */
    public PaymentMethod(String nameMethod, String cardCode, String nameOwner, String cvvCode, String dateExpired) {
        this.nameMethod = nameMethod;
        this.cardCode = cardCode;
        this.nameOwner = nameOwner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    /**
     * Get name of payment method.
     *
     * @return String.
     */
    public String getNameMethod() {
        return nameMethod;
    }

    /**
     * Get card code of payment method.
     *
     * @return string.
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * get name owner of payment method.
     *
     * @return string.
     */
    public String getNameOwner() {
        return nameOwner;
    }

    /**
     * get cvv code of payment method.
     *
     * @return String.
     */
    public String getCvvCode() {
        return cvvCode;
    }

    /**
     * get date expired of payment method.
     *
     * @return String.
     */
    public String getDateExpired() {
        return dateExpired;
    }

    /**
     * Override pay method for payment interface.
     *
     * @param amount
     * @return JsonObject.
     */
    @Override
    public JsonObject pay(double amount) {
        return null;
    }

    /**
     * Override refund method for payment interface.
     *
     * @param amount
     * @return JsonObjet.
     */
    @Override
    public JsonObject refund(double amount) {
        return null;
    }
}
