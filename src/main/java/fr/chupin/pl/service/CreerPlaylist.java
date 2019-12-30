package fr.chupin.pl.service;

import java.sql.SQLException;

import fr.chupin.pl.dao.PlaylistDAO;
import fr.chupin.pl.dao.TitreDAO;
import fr.chupin.pl.domaine.Playlist;
import fr.chupin.pl.domaine.Titre;

public class CreerPlaylist {

private PlaylistDAO playlistdao;
	
	public CreerPlaylist(PlaylistDAO t) {
		this.playlistdao = t;
	}
	
	public boolean execute(Playlist p) {
		
		try {
			playlistdao.insert(p);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
