/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.dao;

import fr.chupin.playlist.domaine.Playlist;
import fr.chupin.playlist.domaine.TitreMusical;

/**
 *  Cette classe va permettre de définir les méthodes à implémenter pour un Titre Musical
 * @author Pierre
 */
public interface TitreMusicalDAO {
    
    void insert(TitreMusical titreMusical);
    TitreMusical selectOneById(int id);
    
    
}
