package fr.chupin.pl.domaine;

public class Titre {
	
	private int id;
	private String nom;
	private int duree;
	private String nomGroupe;
	
	public Titre() {
		
	}
	public Titre(String pNom, int pDuree, String pNomGroupe) {
		this.nom = pNom;
		this.duree = pDuree;
		this.nomGroupe = pNomGroupe;
	}
	public Titre(int pId, String pNom, int pDuree, String pNomGroupe) {
		this.id = pId;
		this.nom = pNom;
		this.duree = pDuree;
		this.nomGroupe = pNomGroupe;
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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public String toString() {
		return nom;
		
	}

}
