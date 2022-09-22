package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conecctionfactory {
	public Connection recuperaConexion() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "root1234");
		
	}
}
