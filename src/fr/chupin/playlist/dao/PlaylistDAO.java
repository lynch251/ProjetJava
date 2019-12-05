/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.dao;

import fr.chupin.playlist.domaine.Format;
import fr.chupin.playlist.domaine.Playlist;
import fr.chupin.playlist.domaine.TitreMusical;
import java.util.List;

/**
 *
 * @author Pierre
 */
public interface PlaylistDAO {
    
    List<Playlist> selectAll();
    void ajouterTM(TitreMusical pTm, Playlist pPlaylist);
    Playlist selectOneById(int id);
    void insert(Playlist p);
    void export(Format format);
    
    
    
}
