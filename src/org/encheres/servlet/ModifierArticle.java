package org.encheres.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.bll.ArticleManager;
import org.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class ModifierArticle
 */
@WebServlet("/ModifierArticle")
public class ModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticleVendu art = new ArticleVendu();
    private static ArticleManager artMng = new ArticleManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupérer le pseudo du profil depuis l'url
		int noArticle = Integer.parseInt(request.getParameter("article"));
				
		//récupération des info du user dans la BDD via le manager
		art = artMng.selectArticleParID(noArticle);
		request.setAttribute("article", art);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean test=true;
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
		int miseAPrix = Integer.parseInt(request.getParameter("prixInitial"));
		
	}

}
