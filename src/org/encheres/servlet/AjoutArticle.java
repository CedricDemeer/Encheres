package org.encheres.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.encheres.bll.ArticleManager;
import org.encheres.bll.CategorieManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Categories;
import org.encheres.bo.Retrait;
import org.encheres.bo.Utilisateur;
import org.encheres.exceptions.BusinessException;




/**
 * Servlet implementation class AjoutArticle
 */
@WebServlet("/AjoutArticle")
@MultipartConfig
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager catManager = new CategorieManager();
		
		request.setAttribute("listCategories", catManager.getListCategories());
		
		
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
		
		//Création d'un nouvel article à vendre
		ArticleVendu art = new ArticleVendu();
		
		//On set les informations dans ce nouvel article
		art.setNomArticle(nomArticle);
		art.setDescription(description);
		art.setMiseAPrix(miseAPrix);
		art.setEtatVente("CR");
		
		//A gerer dans jsp + ici
		 //récupérer l’image provenant de la JSP	
		 Part filePart = request.getPart("photo");
		 
		 //si l’utilisateur a saisi une image 		 
		 if(filePart != null && filePart.getSize() > 0) 
		 {
			//récupérer le nom de l’image
		     String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		     
		     //séparer le nom et l’extension
		     String[] fn = fileName.split("(\\.)");
		     
		     //stocker l’extension
		     String ext = fn[(fn.length-1)];
		     /*
		     if(!ext.isEmpty()) {
		    	 //mettre en place un mécanisme ici, pour générer un nom de fichier unique 
		    	 //afin d’éviter les écrasements de fichier
		    	 UUID uuid = UUID.randomUUID();
	             String rand = uuid.toString();

	             System.out.println("Random UUID String = " + rand);
		     
	             //recréer le nom complet
			     fileName = rand.toLowerCase() + "." + ext;
			     
			     InputStream fileContent = filePart.getInputStream();
		     }*/

		 }
		art.setImage("");
		
		
		//Connection à la BDD pour récup l'id de la Categorie
		CategorieManager CatMgr = new CategorieManager();		
		Categories cat = CatMgr.getCategorieByLibelle(request.getParameter("Categorie"));
			
		
		//On créer un lieu de retrait avec les infos du formulaire
		Retrait lieuRetrait = new Retrait(request.getParameter("street"), request.getParameter("zipcode"), request.getParameter("city"));
		
		//on récupère les infos du lieuRetrait pour le mettre dans notre article
		art.setLieuRetrait(lieuRetrait);
		art.setCategorie(cat);
		
		//On créer une image de l'utilisateur pour pouvoir récupérer son no_utilisateur
		Utilisateur user = new Utilisateur();
		
		//les infos de l'utilisateur sont stockées dans la session
		HttpSession session = request.getSession();
		user = (Utilisateur) session.getAttribute("user");
		
		//on affecte à l'article les infos de l'utilisateur
		art.setUtilisateur(user);
		
				
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
		
		//envoi a la BLL
		
		try {
			ArticleMgr.AjoutArticle(art);
		} catch (BusinessException e) {
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
