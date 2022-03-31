package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {
    private int primary_key=0;
    private Membre membre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(){}

    public Emprunt(Membre membre, Livre livre, LocalDate dateE, LocalDate dateR){
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateE;
        this.dateRetour = dateR;
    }

    public Emprunt(int key, Membre membre, Livre livre, LocalDate dateE, LocalDate dateR){
        this.primary_key = key;
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateE;
        this.dateRetour = dateR;
    }
    public int getKey() {return this.primary_key;}
    public Membre getMembre() {return this.membre;}
    public Livre getLivre() {return this.livre;}
    public LocalDate getDateEmprunt() {return this.dateEmprunt;}
    public LocalDate getDateRetour() {return this.dateRetour;}

    public void setKey(int primary_key){this.primary_key=primary_key;}
    public void setMembre(Membre membre){this.membre=membre;}
    public void setLivre(Livre livre){this.livre=livre;}
    public void setDateEmprunt(LocalDate date){this.dateEmprunt=date;}
    public void setDateRetour(LocalDate date){this.dateRetour=date;}

    @Override
    public String toString() {
        return "|"+primary_key+"|" + membre.getNom() + "|" + livre.getTitre() + "|" + dateEmprunt
        + "|" + dateRetour + "|";
    }
}