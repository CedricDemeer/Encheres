package org.encheres.dal;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Enchere;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;

public class ArticleDAOImpl implements ArticleDAO{

	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	
	private static final String SELECT_BY_ID = "SELECT a.no_article,"
			+ "nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente, "
			+ "a.no_utilisateur as no_user, u.pseudo ,a.no_categorie,etat_vente,image,"
			+ "r.rue as arue,r.code_postal as acp,r.ville as aville, c.libelle,"
			+ "e.no_utilisateur as ench_no_utilisateur, e.date_enchere, e.montant_enchere "
			+ "	FROM (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) "
			+ "			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)"
			+ "			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))"
			+ "			WHERE a.no_article=?";
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente)"
			+ "			+ \"     VALUES(?,?,?,?,?,?,?,?,?,?)";
	//a voir pour l'image si on garde 'image' ou non
	/*private static final String INSERT_ARTICLE2 = "INSERT INTO ARTICLES_VENDUS (no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,image)"
			+ "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";*/
	private static final String DELETE_ARTICLE = "delete from ARTICLES_VENDUS where no_article=?";
	private static final String UPDATE_PRIXVENTE_INITIAL_ARTICLE="update ARTICLES_VENDUS set prix_initial=? where no_article=?";
	private static final String UPDATE_PRIXVENTE_FINAL_ARTICLE="update ARTICLES_VENDUS set prix_vente=? where no_article=?";
	private static final String UPDATE_IMAGE_ARTICLE="update ARTICLES_VENDUS set image=? where no_article=?";
	
	
	
	@Override
	public void insert(ArticleVendu article) {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				article.setNoArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateDebutEncheres(rs.getDate(4));
				article.setDateFinEncheres(rs.getDate(5));
				article.setMiseAPrix(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				//manque no_utilisateur
				//manque no_categorie
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
				
	}

	@Override
	public void delete(int noArticle) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArticleVendu selectById(int noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	@Override
	public void update(ArticleVendu article) {
		// on doit pouvoir faire les modif de l'article : 
		//ajout d'une photo
		//ajout du prix de vent initial
		//ajout du prix de vent final
		
	}

	@Override
	public List<ArticleVendu> selectAll() {
		List<ArticleVendu> ListeArticles = new ArrayList<ArticleVendu>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ListeArticles.add(new ArticleVendu(
						rs.getInt("noArticle"),
						rs.getString("nomArticle"),
						rs.getString("description"),
						rs.getDate("date_debut_enchere"),
						
						//je suis en train de le faire 
						
						
							
						));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListeArticles;
	}

	
}
