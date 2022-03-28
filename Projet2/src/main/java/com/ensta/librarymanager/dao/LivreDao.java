package com.ensta.librarymanager.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;


public class LivreDao implements ILivreDao{

    //Singleton
    private static LivreDao instance;
    private LivreDao(){};
    public static LivreDao getInstance(){
        if (instance == null) instance = new LivreDao();
        return instance;
    }

    public List<Livre> getList() throws DaoException{
        try{
            List<Livre> livres = new ArrayList<Livre>();

            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT id, titre, auteur, isbn FROM livre;");
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String auteur = resultSet.getString("auteur");
            String isbn = resultSet.getString("isbn");
            livres.add(new Livre(id,titre, auteur, isbn));
            }

            resultSet.close();
            stmt.close();
            connection.close();

            return livres;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

	public Livre getById(int id) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();

            Livre livreId = new Livre();
            resultSet.next();
            int id_1 = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String auteur = resultSet.getString("auteur");
            String isbn = resultSet.getString("isbn");
            livreId = new Livre(id_1,titre, auteur, isbn);
            
            resultSet.close();
            stmt.close();
            connection.close();

            return livreId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }


	public int create(String titre, String auteur, String isbn) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)"
                                    ,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, titre);
            stmt.setString(2, auteur);
            stmt.setString(3, isbn);
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            int id=-1;
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }
            stmt.close();
            connection.close();

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

    }
	public void update(Livre livre) throws DaoException{
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;");
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getIsbn());
            stmt.setInt(4, livre.getKey());
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
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM livre WHERE id = ?;");
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

            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(id) AS count FROM livre;");
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
