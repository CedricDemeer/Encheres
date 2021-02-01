package org.encheres.bo;

public class Enchère {
	
	private String dateEnchère;
	private int montant_enchere;
	
	
	public String getDateEnchère() {
		return dateEnchère;
	}
	public void setDateEnchère(String dateEnchère) {
		this.dateEnchère = dateEnchère;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	public Enchère() {
		super();
	}
	
	public Enchère(String dateEnchère, int montant_enchere) {
		super();
		this.dateEnchère = dateEnchère;
		this.montant_enchere = montant_enchere;
	}
	
	
	
}
