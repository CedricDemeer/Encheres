package org.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.bll.ArticleManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class SuppArticle
 */
@WebServlet("/SuppArticle")
public class SuppArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final ArticleManager artMng = new ArticleManager();
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupérer l'article depuis l'url
		int noArticle = Integer.parseInt(request.getParameter("numArticle"));
		System.out.println(noArticle);
				
			
		ArticleVendu art = artMng.selectArticleParID(noArticle);
		
		//envoi a la BLL
				try {
					artMng.supprimerArticle(art);
					//retour à l'accueil si la suppression s'est effectuée
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Accueil");
					dispatcher.forward(request, response);
				}
				//sinon une exception est levée et l'on reste sur la page
				catch (BusinessException e) {
					e.printStackTrace();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/AjoutArticle");
					dispatcher.forward(request, response);
				}
	}

}
