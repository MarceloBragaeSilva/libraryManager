package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDao implements IMembreDao{

    //Singleton
    private static MembreDao instance;
    private MembreDao(){};
    public static MembreDao getInstance(){
        if (instance == null)   
            instance = new MembreDao();
        return instance;
    }

    public List<Membre> getList() throws DaoException{
        List<Membre> membres = new ArrayList<Membre>();

        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT id, nom, prenom, adresse, email,"+
                " telephone, abonnement FROM membre ORDER BY nom, prenom;");
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String email = resultSet.getString("email");
            String tel = resultSet.getString("telephone");
            String adr = resultSet.getString("adresse");
            String abon = resultSet.getString("abonnement");
            membres.add(new Membre(id,nom, prenom, email, tel, adr, Abonnement.valueOf(abon)));
            }
            

            resultSet.close();
            stmt.close();
            connection.close();

            return membres;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    

    }
	public Membre getById(int id) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT id, nom, prenom, adresse,"+
            " email, telephone, abonnement FROM membre WHERE id = ?;");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();

            Membre membreId = new Membre();
            resultSet.next();
            
            int id_1 = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String email = resultSet.getString("email");
            String tel = resultSet.getString("telephone");
            String adr = resultSet.getString("adresse");
            String abon = resultSet.getString("abonnement");
            membreId = new Membre(id_1,nom, prenom, email, tel, adr, Abonnement.valueOf(abon));
            
            resultSet.close();
            stmt.close();
            connection.close();

            return membreId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO membre(nom, prenom, adresse,"+
            " email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, adresse);
            stmt.setString(4, email);
            stmt.setString(5, telephone);
            stmt.setString(6, abonnement.toString());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            int id=-1;
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }
            resultSet.close();
            stmt.close();
            connection.close();

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }
	public void update(Membre membre) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE membre SET nom = ?, prenom = ?,"+
            " adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;");
            
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getAdresse());
            stmt.setString(4, membre.getEmail());
            stmt.setString(5, membre.getTelephone());
            stmt.setString(6, membre.getAbonnement().toString());
            stmt.setInt(7, membre.getKey());
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

    }

	public void delete(int id) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM membre WHERE id=?;");
            stmt.setInt(1, id);
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

            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(id) AS count FROM membre;");
            ResultSet resultSet = stmt.executeQuery();

            int count = -1;
            if(resultSet.next()){
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
