package fr.chupin.pl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.chupin.pl.domaine.Playlist;
import fr.chupin.pl.domaine.Titre;

public class TitreDAOMySQL implements TitreDAO {

	private Titre titre;
	private List<Titre> titres = new ArrayList<Titre>();
	private Connection connection;
	private PreparedStatement requete;
	
	public TitreDAOMySQL(Connection connection) {
		this.connection = connection;
	}
	
	public void insert(Titre titre) throws SQLException {
		try {

			requete = connection.prepareStatement(RequetesSQL.INSERT_ONE_TITRE);
			requete.setString(1, titre.getNom());
			requete.setInt(2, titre.getDuree());
			requete.setString(3, titre.getNomGroupe());
			requete.executeUpdate();
			
		}
		catch(SQLException ex) {			
			System.out.println(ex);
		}
	}
	
	public List<Titre> selectAll() throws SQLException {
		
		try {
			requete = connection.prepareStatement(RequetesSQL.SELECT_ALL_TITRES);
		ResultSet resultat = requete.executeQuery();
		
		// Traitement
		while (resultat.next()) {
			int id = resultat.getInt("id");
			String nom = resultat.getString("nom");
			int duree = resultat.getInt("duree");
			String nomGroupe = resultat.getString("nomGroupe");
			// Mapping
			Titre t = new Titre(id, nom, duree, nomGroupe);
			titres.add(t);
		}
		return titres;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			return null;
		}
		
	}
	
	/**
	 * Méthode permettant de récupérer en BDD un Titre à parti de son ID.
	 * @param id
	 */
	public Titre selectById(String pId) {
		
		try {

			titre = new Titre();
			requete = connection.prepareStatement(RequetesSQL.SELECT_ONE_TITRE);
			requete.setString(1, pId);
			ResultSet resultat = requete.executeQuery();
			
			// Traitement de la réponse
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				int duree = resultat.getInt("duree");
				String nomGroupe = resultat.getString("nomGroupe");
				// mapping
				Titre t = new Titre(id,nom,duree,nomGroupe);
				titre = t;
			}
			return this.titre;
		}
		catch(SQLException ex) {			
			System.out.println(ex);
			return null;
		}
	}
}
