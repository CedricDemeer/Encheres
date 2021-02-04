package org.encheres.bll;

import java.util.List;

import org.encheres.bo.Categories;
import org.encheres.dal.CategorieDAO;
import org.encheres.dal.DAOFactory;

public class CategorieManager {

	private CategorieDAO CatDAO;
	
	public CategorieManager() {
		this.CatDAO=DAOFactory.getCategorieDAO();
	}
	public List<Categories> getListCategories(){
		return CatDAO.selectAll();
	}
	public Categories getCategorieByLibelle(String lib) {
		return CatDAO.getCategorieByLibelle(lib);
	}
}
