package org.encheres.bll;

import org.encheres.bo.Enchere;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.EnchereDAO;
import org.encheres.dal.UtilisateurDAO;
import org.encheres.exceptions.BusinessException;

public class EnchereManager {
	private EnchereDAO EnchereDAO;
	
	public EnchereManager() {
		
		this.EnchereDAO=DAOFactory.getEnchereDAO();
		
	}
	
	public void AjoutEnchere( Enchere e) {
		
		
		try {
			EnchereDAO.insert(e);
			
			
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void modifierArticle (int n_Utilisateur, int n_article, int nouvelle_enchere) {
		
		try {
			
			EnchereDAO.update(n_Utilisateur, n_article, nouvelle_enchere);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
