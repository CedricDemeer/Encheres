package org.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Utilisateur;
import org.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager userMng = new UtilisateurManager();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ModifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//récup de variable du formulaire
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("lastname");
		String prenom = request.getParameter("firstname");
		String email = request.getParameter("email");
		String telephone = request.getParameter("phone");
		String rue = request.getParameter("street");
		String code_postal = request.getParameter("zipcode");
		String ville = request.getParameter("city");
		String oldPassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String confirm_password= request.getParameter("confirm_password");
		
		//création de l'utilisateur
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		if(user.getPrenom() != prenom) {
			user.setPrenom(prenom);
		}
		
		if(user.getNom() != nom) {
			user.setNom(nom);
		}
		
		if(user.getPseudo() != pseudo) {
			user.setPseudo(pseudo);
		}
		
		if(user.getEmail() != email) {
			user.setEmail(email);
		}
		
		if(user.getTelephone() != telephone) {
			user.setTelephone(telephone);
		}
		
		if(user.getRue() != rue) {
			user.setRue(rue);
		}
		
		if(user.getCodePostal() != code_postal) {
			user.setCodePostal(code_postal);
		}
		
		if(user.getVille()!= ville) {
			user.setVille(ville);
		}

		if(user.getMotDePasse() != password) {
			user.setMotDePasse(password);
		}
		
		
		System.out.println(pseudo + " " + nom + " " + prenom);
		//envoi a la BLL
		
		try {
			userMng.modifierUtilisateur(user, password);
		} catch (BusinessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
		}
		
		System.out.println(user.toString());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);
		
	}

}
