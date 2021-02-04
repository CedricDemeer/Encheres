package org.encheres.bll;

import org.encheres.bo.ArticleVendu;
import org.encheres.dal.ArticleDAO;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.UtilisateurDAO;
import org.encheres.exceptions.BusinessException;
import org.encheres.exceptions.DALException;

public class ArticleManager {

	
		private ArticleDAO ArticleDAO;

		/**
		 * Le constructeur permet d'initialiser la variable membre AricleDAO pour 
		 * permettre une communication avec la base de données. 
		 */
		public ArticleManager() {
			this.ArticleDAO=DAOFactory.getArticleDAO();

		}

		public void AjoutArticle(ArticleVendu article) throws BusinessException{
			//BusinessException exception =new BusinessException();
			//vérif sur l'utilisateur, les champs obligatoire en BDD


			ArticleDAO.insert(article);


		}
		
		
		
		
	}


