package org.encheres.bo;

import java.time.LocalDate;
import java.util.Date;

public class Enchere {
	private LocalDate dateEnchere;
	private int montant_enchere;
	private int no_utilisateur;
	private int no_article;
	
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	public Enchere() {
		super();
	}
	
	public Enchere(LocalDate dateEnchere, int montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}
	public int getNo_utilisateur() {
		return no_utilisateur;
	}
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
	public int getNo_article() {
		return no_article;
	}
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}
	public Enchere(LocalDate dateEnchere, int montant_enchere, int no_user, int no_art) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_art;
		this.no_utilisateur = no_user;
	}
	
}
