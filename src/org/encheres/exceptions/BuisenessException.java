package org.encheres.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BuisenessException extends Exception {

private List<String> listeMessagesErreur;
	
	public BuisenessException() {
		super();
		listeMessagesErreur = new ArrayList<String>();
	}
	
	public void ajouterErreur(String message) {
		//TODO gérer les doublons dans la liste
		if (!listeMessagesErreur.contains(message)) {
			listeMessagesErreur.add(message);
		}
	}
	
	public boolean hasErreurs() {
		return listeMessagesErreur.size() > 0;
	}

	public List<String> getListeMessagesErreur() {
		return listeMessagesErreur;
	}
	
	
}
