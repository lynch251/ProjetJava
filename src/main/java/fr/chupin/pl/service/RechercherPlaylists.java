package fr.chupin.pl.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.chupin.pl.dao.BDConnexion;
import fr.chupin.pl.dao.PlaylistDAO;
import fr.chupin.pl.domaine.Playlist;



public class RechercherPlaylists {
	
	private PlaylistDAO playlistdao;
	private List<Playlist> playlists;
	
	public RechercherPlaylists(PlaylistDAO p){
	
		this.playlistdao = p;
		
	}
	
	public List<Playlist> execute(){
		
		try {
			
			playlists = playlistdao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.playlists;
	}
}