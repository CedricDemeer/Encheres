package org.encheres.bll;

import java.util.List;

import org.encheres.bo.Utilisateur;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO UtilisateurDAO;
	
	/**
	 * Le constructeur permet d'initialiser la variable membre UtilisateurDAO pour 
	 * permettre une communication avec la base de données. 
	 */
	public UtilisateurManager() {
		this.UtilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	/**
	 * 
	 * @return la nouvelle liste créer avec les Utilisateurs
	 */
	public List<Utilisateur> selectToutLesUtilisateur() {
		return UtilisateurDAO.selectAll();
	}
	
	/**
	 * @param id Le no_utilisateur
	 * @return l'utilisateur
	 */
	public Utilisateur selectUtilisateurParID(int id) {
		Utilisateur user = null;
		if (id>=0) {
			user = UtilisateurDAO.selectById(id);
		}else {
			//erreur, ID négatif
		}
		return user;
	}
	/**
	 * @param u L'utilisateur a ajouter dans la BDD
	 * @return l'utilisateur
	 */
	public Utilisateur AjoutUtilisateur(Utilisateur  u) {
		//vérif sur l'utilisateur, les champs obligatoire en BDD
		
		
		UtilisateurDAO.insert(u);
		
		return u;
		
	}
	
}
