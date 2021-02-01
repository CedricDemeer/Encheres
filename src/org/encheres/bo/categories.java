package org.encheres.bo;

public class categories {
	private int no_categorie;
	private String libelle;
	
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

		//YR test commit 
		this.libelle = libelle;
	}
	public categories() {
		super();
	}
	public categories(int no_categorie, String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}

}
