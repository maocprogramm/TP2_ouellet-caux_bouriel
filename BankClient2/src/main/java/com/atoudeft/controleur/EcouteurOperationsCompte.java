package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.*;
import com.programmes.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private JFrame fenetre;

    public EcouteurOperationsCompte(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        Object source = e.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JButton) {
            action = ((JButton)source).getActionCommand();
            switch (action) {
                case("EPARGNE"):
                    client.envoyer("EPARGNE");
                    break;
                case("DEPOT"):
                    PanneauDepot panneauDepot = new PanneauDepot();
                    res = JOptionPane.showConfirmDialog(fenetre,panneauDepot,"Depot dans le compte courant",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.envoyer("DEPOT " + panneauDepot.getMontantDepot());
                    }
                    break;
                case("RETRAIT"):
                    PanneauRetrait panneauRetrait = new PanneauRetrait();
                    res = JOptionPane.showConfirmDialog(fenetre,panneauRetrait,"Retrait dans le compte courant",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.envoyer("RETRAIT " + panneauRetrait.getMontantRetrait());
                    }
                    break;
                case("TRANSFER"):
                    PanneauTranfer panneauTranfer = new PanneauTranfer();
                    res = JOptionPane.showConfirmDialog(fenetre,panneauTranfer,"Tranfert entre deux comptes",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.envoyer("TRANSFER " + panneauTranfer.getMontantTransfert() + " " + panneauTranfer.getCompteDestinataire());
                    }
                    break;
                case("FACTURE"):
                    PanneauFacture panneauFacture = new PanneauFacture();
                    res = JOptionPane.showConfirmDialog(fenetre,panneauFacture,"Paiement de facture",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.envoyer("FACTURE " + panneauFacture.getMontantFacture() + " " + panneauFacture.getNumFacture() + " " + panneauFacture.getDesc());
                    }
                    break;
                case("HIST"):
                    client.envoyer("HIST");
                    break;
            }
        }
    }
}
