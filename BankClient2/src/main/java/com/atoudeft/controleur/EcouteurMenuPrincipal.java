package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;
import com.programmes.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                        else {
                            fenetre.getJMenuBar().getMenu(0).getItem(0).setEnabled(false);
                            fenetre.getJMenuBar().getMenu(0).getItem(1).setEnabled(true);
                            ((MainFrame)fenetre).seMettreAJour(client);
                        }
                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                        fenetre.getJMenuBar().getMenu(0).getItem(0).setEnabled(true);
                        fenetre.getJMenuBar().getMenu(0).getItem(1).setEnabled(false);
                        ((MainFrame)fenetre).seMettreAJour(client);
                    }
                    break;
                case "CONFIGURER":
                    //TODO : compléter (question 1.3)
                    PanneauConfigServeur panneauConfigServeur = new PanneauConfigServeur(client.getAdrServeur(),client.getPortServeur());
                    res = JOptionPane.showConfirmDialog(fenetre, panneauConfigServeur,
                            "Configuration serveur", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.setAdrServeur(panneauConfigServeur.getAdresseServeur());
                        client.setPortServeur(Integer.parseInt(panneauConfigServeur.getPortServeur()));
                        ((MainFrame)fenetre).seMettreAJour(client);
                    }
                    break;
                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}