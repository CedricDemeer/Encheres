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
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inscription = "ins";
		request.setAttribute("inscription", inscription);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récup de variable du formulaire
				Boolean test=true;
				String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("lastname");
				String prenom = request.getParameter("firstname");
				String email = request.getParameter("email");
				String telephone = request.getParameter("phone");
				String rue = request.getParameter("street");
				String code_postal = request.getParameter("zipcode");
				String ville = request.getParameter("city");
				String password = request.getParameter("password");
				String confirm_password= request.getParameter("confirm_password");
				//création du utilisateur manager
				UtilisateurManager UserMgr = new UtilisateurManager();
				//création de l'utilisateur
				Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, password);
				System.out.println(pseudo+ nom + prenom);
				//envoi a la BLL
				
				
				
				try {
					UserMgr.AjoutUtilisateur(user);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					request.setAttribute("erreurs", e.getListeMessagesErreur());
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Inscription.jsp");
					rd.forward(request, response);	
					test=false;
				}
				
				
				
				if(test==true) {
				
					//enregistrement dans la session
					HttpSession session=request.getSession();
					session.setAttribute("user", user);
					
					//redirection si pas d'exceptions
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
					rd.forward(request, response);
				}
				
	}

}
