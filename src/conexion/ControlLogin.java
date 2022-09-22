package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlLogin {
	public static List<Map<String, String>> loginOn() throws SQLException{
		Connection con = new conecctionfactory().recuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("SELECT id, usuario, password FROM login");
		ResultSet resultSet = statement.getResultSet();
		System.out.println("conexcion correcta Login");
		
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		
		
		while(resultSet.next()) {
			
			Map<String, String> fila = new HashMap<>();
			fila.put("usuario", String.valueOf(resultSet.getString("usuario")));
			fila.put("password", String.valueOf(resultSet.getString("password")));
			resultado.add(fila);
			System.out.println(fila);
			System.out.println("Cantidad"+ fila.size());
			
		}
		
		con.close();
		return resultado;
	}
}

