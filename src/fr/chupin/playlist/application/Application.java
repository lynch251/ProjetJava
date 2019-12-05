/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.chupin.playlist.application;

import fr.chupin.playlist.dao.TitreMusicalDAO;
import fr.chupin.playlist.dao.TitreMusicalDAOMemory;
import fr.chupin.playlist.domaine.TitreMusical;
import fr.chupin.playlist.service.AfficherPlaylist;
import fr.chupin.playlist.service.CreerTitreMusical;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pierre
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // création de la source de données : liste
        ArrayList L = new ArrayList<TitreMusical>();
        
        // 
        TitreMusicalDAO tmDAO = new TitreMusicalDAOMemory(L);
        CreerTitreMusical ctm = new CreerTitreMusical(tmDAO);
        TitreMusical titreMusical = new TitreMusical(1,"titre1", 4, "Groupe1");
        
        AfficherPlaylist aP = new AfficherPlaylist();

        // Ajout du titre dans la source de données
        try {
            ctm.execute(titreMusical);
            aP.executeAll();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
