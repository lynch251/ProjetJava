package fr.chupin.pl.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.chupin.pl.dao.BDConnexion;
import fr.chupin.pl.dao.TitreDAO;
import fr.chupin.pl.domaine.Titre;




public class RechercherTitres {
	
	private TitreDAO titredao;
	private List<Titre> titres = new ArrayList<Titre>();
	
	public RechercherTitres(TitreDAO p){
	
		this.titredao = p;
		
	}
	
	public List<Titre> execute(){
		
		try {
			
			titres = titredao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.titres;
	}
}