package org.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;

import org.encheres.bo.Enchere;
import org.encheres.exceptions.BusinessException;

public class EnchereDAOJDBCImpl implements EnchereDAO{
	
	
	private static final String INSERT="insert into Encheres(no_utilisateur,no_article,date_enchere,montant_enchere) values (?,?,?,?)";
	private static final String UPDATE="update ENCHERES set montant_enchere=?, date_enchere=getdate() where no_utilisateur=? and no_article=?";
		
	
	
	
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		BusinessException BusinessException=new BusinessException();
		try(Connection cnx= ConnectionProvider.getConnection())
		
		{
			PreparedStatement pstmt=cnx.prepareStatement(INSERT);
			pstmt.setInt(1, enchere.getNo_utilisateur());
			pstmt.setInt(2, enchere.getNo_article());
			pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontant_enchere());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		
	}

	

	
	@Override
	
	
	public void update(int n_utilisateur, int n_article,int montantEnchere)throws BusinessException {
		BusinessException BusinessException=new BusinessException();
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement pstmt=cnx.prepareStatement(UPDATE);
			pstmt.setInt(1,montantEnchere);
			pstmt.setInt(2,n_utilisateur);
			pstmt.setInt(3, n_article);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			BusinessException.ajouterErreur(e.getMessage());
		}
		
		// TODO Auto-generated method stub
		
	}

}
