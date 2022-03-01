package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {

    private int primary_key;
    //private int idMembre;
    private Membre membre;
    //private int idLivre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    @Override
    public String toString() {
        return "|" + primary_key + "|" + membre.getKey() + "|" + livre.getKey() + "|" + dateEmprunt
        + "|" + dateRetour + "|";
    }
}