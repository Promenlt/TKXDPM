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
package com.hust.group11.ecobikerentalgroup11.boundary;

import com.hust.group11.ecobikerentalgroup11.Constants;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import com.hust.group11.ecobikerentalgroup11.entity.DockingPoint;
import com.hust.group11.ecobikerentalgroup11.entity.PaymentMethod;
import com.hust.group11.ecobikerentalgroup11.entity.Transaction;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.controller.PaymentController;
import com.hust.group11.ecobikerentalgroup11.controller.RentBikeController;
import com.hust.group11.ecobikerentalgroup11.controller.ReturnBikeController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Thanh Long
 */
public class PaymentScreen extends javax.swing.JFrame {

    private RentBikeController rentBikeController;
    private ReturnBikeController returnBikeController;
    private final JFrame backScreen;
    private final ArrayList<PaymentMethod> arrPaymentMethod;
    private static HttpURLConnection connection;
    private final Bike bike;
    private final DockingPoint dockingPoint;
    private Transaction transaction;
    private final PaymentController paymentController;
    private final double amount;

    /**
     * Constructor Payment Screen for user return bike.It will receive
     * transaction and show it in table.
     *
     * @param transaction
     * @param dockingPoint
     * @param backScreen
     * @throws SQLException
     * @throws Exception
     */
    public PaymentScreen(Transaction transaction, DockingPoint dockingPoint, JFrame backScreen) throws SQLException, Exception {
        initComponents();
        this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/icon_app.png")));
        paymentController = new PaymentController();
        returnBikeController = new ReturnBikeController();
        this.backScreen = backScreen;
        this.bike = transaction.getBikeTransaction();
        this.dockingPoint = dockingPoint;
        this.transaction = transaction;
        amount = returnBikeController.showInfoReturn(bike, transaction, paymentInfoTable);
        confirmCheckBox.setText("Confirm bike rental");
        this.arrPaymentMethod = new ArrayList<>();
        paymentController.setPaymentMethod(arrPaymentMethod, depositComboBox);
    }

    /**
     * Constructor Payment Screen for user rent bike. It will receive bike and
     * docking point which user have chosen.
     *
     * @param bike
     * @param dockingPoint
     * @param backScreen
     * @throws SQLException
     * @throws Exception
     */
    public PaymentScreen(Bike bike, DockingPoint dockingPoint, JFrame backScreen) throws SQLException, Exception {
        initComponents();
        this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/icon_app.png")));
        paymentController = new PaymentController();
        rentBikeController = new RentBikeController();
        this.backScreen = backScreen;
        this.bike = bike;
        this.dockingPoint = dockingPoint;
        this.transaction = new Transaction(bike.getBikeId(), MainEntry.user.getUserId(), Constants.TRANSACTION_RENTING);
        showInfoDeposit();
        this.amount = rentBikeController.calculateDeposit(bike.getValue());
        confirmCheckBox.setText("Pay deposit (40% bike' value)");
        this.arrPaymentMethod = new ArrayList<>();
        paymentController.setPaymentMethod(arrPaymentMethod, depositComboBox);
    }

    public void showInfoDeposit() throws Exception {
        DefaultTableModel model = (DefaultTableModel) paymentInfoTable.getModel();
        model.addRow(new Object[]{"Bike Model", bike.getModel()});
        model.addRow(new Object[]{"Bike value", bike.getValue() + Constants.MONEY_UNIT});
        model.addRow(new Object[]{"Deposit", rentBikeController.calculateDeposit(bike.getValue()) + Constants.MONEY_UNIT});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        depositComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        confirmCheckBox = new javax.swing.JCheckBox();
        confirmButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        paymentInfoTable = new javax.swing.JTable();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Payment");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        depositComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Choose payment method:");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(245, 245, 245));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("   \n  You have to pay deposit\n  equal 40% bike's price\n  After you return bike, you\n  will receive your deposit.");
        jTextArea1.setToolTipText("");
        jTextArea1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setViewportView(jTextArea1);

        confirmButton.setText("Confirm Payment");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPayment(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBack(evt);
            }
        });

        jButton2.setText("Add Payment Method");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPaymentMethod(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("If you don't have any payment method");

        paymentInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(paymentInfoTable);
        if (paymentInfoTable.getColumnModel().getColumnCount() > 0) {
            paymentInfoTable.getColumnModel().getColumn(0).setResizable(false);
            paymentInfoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            paymentInfoTable.getColumnModel().getColumn(1).setResizable(false);
            paymentInfoTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(depositComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(confirmButton))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(confirmCheckBox))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(depositComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(confirmCheckBox)
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(confirmButton)
                            .addComponent(jButton1)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void confirmPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPayment
        for (int i = 0; i < arrPaymentMethod.size(); i++) {
            PaymentMethod pm = arrPaymentMethod.get(i);
            if (pm.getNameMethod().equals(depositComboBox.getSelectedItem())) {
                if (confirmCheckBox.isSelected()) {
                    if (transaction.getTransactionId() != 0) {
                        boolean res = paymentController.processReturnPayment(pm, amount, bike, dockingPoint, transaction);
                        if (res) {
                            try {
                                MainEntry.move(this, new HomeScreen());
                            } catch (SQLException | IOException ex) {
                                Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Update Returned Status Fail");
                        }
                    } else {
                        try {
                            transaction = paymentController.processRentPayment(pm, amount, bike, dockingPoint, transaction);
                            if (transaction != null) {
                                MainEntry.move(this, new RentingScreen(transaction, this));
                            } else {
                                JOptionPane.showMessageDialog(this, "Transaction fail or User Has Another Transaction");
                            }
                        } catch (ParseException | IOException ex) {
                            Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "You have to check confirm!!");
                }
            }
        }
    }//GEN-LAST:event_confirmPayment

    /**
     * Handle event user click to button back. it will rout user to backScreen
     * (Rent bike, Return bike..)
     *
     * @param evt
     */
    private void goBack(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBack
        MainEntry.move(this, backScreen);
    }//GEN-LAST:event_goBack

    /**
     * Handle event user click to button Add Payment Method. it will route user
     * to Add Payment Method Screen.
     *
     * @param evt
     */
    private void addPaymentMethod(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPaymentMethod
        try {
            MainEntry.move(this, new AddPayentMethodScreen(this));
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addPaymentMethod


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmButton;
    private javax.swing.JCheckBox confirmCheckBox;
    private javax.swing.JComboBox<String> depositComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTable paymentInfoTable;
    // End of variables declaration//GEN-END:variables
}