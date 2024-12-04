package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        txtAdrServeur = new JTextField(adr);
        txtNumPort = new JTextField(""+port);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel p1Labels = new JPanel(new GridLayout(2,1));
        JPanel p1TxtFields = new JPanel(new GridLayout(2,1));
        JLabel lblAdr = new JLabel("Adresse IP:");
        JLabel lblPort = new JLabel("Port:");
        p1Labels.add(lblAdr);
        p1Labels.add(lblPort);
        p1TxtFields.add(txtAdrServeur);
        p1TxtFields.add(txtNumPort);
        p1TxtFields.setPreferredSize(new Dimension(70,50));
        this.add(p1Labels);
        this.add(p1TxtFields);
        this.setPreferredSize(new Dimension(100,100));
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
