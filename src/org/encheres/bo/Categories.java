package org.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categories {
	private int no_categorie;
	private String libelle;
	private List<ArticleVendu> articleCategorie = new ArrayList<ArticleVendu>();
	
	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	public List<ArticleVendu> getArticleCategorie() {
		return articleCategorie;
	}
	public void setArticleCategorie(List<ArticleVendu> articleCategorie) {
		this.articleCategorie = articleCategorie;
	}
	public Categories() {
		super();
	}
	public Categories(int no_categorie, String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}
	
	public Categories(int no_categorie, String libelle, List<ArticleVendu> articleCategorie) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
		this.articleCategorie = articleCategorie;
	}
	
	

}
