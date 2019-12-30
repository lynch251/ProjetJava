package fr.chupin.pl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.chupin.pl.domaine.EnregistrementStrategie;
import fr.chupin.pl.domaine.Playlist;
import fr.chupin.pl.domaine.Titre;

public class PlaylistDAOMySQL implements PlaylistDAO {

	private Playlist playlist;
	private List<Playlist> playlists = new ArrayList<Playlist>();
	private Connection connection;
	private PreparedStatement requete;
	
	public PlaylistDAOMySQL(Connection connection) {
		this.connection = connection;
	}
	
	public Playlist selectById(String pId) throws SQLException {
		try {

			playlist = new Playlist();
			requete = connection.prepareStatement(RequetesSQL.SELECT_ONE_PLAYLIST);
			requete.setString(1, pId);
			ResultSet resultat = requete.executeQuery();
			
			// Traitement de la réponse
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				java.sql.Date dateCrea = resultat.getDate("dateCrea");
				// mapping
							
				Playlist p = new Playlist(id,nom,dateCrea);
				List<Titre> titresWithPlaylist = new ArrayList<Titre>();
				
				//récupérer les titres associés à la playlist 
				requete = connection.prepareStatement(RequetesSQL.SELECT_ALL_TITRES_WITH_PLAYLIST);
				requete.setString(1, pId);
				ResultSet resultat2 = requete.executeQuery();
				// Traitement de la réponse
				while (resultat2.next()) {
					int idTitre = resultat2.getInt("id");
					String nomTitre = resultat2.getString("nom");
					int dureeTitre = resultat2.getInt("duree");
					String nomGroupeTitre = resultat2.getString("nomGroupe");
					Titre titreWplaylist = new Titre(idTitre, nomTitre, dureeTitre, nomGroupeTitre);
					titresWithPlaylist.add(titreWplaylist);
				}
				
				p.setTitres(titresWithPlaylist);
				playlist = p;
			}
			return this.playlist;
		}
		catch(SQLException ex) {			
			System.out.println(ex);
			return null;
		}
	}
	
	public void insert(Playlist p) throws SQLException{
		try {
			requete = connection.prepareStatement(RequetesSQL.INSERT_ONE_PLAYLIST);
			requete.setString(1, p.getNom());
			requete.setDate(2, p.getDateCrea());
			requete.executeUpdate();
			
		}
		catch(SQLException ex) {			
			System.out.println(ex);
		}
	}
	
	public int calculerDureePlaylist(Playlist p) throws SQLException {
		
		int duree = 0;
		requete = connection.prepareStatement(RequetesSQL.CALCULER_DUREE_PLAYLIST);
		requete.setInt(1, p.getId());
		ResultSet resultat = requete.executeQuery();
		while (resultat.next()) {
			duree = resultat.getInt("somme");
		}
		return duree;
	}
	
	public List<Playlist> selectAll() throws SQLException  {
		
		try {
			//Ouverture de la connexion
			requete = connection.prepareStatement(RequetesSQL.SELECT_ALL_PLAYLISTS);
			ResultSet resultats = requete.executeQuery();
			//Traitement de la réponse
			
			while (resultats.next()) {
				int id = resultats.getInt("id");
				String nom = resultats.getString("nom");
				java.sql.Date dateCrea = resultats.getDate("dateCrea");
				//Mapping
				Playlist p = new Playlist(id, nom, dateCrea);
				List<Titre> titresWithPlaylist = new ArrayList<Titre>();
				
				//récupérer les titres associés à la playlist 
				requete = connection.prepareStatement(RequetesSQL.SELECT_ALL_TITRES_WITH_PLAYLIST);
				requete.setString(1, String.valueOf(id));
				ResultSet resultat2 = requete.executeQuery();
				// Traitement de la réponse
				while (resultat2.next()) {
					int idTitre = resultat2.getInt("id");
					String nomTitre = resultat2.getString("nom");
					int dureeTitre = resultat2.getInt("duree");
					String nomGroupeTitre = resultat2.getString("nomGroupe");
					Titre titreWplaylist = new Titre(idTitre, nomTitre, dureeTitre, nomGroupeTitre);
					titresWithPlaylist.add(titreWplaylist);
				}
				p.setTitres(titresWithPlaylist);
				
				playlists.add(p);
			}
			return this.playlists;
		}
		
		catch(SQLException ex) {			
			System.out.println(ex);
			return null;
		}
		
		
	}
	
}
