package com.ensta.librarymanager.test;

import java.time.LocalDate;

import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.IEmpruntService;
import com.ensta.librarymanager.service.ILivreService;
import com.ensta.librarymanager.service.IMembreService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.utils.FillDatabase;

public class ServiceTest {
    
    public static void main(String args[]) throws Exception{
        FillDatabase.main(args);

        //MEMBRES
        
        System.out.println("------------------TEST MEMBERS---------------------");
        IMembreService membreService = MembreService.getInstance();
        membreService.getList();
        System.out.println();
        membreService.getListMembreEmpruntPossible();
        membreService.getById(1);
        System.out.println();
        membreService.create("nomEx", "prenomEx", "adresseEx", "emailEx", "telephoneEx", Abonnement.VIP);
        System.out.println();
        membreService.getList();
        System.out.println();
        //update makes every string NOMEX2 ????
        membreService.update(new Membre(1, "nomEx2", "prenomEx2", "adresseEx2", "emailEx2", "telephoneEx2", Abonnement.BASIC));
        System.out.println();
        membreService.getList();
        System.out.println();
        membreService.count();
        


        //LIVRES
        /*
        System.out.println("------------------TEST LIVRES---------------------");
        ILivreService livreService = LivreService.getInstance();
        livreService.getList();
        System.out.println();
        livreService.getListDispo();
        System.out.println();
        livreService.getById(1);
        System.out.println();
        livreService.create("titreEx", "auteurEx", "isbnEx");
        System.out.println();
        livreService.getList();
        livreService.update(new Livre(1, "titreEx2", "auteurEx2", "isbnEx2"));
        livreService.getList();
        livreService.count();
        */


        //EMPRUNTS
        /*
        System.out.println("------------------TEST EMPRUNTS---------------------");
        IEmpruntService empruntService = EmpruntService.getInstance();
        empruntService.getList();
        System.out.println();
        empruntService.getListCurrent();
        System.out.println();
        //empruntService.getListCurrentByMembre(1);
        System.out.println();
        empruntService.getListCurrentByLivre(1);
        System.out.println();
        empruntService.getById(1);
        System.out.println();
        empruntService.create(1, 1, LocalDate.now());
        empruntService.getList();
        System.out.println();
        empruntService.count();
        System.out.println();
        empruntService.isLivreDispo(1);
        System.out.println();
        */

    }




}
