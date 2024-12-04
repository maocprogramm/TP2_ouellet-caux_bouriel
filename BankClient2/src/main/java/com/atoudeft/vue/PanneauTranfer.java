package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauTranfer extends JPanel {
    private JTextField txtMontantTransfert,txtCompteDestinataire;

    public PanneauTranfer(){
        JPanel p1Labels = new JPanel();
        JPanel p1TxtFields = new JPanel();
        p1Labels.setLayout(new GridLayout(2,1));
        p1TxtFields.setLayout(new GridLayout(2,1));
        p1Labels.setPreferredSize(new Dimension(130,80));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblMontantTransfert = new JLabel("Montant du transfert: ");
        JLabel lblCompteDestinataire = new JLabel("Destinataire: ");
        txtMontantTransfert = new JTextField();
        txtMontantTransfert.setPreferredSize(new Dimension(70,25));
        txtCompteDestinataire = new JTextField();
        txtCompteDestinataire.setPreferredSize(new Dimension(70,25));
        p1Labels.add(lblMontantTransfert);
        p1Labels.add(lblCompteDestinataire);
        p1TxtFields.add(txtMontantTransfert);
        p1TxtFields.add(txtCompteDestinataire);
        this.add(p1Labels);
        this.add(p1TxtFields);
    }

    public String getMontantTransfert(){
        return txtMontantTransfert.getText();
    }

    public String getCompteDestinataire(){
        return txtCompteDestinataire.getText();
    }
}
