/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.service;

import fr.chupin.playlist.dao.PlaylistDAO;
import fr.chupin.playlist.domaine.Playlist;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class AfficherPlaylist {
    
    private PlaylistDAO playlist;


    
    /**
     * Afficher toutes les playlists
     */
    public void executeAll(){
        
        playlist.selectAll();
    }
}
