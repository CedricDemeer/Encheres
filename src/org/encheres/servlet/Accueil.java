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
import org.encheres.bo.Categories;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
    	//listeCategories
		CategorieManager catManager = new CategorieManager();		
		request.setAttribute("listCategories", catManager.getListCategories());
		
		//activer ts les articles pour test
		boolean listecomplette = true;
		//filtre Achat
		boolean Achat = false;		
		boolean EnchereOuverte = false;
		boolean MesEnchereEnCours = false;
		boolean MesEnchereRemporter = false;
		//filtre Vente
		boolean Vente = false;
		boolean MesVenteEnCours = false;
		
		
		//recup la liste des articles en BDD
		ArticleManager ArtMgr = new ArticleManager();
		List<ArticleVendu> ListeBDD = ArtMgr.getListArticle();
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		
		if(listecomplette) {
			ListeArticles = ListeBDD;			
		}
		if(Achat) {
			for(ArticleVendu a:ListeBDD) {
				
				
				
				
				
				if(user!=null) {
					if(a.getUtilisateur().getNoUtilisateur() != user.getNoUtilisateur()) {
						ListeArticles.add(a);
					}
				}else {
					ListeArticles.add(a);
				}				
			}
		}
		if(Vente) {
			
		}
		
		
		request.setAttribute("listearticles", ListeArticles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
