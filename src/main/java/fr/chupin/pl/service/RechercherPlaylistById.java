package fr.chupin.pl.service;

import java.sql.SQLException;

import fr.chupin.pl.dao.PlaylistDAO;
import fr.chupin.pl.domaine.Playlist;

public class RechercherPlaylistById {

	private PlaylistDAO playlistdao;
	
	public RechercherPlaylistById(PlaylistDAO p){
		
		this.playlistdao = p;
		
	}
	
	public Playlist execute(String id) {
		
		try {
			return playlistdao.selectById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
