package org.encheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.bll.ArticleManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Categories;
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
		
		int miseAPrix = Integer.parseInt(request.getParameter("prixInitial"));
		ArticleVendu art = new ArticleVendu();
		art.setNomArticle(nomArticle);
		art.setDescription(description);
		art.setMiseAPrix(miseAPrix);
		art.setEtatVente("CR");
		
		//A gerer dans jsp + ici
		art.setImage("");
		
		int no_cat = 0;
		String libelle = null;
		
		switch (request.getParameter("Categorie")) {
		case "Ameublement":
			no_cat = 2;
			libelle = "Ameublement";
			break;
		case "Informatique":
			no_cat = 1;
			libelle = "Informatique";
		break;
		case "SportEtLoisirs":
			no_cat = 4;
			libelle = "Sport & Loisir";
		break;
		case "Vetement":
			no_cat = 3;
			libelle = "Vêtement";
		break;

		default:
			break;
		}
		//lever une exception
		
		if(no_cat != 0) 
		{
			Categories cat = new Categories(no_cat, libelle);
			
		
		//on récupère le no_categorie contenu dans cat pour le mettre dans notre article
		
		
		Retrait lieuRetrait = new Retrait(request.getParameter("street"), request.getParameter("zipcode"), request.getParameter("city"));
		
		//on récupère les infos du lieuRetrait pour le mettre dans notre article
		
		art.setLieuRetrait(lieuRetrait);
		
		art.setCategorie(cat);
		
		Utilisateur user = new Utilisateur();
		
		HttpSession session = request.getSession();
		user = (Utilisateur) session.getAttribute("user");
		art.setUtilisateur(user);
		
		System.out.println(request.getParameter("dateDebutEncheres"));
		
		//lire la date
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"),dtf);
			dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"), dtf);
			art.setDateDebutEncheres(dateDebutEncheres);
			art.setDateFinEncheres(dateFinEncheres);
			
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			//listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_DATE_ERREUR);
		}
		
		//création d'un article manager
		ArticleManager ArticleMgr = new ArticleManager();
		
		
		
		System.out.println(nomArticle + description);
		//envoi a la BLL
		
		try {
			ArticleMgr.AjoutArticle(art);
		} catch (BusinessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
			rd.forward(request, response);	
			test=false;
		}
		}
		
		if(test==true) {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);
		}
	}

}
