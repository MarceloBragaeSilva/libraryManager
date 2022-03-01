package com.ensta.librarymanager.modele;

public class Membre {

    private int primary_key;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;


    public int getKey(){
        return this.primary_key;
    }



    @Override
    public String toString() {
        return "|" + primary_key + "|" + nom + "|" + prenom + "|" + adresse
        + "|" + email + "|" + telephone + "|" + abonnement + "|";
    }
}
