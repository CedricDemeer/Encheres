package org.encheres.bo;

import java.io.Serializable;

public class ArticleVendu implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int noArticle;
	private String nomArticle;
	private String description ;
	private String dateDebutEncheres;
	private String dateFinEncheres ;
	private int miseAPrix ;
	private int prixVente;
	//boolean ? 0 en attente de récupération et 1 récupéré ? 
	//ou int avec 0 en attente de récupération et 1 récupéré ?
	private boolean etatVente;
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public String getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(String dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public boolean isEtatVente() {
		return etatVente;
	}
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	
}
