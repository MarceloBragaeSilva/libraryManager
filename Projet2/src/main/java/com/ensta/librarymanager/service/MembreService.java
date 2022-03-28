package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.IMembreDao;
import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;

public class MembreService implements IMembreService{

    //Singleton
    private static MembreService instance;
    private MembreService(){};
    public static MembreService getInstance(){
        if (instance == null)
            instance = new MembreService();
        return instance;
    }

    @Override
    public List<Membre> getList() throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();
        List<Membre> list_allMembre = new ArrayList<Membre>();

        try {
            list_allMembre = membreDao.getList();
            System.out.println("\n ALL MEMBERS:\n " + list_allMembre);
            return list_allMembre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list all Members.");
        }
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();
        List<Membre> list_possMembre = new ArrayList<Membre>();
        List<Membre> list_allMembre = new ArrayList<Membre>();

        EmpruntService empruntService = EmpruntService.getInstance();
        try {
            list_allMembre = membreDao.getList();
            for(int i=0; i<list_allMembre.size(); i++){
                if(empruntService.isEmpruntPossible(list_allMembre.get(i)))
                    list_possMembre.add(list_allMembre.get(i));
            }
            System.out.println("\n ALL MEMBERS THAT CAN LOAN:\n " + list_possMembre);
            return list_possMembre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list Members that can loan Books.");
        }
    }

    @Override
    public Membre getById(int id) throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();
        Membre membre = new Membre();

        try {
            membre = membreDao.getById(id);
            System.out.println("\n MEMBER OF ID "+ id +": " + membre);
            return membre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not get Member by ID.");
        }
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();

        try {
            int id_m;
            if (nom == null || nom == "" || prenom == null  || prenom == ""){
                throw new ServiceException("\nName is empty. Cannot create Member.");
            } 
            else{
                id_m = membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone, abonnement);
                System.out.println("\n MEMBER CREATED WITH ID: " + id_m);
            }
            return id_m;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not create Book.");
        }
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();
        try {
            if (membre.getNom() == null || membre.getNom() == "" || membre.getPrenom() == null || membre.getPrenom() == ""){
                throw new ServiceException("\nName is empty. Cannot update Member.");
            }
            else{
                membre.setNom(membre.getNom().toUpperCase());
                membreDao.update(membre);
                System.out.println("\nMEMBER WITH ID "+ membre.getKey() +" UPDATED.");
            }
        } catch (Exception | DaoException e) {
            throw new ServiceException("\n Member can't be updated.");
        }
        
    }

    @Override
    public void delete(int id) throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();

        try {
            membreDao.delete(id);
            System.out.println("\nMEMBER WITH ID "+ id +" DELETED.");
        } catch (Exception | DaoException e) {
            throw new ServiceException("\n Could not delete Member.");
        }
        
    }

    @Override
    public int count() throws ServiceException {
        IMembreDao membreDao = MembreDao.getInstance();

        try {
            int count_membre = membreDao.count();
            System.out.println("\n MEMBER COUNT: " + count_membre);
            return count_membre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not count Members.");
        }
    }
    
}
