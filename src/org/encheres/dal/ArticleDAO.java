package org.encheres.dal;

import org.encheres.bo.ArticleVendu;
import java.util.List;

public interface ArticleDAO {
	public void insert(ArticleVendu article);
	public void delete(int noArticle);
	public List<ArticleVendu> selectAll();
	public ArticleVendu selectById(int noArticle);
	public void update(ArticleVendu article);
	
}
