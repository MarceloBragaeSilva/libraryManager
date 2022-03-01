package com.ensta.librarymanager.modele;

public class Livre {

    private int primary_key;
    private String titre;
    private String auteur;
    private String isbn;

    public int getKey(){
        return this.primary_key;
    }


    @Override
    public String toString() {
        return "|" + primary_key + "|" + titre + "|" + auteur + "|" + isbn + "|";
    }
}
