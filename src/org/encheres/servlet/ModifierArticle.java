package org.encheres.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.encheres.bll.ArticleManager;
import org.encheres.bll.CategorieManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Categories;
import org.encheres.bo.Retrait;
import org.encheres.exceptions.BusinessException;

/**
 * Servlet implementation class ModifierArticle
 */
@WebServlet("/ModifierArticle")
public class ModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 10240;
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
		//récupérer le pseudo du profil depuis l'url
		int noArticle = Integer.parseInt(request.getParameter("article"));

		//récupération des info du user dans la BDD via le manager
		art = artMng.selectArticleParID(noArticle);
		request.setAttribute("article", art);
		
		//art = (ArticleVendu) request.getSession().getAttribute("article");
		System.out.println(art);
				
		Boolean test = true;
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		LocalDate dateDebutEncheres = null;
		LocalDate dateFinEncheres = null;

		
		
		System.out.println(nomArticle + art.getNomArticle());
		
		if(art.getNomArticle() != nomArticle) {
			art.setNomArticle(nomArticle);
		}

		if(art.getDescription() != description) {
			art.setDescription(description);
		}
/*
		if(art.getDateDebutEncheres() != dateDebutEncheres) {
			art.setDateDebutEncheres(dateDebutEncheres);
		}

		if(art.getDateFinEncheres() != dateFinEncheres) {
			art.setDateFinEncheres(dateFinEncheres);
		}

		if(request.getParameter("prixInitiale") != null) {
			int miseAPrix = Integer.parseInt(request.getParameter("prixInitial"));
			if(art.getMiseAPrix() != miseAPrix) {
				art.setMiseAPrix(miseAPrix);
			}
		}

		// On récupère le champ du fichier
		Part part = request.getPart("photo");
		if(part != null) {
		//On récupère le nom du fichier
		String nomImage = Paths.get(part.getSubmittedFileName()).getFileName().toString();

		//séparer le nom et l’extension
		String[] fn = nomImage.split("(\\.)");

		//stocker l’extension
		String ext = fn[(fn.length-1)];

		// Si on a bien un fichier
		if (nomImage != null && !nomImage.isEmpty()) {
			//mettre en place un mécanisme ici, pour générer  
			//un nom de fichier unique afin d’éviter les écrasements de fichier
			UUID uuid = UUID.randomUUID();
			String random = uuid.toString();
			random = random.substring(0, 15);
			System.out.println(random);
			//recréer le nom complet
			nomImage = random.toLowerCase() + "." + ext;
			String sContext = this.getServletContext().getRealPath("/");
			sContext += "images\\upload\\";
			// On écrit définitivement le fichier sur le disque
			ecrireFichier(part, nomImage, sContext);
			//sContext += nomImage;
			art.setImage(nomImage);
		}
		}

		//Connection à la BDD pour récup l'id de la Categorie
		//CategorieManager CatMgr = new CategorieManager();	
		//if(art.getCategorie().getLibelle() != null) {
		//Categories cat = CatMgr.getCategorieByLibelle(request.getParameter("Categorie"));
		
		//	if(art.getCategorie().getLibelle() != cat.getLibelle()) {
		//		art.setCategorie(cat);
		//	}
		//}
		
		//On créer un lieu de retrait avec les infos du formulaire
		Retrait lieuRetrait = new Retrait(request.getParameter("street"), request.getParameter("zipcode"), request.getParameter("city"));
		//on récupère les infos du lieuRetrait pour le mettre dans notre article
		if (art.getLieuRetrait().getRue() != lieuRetrait.getRue() || art.getLieuRetrait().getCode_postal() != lieuRetrait.getCode_postal() || art.getLieuRetrait().getVille() != lieuRetrait.getVille()) {
			art.setLieuRetrait(lieuRetrait);
		}
		
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"),dtf);
			dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"), dtf);
			if(art.getDateDebutEncheres() != dateDebutEncheres) {
				art.setDateDebutEncheres(dateDebutEncheres);
			}
			
			if(art.getDateFinEncheres() != dateFinEncheres) {
				art.setDateFinEncheres(dateFinEncheres);
			}
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			//listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_DATE_ERREUR);
		}
		*/
		try {
			artMng.ModifierArticle(art);
		} catch (BusinessException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
			rd.forward(request, response);	
			test=false;
		}
		
		if(test==true) {
		
		response.sendRedirect(request.getContextPath() + "/Accueil");
		//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		//rd.forward(request, response);
		}
	}


	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}}
}


