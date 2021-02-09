package org.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Utilisateur;
import org.encheres.dal.UtilisateurDAO;
import org.encheres.dal.UtilisateurDAOJDBCImpl;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String RememberMe=getCookieValue(request, "user");
		if(RememberMe!=null)
		{
			
			Utilisateur user= new Utilisateur();
			UtilisateurManager utilisateurManager=new UtilisateurManager();
			user=utilisateurManager.selectUtilisateurParPseudo(RememberMe);
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			
			
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			//rd.forward(request, response);
			
			
		} 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp");
		rd.forward(request, response);
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String identifiant;
		String password;
		String remember;
		boolean cnx=false;
	
		
		identifiant=request.getParameter("pseudo");
		password=request.getParameter("password");
		remember=request.getParameter("remember");
		
		
		
		Utilisateur user= new Utilisateur();
		UtilisateurManager utilisateurManager=new UtilisateurManager();

		user=utilisateurManager.selectUtilisateurParPseudo(identifiant);

		if(user!=null) {
			if (user.getPseudo().equals(identifiant) && user.getMotDePasse().equals(password))
			{
				cnx=true;
	
			}
		}

		
		
		
		if(cnx==true) {

			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			
			if(remember!=null) {
			Cookie cookie= new Cookie("user",user.getPseudo());
			cookie.setMaxAge(3600);
			response.addCookie(cookie);}
			
			response.sendRedirect(request.getContextPath() + "/Accueil");
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			//rd.forward(request, response);

		}else
		{
			cnx=true;
			request.setAttribute("bool","identifiant ou mot de passe erroné");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp");
			rd.forward(request, response);

		}

	}



	private static String getCookieValue( HttpServletRequest request, String nom ) {
		Cookie[] cookies = request.getCookies();
		if ( cookies != null ) {
			for ( Cookie cookie : cookies ) {
				if ( cookie != null && nom.equals( cookie.getName() ) ) {
					return cookie.getValue();
				}
			}
		}
		return null;

	}
}








