package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Categories;
import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;
import org.encheres.exceptions.BusinessException;
import org.encheres.exceptions.DALException;



public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	private static final String SELECT_BY_ID = "select"
			+ "	u.no_utilisateur as no_user,"
			+ "	u.pseudo as pseudo,"
			+ "	u.nom as nom,"
			+ "	u.prenom as prenom,"
			+ "	u.email as email,"
			+ "	u.telephone as tel,"
			+ "	u.rue as rue,"
			+ "	u.code_postal as cp,"
			+ "	u.ville as ville,"
			+ "	u.mot_de_passe as mdp,"
			+ "	u.credit as credit,"
			+ "	u.administrateur as admin,"
			+ "	a.no_article as no_article,"
			+ "	a.nom_article as nom_article,"
			+ "	a.description as description_article,"
			+ "	a.date_debut_enchere as date_debut_article,"
			+ "	a.date_fin_enchere as date_fin_article,"
			+ "	a.prix_initial as miseAPrix_article,"
			+ "	a.prix_vente as prix_vente_article,"
			+ "	a.no_utilisateur as no_user_article,"
			+ "	a.no_categorie as no_cat_article,"
			+ "	a.etat_vente as stat_article,"
			+ "	a.image as image_article,"
			+ "	e.date_enchere as date_enchere,"
			+ "	e.montant_enchere as montant_enchere,"
			+ "	e.no_article as no_article_enchere,"
			+ "	e.no_utilisateur as no_user_enchere,"
			+ "	c.libelle as libelle_cat,"
			+ "	c.no_categorie as no_cat"
			+ " from ((UTILISATEURS u left join ARTICLES_VENDUS a on u.no_utilisateur=a.no_utilisateur) left join ENCHERES e on u.no_utilisateur=e.no_utilisateur)left join CATEGORIES c on a.no_categorie=c.no_categorie"
			+ " where u.no_utilisateur=?;";
	
	private static final String SELECT_BY_PSEUDO_OR_EMAIL = "select"
			+ "	u.no_utilisateur as no_user,"
			//+ "	u.pseudo as pseudo,"
			+ " u.no_utilisateur as no_user,"
			+ "	u.nom as nom,"
			+ "	u.prenom as prenom,"
			//+ "	u.email as email,"
			+ "	u.telephone as tel,"
			+ "	u.rue as rue,"
			+ "	u.code_postal as cp,"
			+ "	u.ville as ville,"
			+ "	u.mot_de_passe as mdp,"
			+ "	u.credit as credit,"
			+ "	u.administrateur as admin,"
			+ "	a.no_article as no_article,"
			+ "	a.nom_article as nom_article,"
			+ "	a.description as description_article,"
			+ "	a.date_debut_enchere as date_debut_article,"
			+ "	a.date_fin_enchere as date_fin_article,"
			+ "	a.prix_initial as miseAPrix_article,"
			+ "	a.prix_vente as prix_vente_article,"
			+ "	a.no_utilisateur as no_user_article,"
			+ "	a.no_categorie as no_cat_article,"
			+ "	a.etat_vente as stat_article,"
			+ "	a.image as image_article,"
			+ "	e.date_enchere as date_enchere,"
			+ "	e.montant_enchere as montant_enchere,"
			+ "	e.no_article as no_article_enchere,"
			+ "	e.no_utilisateur as no_user_enchere,"
			+ "	c.libelle as libelle_cat,"
			+ "	c.no_categorie as no_cat"
			+ " from ((UTILISATEURS u left join ARTICLES_VENDUS a on u.no_utilisateur=a.no_utilisateur) left join ENCHERES e on u.no_utilisateur=e.no_utilisateur)left join CATEGORIES c on a.no_categorie=c.no_categorie"
			+ " where u.pseudo=? OR u.email=?;";
	private static String INSERT= "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static String DELETE= "delete from UTILISATEURS where no_utilisateur=?";
	private static String UPDATE_USER= "update UTILISATEURS set telephone=?, ville=?, administrateur=?, code_postal=?, credit=?, email=?, mot_de_passe=?, nom=?, prenom=?, pseudo=?, rue=? WHERE no_utilisateur=?";
	
	@Override
	public void insert(Utilisateur user) throws DALException, BusinessException {
		BusinessException erreurs = new BusinessException();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getNom());
			pstmt.setString(3, user.getPrenom());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getRue());
			pstmt.setString(7, user.getCodePostal());
			pstmt.setString(8, user.getVille());
			pstmt.setString(9, user.getMotDePasse());
			pstmt.setInt(10, user.getCredit());
			pstmt.setBoolean(11, user.isAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				user.setNoUtilisateur(rs.getInt(1));
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
			if(e.getMessage().contains("utilisateurs_pseudo_uq")) {
				erreurs.ajouterErreur("Pseudo déjà utilisé");
			}
			if(e.getMessage().contains("utilisateurs_email_uq")) {
				erreurs.ajouterErreur("Email déjà  utilisé");
			}
			if (erreurs.hasErreurs()) {
				throw erreurs;
			}
			throw new DALException (e.getMessage());
		}
		
	}

	@Override
	public void delete(Utilisateur user) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, user.getNoUtilisateur());
			pstmt.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
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
					ArticleVendu  a = new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description_article"),
							rs.getDate("date_debut_article").toLocalDate(),
							rs.getDate("date_fin_article").toLocalDate(),
							rs.getInt("miseAPrix_article"),
							rs.getInt("prix_vente_article"),
							rs.getString("stat_article")
							);
					
					a.setImage(rs.getString("image_article"));
					a.setUtilisateur(user);
					a.setCategorie(new Categories(rs.getInt("no_cat"),rs.getString("libelle_cat")));
					user.getListeVentes().add(a);
					
				}
				if(rs.getString("no_user_enchere")!=null) //si il y a une enchere
				{
					user.getListeEncheres().add(new Enchere(rs.getDate("date_enchere"), rs.getInt("montant_enchere"), rs.getInt("no_user_enchere"),rs.getInt("no_article_enchere")));
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		return user;
	}

	@Override
	public void Update(Utilisateur user) throws BusinessException {
		BusinessException erreurs = new BusinessException();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);				
				pstmt.setString(1, user.getTelephone()); //telephone
				pstmt.setString(2, user.getVille()); //ville
				pstmt.setBoolean(3, user.isAdministrateur()); //administrateur
				pstmt.setString(4, user.getCodePostal()); //code_postal
				pstmt.setInt(5, user.getCredit()); //credit
				pstmt.setString(6, user.getEmail()); //email
				pstmt.setString(7, user.getMotDePasse()); //mot_de_passe
				pstmt.setString(8, user.getNom()); //nom
				pstmt.setString(9, user.getPrenom()); //prenom
				pstmt.setString(10, user.getPseudo()); //pseudo
				pstmt.setString(11, user.getRue()); //rue
				pstmt.setInt(11, user.getNoUtilisateur()); //no_utilisateur
				pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			if(e.getMessage().contains("utilisateurs_pseudo_uq")) {
				erreurs.ajouterErreur("Pseudo déjà utilisé");
			}else if(e.getMessage().contains("utilisateurs_email_uq")) {
				erreurs.ajouterErreur("Email déjà  utilisé");
			}else{
				erreurs.ajouterErreur(e.getMessage());
			}
			if (erreurs.hasErreurs()) {
				throw erreurs;
			}
			e.printStackTrace();			
		}
	}

	@Override
	public Utilisateur selectByPseudoOrEmail(String pseudo, String email) {
		Utilisateur user = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO_OR_EMAIL);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne = true;
			while(rs.next())
			{
				if(premiereLigne) //user
				{
					user = new Utilisateur(
							rs.getInt("no_user"),
							//rs.getString("pseudo"),
							pseudo,
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
					ArticleVendu  a = new ArticleVendu(
							rs.getInt("no_article"),
							rs.getString("nom_article"),
							rs.getString("description_article"),
							rs.getDate("date_debut_article").toLocalDate(),
							rs.getDate("date_fin_article").toLocalDate(),
							rs.getInt("miseAPrix_article"),
							rs.getInt("prix_vente_article"),
							rs.getString("stat_article")
							);
					
					a.setImage(rs.getString("image_article"));
					a.setUtilisateur(user);
					a.setCategorie(new Categories(rs.getInt("no_cat"),rs.getString("libelle_cat")));
					user.getListeVentes().add(a);
					
				}
				if(rs.getString("no_user_enchere")!=null) //si il y a une enchere
				{
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
