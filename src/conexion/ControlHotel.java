package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

public class ControlHotel {
	public static Connection con;
	private static PreparedStatement PS;
	
	public void connectar() throws SQLException {
		
	}
	
public static Connection getConnection() throws SQLException {
	Connection conn = new conecctionfactory().recuperaConexion();
	con = conn;
		return con; // ES CN
	}

	public static void close() throws SQLException {
		try {
			con.close();
			System.out.println("Conexion terminada");
		} catch (SQLException ex) {
			System.out.println("Error al cerrar la conexion");
		}
	}
	
	public static List<Map<String, String>> listar() throws SQLException {
		Connection con = new conecctionfactory().recuperaConexion();

		Statement statement = getConnection().createStatement();
		statement.execute("SELECT id, fecha_entrada, fechaSalida, valor, forma_de_pago FROM reservas");
		ResultSet resultSet = statement.getResultSet();
		
		
		System.out.println("conexcion correcta");
		List<Map<String, String>> resultado = new ArrayList<>();
		while(resultSet.next()) {
			
			Map<String, String> fila = new HashMap<>();
			fila.put("id", String.valueOf(resultSet.getInt("id")));
			fila.put("fecha_entrada", String.valueOf(resultSet.getString("fecha_entrada")));
			fila.put("fechaSalida", String.valueOf(resultSet.getString("fechaSalida")));
			fila.put("valor", String.valueOf(resultSet.getInt("valor")));
			fila.put("forma_de_pago", String.valueOf(resultSet.getString("forma_de_pago")));
			resultado.add(fila);
		}
		
	
		con.close();
		return resultado;
	}
	
	
	public static List<Map<String, String>> listar2() throws SQLException {
		Connection con = new conecctionfactory().recuperaConexion();

		Statement statement = con.createStatement();
		statement.execute("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes");
		ResultSet resultSet = statement.getResultSet();
		
		
		System.out.println("conexcion correcta");
		List<Map<String, String>> resultado = new ArrayList<>();
		while(resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("id", String.valueOf(resultSet.getInt("id")));
			fila.put("nombre", String.valueOf(resultSet.getString("nombre")));
			fila.put("apellido", String.valueOf(resultSet.getString("apellido")));
			fila.put("fecha_nacimiento", String.valueOf(resultSet.getString("fecha_nacimiento")));
			fila.put("nacionalidad", String.valueOf(resultSet.getString("nacionalidad")));
			fila.put("telefono", String.valueOf(resultSet.getInt("telefono")));
			fila.put("id_reserva", String.valueOf(resultSet.getInt("id_reserva")));
			resultado.add(fila);
			
			System.out.println(fila.get("id"));
			System.out.println(fila.values());
			System.out.println(fila);
			 
			
		}
		
		con.close();
		return resultado;
	}
	//RETORNAMOS CONECCION
	
	
	
	public static void deleteDatos(String id) throws SQLException {
		String SQL = "DELETE from huespedes WHERE id=" +id;
		 
		int res = 0;
		try {
			 PS = getConnection().prepareStatement(SQL);
			 res = PS.executeUpdate();
			 if(res >0) {
				 System.out.println("Registro eliminado");
			 }
		} catch (SQLException e) {
			System.err.println("Error al modificar los datos en la db" + e.getMessage());
		}
		ControlHotel.close();
	}
	
}
