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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Thanh Long
 */
public class ImageCodePanel extends JPanel {

    private BufferedImage image;
    private boolean isSet;

    /**
     * constructor init ImageCodePanel. if image has set, it will not initial
     * default image.
     */
    public ImageCodePanel() {
        isSet = false;
        if (!isSet) {
            try {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/scan_code.png"));
                isSet = true;
            } catch (IOException ex) {
                Logger.getLogger(ImageCodePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method will display image by input file image.
     *
     * @param in
     */
    public void showImage(InputStream in) {
        try {
            image = ImageIO.read(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Override method of JPanel. this will paint component to panel.
     *
     * @param graphics
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, this.getSize().width, this.getSize().height, this);
    }

}
