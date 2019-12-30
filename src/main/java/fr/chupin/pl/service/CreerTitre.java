package fr.chupin.pl.service;

import java.sql.SQLException;

import fr.chupin.pl.dao.TitreDAO;
import fr.chupin.pl.domaine.Titre;

public class CreerTitre {

	private TitreDAO titredao;
	
	public CreerTitre(TitreDAO t) {
		this.titredao = t;
	}
	
	public boolean execute(Titre t) {

		try {
			titredao.insert(t);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
