package org.encheres.dal;

public abstract class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJDBCImpl();
	}
	
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
			
	}


	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}
	
	public static EnchereDAO getEnchereDAO()
	{
		
		return new EnchereDAOJDBCImpl();
		
		
	}
}
