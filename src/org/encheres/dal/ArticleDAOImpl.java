package org.encheres.dal;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Categories;
import org.encheres.bo.Enchere;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;

public class ArticleDAOImpl implements ArticleDAO{

	private static final String SELECT_ALL = "SELECT "
			+ "a.no_article as no_article,"
			+ "a.nom_article as nom_article,"
			+ "a.description as description_article,"
			+ "a.date_debut_enchere as debut_enchere_article,"
			+ "a.date_fin_enchere as fin_enchere_article,"
			+ "a.prix_initial as prix_initial_article,"
			+ "a.prix_vente as prix_vente_article,"
			+ "a.etat_vente as etat_article,"
			+ "a.image as image_article,"
			+ "c.no_categorie as no_categorie,"
			+ "c.libelle as libelle,"
			+ "u.no_utilisateur as num_user,"
			+ "u.pseudo as pseudo_user,"
			+ "r.code_postal as cp_retraits,"
			+ "r.rue as rue_retraits,"
			+ "r.ville as ville_retraits,"
			+ "e.date_enchere as date_enchere,"
			+ "e.montant_enchere as montant_enchere"
			+ " FROM ARTICLES_VENDUS a "
			+ "LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie "
			+ "LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "LEFT JOIN RETRAITS r ON a.no_article = r.no_article "
			+ "LEFT JOIN ENCHERES e ON (a.no_article = e.no_article "
			+ "AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))";
			
	
	private static final String SELECT_BY_ID = "SELECT "
			+ "a.no_article as no_article,"
			+ "nom_article,"
			+ "description as description_article,"
			+ "date_debut_enchere as debut_enchere_article,"
			+ "date_fin_enchere as fin_enchere_article,"
			+ "prix_initial as prix_initial_article,"
			+ "prix_vente as prix_vente_article, "
			+ "u.no_utilisateur as num_user, "
			+ "u.pseudo as pseudo_user,"
			+ "a.no_categorie as no_categorie,"
			+ "etat_vente as etat_article,"
			+ "image as image_article,"
			+ "r.rue as rue_retraits,"
			+ "r.code_postal as cp_retraits,"
			+ "r.ville as ville_retraits, "
			+ "c.libelle as libelle,"
			+ "e.no_utilisateur as ench_no_utilisateur, "
			+ "e.date_enchere as ench_date, "
			+ "e.montant_enchere as ench_montant"
			+ "	FROM (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) "
			+ "			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)"
			+ "			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))"
			+ "			WHERE a.no_article=?";
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,image)"
			+ "     VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_ARTICLE = "delete from ARTICLES_VENDUS where no_article=?";
	//private static final String UPDATE_PRIXVENTE_INITIAL_ARTICLE="update ARTICLES_VENDUS set prix_initial=? where no_article=?";
	//private static final String UPDATE_PRIXVENTE_FINAL_ARTICLE="update ARTICLES_VENDUS set prix_vente=? where no_article=?";
	//private static final String UPDATE_IMAGE_ARTICLE="update ARTICLES_VENDUS set image=? where no_article=?";
	private static String UPDATE_ARTICLE= "update ARTICLES_VENDUS set date_debut_enchere=?, date_fin_enchere=?, description=?, etat_vente=?, image=?, nom_article=?, prix_initial=?, prix_vente=? where no_article=?";
	private static final String INSERT_LIEU_RETRAIT = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES(?,?,?,?)";
	private static final String UPDATE_LIEU_RETRAIT ="UPDATE RETRAITS set rue=?,code_postal=?,ville=? WHERE no_article = ?";
	
	
    @Override
    public void update(ArticleVendu article) {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            try
            {
                PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);                
                pstmt.setDate(1, java.sql.Date.valueOf(article.getDateDebutEncheres())); //date_debut_enchere
                pstmt.setDate(2, java.sql.Date.valueOf(article.getDateFinEncheres())); //date_fin_enchere
                pstmt.setString(3, article.getDescription()); //description
                pstmt.setString(4, article.getEtatVente()); //etat_vente
                pstmt.setString(5, article.getImage()); //image
                pstmt.setString(6, article.getNomArticle()); //nom_article
                pstmt.setInt(7, article.getMiseAPrix()); //prix_initial
                pstmt.setInt(8, article.getPrixVente()); //prix_vente
                pstmt.setInt(9, article.getNoArticle()); //no_article
                updateRetrait(article);
                pstmt.executeUpdate();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                cnx.rollback();
                throw e;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();            
        }
    }
	
    private void updateRetrait (ArticleVendu article) {
    	try(Connection cnx = ConnectionProvider.getConnection())
        {
            try
            {
                PreparedStatement pstmt = cnx.prepareStatement(UPDATE_LIEU_RETRAIT);                
                pstmt.setString(1, article.getLieuRetrait().getRue()); 
                pstmt.setString(2, article.getLieuRetrait().getCode_postal()); 
                pstmt.setString(3, article.getLieuRetrait().getVille()); 
                pstmt.setInt(4, article.getNoArticle()); 
                
                pstmt.executeUpdate();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                cnx.rollback();
                throw e;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();            
        }
	}
    
	@Override
	public void insert(ArticleVendu article) {
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			//cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
									
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getPrixVente());
				pstmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(8, article.getCategorie().getNo_categorie());
				pstmt.setString(9, article.getEtatVente());
				pstmt.setString(10, article.getImage());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next())
				{
					article.setNoArticle(rs.getInt(1));
					System.err.println(rs.getInt(1));
					
					insertRetrait(article);
					
					
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertRetrait (ArticleVendu article) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			//cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_LIEU_RETRAIT);
			pstmt.setInt(1, article.getNoArticle());
			pstmt.setString(2, article.getLieuRetrait().getRue());
			pstmt.setString(3, article.getLieuRetrait().getCode_postal());
			pstmt.setString(4, article.getLieuRetrait().getVille());
			
			pstmt.executeUpdate();
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
		ArticleVendu item = new ArticleVendu();
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne = true;
			
			while(rs.next())
			{
				if(premiereLigne) {
					item = ArticleBuilder(rs);
					premiereLigne = false;
				}
				//si il y a bien un utilisateur (normalement oui)
				if(rs.getString("num_user")!=null) {
					item.setUtilisateur(userBuilder(rs));
				}
				//si il y a bien un lieu de retrait
				//Alors on récupère le RETRAIT
				if(rs.getString("rue_retraits")!=null) {
					item.setLieuRetrait(retraitBuilder(rs));
				}
				
				//si il y a bien un catégorie
				//alors on récupère cette Catégorie
				if(rs.getString("libelle")!=null) {
					item.setCategorie(categorieBuilder(rs));
				}
				
				if(rs.getString("ench_no_utilisateur")!=null) //si il y a une enchere
				{
					/*item.getListeEncheres().add(
							new Enchere(
									rs.getDate("ench_date").toLocalDate(), 
									rs.getInt("ench_montant"), 
									rs.getInt("ench_no_utilisateur"),
									rs.getInt("no_article")
									));*/
					item.setEnchere(new Enchere(
									rs.getDate("ench_date").toLocalDate(), 
									rs.getInt("ench_montant"), 
									rs.getInt("ench_no_utilisateur"),
									rs.getInt("no_article")));
				}
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}

	@Override
	public List<ArticleVendu> selectAll() {
		List<ArticleVendu> ListeArticles = new ArrayList<ArticleVendu>();
		ArticleVendu ArticleConsulte=new ArticleVendu();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				ArticleConsulte = ArticleBuilder(rs);
				
				
				//ici il faut ajouter la catégorie , l'utilisateur et le lieu de retrait
				//à chaque article.
				ArticleConsulte.setCategorie(categorieBuilder(rs));
				ArticleConsulte.setUtilisateur(userBuilder(rs));
				ArticleConsulte.setLieuRetrait(retraitBuilder(rs));
				if(rs.getDate("date_enchere")!=null) {
					ArticleConsulte.setEnchere(encherBuilder(rs));
				}
				
				
				ListeArticles.add(ArticleConsulte);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ListeArticles;
	}

	private Enchere encherBuilder(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		enchere.setNo_article(rs.getInt("no_article"));
		enchere.setNo_utilisateur(rs.getInt("num_user"));
		enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
		enchere.setMontant_enchere(rs.getInt("montant_enchere"));		
		
		
		return enchere;
	}

	private ArticleVendu ArticleBuilder(ResultSet rs) throws SQLException {
		ArticleVendu article = new ArticleVendu();
		article.setNoArticle(rs.getInt("no_article"));
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description_article"));
		article.setDateDebutEncheres(rs.getDate("debut_enchere_article").toLocalDate());
		article.setDateFinEncheres(rs.getDate("fin_enchere_article").toLocalDate());
		article.setMiseAPrix(rs.getInt("prix_initial_article"));
		article.setPrixVente(rs.getInt("prix_vente_article"));
		article.setEtatVente(rs.getString("etat_article"));
		article.setImage(rs.getString("image_article"));

		return article;
	}

	private Categories categorieBuilder(ResultSet rs) throws SQLException {
		Categories categorie = new Categories(rs.getInt("no_categorie"), rs.getString("libelle"));
		return categorie;
	}
	
	private Utilisateur userBuilder(ResultSet rs) throws SQLException {
		
		Utilisateur user;
		user = new Utilisateur();
		user.setNoUtilisateur(rs.getInt("num_user"));
		user.setPseudo(rs.getString("pseudo_user"));
		
		return user;
	}
	
	private Retrait retraitBuilder(ResultSet rs) throws SQLException {
		Retrait retrait = new Retrait(rs.getString("rue_retraits"), rs.getString("cp_retraits"), rs.getString("ville_retraits"));
		return retrait;
	}
}
