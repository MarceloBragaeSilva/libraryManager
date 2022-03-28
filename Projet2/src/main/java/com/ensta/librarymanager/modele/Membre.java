package com.ensta.librarymanager.modele;

import org.h2.util.AbbaLockingDetector;

public class Membre {

    private int primary_key=0;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;

    public Membre(){}

    public Membre(String nom, String prenom, String adresse, String email,
    String telephone, Abonnement ab){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = ab;
    }

    public Membre(int key, String nom, String prenom, String adresse, String email,
    String telephone, Abonnement ab){
        this.primary_key = key;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = ab;
    }

    public int getKey() {return this.primary_key;}
    public String getNom() {return this.nom;}
    public String getPrenom() {return this.nom;}
    public String getAdresse() {return this.nom;}
    public String getEmail() {return this.nom;}
    public String getTelephone() {return this.nom;}
    public Abonnement getAbonnement(){return this.abonnement;}

    public void setKey(int primary_key){this.primary_key=primary_key;}
    public void setNom(String nom){this.nom = nom;}
    public void setPrenom(String prenom){this.prenom = prenom;}
    public void setAdress(String add){this.adresse = add;}
    public void setEmail(String email){this.email = email;}
    public void setTelephone(String tel){this.telephone = tel;}
    public void setAbonnement(Abonnement ab){this.abonnement = ab;}




    @Override
    public String toString() {
        return "|" + nom + "|" + prenom + "|" + adresse
        + "|" + email + "|" + telephone + "|" + abonnement + "|";
    }
}
