package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauFacture extends JPanel {
    private JTextField txtMontantFacture,txtNumFacture,txtDesc;

    public PanneauFacture(){
        JPanel p1Labels = new JPanel();
        JPanel p1TxtFields = new JPanel();
        p1Labels.setLayout(new GridLayout(3,1));
        p1TxtFields.setLayout(new GridLayout(3,1));
        p1Labels.setPreferredSize(new Dimension(130,80));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblMontantFacture = new JLabel("Montant de la facture: ");
        JLabel lblNumFacture = new JLabel("Numéro de la facture: ");
        JLabel lblDesc = new JLabel("Description: ");
        txtMontantFacture = new JTextField();
        txtMontantFacture.setPreferredSize(new Dimension(70,25));
        txtNumFacture = new JTextField();
        txtNumFacture.setPreferredSize(new Dimension(70,25));
        txtDesc = new JTextField();
        txtDesc.setPreferredSize(new Dimension(70,25));
        p1Labels.add(lblMontantFacture);
        p1Labels.add(lblNumFacture);
        p1Labels.add(lblDesc);
        p1TxtFields.add(txtMontantFacture);
        p1TxtFields.add(txtNumFacture);
        p1TxtFields.add(txtDesc);
        this.add(p1Labels);
        this.add(p1TxtFields);
    }

    public String getMontantFacture(){
        return txtMontantFacture.getText();
    }

    public String getNumFacture(){
        return txtNumFacture.getText();
    }

    public String getDesc(){
        return txtDesc.getText();
    }
}
