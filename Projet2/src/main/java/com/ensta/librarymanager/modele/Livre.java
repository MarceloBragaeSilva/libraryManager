package com.ensta.librarymanager.modele;

public class Livre {
    private static int COUNT = 0;

    private int primary_key;
    private String titre;
    private String auteur;
    private String isbn;


    public Livre(){
        COUNT++;
        this.primary_key = COUNT;
    }

    public Livre(String titre, String auteur, String isbn){
        COUNT++;
        this.primary_key = COUNT;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public int getKey(){
        return this.primary_key;
    }


    @Override
    public String toString() {
        return "|" + primary_key + "|" + titre + "|" + auteur + "|" + isbn + "|";
    }
}
