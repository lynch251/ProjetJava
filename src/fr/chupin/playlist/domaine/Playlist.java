/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.domaine;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class Playlist {
    
    private int id;
    private String nom;
    private Date dateCrea;
    private List<TitreMusical> titresMusicaux;

    public Playlist(int id, String nom, Date dateCrea, List<TitreMusical> titresMusicaux) {
        this.id = id;
        this.nom = nom;
        this.dateCrea = dateCrea;
        this.titresMusicaux = titresMusicaux;
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

    public Date getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(Date dateCrea) {
        this.dateCrea = dateCrea;
    }

    public List<TitreMusical> getTitresMusicaux() {
        return titresMusicaux;
    }

    public void setTitresMusicaux(List<TitreMusical> titresMusicaux) {
        this.titresMusicaux = titresMusicaux;
    }
    
    public void setTitre(TitreMusical titre) {
        this.titresMusicaux.add(titre);
    }

}
