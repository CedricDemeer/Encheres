package org.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.bll.ArticleManager;
import org.encheres.bll.CategorieManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private List<ArticleVendu> ListeArticles = new ArrayList<ArticleVendu>();
	private List<ArticleVendu> ListeBDD = new ArrayList<ArticleVendu>();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	
    	//listeCategories
		CategorieManager catManager = new CategorieManager();		
		request.setAttribute("listCategories", catManager.getListCategories());
		
		//activer ts les articles pour test
		boolean listecomplette = false;
		
		//recup la liste des articles en BDD
		ArticleManager ArtMgr = new ArticleManager();
		ListeBDD = ArtMgr.getListArticle();
		
		//actualisation liste actuel
		ListeArticles.clear();
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		//pour test
		if(listecomplette) {
			ListeArticles = this.ListeBDD;
		}
		
		if(user!=null) //si utilisateur connecter 
		{
			if(request.getParameter("encheresouv") != null) {
				if(request.getParameter("encheresouv").equals("ouvertes"))
					ajoutOuverte(user, request);
			}
			if(request.getParameter("encheresenc") != null) {
				if(request.getParameter("encheresenc").equals("encours"))
					ajoutMesEnCours(user, request);
			}
			if(request.getParameter("encheresrem") != null) {
				if(request.getParameter("encheresrem").equals("remportees"))
					ajoutMesEnchereRemportees(user, request);
			}
			
				
			
			
			if(request.getParameter("ventesenc") != null) {
				if(request.getParameter("ventesenc").equals("venteencours"))
					ajoutMesVentesEnCours(user, request);
			}
			if(request.getParameter("ventesnon")!=null) {
			if(request.getParameter("ventesnon").equals("nondebutees"))	
					ajoutMesVentesNonDebutees(user, request);
			}
			if(request.getParameter("ventester")!=null) {
			if(request.getParameter("ventester").equals("terminees"))	
					ajoutMesVentesTerminees(user, request);
			}
			
			
		}else //utilisateur non connecter 
		{
			ajoutEnCours(request);
		}
		
		request.setAttribute("listearticles", ListeArticles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);
		//response.sendRedirect(request.getContextPath() + "/Accueil");
	}

	private boolean persofilter(HttpServletRequest request, ArticleVendu a ) {
		
		if(request.getParameter("q") !=null) {
			if(request.getParameter("q").equals("")) {
				return true;
			}
			
			if(a.getNomArticle().contains(request.getParameter("q"))) {
				return true;
			}
			return false;
		}
		
		
		return true;
	}
	private boolean persofiltercat(HttpServletRequest request, ArticleVendu a) {
		if(request.getParameter("categorie") != null) {
			if(a.getCategorie().getLibelle().equals(request.getParameter("categorie")) || request.getParameter("categorie").equals("Toutes") ) {
				return true;
			}
			return false;
		}
		
		return true;
	}	
	
	private void ajoutMesVentesTerminees(Utilisateur user, HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			//si etatVente est terminer + article a l'utilisateur
			if((a.getEtatVente().equals("VD") || a.getEtatVente()=="RT") && a.getUtilisateur().getNoUtilisateur() == user.getNoUtilisateur() && persofilter(request, a ) && persofiltercat(request, a)) {
				
				ListeArticles.add(a);
				//dzd
			}
		}
		
	}

	private void ajoutMesVentesNonDebutees(Utilisateur user, HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			//si etatVente est Créer + article a l'utilisateur
			if(a.getEtatVente().equals("CR") && a.getUtilisateur().getNoUtilisateur() == user.getNoUtilisateur() && persofilter(request, a ) && persofiltercat(request, a)) {
				ListeArticles.add(a);
			}
		}
		
	}

	private void ajoutMesVentesEnCours(Utilisateur user, HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			//si etatVente est En cour + article a l'utilisateur
			if(a.getEtatVente().equals("EC") && a.getUtilisateur().getNoUtilisateur() == user.getNoUtilisateur() && persofilter(request, a ) && persofiltercat(request, a)) {
				ListeArticles.add(a);
			}
		}	
		
	}

	private void ajoutMesEnchereRemportees(Utilisateur user, HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			//si etatVente est vendu ou retirer + article a l'utilisateur
			if(a.getEnchere() != null && (a.getEtatVente().equals("VD") || a.getEtatVente()=="RT")) {
				if(a.getEnchere().getNo_utilisateur() == user.getNoUtilisateur() && persofilter(request, a ) && persofiltercat(request, a)) {
					ListeArticles.add(a);
				}
			}
			/*if((a.getEtatVente().equals("VD") || a.getEtatVente()=="RT") && a.getUtilisateur().getNoUtilisateur() != user.getNoUtilisateur() && persofilter(request, a ) && persofiltercat(request, a)) {
				ListeArticles.add(a);
			}*/
		}	
		
	}

	private void ajoutMesEnCours(Utilisateur user, HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			//si etatVente est En Cour + enchere sur l'article    //LE getEnchere() return null
			if(a.getEnchere() != null
					&& a.getEtatVente().equals("EC") 
					&& a.getUtilisateur().getNoUtilisateur() != user.getNoUtilisateur()
					&& persofilter(request, a )
					&& persofiltercat(request, a))
			{
				ListeArticles.add(a);			
			}			
		}	
		
	}
	

	private void ajoutOuverte(Utilisateur user, HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			//si etatVente est En Cour + pas d'enchere sur l'article
			if(a.getEtatVente().equals("EC") && a.getUtilisateur().getNoUtilisateur() != user.getNoUtilisateur() && persofilter(request, a ) && persofiltercat(request, a)) {
					ListeArticles.add(a);
				
			}
		}		
	}

	

	private void ajoutEnCours(HttpServletRequest request) {
		for(ArticleVendu a:ListeBDD) {
			if(a.getEtatVente().equals("EC") && persofilter(request, a ) && persofiltercat(request, a)) {
				ListeArticles.add(a);
			}
		}		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}