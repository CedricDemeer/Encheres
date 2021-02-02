package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;



public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	private static final String SELECT_BY_ID = "select\r\n"
			+ "	u.no_utilisateur as no_user,\r\n"
			+ "	u.pseudo as pseudo,\r\n"
			+ "	u.nom as nom,\r\n"
			+ "	u.prenom as prenom,\r\n"
			+ "	u.email as email,\r\n"
			+ "	u.telephone as tel,\r\n"
			+ "	u.rue as rue,\r\n"
			+ "	u.code_postal as cp,\r\n"
			+ "	u.ville as ville,\r\n"
			+ "	u.mot_de_passe as mdp,\r\n"
			+ "	u.credit as credit,\r\n"
			+ "	u.administrateur as admin,\r\n"
			+ "	a.no_article as no_article,\r\n"
			+ "	a.nom_article as nom_article,\r\n"
			+ "	a.description as description_article,\r\n"
			+ "	a.date_debut_enchere as date_debut_article,\r\n"
			+ "	a.date_fin_enchere as date_fin_article,\r\n"
			+ "	a.prix_initial as miseAPrix_article,\r\n"
			+ "	a.prix_vente as prix_vente_article,\r\n"
			+ "	a.no_utilisateur as no_user_article,\r\n"
			+ "	a.no_categorie as no_cat_article,\r\n"
			+ "	a.etat_vente as stat_article,\r\n"
			+ "	a.image as image_article,\r\n"
			+ "	e.date_enchere as date_enchere,\r\n"
			+ "	e.montant_enchere as montant_enchere,\r\n"
			+ "	e.no_article as no_article_enchere,\r\n"
			+ "	e.no_utilisateur as no_user_enchere\r\n"
			+ "\r\n"
			+ "from (UTILISATEURS u left join ARTICLES_VENDUS a on u.no_utilisateur=a.no_utilisateur) left join ENCHERES e on u.no_utilisateur=e.no_utilisateur\r\n"
			+ "where u.no_utilisateur=?;";
	private static String INSERT= "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	@Override
	public void insert(Utilisateur user) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getNom());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getTelephone());
			pstmt.setString(5, user.getRue());
			pstmt.setString(6, user.getCodePostal());
			pstmt.setString(7, user.getVille());
			pstmt.setString(8, user.getMotDePasse());
			pstmt.setInt(9, user.getCredit());
			pstmt.setBoolean(10, user.isAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				user.setNoUtilisateur(rs.getInt(1));
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
						rs.getString("code_postal"),
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
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne = true;
			while(rs.next())
			{
				if(premiereLigne) //user
				{
					user = new Utilisateur(
							rs.getInt("no_user"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("tel"),
							rs.getString("rue"),
							rs.getString("cp"),
							rs.getString("ville"),
							rs.getString("mdp"),
							rs.getInt("credit"),
							rs.getBoolean("admin"));
					
					premiereLigne=false;
				}
				if(rs.getString("nom_article")!=null) //si il y a un nom article sur la ligne
				{
					user.getListeVentes().add(new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description_article"),
							rs.getDate("date_debut_article"),
							rs.getDate("date_fin_article"),
							rs.getInt("miseAPrix_article"),
							rs.getInt("prix_vente_article"),
							rs.getString("stat_article"),
							rs.getString("image_article"),
							rs.getInt("no_user_article"),
							rs.getInt("no_cat_article")));
					/*int noArticle, String nomArticle, String description, String dateDebutEncheres,
					String dateFinEncheres, int miseAPrix, int prixVente, String etatVente, String image*/
				}
				if(rs.getString("no_user_enchere")!=null) //ligne suivante nom_article
				{
					//listeCourse.getArticles().add(new Article(rs.getInt("id_article"), rs.getString("nom_article"), rs.getBoolean("coche")));
					user.getListeEncheres().add(new Enchere(rs.getDate("date_enchere"), rs.getInt("montant_enchere"), rs.getInt("no_user_enchere"),rs.getInt("no_article_enchere")));
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		return user;
	}

}
