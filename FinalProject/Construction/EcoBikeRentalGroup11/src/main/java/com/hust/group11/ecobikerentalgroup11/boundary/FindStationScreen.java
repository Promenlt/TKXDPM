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

/**
 *
 * @author Nguyen Hai Long
 */
import com.hust.group11.ecobikerentalgroup11.Constants;
import com.hust.group11.ecobikerentalgroup11.entity.Station;
import com.hust.group11.ecobikerentalgroup11.entity.Transaction;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import com.hust.group11.ecobikerentalgroup11.MainEntry;
import com.hust.group11.ecobikerentalgroup11.controller.ViewStationController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FindStationScreen extends javax.swing.JFrame {

    /**
     * Creates new form FindStationScreen
     */
    private final JFrame backScreen;
    private final ArrayList<Station> arrStation;
    private Station passStation;
    private Transaction transaction;
    private final ViewStationController stationController;

    /**
     * For user want to return bike
     *
     * @param transaction
     * @param backScreen
     * @throws SQLException
     * @throws IOException
     */
    public FindStationScreen(Transaction transaction, JFrame backScreen) throws SQLException, IOException {
        initComponents();
        this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/icon_app.png")));
        this.backScreen = backScreen;
        this.transaction = transaction;
        this.stationController = new ViewStationController();
        this.arrStation = new Station().getArrayStation();
        DefaultTableModel model = (DefaultTableModel) stationListTable.getModel();
        this.stationController.processViewStation(model, arrStation);
        sortTable();
    }

    /**
     * For user want to rent
     *
     * @param backScreen
     * @throws SQLException
     * @throws IOException
     */
    public FindStationScreen(JFrame backScreen) throws SQLException, IOException {
        initComponents();
        this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/icon_app.png")));
        this.backScreen = backScreen;
        this.stationController = new ViewStationController();
        this.arrStation = new Station().getArrayStation();
        DefaultTableModel model = (DefaultTableModel) stationListTable.getModel();
        this.stationController.processViewStation(model, arrStation);
        sortTable();
    }

    /**
     * Sort table when change text in input field
     */
    private void sortTable() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(stationListTable.getModel());
        stationListTable.setRowSorter(sorter);
        textFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textFieldSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textFieldSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
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
        stationListTable = new javax.swing.JTable();
        textFieldSearch = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        buttonViewStationInfo = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Find Station");
        setResizable(false);

        stationListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stt", "Station Name", "Distance(m)", "Address", "Bike Avail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stationListTable.setToolTipText("");
        stationListTable.setRowHeight(18);
        stationListTable.setRowMargin(2);
        stationListTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(stationListTable);
        if (stationListTable.getColumnModel().getColumnCount() > 0) {
            stationListTable.getColumnModel().getColumn(0).setResizable(false);
            stationListTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            stationListTable.getColumnModel().getColumn(1).setResizable(false);
            stationListTable.getColumnModel().getColumn(1).setPreferredWidth(160);
            stationListTable.getColumnModel().getColumn(2).setResizable(false);
            stationListTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            stationListTable.getColumnModel().getColumn(3).setResizable(false);
            stationListTable.getColumnModel().getColumn(3).setPreferredWidth(250);
            stationListTable.getColumnModel().getColumn(4).setResizable(false);
            stationListTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        searchLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        searchLabel.setText("Search Station");

        buttonViewStationInfo.setText("Station Info");
        buttonViewStationInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStationInfo(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBack(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("List Stations");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(buttonViewStationInfo))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldSearch)
                    .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(buttonViewStationInfo))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handle event user click to button view station. when user have not choose
     * any station in table it will show message. Else it will redirect to
     * Station InFormation screen.
     *
     * @param evt
     */
    private void viewStationInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStationInfo
        int column = 1;
        int row = stationListTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "You have not choose station");
        } else {
            String value = stationListTable.getModel().getValueAt(row, column).toString();
            arrStation.forEach((Station s) -> {
                if (s.getStationName().equals(value)) {
                    passStation = s;
                    try {
                        if (MainEntry.user.getStatus() == Constants.USER_AVAIL) {
                            MainEntry.move(this, new StationInfoScreen(this, passStation));
                        } else {
                            MainEntry.move(this, new StationInfoScreen(this, passStation, transaction));
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FindStationScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }//GEN-LAST:event_viewStationInfo

    /**
     * Handle event user click button go back
     *
     * @param evt
     */
    private void goBack(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBack
        MainEntry.move(this, backScreen);
    }//GEN-LAST:event_goBack

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton buttonViewStationInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTable stationListTable;
    private javax.swing.JTextField textFieldSearch;
    // End of variables declaration//GEN-END:variables
}
