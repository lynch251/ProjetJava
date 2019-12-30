package fr.chupin.pl.domaine;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFStrategie extends EnregistrementStrategie {

	private String repDestination;	
	private String nom;
	
	public PDFStrategie(String pNom) {
		this.nom = pNom;
	}
	
	@Override
	public void Enregistrer(Playlist p) {
			
		Document document = new Document();
		try {
			File f = new File("export/" + p.getNom() + ".pdf");
			PdfWriter.getInstance(document, new FileOutputStream(f));
			document.open();
			
			document.addTitle("Ma playlist en PDF !");
			
			document.add(createTable(p));
			document.close();
			
			// ouverture du fichier 
			Desktop.getDesktop().open(f);
		}
		catch (DocumentException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
		catch (IOException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
		
			
	}
	
	/**
	 * Retourne un PdfPTable rempli par l'objet donné en paramètre
	 * @param obj : une playlist
	 * @return
	 */
	public PdfPTable createTable(Playlist obj) {
		
		
		PdfPTable table = new PdfPTable(3);
		
		PdfPCell cell;
		
		cell = new PdfPCell(new Phrase ("Nom playlist"));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase ("Date de création"));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase ("Titres"));
		table.addCell(cell);
		
		table.addCell(obj.getNom());
		table.addCell(obj.getDateCrea().toString());
		String vChaineTitres = "";
		if (obj.getTitres() != null) {
			for (Titre titre : obj.getTitres()) {
				vChaineTitres = vChaineTitres + titre.getNom() + ", " + titre.getNomGroupe() + ", " + titre.getDuree() + " sec" +  System.getProperty("line.separator");
				
			}	
		}
		table.addCell(vChaineTitres);
		return table;
		
	}
	
	
	public String toString() {
		return this.nom;
	}
	
}

