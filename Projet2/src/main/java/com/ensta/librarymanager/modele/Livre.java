package com.ensta.librarymanager.modele;

public class Livre {

    private int primary_key=0;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(){}

    public Livre(String titre, String auteur, String isbn){
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public Livre(int key, String titre, String auteur, String isbn){
        this.primary_key = key;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public int getKey() {return this.primary_key;}
    public String getTitre() {return this.titre;}
    public String getAuteur(){ return this.auteur;}
    public String getIsbn() {return this.isbn;}

    public void setKey(int primary_key){this.primary_key=primary_key;}
    public void setTitre(String titre){this.titre=titre;}
    public void setAuteur(String auteur){this.auteur=auteur;}
    public void setIsbn(String isbn){this.isbn=isbn;}

    @Override
    public String toString() {
        return "|" +primary_key+"|"+ titre + "|" + auteur + "|" + isbn + "|";
    }
}
