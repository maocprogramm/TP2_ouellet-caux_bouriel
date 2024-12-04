package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauPrincipal;
import com.atoudeft.vue.PanneauRetrait;
import com.programmes.MainFrame;

import javax.swing.*;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;

    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        //Connexion cnx;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;

        if (source instanceof Connexion) {
            //cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
                case "HIST":
                    arg = evenement.getArgument();
                    JTextArea txtHist = new JTextArea(arg);
                    txtHist.setEnabled(false);
                    JOptionPane.showMessageDialog(panneauPrincipal,txtHist);
                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE" :
                    String[] args = evenement.getArgument().split(" ");
                    //JOptionPane.showMessageDialog(panneauPrincipal,"EPARGNE "+arg);
                    if (args[0].equals("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Création du compte d'épargne impossible: EPARGNE "+args[0]);
                    }
                    else if (args[0].equals("OK")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Création du compte d'épargne réussie: EPARGNE "+args[0] + " " + args[1]);
                        panneauPrincipal.ajouterCompte(args[1]+"[EPARGNE] 0.0");
                        ((MainFrame)panneauPrincipal.getTopLevelAncestor()).seMettreAJour(client);
                    }
                    break;
                case "SELECT" :
                    args = evenement.getArgument().split(" ");
                    if (args[0].equals("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Selection de compte impossible.");
                    }
                    else if (args[0].equals("OK")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Selection de compte réussie.");
                        panneauPrincipal.getPanneauOperationsCompte().setSolde(args[2]);
                        ((MainFrame)panneauPrincipal.getTopLevelAncestor()).seMettreAJour(client);
                    }
                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    args = evenement.getArgument().split(" ");
                    if (args[0].equals("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Dépôt impossible");
                    }
                    else{
                        JOptionPane.showMessageDialog(panneauPrincipal,"Dépôt réussi!");
                        panneauPrincipal.getPanneauOperationsCompte().setSolde(args[1]);
                        ((MainFrame)panneauPrincipal.getTopLevelAncestor()).seMettreAJour(client);
                    }
                    break;
                case "RETRAIT" :
                    args = evenement.getArgument().split(" ");
                    if (args[0].equals("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal, "Retrait impossible");
                    }
                    else {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Retrait réussi!");
                        panneauPrincipal.getPanneauOperationsCompte().setSolde(args[1]);
                        ((MainFrame)panneauPrincipal.getTopLevelAncestor()).seMettreAJour(client);
                    }
                    break;
                case "FACTURE" :
                    args = evenement.getArgument().split(" ");
                    if (args[0].equals("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Paiement de facture impossible");
                    }
                    else {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Paiement de facture réussie!");
                        panneauPrincipal.getPanneauOperationsCompte().setSolde(args[1]);
                        ((MainFrame)panneauPrincipal.getTopLevelAncestor()).seMettreAJour(client);
                    }
                    break;
                case "TRANSFER" :
                    args = evenement.getArgument().split(" ");
                    if (args[0].equals("NO")){
                        JOptionPane.showMessageDialog(panneauPrincipal,"Transfert impossible");
                    }
                    else {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Transfert réussi!");
                        panneauPrincipal.getPanneauOperationsCompte().setSolde(args[1]);
                        ((MainFrame)panneauPrincipal.getTopLevelAncestor()).seMettreAJour(client);
                    }
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}