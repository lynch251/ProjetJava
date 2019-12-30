package fr.chupin.pl.dao;

public class RequetesSQL {

	
	public static final String SELECT_ALL_PLAYLISTS = "SELECT * FROM Playlist";
	public static final String SELECT_ALL_TITRES = "SELECT * FROM Titre";
	public static final String SELECT_ONE_PLAYLIST = "SELECT * FROM Playlist WHERE id = ?";
	public static final String INSERT_ONE_PLAYLIST = "INSERT INTO Playlist(nom, dateCrea) VALUES(?,?)";
	public static final String CALCULER_DUREE_PLAYLIST = "SELECT SUM(Titre.duree) AS somme FROM Titre INNER JOIN TitreWithPlaylist ON Titre.id = TitreWithPlaylist.idTitre WHERE TitreWithPlaylist.idPlaylist = ?";
	public static final String INSERT_ONE_TITRE = "INSERT INTO Titre(nom, duree, nomGroupe) VALUES (?,?,?)";
	public static final String SELECT_ONE_TITRE = "SELECT * FROM Titre WHERE id = ?";
	public static final String ENREGISTRER_PLAYLIST_SOUS = "";
	public static final String SELECT_ONE_STRATEGIE = "SELECT * FROM EnregistrementStrategie WHERE id = ?";
	public static final String SELECT_ALL_TITRES_WITH_PLAYLIST = "SELECT * FROM Titre INNER JOIN TitreWithPlaylist ON Titre.id = TitreWithPlaylist.idTitre WHERE idPlaylist = ?";
	
}
