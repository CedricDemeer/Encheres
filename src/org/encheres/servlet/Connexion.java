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

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String identifiant;
		String password;
		boolean cnx=false;
		Cookie[] cookies = request.getCookies(); 
		Utilisateur user= new Utilisateur();

		identifiant=request.getParameter("pseudo");
		password=request.getParameter("password");
		List<Utilisateur> utilisateurs=new ArrayList<>();
		UtilisateurManager utilisateurManager=new UtilisateurManager();

		utilisateurs=utilisateurManager.selectToutLesUtilisateur();

		


		for (int i=0;i<utilisateurs.size();i++)
		{
			String u1=utilisateurs.get(i).getPseudo().trim();
			String u2=utilisateurs.get(i).getMotDePasse().trim();

			if (u1.equals(identifiant) && u2.equals(password)) {

				cnx=true;
				user=utilisateurs.get(i);

			}
		}


		if (cnx==true)
		{

			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rd.forward(request, response);



		}else
		{
			cnx=true;
			request.setAttribute("bool","identifiant ou mot de passe erron�");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp");
			rd.forward(request, response);

		}

	
		
		Cookie unCookie= new Cookie("pseudo",user.getPseudo());
		unCookie.setMaxAge(-1);     
		response.addCookie(unCookie);   

		for(Cookie unCookie1:cookies)    {    
			if (unCookie1.getName()=="pseudo") {

				user=utilisateurManager.selectUtilisateurParPseudo(unCookie1.getValue());
				HttpSession session=request.getSession();
				session.setAttribute("user", user);


			}
		}

	}
	
}






