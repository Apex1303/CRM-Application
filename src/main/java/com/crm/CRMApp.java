package com.crm;

import com.crm.ui.CRMFrame;
import javax.swing.SwingUtilities;

public class CRMApp {
    public static void main(String[] args) {
        // Run GUI codes on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            CRMFrame frame = new CRMFrame();
            frame.setVisible(true);
        });
    }
}