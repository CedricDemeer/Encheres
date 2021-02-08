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
 * Servlet implementation class SuppProfil
 */
@WebServlet
(name="SuppProfil", urlPatterns = {"/SuppProfil"})
public class SuppProfil extends HttpServlet {
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
		//Création de l'utilisateur
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		
		//envoi a la BLL
		try {
			userMng.supprimerUtilisateur(user);
			
			//si l'utilisateur est bien supprimer on clean la session
			session.removeAttribute("user");
			session.invalidate();
			
			//retour à l'accueil si la suppression s'est effectuée
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Accueil");
			dispatcher.forward(request, response);
		}
		//sinon une exception est levée et l'on reste sur la page
		catch (BusinessException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ModifierProfil");
			dispatcher.forward(request, response);
		}
	}
}
