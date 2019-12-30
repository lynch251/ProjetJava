package fr.chupin.pl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnexion {

	private static final String URL = "jdbc:mysql://localhost:8889/tp_playlist?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static Connection connection;
	
	private BDConnexion() {
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Connexion effective !");    
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection getInstance() {
		
		if(connection == null) {
			new BDConnexion();
		}
		return connection;
		
	}
	
}
