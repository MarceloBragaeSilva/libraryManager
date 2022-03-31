package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.IEmpruntDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntService implements IEmpruntService {

    //Singleton
    private static EmpruntService instance;
    private EmpruntService(){};
    public static EmpruntService getInstance(){
        if (instance == null)
            instance = new EmpruntService();
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> list_allEmprunt = new ArrayList<Emprunt>();

        try {
            list_allEmprunt = empruntDao.getList();
            System.out.println("\n ALL LOANS:\n " + list_allEmprunt);
            return list_allEmprunt;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list all Loans.");
        }
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> list_currentEmprunt = new ArrayList<Emprunt>();

        try {
            list_currentEmprunt = empruntDao.getListCurrent();
            System.out.println("\n CURRENT LOANS:\n " + list_currentEmprunt);
            return list_currentEmprunt;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list all current Loans.");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> list_empruntMembre = new ArrayList<Emprunt>();

        try {
            list_empruntMembre = empruntDao.getListCurrentByMembre(idMembre);
            System.out.println("\n CURRENT LOANS OF MEMBER "+ idMembre + ":\n " + list_empruntMembre);
            return list_empruntMembre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list Loans by Member.");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();
        List<Emprunt> list_empruntLivre = new ArrayList<Emprunt>();

        try {
            list_empruntLivre = empruntDao.getListCurrentByLivre(idLivre);
            System.out.println("\n CURRENT LOANS BY BOOK "+ idLivre + ":\n " + list_empruntLivre);
            return list_empruntLivre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list Loans by Book.");
        }
    }

    @Override
    public Emprunt getById(int id) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();
        Emprunt emprunt = new Emprunt();

        try {
            emprunt = empruntDao.getById(id);
            System.out.println("\n LOAN OF ID "+ id +": " + emprunt);
            return emprunt;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not get Loan by ID.");
        }
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();

        try {
            empruntDao.create(idMembre, idLivre, dateEmprunt);
            System.out.println("\n LOAN CREATED.");
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not create Loan.");
        }
        
    }

    @Override
    public void returnBook(int id) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();

        try {
            Emprunt empruntToUpdate = empruntDao.getById(id);
            empruntToUpdate.setDateRetour(LocalDate.now());
            empruntDao.update(empruntToUpdate);

            System.out.println("\n  Book returned, loan updated " + empruntToUpdate);
        } catch (Exception | DaoException e) {
            throw new ServiceException("\n Can't be returned yet.");
        }
        
    }

    @Override
    public int count() throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();

        try {
            int count_emprunt = empruntDao.count();
            System.out.println("\n LOAN COUNT: " + count_emprunt);
            return count_emprunt;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not count Loans.");
        }
    }

    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();

        try {
            boolean isDispo = empruntDao.getListCurrentByLivre(idLivre).isEmpty();
            if(isDispo)
                System.out.println("BOOK "+ idLivre +" IS AVAILABLE");
            else System.out.println("BOOK "+ idLivre +" IS NOT AVAILABLE");
            return isDispo;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not verify Book availability.");
        }
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        IEmpruntDao empruntDao = EmpruntDao.getInstance();

        try {
            int n_emprunts = empruntDao.getListCurrentByMembre(membre.getKey()).size();
            boolean isPossible = n_emprunts < membre.getAbonnement().getMaxCount();

            if(isPossible)
                System.out.println("MEMBER "+ membre.getKey() +" CAN LOAN A BOOK");
            else System.out.println("MEMBER "+ membre.getKey() +" CANNOT LOAN A BOOK");
            return isPossible;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not verify Loan possibility.");
        }
    }
    
}
