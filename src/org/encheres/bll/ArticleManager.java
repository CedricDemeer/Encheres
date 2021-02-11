package org.encheres.bll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.encheres.bo.ArticleVendu;
import org.encheres.dal.ArticleDAO;
import org.encheres.dal.DAOFactory;
import org.encheres.exceptions.BusinessException;
import org.encheres.exceptions.DALException;


public class ArticleManager {

	
		private ArticleDAO articleDAO;

		/**
		 * Le constructeur permet d'initialiser la variable membre AricleDAO pour 
		 * permettre une communication avec la base de données. 
		 */
		public ArticleManager() {
			this.articleDAO=DAOFactory.getArticleDAO();

		}

		public void AjoutArticle(ArticleVendu article) throws BusinessException{
			//BusinessException exception =new BusinessException();
			//vérif sur l'utilisateur, les champs obligatoire en BDD

			articleDAO.insert(article);

		}
		
		public void ModifierArticle (ArticleVendu article) throws BusinessException {
			
			articleDAO.update(article);
		}
		
		public ArticleVendu selectArticleParID (int id) {
			ArticleVendu art = null;
			if(id>=0) {
				art = articleDAO.selectById(id);
			}
			else {
				System.out.println("Vous souhaitez afficher un article dont l'id est négatif.");
			}
			return art;
			
		}
		
		public List<ArticleVendu> getListArticle(){
			return articleDAO.selectAll();
		}
		
		
		public ArticleVendu supprimerArticle (ArticleVendu art) throws BusinessException {
	        BusinessException exception= new BusinessException();
	        try {
	            articleDAO.delete(art.getNoArticle());
	        }catch (DALException e) {
	            e.printStackTrace();
	            exception.ajouterErreur(e.getMessage());
	            throw exception;
	        }
	        return art;
	    }
		
		
		
		
		
		
		
		
		public static void receiveFile(InputStream is, File dest) throws IOException {
	    	
	        FileOutputStream fos = null;
	        try {
	            fos = new FileOutputStream(dest);
	            byte[] buffer = new byte[32 * 1024];
	            int len;
	            while ((len = is.read(buffer)) > -1)
	            	fos.write(buffer, 0, len);
	        } catch(IOException e) {
	        	dest.delete();
	        } finally {
	        	if (fos != null)
	        		try { fos.close(); } catch (IOException ignored) {};
	        	if (is != null)
	        		try { is.close(); } catch (IOException ignored) {};
	        }
		}
		
	}


