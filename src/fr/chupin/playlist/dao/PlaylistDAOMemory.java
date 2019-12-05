/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.dao;

import fr.chupin.playlist.domaine.Format;
import fr.chupin.playlist.domaine.Playlist;
import fr.chupin.playlist.domaine.TitreMusical;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class PlaylistDAOMemory implements PlaylistDAO {
    
    private final List<Playlist> playlistMemory;

    public PlaylistDAOMemory(List<Playlist> playlistMemory) {
        this.playlistMemory = playlistMemory;
    }
    
    
    @Override
    public void insert(Playlist p) {
        
        try {
        // ajouter une playlist à la liste des playlists
        playlistMemory.add(p);   
            
        }
        catch(Exception ex) {
                System.out.println(ex);
        } 
    }
    
    @Override
    public void export(Format format) {
        
    }
    
    @Override 
    public List<Playlist> selectAll() {
        // Requete SELECT * FROM Playlist
        return this.playlistMemory;
    }
    
    @Override
    public Playlist selectOneById(int id) {
        try {
            // Requete SELECT * FROM Playlist WHERE playlist.id = ?
            for (Playlist p : playlistMemory) {
                if (p.getId() == id) {
                    return p;
                }
            }
            return null;
        }
        catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
        
    @Override
    public void ajouterTM(TitreMusical pTm, Playlist pPlaylist) {
        try {
            // Requete Update en bdd
            for (Playlist p : playlistMemory) {
                if (p.getId() == pPlaylist.getId()) {
                    p.setTitre(pTm);
                }
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
   
}
