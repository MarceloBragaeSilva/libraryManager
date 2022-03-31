package com.ensta.librarymanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.persistence.ConnectionManager;
import java.sql.Connection;

public class EmpruntDao implements IEmpruntDao{

    //Singleton
    private static EmpruntDao instance;
    private EmpruntDao(){};
    public static EmpruntDao getInstance(){
        if (instance == null) instance = new EmpruntDao();
        return instance;
    }

    public List<Emprunt> getList() throws DaoException{
        List<Emprunt> list_emprunt = new ArrayList<Emprunt>();

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement,"+
            " idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre"+
            " INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;");
            ResultSet resultSet = stmt.executeQuery();

            MembreDao membreDao = MembreDao.getInstance();
            LivreDao livreDao = LivreDao.getInstance();
            while(resultSet.next()){
                list_emprunt.add(new Emprunt(resultSet.getInt("id"), membreDao.getById(resultSet.getInt("idMembre")), livreDao.getById(resultSet.getInt("idLivre")), resultSet.getDate("dateEmprunt").toLocalDate(), resultSet.getDate("dateRetour") == null ? null : resultSet.getDate("dateRetour").toLocalDate()));
            }
            resultSet.close();
            stmt.close();
            stmt.close();

            return list_emprunt;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public List<Emprunt> getListCurrent() throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,"+
            "dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;");
            ResultSet resultSet = stmt.executeQuery();
            List<Emprunt> listCurrent = new ArrayList<Emprunt>();

            MembreDao membreDao = MembreDao.getInstance();
            LivreDao livreDao = LivreDao.getInstance();

            while(resultSet.next()){
                listCurrent.add(new Emprunt(resultSet.getInt("id"), membreDao.getById(resultSet.getInt("idMembre")), livreDao.getById(resultSet.getInt("idLivre")),
                resultSet.getDate("dateEmprunt").toLocalDate(), resultSet.getDate("dateRetour") == null ? null : resultSet.getDate("dateRetour").toLocalDate()));
            }

            resultSet.close();
            stmt.close();
            connection.close();

            return listCurrent;

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email,"+
            " telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre"+
            " ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;");
            stmt.setInt(1,idMembre);
            ResultSet resultSet = stmt.executeQuery();
            List<Emprunt> listCurrentByMembre = new ArrayList<Emprunt>();

            MembreDao membreDao = MembreDao.getInstance();
            LivreDao livreDao = LivreDao.getInstance();

            while(resultSet.next()){

                listCurrentByMembre.add(new Emprunt(resultSet.getInt("id"), membreDao.getById(resultSet.getInt("idMembre")), livreDao.getById(resultSet.getInt("idLivre")),
                resultSet.getDate("dateEmprunt").toLocalDate(), resultSet.getDate("dateRetour") == null ? null : resultSet.getDate("dateRetour").toLocalDate()));
            }

            resultSet.close();
            stmt.close();
            connection.close();

            return listCurrentByMembre;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email,"+
            " telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e"+
            " INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;");
            stmt.setInt(1,idLivre);
            ResultSet resultSet = stmt.executeQuery();
            List<Emprunt> listCurrentByLivre = new ArrayList<Emprunt>();

            MembreDao membreDao = MembreDao.getInstance();
            LivreDao livreDao = LivreDao.getInstance();

            while(resultSet.next()){

                listCurrentByLivre.add(new Emprunt(resultSet.getInt("id"), membreDao.getById(resultSet.getInt("idMembre")), livreDao.getById(resultSet.getInt("idLivre")),
                resultSet.getDate("dateEmprunt").toLocalDate(), resultSet.getDate("dateRetour") == null ? null : resultSet.getDate("dateRetour").toLocalDate()));
            }

            resultSet.close();
            stmt.close();
            connection.close();

            return listCurrentByLivre;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public Emprunt getById(int id) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone,"+
                " abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();

            MembreDao membreDao = MembreDao.getInstance();
            LivreDao livreDao = LivreDao.getInstance();
            Emprunt empruntId = new Emprunt();

            if(resultSet.next()){
                empruntId = new Emprunt(resultSet.getInt("id"), membreDao.getById(resultSet.getInt("idMembre")), livreDao.getById(resultSet.getInt("idLivre")),
                resultSet.getDate("dateEmprunt").toLocalDate(), resultSet.getDate("dateRetour") == null ? null : resultSet.getDate("dateRetour").toLocalDate());
            }
            resultSet.close();
            stmt.close();
            connection.close();

            return empruntId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Emprunt(idMembre, idLivre,"+
                                        " dateEmprunt, dateRetour) VALUES (?,?,?,?);");
            stmt.setInt(1, idMembre);
            stmt.setInt(2, idLivre);
            stmt.setString(3, dateEmprunt.toString());
            stmt.setDate(4, null);
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }
    
	public void update(Emprunt emprunt) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE Emprunt SET idMembre=?, idLivre=?,"+
                                            "dateEmprunt=?, dateRetour=? WHERE id=?;");
            stmt.setInt(1, emprunt.getMembre().getKey());
            stmt.setInt(2, emprunt.getLivre().getKey());
            stmt.setString(3, emprunt.getDateEmprunt().toString());
            stmt.setString(4, emprunt.getDateRetour().toString());
            stmt.setInt(5, emprunt.getKey());
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public int count() throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(id) AS count FROM emprunt;");
            ResultSet resultSet = stmt.executeQuery();

            int count = -1;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            resultSet.close();
            stmt.close();
            connection.close();

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }       
    }

}
