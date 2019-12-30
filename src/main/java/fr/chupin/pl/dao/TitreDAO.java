package fr.chupin.pl.dao;

import java.sql.SQLException;
import java.util.List;

import fr.chupin.pl.domaine.Titre;

public interface TitreDAO {

	void insert(Titre titre) throws SQLException;
	Titre selectById(String id) throws SQLException;
	List<Titre> selectAll() throws SQLException;
}
