package fr.chupin.pl.domaine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import fr.chupin.pl.service.EnregistrerSous;

public class Playlist {
	
	private int id;
	private String nom;
	private java.sql.Date dateCrea;
	private List<Titre> titres;
	private EnregistrementStrategie strategie;
	
	public Playlist(String pNom, java.sql.Date pDateCrea) {
		this.nom = pNom;
		this.dateCrea = pDateCrea;
	}
	public Playlist(int pId, String pNom, java.sql.Date pDateCrea) {
		this.id = pId;
		this.nom = pNom;
		this.dateCrea = pDateCrea;

	}
	
	public Playlist() {
		// TODO Auto-generated constructor stub
	}

	public void EnregistrerSous() {
		
		try {
			this.strategie.Enregistrer(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public java.sql.Date getDateCrea() {
		return dateCrea;
	}

	public void setDateCrea(java.sql.Date dateCrea) {
		this.dateCrea = dateCrea;
	}

	public List<Titre> getTitres() {
		return titres;
	}

	public void setTitres(List<Titre> titres) {
		this.titres = titres;
	}

	public EnregistrementStrategie getStrategie() {
		return strategie;
	}

	public void setStrategie(EnregistrementStrategie strategie) {
		this.strategie = strategie;
	}
	
	public String toString() {
		return nom;
		
	}
}
