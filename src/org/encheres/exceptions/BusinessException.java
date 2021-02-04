package org.encheres.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

private List<String> listeMessagesErreur;
	
	public BusinessException() {
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
