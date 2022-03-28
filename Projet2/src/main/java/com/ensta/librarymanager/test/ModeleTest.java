package com.ensta.librarymanager.test;

import java.time.LocalDate;

import com.ensta.librarymanager.modele.*;
//no final fazer mvn site
public class ModeleTest{

    public static void main(String args[]){
        Livre livre = new Livre("BTitle", "BAuthor", "BISBN");
        Membre membre = new Membre("MPrenom", "MNom", "M@email.com", "06506060504","adress", Abonnement.BASIC);
        Emprunt emprunt = new Emprunt(membre, livre, LocalDate.of(2022,3,8), LocalDate.of(2022,5,8));
        
        System.out.println(livre);
        System.out.println(membre);
        System.out.println(emprunt);
    }
} 