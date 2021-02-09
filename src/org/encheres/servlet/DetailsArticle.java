package org.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.bll.ArticleManager;
import org.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class DetailsArticle
 */
@WebServlet("/DetailsArticle")
public class DetailsArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVendu art = new ArticleVendu();
	private static ArticleManager artMng = new ArticleManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupérer l'article depuis l'url
		int noArticle = Integer.parseInt(request.getParameter("numArticle"));
				System.out.println(noArticle);
		//récupération des info du user dans la BDD via le manager
		art = artMng.selectArticleParID(noArticle);
		
		//les infos en attribut dans la requête 
		//le user contient toutes les infos issues de la BDD
		request.setAttribute("article", art);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/DetailsArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
