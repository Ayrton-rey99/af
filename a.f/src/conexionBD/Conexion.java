package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexion {
	private  Connection conexion;
	
	public Conexion() {
		
		try {
			 conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/af", "root", "");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
			e.printStackTrace();
		}
	}
	
	public Connection getConexion() {
		return conexion;
		
	}
		
}
