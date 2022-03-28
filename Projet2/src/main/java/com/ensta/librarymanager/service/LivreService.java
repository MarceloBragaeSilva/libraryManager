package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.ILivreDao;
import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreService implements ILivreService{

    //Singleton
    private static LivreService instance;
    private LivreService(){};
    public static LivreService getInstance(){
        if (instance == null)
            instance = new LivreService();
        return instance;
    }

    @Override
    public List<Livre> getList() throws ServiceException {
        ILivreDao livreDao = LivreDao.getInstance();
        List<Livre> list_allLivre = new ArrayList<Livre>();

        try {
            list_allLivre = livreDao.getList();
            System.out.println("\n ALL BOOKS:\n " + list_allLivre);
            return list_allLivre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list all Books.");
        }
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        ILivreDao livreDao = LivreDao.getInstance();
        List<Livre> list_dispoLivre = new ArrayList<Livre>();
        List<Livre> list_allLivre = new ArrayList<Livre>();
        try {
            list_allLivre = livreDao.getList();
            for(int i=0; i<list_allLivre.size(); i++){
                if(EmpruntService.getInstance().isLivreDispo(list_allLivre.get(i).getKey()))
                    list_dispoLivre.add(list_allLivre.get(i));
            }
            System.out.println("\n ALL AVAILABLE BOOKS:\n " + list_dispoLivre);
            return list_dispoLivre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not list available Books.");
        }
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        ILivreDao livreDao = LivreDao.getInstance();
        Livre livre = new Livre();

        try {
            livre = livreDao.getById(id);
            System.out.println("\n BOOK OF ID "+ id +": " + livre);
            return livre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not get Book by ID.");
        }
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException {
        ILivreDao livreDao =LivreDao.getInstance();

        try {
            int id_l;
            if (titre == null || titre == ""){
                throw new ServiceException("\nTitle is empty. Cannot create Book.");
            } 
            else{
                id_l = livreDao.create(titre, auteur, isbn);
                System.out.println("\n BOOK CREATED WITH ID: " + id_l);
            }
            return id_l;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not create Book.");
        }
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        ILivreDao livreDao = LivreDao.getInstance();

        try {
            if (livre.getTitre() == null || livre.getTitre() == ""){
                throw new ServiceException("\nTitle is empty. Cannot update Book.");
            }
            else{
                livreDao.update(livre);
                System.out.println("\nBOOK WITH ID "+ livre.getKey() +" UPDATED.");
            }
        } catch (Exception | DaoException e) {
            throw new ServiceException("\n Book can't be updated.");
        }
        
    }

    @Override
    public void delete(int id) throws ServiceException {
        ILivreDao livreDao = LivreDao.getInstance();

        try {
            livreDao.delete(id);
            System.out.println("\nBOOK WITH ID "+ id +" DELETED.");
        } catch (Exception | DaoException e) {
            throw new ServiceException("\n Could not delete book.");
        }
        
    }

    @Override
    public int count() throws ServiceException {
        ILivreDao livreDao = LivreDao.getInstance();

        try {
            int count_livre = livreDao.count();
            System.out.println("\n BOOK COUNT: " + count_livre);
            return count_livre;
        } catch (Exception | DaoException e) {
            throw new ServiceException("\nCould not count Books.");
        }
    }
    
}
