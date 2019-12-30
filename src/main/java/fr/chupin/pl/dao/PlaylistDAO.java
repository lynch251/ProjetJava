package fr.chupin.pl.dao;

import java.sql.SQLException;
import java.util.List;

import fr.chupin.pl.domaine.Playlist;

public interface PlaylistDAO  {
	
	Playlist selectById(String id) throws SQLException;
	void insert(Playlist p) throws SQLException;
	int calculerDureePlaylist(Playlist p) throws SQLException;
	List<Playlist> selectAll() throws SQLException;
	
}
