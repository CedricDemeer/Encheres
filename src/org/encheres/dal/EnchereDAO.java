package org.encheres.dal;

import java.util.List;

import org.encheres.bo.Enchere;
import org.encheres.exceptions.BusinessException;

public interface EnchereDAO {
	
public void insert( Enchere enchere) throws BusinessException;
public void update (int n_utilisateur, int n_article, int montantEnchere) throws BusinessException;

}
