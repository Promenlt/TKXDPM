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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hust.group11.ecobikerentalgroup11.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Nguyen Thanh Long
 * @createAt 2021.01.01
 */
public class Interbank extends PaymentMethod {

    private final JsonObject payment;
    private final Gson gson;

    /**
     * Constructor for interbank.
     *
     * @param nameMethod
     * @param cardCode
     * @param nameOwner
     * @param cvvCode
     * @param dateExpired
     */
    public Interbank(String nameMethod, String cardCode, String nameOwner, String cvvCode, String dateExpired) {
        super(nameMethod, cardCode, nameOwner, cvvCode, dateExpired);
        this.gson = new GsonBuilder().disableHtmlEscaping().create();
        this.payment = new JsonObject();
    }

    /**
     * Call API patch to process payment.
     *
     * @param payment
     * @return JsonObject
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private JsonObject callAPI(JsonObject payment) throws UnsupportedEncodingException, IOException {
        String result;
        HttpPatch patch = new HttpPatch(Constants.API_PAYMENT + "api/card/processTransaction");
        patch.addHeader("content-type", "application/json");
        patch.addHeader("Accept", "application/json");

        String body = gson.toJson(payment);
        patch.setEntity(new StringEntity(body));
        JsonObject paymentResponse = new JsonObject();
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(patch)) {
            result = EntityUtils.toString(response.getEntity());
            paymentResponse = (JsonObject) new JsonParser().parse(result);
            System.out.println("response: " + paymentResponse.toString());
//            JOptionPane.showMessageDialog(screen, getError(paymentResponse.get("errorCode").getAsInt()));
        } catch (UnknownHostException e) {
            System.out.println("Error call api: " + e);
            paymentResponse.addProperty("errorCode", 8);
//            JOptionPane.showMessageDialog(screen, getError(8));
        }
        return paymentResponse;
    }

    /**
     * Override pay method of PaymentMethod parent class.
     *
     * @param amount
     * @return JsonObject.
     */
    @Override
    public JsonObject pay(double amount) {
        String transactionContent = "Thanh Toan Deposit Group 11";
        this.updatePayment(amount, Constants.PAY_COMMAND, getDateFormatNow(), transactionContent);
        try {
            return callAPI(this.payment);
        } catch (IOException ex) {
            Logger.getLogger(Interbank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Override refund method of PaymentMethod parent class.
     *
     * @param amount
     * @return JsonObject.
     */
    @Override
    public JsonObject refund(double amount) {
        String transactionContent = "Thanh Toan Return Group 11";
        String command = amount > 0 ? Constants.PAY_COMMAND : Constants.REFUND_COMMAND;
        this.updatePayment(Math.abs(amount), command, getDateFormatNow(), transactionContent);
        try {
            return callAPI(this.payment);
        } catch (IOException ex) {
            Logger.getLogger(Interbank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Update payment. add properties for payment JsonObjet.
     *
     * @param amount
     * @param command
     * @param dateTimeNow
     * @param content
     */
    public void updatePayment(double amount, String command, String dateTimeNow, String content) {
        JsonObject hash = new JsonObject();
        hash.addProperty("secretKey", Constants.SECRET_KEY);
        JsonObject pmTransaction = new JsonObject();
        pmTransaction.addProperty("amount", amount > 0 ? amount : amount * -1.0);
        pmTransaction.addProperty("command", command);
        pmTransaction.addProperty("cardCode", this.getCardCode());
        pmTransaction.addProperty("createdAt", dateTimeNow);
        pmTransaction.addProperty("cvvCode", this.getCvvCode());
        pmTransaction.addProperty("dateExpired", this.getDateExpired());
        pmTransaction.addProperty("owner", this.getNameOwner());
        pmTransaction.addProperty("transactionContent", content);

        hash.add("transaction", pmTransaction);
        String plainText = gson.toJson(hash);
        System.out.println("plain text: " + plainText);
        String hashCode = "";
        try {
            hashCode = this.hashMD5(plainText);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        this.payment.addProperty("version", Constants.APP_VERSION);
        this.payment.add("transaction", pmTransaction);
        this.payment.addProperty("appCode", Constants.APP_CODE);
        this.payment.addProperty("hashCode", hashCode);
    }

    /**
     * Hash MD5. hash plain text to hash text
     *
     * @param plainText
     * @return hash text
     * @throws NoSuchAlgorithmException
     */
    private String hashMD5(String plainText) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(plainText.getBytes());
        BigInteger no = new BigInteger(1, digest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    /**
     * Get Date follow format
     *
     * @return String formatted date
     */
    public String getDateFormatNow() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }
}
