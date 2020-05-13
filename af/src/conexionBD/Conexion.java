package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	//private Statement declaracion;
	private static Statement declaracion;
	public Conexion() {
		// TODO Auto-generated constructor stub
		try {
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/af", "root", "");
			declaracion = conexion.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Statement getDeclaracion() {
		return declaracion;
	}
		
}
