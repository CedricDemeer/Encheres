package org.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.encheres.bo.Utilisateur;
import org.encheres.dal.DAOFactory;
import org.encheres.dal.UtilisateurDAO;
import org.encheres.exceptions.BusinessException;
import org.encheres.exceptions.DALException;

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
	 * @param pseudo Le no_utilisateur
	 * @param email L'email de l'utilisateur
	 * @return l'utilisateur
	 */
	public Utilisateur selectUtilisateurParPseudoOuEmail(String pseudo, String email) {

		Utilisateur user = null;		
		user = UtilisateurDAO.selectByPseudoOrEmail(pseudo, email);	
		return user;
	}
	
	/**
	 * @param u L'utilisateur a ajouter dans la BDD
	 * @return l'utilisateur
	 * @throws BusinessException 
	 */
	public Utilisateur AjoutUtilisateur(Utilisateur  u) throws BusinessException {
		BusinessException exception =new BusinessException();
		//vérif sur l'utilisateur, les champs obligatoire en BDD


		try {
			UtilisateurDAO.insert(u);
		} catch (DALException e) {

			e.printStackTrace();
			exception.ajouterErreur(e.getMessage());
			throw exception;
		}


		return u;

	}

	/**
	 * 
	 * @param u L'utilisateur � supprimer
	 * @return l'utilisateur
	 * @throws BusinessException
	 */
	public Utilisateur supprimerUtilisateur (Utilisateur u) throws BusinessException {
		BusinessException exception= new BusinessException();


		try {

			UtilisateurDAO.delete(u);


		}catch (DALException e) {
			e.printStackTrace();
			exception.ajouterErreur(e.getMessage());
			throw exception;

		}

		return u;

	}

	/**
	 * 
	 * @param u
	 * @return l'utilisateur
	 * @throws BusinessException
	 */

	public Utilisateur modifierUtilisateur (Utilisateur u) throws BusinessException {
		BusinessException exception= new BusinessException();


		UtilisateurDAO.Update(u);




		return u;
	}


}
