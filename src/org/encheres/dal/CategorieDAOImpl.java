package org.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.encheres.bo.Categories;
import org.encheres.bo.Utilisateur;

public class CategorieDAOImpl implements CategorieDAO {

	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES ORDER BY libelle ASC";
	private static final String SELECT_BY_LIB = "SELECT * FROM CATEGORIES where libelle=? ";
	@Override
	public List<Categories> selectAll() {
		
			List<Categories> ListeCat = new ArrayList<Categories>();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					ListeCat.add(new Categories(
							rs.getInt("no_categorie"),
							rs.getString("libelle")
							));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return ListeCat;
	}
	
	public Categories getCategorieByLibelle(String lib) {
		Categories Cat = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LIB);
			pstmt.setString(1, lib);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Cat = new Categories(rs.getInt(1),lib);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Cat;
	}

	@Override
	public void insert(Categories c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Categories c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Categories c) {
		// TODO Auto-generated method stub

	}

}
