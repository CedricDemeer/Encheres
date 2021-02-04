package org.encheres.bll;

import org.encheres.dal.ArticleDAO;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.UtilisateurDAO;

public class ArticleManager {

	
		private ArticleDAO ArticleDAO;

		/**
		 * Le constructeur permet d'initialiser la variable membre AricleDAO pour 
		 * permettre une communication avec la base de données. 
		 */
		public ArticleManager() {
			this.ArticleDAO=DAOFactory.getArticleDAO();

		}
		
		
		
		
	}


