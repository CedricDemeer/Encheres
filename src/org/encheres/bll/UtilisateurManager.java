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
	
	public Utilisateur selectUtilisateurParPseudo(String pseudo) {
        
        return selectUtilisateurParPseudoOuEmail(pseudo, "pasdemail");
    }
    public  Utilisateur selectUtisateurParEmail(String email) {
        return selectUtilisateurParPseudoOuEmail("pasdepseudo", email);
    }
	
	
	
	/**
	 * @param u L'utilisateur a ajouter dans la BDD
	 * @return l'utilisateur
	 * @throws BusinessException 
	 */
	public Utilisateur AjoutUtilisateur(Utilisateur  u, String motDePasse) throws BusinessException {
		BusinessException exception =new BusinessException();
		//vérif sur l'utilisateur, les champs obligatoire en BDD

		this.validerUtilisateur(u, exception);
		this.motDePasseidentique(u.getMotDePasse(), motDePasse, exception);


		if(exception.hasErreurs())
		{
			throw exception;

		}
		UtilisateurDAO.insert(u);


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

		BusinessException exception =new BusinessException();
		//vérif sur l'utilisateur, les champs obligatoire en BDD
		this.validerUtilisateur(u, exception);


		if(exception.hasErreurs())
		{
			throw exception;

		}

	

		UtilisateurDAO.Update(u);

		return u;
	}




	private  void validerUtilisateur(Utilisateur u,  BusinessException BusinessException ) 
	{




		if(!is_Valid_AlphaNumeric(u.getPseudo())){

			BusinessException.ajouterErreur("Pseudo inexistant ou taille > à 30 ou ne contentant pas un caractère numérique ou alpha");

		}


		if(u.getNom().length()>30 || u.getNom()=="") {

			BusinessException.ajouterErreur("Nom inexistant ou taille > à 30");

		}

		if(u.getPrenom().length()>30 || u.getPrenom()=="") {

			BusinessException.ajouterErreur("Prenom inexistant ou taille > à 30");

		}

		if(u.getEmail().length()>20 || u.getEmail()=="") {

			BusinessException.ajouterErreur("Email inexistant ou taille > à 30");

		}

		if(u.getRue().length()>30 || u.getRue()=="") {

			BusinessException.ajouterErreur("Rue inexistant ou taille > à 30");

		}
		if(u.getCodePostal().length()>10 || u.getCodePostal()=="") {

			BusinessException.ajouterErreur("CodePostale inexistant ou taille > à 30");

		}
		if(u.getVille().length()>30 || u.getVille()=="") {

			BusinessException.ajouterErreur("Ville inexistant ou taille > à 30");


		}	

		if(!is_Valid_Password(u.getMotDePasse())) {

			BusinessException.ajouterErreur("Format du mot de Passe invalide il faut ou moins un caratère numérique et un alpha");


		}


	}


	private void motDePasseidentique (String motPasse, String ConfirmMotDepasse, BusinessException BusinessException) {

		if(!motPasse.equals(ConfirmMotDepasse)) {

			BusinessException.ajouterErreur("Mot de passe différent");

		}

	}



	
	private static boolean is_Valid_AlphaNumeric (String mot){
		
		if (mot.length() <1 || mot.length()> 30) return false;



		int charCount = 0;
		int numCount = 0;
		
		for (int i = 0; i < mot.length(); i++) {

			char ch = mot.charAt(i);

			if (is_Numeric(ch)) numCount++;
			else if (is_Letter(ch)) charCount++;
			
			else return false;
		}

		return (charCount >= 1 && numCount >= 1);
		
		
		
	}
	

	private static boolean is_Valid_Password(String password) {

		if (password.length() <1 || password.length()> 30) return false;



		int charCount = 0;
		int numCount = 0;
		int csCount=0;
		for (int i = 0; i < password.length(); i++) {

			char ch = password.charAt(i);

			if (is_Numeric(ch)) numCount++;
			else if (is_Letter(ch)) charCount++;
			else if (is_caracteres(ch)) csCount++;
			else return false;
		}

		return (charCount >= 1 && numCount >= 1);
	}



	private static boolean is_Letter(char ch) {
		ch = Character.toUpperCase(ch);
		return (ch >= 'A' && ch <= 'Z');
	}



	private static boolean is_Numeric(char ch) {

		return (ch >= '0' && ch <= '9');
	}



	private static boolean is_caracteres (char ch) {

		int count=0;
		String CS="=$£&/*-+=";


		for (int j=0;j<CS.length();j++) {

			if (ch==CS.charAt(j))
			{

				count++;

			}
		}


		if (count>0)
		{

			return true;
		}
		else {

			return false;
		}
	}




}


