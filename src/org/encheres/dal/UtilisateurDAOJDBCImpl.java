package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.encheres.bo.Utilisateur;



public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE id=?";
	
	@Override
	public void insert(Utilisateur user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Utilisateur> selectAll() {
		List<Utilisateur> ListeUsers = new ArrayList<Utilisateur>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				ListeUsers.add(new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postale"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur")
						));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ListeUsers;
	}

	@Override
	public Utilisateur selectById(int id){
		Utilisateur user = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			/*PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				if(premiereLigne) //user
				{
					listeCourse.setId(rs.getInt("id_liste"));
					listeCourse.setNom(rs.getString("nom_liste"));
					premiereLigne=false;
				}
				if(rs.getString("nom_article")!=null) //ligne suivante nom_article
				{
					listeCourse.getArticles().add(new Article(rs.getInt("id_article"), rs.getString("nom_article"), rs.getBoolean("coche")));
				}
				if(rs.getString("nom_article")!=null) //ligne suivante nom_article
				{
					listeCourse.getArticles().add(new Article(rs.getInt("id_article"), rs.getString("nom_article"), rs.getBoolean("coche")));
				}
				
			}*/
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
