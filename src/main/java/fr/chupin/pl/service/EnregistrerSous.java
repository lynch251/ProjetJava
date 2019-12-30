package fr.chupin.pl.service;

import java.io.IOException;

import javax.swing.JOptionPane;

import fr.chupin.pl.domaine.EnregistrementStrategie;
import fr.chupin.pl.domaine.Playlist;

public class EnregistrerSous {

	private EnregistrementStrategie strategie;
	
	EnregistrerSous(EnregistrementStrategie pStrategie) {
		
		this.strategie = pStrategie;
	}
	
	public void execute(Playlist pPlaylist) {
		
		try {
			strategie.Enregistrer(pPlaylist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}
