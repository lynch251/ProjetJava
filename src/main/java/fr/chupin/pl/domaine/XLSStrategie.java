package fr.chupin.pl.domaine;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class XLSStrategie extends EnregistrementStrategie {

	private String nom;
	
	public XLSStrategie(String pNom) {
		this.nom = pNom;
	}
	
	@Override
	public void Enregistrer(Playlist p) throws IOException {
			
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Playlist export" + p.getNom());
		
		int rownum = 0;
		Cell cell;
		Row row;
		
		HSSFCellStyle style = workbook.createCellStyle();
		
		row = sheet.createRow(rownum);
		
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Titre");
		cell.setCellStyle(style);
		
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Date de création");
		cell.setCellStyle(style);
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Titres : ");
		cell.setCellStyle(style);
		
		// Données
		rownum = 1;
		row = sheet.createRow(rownum);
		
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue(p.getNom());
		
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue(p.getDateCrea().toString());
		
		cell = row.createCell(2, CellType.STRING);
		String vChaineTitres = "";
		if (p.getTitres() != null) {
			for (Titre titre : p.getTitres()) {
				vChaineTitres = vChaineTitres + titre.getNom() + ", " + titre.getNomGroupe() + ", " + titre.getDuree() + " sec" + "\n";
			}
		}
		cell.setCellValue(vChaineTitres);
		
		
		File f = new File("export/" + p.getNom() + ".xls");
		FileOutputStream outFile;
		
		try {
			outFile = new FileOutputStream(f);
			workbook.write(outFile);
			
			// ouverture du fichier 
			Desktop.getDesktop().open(f);
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	
	public String toString() {
		return this.nom;
	}
}
