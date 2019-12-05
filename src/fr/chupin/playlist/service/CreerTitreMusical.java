/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.service;

import fr.chupin.playlist.dao.TitreMusicalDAO;
import fr.chupin.playlist.domaine.TitreMusical;

/**
 *
 * @author Pierre
 */
public class CreerTitreMusical {
    
    private TitreMusicalDAO titreMusicalDAO;

    public CreerTitreMusical(TitreMusicalDAO titreMusicalDAO) {
        this.titreMusicalDAO = titreMusicalDAO;
    }
    
    public void execute(TitreMusical tm) {
        
        // règles de gestion métier
        
        // Autres règles
        
        // Accès à la source de données
        titreMusicalDAO.insert(tm);
    }
}
