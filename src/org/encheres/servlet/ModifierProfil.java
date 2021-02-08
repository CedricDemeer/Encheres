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
		String modification = "modif";
		request.setAttribute("modification", modification);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ModifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, password);
		System.out.println(pseudo + " " + nom + " " + prenom);
		//envoi a la BLL
		
		try {
			userMng.modifierUtilisateur(user, confirm_password);
		} catch (BusinessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);
		
	}

}
