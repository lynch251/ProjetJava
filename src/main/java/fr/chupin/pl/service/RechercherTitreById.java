package fr.chupin.pl.service;

import java.sql.SQLException;

import fr.chupin.pl.dao.PlaylistDAO;
import fr.chupin.pl.dao.TitreDAO;
import fr.chupin.pl.domaine.Playlist;
import fr.chupin.pl.domaine.Titre;

public class RechercherTitreById {
	
private TitreDAO titredao;
	
	public RechercherTitreById(TitreDAO t){	
		this.titredao = t;	
	}
	
	public Titre execute(String id) {
		
		try {
			return titredao.selectById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
