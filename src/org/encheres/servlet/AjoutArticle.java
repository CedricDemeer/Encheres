package org.encheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ParameterizedSingleTypeReference;
import org.encheres.bll.ArticleManager;
import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Categories;
import org.encheres.bo.Enchere;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;
import org.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class AjoutArticle
 */
@WebServlet("/AjoutArticle")
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
		//récupération et affichage des infos en UTF-8
		request.setCharacterEncoding("UTF-8");
		//récup de variable du formulaire
		
		Boolean test=true;
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		LocalDate dateDebutEncheres = null;
		LocalDate dateFinEncheres = null;
		String Categorie = request.getParameter("Categorie");
		String miseAPrix = request.getParameter("prixInitial");
		
		
		//lire la date
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"),dtf);
			dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"), dtf);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			//listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_DATE_ERREUR);
		}
		
		//création d'un article manager
		ArticleManager ArticleMgr = new ArticleManager();
		
		//création de l'article
		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
				 miseAPrix);
		System.out.println(nomArticle + description);
		//envoi a la BLL
		
		try {
			ArticleMgr.AjoutArticle(article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
			rd.forward(request, response);	
			test=false;
		}
		
		
		if(test==true) {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);
		}
	}

}
