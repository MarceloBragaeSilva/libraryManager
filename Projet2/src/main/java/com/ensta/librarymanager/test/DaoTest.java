package com.ensta.librarymanager.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.utils.FillDatabase;

public class DaoTest {
    public static void main(String args[]) throws Exception, DaoException{
        FillDatabase.main(args);

        //MEMBRES
        List<Membre> list_membre = new ArrayList<Membre>();
        IMembreDao membreDao = MembreDao.getInstance();
        list_membre = membreDao.getList();
        System.out.println("\n\nList of Members ");
        for(int i=0; i<list_membre.size();i++){
            System.out.println(list_membre.get(i));
        }

        //Membre firstMembre = membreDao.getById(list_membre.get(1).getKey());
        //System.out.println("Membre ID =1 :\n "+firstMembre);

        //LIVRES
        ILivreDao livreDao = LivreDao.getInstance();

        List<Livre> list_livre = new ArrayList<Livre>();
        list_livre = livreDao.getList();
        System.out.println("\n\nList of Books ");
        for(int i=0; i<list_livre.size();i++){
            System.out.println(list_livre.get(i));
        }
        int id_Les_oiseaux_migrateurs = list_livre.get(0).getKey();
        System.out.println("Inf Les oiseaux migrateurs\n"+livreDao.getById(id_Les_oiseaux_migrateurs));

        int id_booktest = livreDao.create("LivreExemple", "Marcelo Braga", "978-2867804876");
        System.out.println("\n New book id : " + id_booktest);
        list_livre = livreDao.getList();
        System.out.println("\n\nList of Books ");
        for(int i=0; i<list_livre.size();i++){
            System.out.println(list_livre.get(i));
        }

        
        //EMPRUNTS
        IEmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> list_emprunt = empruntDao.getList();
        System.out.println("\n\nList of Emprunts ");
        for(int i=0; i<list_emprunt.size();i++){
            System.out.println(list_emprunt.get(i));
        }



    }
}
