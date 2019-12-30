package fr.chupin.pl.domaine;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import javax.swing.JOptionPane;

public class HTMLStrategie extends EnregistrementStrategie {

	private String repertoire;
	private String nom;
	
	public HTMLStrategie(String pNom) {
		this.nom = pNom;
	}
	
	@Override
	public void Enregistrer(Playlist p) {
		try {
			File htmlTemplateFile = new File("template/template.html");
			
			String htmlString = FileUtils.readFileToString(htmlTemplateFile);
			String title = "Ma playlist en HTML !";
			String nom = "Nom : " + p.getNom();
			String dateCrea = "Date de création : " + p.getDateCrea();
			String titres = "Titres correspondants : </br>";
			if (p.getTitres() != null) {
				for (Titre t : p.getTitres()) {
				titres = titres + t.getNom() + ", " + t.getNomGroupe() + ", " + t.getDuree() + "</br>";
				}
			}
			
			htmlString = htmlString.replace("$title", title);
			htmlString = htmlString.replace("$nom", nom);
			htmlString = htmlString.replace("$dateCrea", dateCrea);
			htmlString = htmlString.replace("$titres", titres);
			
			// Enregistrement de la playlist en .html
			File newHtmlFile = new File("export/" + p.getNom() + ".html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);

			// ouverture du fichier 
			Desktop.getDesktop().open(newHtmlFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	public String toString() {
		return this.nom;
	}
}
