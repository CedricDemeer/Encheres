package org.encheres.dal;

import org.encheres.bo.ArticleVendu;
import org.encheres.exceptions.BusinessException;
import org.encheres.exceptions.DALException;

import java.util.List;

public interface ArticleDAO {
	public void insert(ArticleVendu article) throws BusinessException;
	public void delete(int noArticle) throws DALException;
	public List<ArticleVendu> selectAll();
	public ArticleVendu selectById(int noArticle);
	public void update(ArticleVendu article) throws BusinessException;
	
}
