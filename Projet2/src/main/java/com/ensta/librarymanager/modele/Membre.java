package com.ensta.librarymanager.modele;

public class Membre {
    private static int COUNT = 0;

    private int primary_key;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;

    public Membre(){
        COUNT++;
        this.primary_key = COUNT;
    }

    public Membre(String nom, String prenom, String adresse, String email,
    String telephone, Abonnement ab){
        COUNT++;
        this.primary_key = COUNT;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = ab;
    }


    public int getKey(){
        return this.primary_key;
    }



    @Override
    public String toString() {
        return "|" + primary_key + "|" + nom + "|" + prenom + "|" + adresse
        + "|" + email + "|" + telephone + "|" + abonnement + "|";
    }
}
