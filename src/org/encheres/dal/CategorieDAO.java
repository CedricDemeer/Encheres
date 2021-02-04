package org.encheres.dal;

import java.util.List;

import org.encheres.bo.Categories;

public interface CategorieDAO {
	public List<Categories> selectAll();
	public void insert(Categories c);
	public void update(Categories c);
	public void delete(Categories c);
	public Categories getCategorieByLibelle(String lib);
}
