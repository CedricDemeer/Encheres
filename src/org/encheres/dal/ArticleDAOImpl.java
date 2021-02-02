package org.encheres.dal;

import org.encheres.bo.ArticleVendu;

public class ArticleDAOImpl implements ArticleDAO{

	private static final String SELECT_ALL = "SELECT no_article, nom_article FROM ARTICLES_VENDUS";
	private static final String SELECT_BY_ID = "SELECT nom_article, description,"
			+ "date_debut_enchere, "
			+ "date_fin_enchere,"
			+ "prix_initial,"
			+ "prix_vente,"
			+ "no_utilisateur,"
			+ "no_categorie,"
			+ "etat_vente,"
			+ "image"
			+ "FROM ARTICLES_VENDUS"
			+ "WHERE no_article=?";
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente)"
			+ "			+ \"     VALUES(?,?,?,?,?,?,?,?,?,?)";
	//a voir pour l'image si on garde 'image' ou non
	/*private static final String INSERT_ARTICLE2 = "INSERT INTO ARTICLES_VENDUS (no_article,nom_article,description,date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,image)"
			+ "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";*/
	private static final String DELETE_ARTICLE = "delete from ARTICLES_VENDUS where no_article=?";
	private static final String UPDATE_DATEVENTE_ARTICLE="update ARTICLES_VENDUS set date_fin_enchere=? where no_article=?";
	private static final String UPDATE_PRIXVENTE_ARTICLE="update ARTICLES_VENDUS set prix_vente=? where no_article=?";
	private static final String UPDATE_IMAGE_ARTICLE="update ARTICLES_VENDUS set image=? where no_article=?";
	
	
	
	@Override
	public void insert(ArticleVendu article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ArticleVendu article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticleVendu selectById(int noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noArticle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prixVenteFinal(int noArticle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dateVenteFinale(int noArticle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajoutImageArticle(int noArticle) {
		// TODO Auto-generated method stub
		
	}

	
}
