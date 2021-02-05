package org.encheres.bll;

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

	public void Testmanager()
	{
		System.out.println("test");

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

	public Utilisateur modifierUtilisateur (Utilisateur u) throws BusinessException 
	{
		BusinessException exception= new BusinessException();


		UtilisateurDAO.Update(u);

		return u;
	}




	public void validerUtilisateur(Utilisateur u, String motDePasse ) throws BusinessException
	{
		BusinessException BusinessException= new BusinessException();

		if(u.getPseudo().length()>30 || u.getPseudo()=="") {

			BusinessException.ajouterErreur("Pseudo inexistant ou taille > � 30");

		}
		if(u.getNom().length()>30 || u.getNom()=="") {

			BusinessException.ajouterErreur("Nom inexistant ou taille > � 30");

		}

		if(u.getPrenom().length()>30 || u.getPrenom()=="") {

			BusinessException.ajouterErreur("Prenom inexistant ou taille > � 30");

		}

		if(u.getEmail().length()>20 || u.getEmail()=="") {

			BusinessException.ajouterErreur("Email inexistant ou taille > � 30");

		}

		if(u.getRue().length()>30 || u.getRue()=="") {

			BusinessException.ajouterErreur("Rue inexistant ou taille > � 30");

		}
		if(u.getCodePostal().length()>10 || u.getCodePostal()=="") {

			BusinessException.ajouterErreur("CodePostale inexistant ou taille > � 30");

		}
		if(u.getVille().length()>30 || u.getVille()=="") {

			BusinessException.ajouterErreur("Pseudo inexistant ou taille > � 30");


		}	
		if(!u.getMotDePasse().equals(motDePasse)) {

			BusinessException.ajouterErreur("Mot de passe diff�rent");

		}


		if (BusinessException.hasErreurs())
		{

			throw BusinessException;

		}


		AjoutUtilisateur(u);

	}

	
	

}
