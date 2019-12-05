/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.dao;

import fr.chupin.playlist.domaine.Playlist;
import fr.chupin.playlist.domaine.TitreMusical;
import java.util.ArrayList;

/**
 *
 * @author Pierre
 */
public class TitreMusicalDAOMemory implements TitreMusicalDAO {
    
    private final ArrayList<TitreMusical> titreMusicalMemory;

    public TitreMusicalDAOMemory(ArrayList<TitreMusical> titreMusicalMemory) {
        this.titreMusicalMemory = titreMusicalMemory;
    }
    
    @Override
    public void insert(TitreMusical tm) {
        
        try {
        // ajouter un titre musical à la liste des titres musicaux
            titreMusicalMemory.add(tm);

        }
        catch(Exception ex) {
                System.out.println(ex);
        } 
    }
    
    @Override
    public TitreMusical selectOneById(int id) {
        return null;
    }
    
}
