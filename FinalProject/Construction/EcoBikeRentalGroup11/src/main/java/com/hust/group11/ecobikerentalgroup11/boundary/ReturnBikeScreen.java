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

import com.hust.group11.ecobikerentalgroup11.controller.RentBikeController;
import com.hust.group11.ecobikerentalgroup11.controller.ReturnBikeController;
import com.hust.group11.ecobikerentalgroup11.Constants;
import com.hust.group11.ecobikerentalgroup11.entity.Bike;
import com.hust.group11.ecobikerentalgroup11.entity.DockingPoint;
import com.hust.group11.ecobikerentalgroup11.entity.Transaction;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Thanh Long
 */
public class ReturnBikeScreen extends javax.swing.JFrame {

    private final Bike bike;
    private final RentBikeController rentBikeController;
    private final ReturnBikeController returnBikeController;
    private final JFrame backScreen;
    private final DockingPoint dockingPoint;
    private final Transaction transaction;

    /**
     * Creates new form RentBikeScreen
     *
     * @param transaction
     * @param dockingPoint
     * @param backScreen
     * @throws java.io.IOException
     */
    public ReturnBikeScreen(Transaction transaction, DockingPoint dockingPoint, JFrame backScreen) throws IOException {
        initComponents();
        this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/icon_app.png")));
        this.backScreen = backScreen;
        this.dockingPoint = dockingPoint;
        this.transaction = transaction;
        this.bike = transaction.getBikeTransaction();
        this.rentBikeController = new RentBikeController();
        this.returnBikeController = new ReturnBikeController();

        displayTransactionInfo(MainEntry.user.getUserId());
        imageCodePanel.showImage(getClass().getClassLoader().getResourceAsStream("img/" + bike.getImage()));
        imageCodePanel.repaint();
    }

    /**
     * show information of transaction will be return
     *
     * @param userId
     */
    public void displayTransactionInfo(int userId) {
        DefaultTableModel model = (DefaultTableModel) bikeInfoTable.getModel();
        rentBikeController.showInfoBike(model, bike);
        model.addRow(new Object[]{"Start time", transaction.getStartTime()});
        model.addRow(new Object[]{"End time", transaction.getEndTime()});
        model.addRow(new Object[]{"Time rental", transaction.getTimeRental()});
        model.addRow(new Object[]{"Rental Money", transaction.getBikeRental() + Constants.MONEY_UNIT});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imageCodePanel = new com.hust.group11.ecobikerentalgroup11.boundary.ImageCodePanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bikeInfoTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        requestButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Return Bike");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        imageCodePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imageCodePanelLayout = new javax.swing.GroupLayout(imageCodePanel);
        imageCodePanel.setLayout(imageCodePanelLayout);
        imageCodePanelLayout.setHorizontalGroup(
            imageCodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );
        imageCodePanelLayout.setVerticalGroup(
            imageCodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageCodePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageCodePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bikeInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bikeInfoTable.setColumnSelectionAllowed(true);
        bikeInfoTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(bikeInfoTable);
        bikeInfoTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (bikeInfoTable.getColumnModel().getColumnCount() > 0) {
            bikeInfoTable.getColumnModel().getColumn(0).setResizable(false);
            bikeInfoTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            bikeInfoTable.getColumnModel().getColumn(1).setResizable(false);
            bikeInfoTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Transaction infomation:");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(245, 245, 245));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("  Free for first 10 minutes. After that, to addition\n  10.000vnd/30 first minute. Then, 3000vnd/15m\n  Example: 1h 10m = 30m + 2x15m + 10m \n  You have pay: 10.000 + 3x300 = 19.000vnd.\n  Electric bike's price 1.5 times normal type.");
        jTextArea1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Price:");

        requestButton.setText("Request To Return");
        requestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestReturnBike(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBack(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(requestButton)))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(requestButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handle event user click to request return button
     *
     * @param evt
     */
    private void requestReturnBike(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestReturnBike
        returnBikeController.processReturnBike(transaction, bike, dockingPoint, this);
    }//GEN-LAST:event_requestReturnBike

    /**
     * Handle event user click to back button
     *
     * @param evt
     */
    private void goBack(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBack
        MainEntry.move(this, backScreen);
    }//GEN-LAST:event_goBack


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bikeInfoTable;
    private com.hust.group11.ecobikerentalgroup11.boundary.ImageCodePanel imageCodePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton requestButton;
    // End of variables declaration//GEN-END:variables
}
