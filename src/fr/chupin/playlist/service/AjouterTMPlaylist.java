/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.service;

import fr.chupin.playlist.dao.PlaylistDAO;
import fr.chupin.playlist.dao.TitreMusicalDAO;
import fr.chupin.playlist.domaine.Playlist;
import fr.chupin.playlist.domaine.TitreMusical;

/**
 *
 * @author Pierre
 */
public class AjouterTMPlaylist {
    
    private TitreMusicalDAO TM;
    private PlaylistDAO playlist;

    public AjouterTMPlaylist(TitreMusicalDAO pTM, PlaylistDAO pPlaylist) {
        this.TM = pTM;
        this.playlist = pPlaylist;
    }
    
    public void execute(TitreMusical pTm, Playlist pPlaylist) {
        // Ajouter un titre à une playlist
        
        playlist.ajouterTM(pTm, pPlaylist);
    }
    
}
