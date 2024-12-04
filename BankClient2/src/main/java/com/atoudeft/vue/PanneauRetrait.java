package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauRetrait extends JPanel {
    private JTextField txtMontantRetrait;

    public PanneauRetrait(){
        JLabel lblRetrait = new JLabel("Montant du retrait: ");
        txtMontantRetrait = new JTextField();
        txtMontantRetrait.setPreferredSize(new Dimension(70,25));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(lblRetrait);
        this.add(txtMontantRetrait);
    }

    public String getMontantRetrait(){
        return txtMontantRetrait.getText();
    }
}
