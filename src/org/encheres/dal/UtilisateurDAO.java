package org.encheres.dal;

import java.util.List;

import org.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
public void insert(Utilisateur user);
public void delete(Utilisateur user);
public List<Utilisateur> selectAll();
public Utilisateur selectById(int id);

}
