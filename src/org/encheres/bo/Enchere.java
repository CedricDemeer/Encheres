package org.encheres.bo;

public class Enchere {
	private String dateEnchere;
	private int montant_enchere;
	
	
	public String getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(String dateEnchere) {
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
	
	public Enchere(String dateEnchere, int montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}
}
