package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {
    private static int COUNT = 0;
    private int primary_key;
    //private int idMembre;
    private Membre membre;
    //private int idLivre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(){
        COUNT++;
        this.primary_key = COUNT;
    }

    public Emprunt(Membre membre, Livre livre, LocalDate dateE, LocalDate dateR){
        COUNT++;
        this.primary_key = COUNT;
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateE;
        this.dateRetour = dateR;
    }

    @Override
    public String toString() {
        return "|" + primary_key + "|" + membre.getKey() + "|" + livre.getKey() + "|" + dateEmprunt
        + "|" + dateRetour + "|";
    }
}