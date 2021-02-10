package org.encheres.bll;

import java.time.LocalDate;

import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;
import org.encheres.dal.EnchereDAOJDBCImpl;
import org.encheres.exceptions.BusinessException;

public class Test {

	public static void main(String[] args) throws BusinessException {
		
		
		// TODO Auto-generated method stub

		UtilisateurManager user=new UtilisateurManager();
		Utilisateur utilisateur=new Utilisateur("yoyo12","moise","martin","martin@gmail.com","rue de la paix","22000","quimper","Pa$$w0rd");
		user.AjoutUtilisateur(utilisateur, "Pa$$w0rd");
		
		
		EnchereManager mgr= new EnchereManager();
		Enchere nvlle= new Enchere(LocalDate.now(),30,2,12);
		
		mgr.AjoutEnchere(nvlle);
		
		
	}

}
