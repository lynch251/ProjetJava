/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.domaine;

/**
 *
 * @author Pierre
 */
public class TitreMusical {
       
    private int id;
    private String nom;
    private int duree;
    private String nomGroupe;

    public TitreMusical(int id, String nom, int duree, String nomGroupe) {
        this.id = id;
        this.nom = nom;
        this.duree = duree;
        this.nomGroupe = nomGroupe;
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

    
}
