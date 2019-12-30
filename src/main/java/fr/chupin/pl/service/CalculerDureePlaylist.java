package fr.chupin.pl.service;

import java.sql.SQLException;

import fr.chupin.pl.dao.PlaylistDAO;
import fr.chupin.pl.domaine.Playlist;
import fr.chupin.pl.domaine.Titre;

public class CalculerDureePlaylist {

	private PlaylistDAO playlistdao;
	private int duree;
	
	public CalculerDureePlaylist(PlaylistDAO pdao) {
		
		this.playlistdao = pdao;
	}
	
	public int execute(Playlist pPlaylist) {
		
		try {
			return duree = playlistdao.calculerDureePlaylist(pPlaylist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		}
	}

