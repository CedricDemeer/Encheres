package org.encheres.dal;

import java.util.List;

import org.encheres.bo.Utilisateur;
import org.encheres.exceptions.DALException;

public interface UtilisateurDAO {
	public void insert(Utilisateur user) throws DALException;
	public void delete(Utilisateur user) throws DALException;
	public List<Utilisateur> selectAll();
	public Utilisateur selectById(int id);
	public void Update(Utilisateur user);

}
