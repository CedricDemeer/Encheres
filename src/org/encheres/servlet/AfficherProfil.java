package org.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Utilisateur;
import org.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet("/AfficherProfil")
public class AfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur user = new Utilisateur();
	private static UtilisateurManager userMng = new UtilisateurManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupérer le pseudo du profil depuis l'url
		String pseudoProfil = request.getParameter("profil");
		
		//récupération des info du user dans la BDD via le manager
		user = userMng.selectUtilisateurParPseudoOuEmail(pseudoProfil, "");
		
		
		//int userID = Integer.parseInt(request.getParameter("id"));
		//user = userMng.selectUtilisateurParID(userID);
		
		//les infos en attribut dans la requête 
		//le user contient toutes les infos issues de la BDD
		request.setAttribute("utilisateur", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AfficherProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
