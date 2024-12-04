package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauDepot extends JPanel {
    private JTextField txtMontantDepot;

    public PanneauDepot(){
        JLabel lblDepot = new JLabel("Montant du dépôt: ");
        txtMontantDepot = new JTextField();
        txtMontantDepot.setPreferredSize(new Dimension(70,25));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(lblDepot);
        this.add(txtMontantDepot);

    }

    public String getMontantDepot(){
        return txtMontantDepot.getText();
    }
}
