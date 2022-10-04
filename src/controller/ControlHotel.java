package controller;

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

import conexion.conecctionfactory;

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
			
		}
		
		con.close();
		return resultado;
	}
	//RETORNAMOS CONECCION
	
	
	
	public static void deleteDatosHuesped(String id) throws SQLException {
		String SQL = "DELETE from huespedes WHERE id=" +id;
		 
		int res = 0;
		try {
			 PS = getConnection().prepareStatement(SQL);
			 res = PS.executeUpdate();
			 if(res >0) {
				 System.out.println("Huesped eliminado");
			 }
		} catch (SQLException e) {
			System.err.println("Error al modificar los datos en la db" + e.getMessage());
		}
		ControlHotel.close();
	}
	public static void deleteDatosReserva(String id) throws SQLException {
		String SQL = "DELETE from reservas WHERE id=" +id;
		 
		int res = 0;
		try {
			 PS = getConnection().prepareStatement(SQL);
			 res = PS.executeUpdate();
			 if(res >0) {
				 System.out.println("Reserva eliminado");
			 }
		} catch (SQLException e) {
			System.err.println("Error al modificar los datos en la db" + e.getMessage());
		}
		ControlHotel.close();
	}
	public static void deleteDatosHuespedViculado(String id_reserva) throws SQLException {
		String SQL = "DELETE from huespedes WHERE id_reserva=" +id_reserva;
		int res = 0;
		try {
			 PS = getConnection().prepareStatement(SQL);
			 res = PS.executeUpdate();
			 if(res >0) {
				 System.out.println("Huesped eliminado");
			 }
		} catch (SQLException e) {
			System.err.println("Error al modificar los datos en la db" + e.getMessage());
		}
		ControlHotel.close();
	}
	public static void deleteDatosReservaViculada(String id) throws SQLException {
		String SQL = "DELETE from reservas WHERE id=" +id;
		int res = 0;
		try {
			 PS = getConnection().prepareStatement(SQL);
			 res = PS.executeUpdate();
			 if(res >0) {
				 System.out.println("Huesped eliminado");
			 }
		} catch (SQLException e) {
			System.err.println("Error al modificar los datos en la db" + e.getMessage());
		}
		ControlHotel.close();
	}
	
	public static Map<String, String> editarReserva(Map<String, String> modificarReserva) throws SQLException { 
		Connection con = new conecctionfactory().recuperaConexion();
		PreparedStatement statement = con.prepareStatement("UPDATE reservas SET fecha_entrada = ?, fechaSalida = ?, valor = ?, forma_de_pago = ? WHERE id =?");
		statement.setString(1, modificarReserva.get("fecha_entrada"));
		statement.setString(2, modificarReserva.get("fechaSalida"));
		statement.setInt(3, Integer.parseInt(modificarReserva.get("valor")));
		statement.setString(4, modificarReserva.get("forma_de_pago"));
		statement.setInt(5, Integer.parseInt(modificarReserva.get("id")));
		statement.execute();
		System.out.println("Modificacion RESERVA exitosa");
		con.close();
		return modificarReserva;
		
	}
	public static Map<String, String> editarHuesped(Map<String, String> modificarHuesped) throws SQLException { 
		Connection con = new conecctionfactory().recuperaConexion();
		PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ? WHERE id =?");
		
		statement.setString(1, modificarHuesped.get("nombre"));
		statement.setString(2, modificarHuesped.get("apellido"));
		statement.setString(3, modificarHuesped.get("fecha_nacimiento"));
		statement.setString(4, modificarHuesped.get("nacionalidad"));
		statement.setInt(5, Integer.parseInt(modificarHuesped.get("telefono")));
		statement.setInt(6, Integer.parseInt(modificarHuesped.get("id")));
		statement.execute();
		System.out.println("Modificacion HUESPED exitosa");
		con.close();
		return modificarHuesped;
		
	}
	
	public static List<Map<String, String>> buscarApellido(String busquedaApellidoHuespe) throws SQLException {
		System.out.println("buscarApellido " + busquedaApellidoHuespe);
		Connection con = new conecctionfactory().recuperaConexion();
		
		PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE apellido = ?");
		statement.setString(1, busquedaApellidoHuespe);
		
		statement.execute();
		
		ResultSet resultSetBuscarH = statement.getResultSet();
		List<Map<String, String>> datosBuscarH = new ArrayList<Map<String, String>>();	
		while(resultSetBuscarH.next()) {
			Map<String, String> fila = new HashMap<String, String>();
			fila.put("id", String.valueOf(resultSetBuscarH.getInt("id")));
			fila.put("nombre", resultSetBuscarH.getString("nombre"));
			fila.put("apellido", resultSetBuscarH.getString("apellido"));
			fila.put("fecha_nacimiento", resultSetBuscarH.getString("fecha_nacimiento"));
			fila.put("nacionalidad", resultSetBuscarH.getString("nacionalidad"));
			fila.put("telefono", String.valueOf(resultSetBuscarH.getInt("telefono")));
			fila.put("id_reserva", String.valueOf(resultSetBuscarH.getInt("id_reserva")));
			datosBuscarH.add(fila);
			System.out.println("SALIENDO FILA " + fila);
		}
		con.close();
		return datosBuscarH;
	}
	public static List<Map<String, String>> buscarIdHuesped(String busquedaIdHuesped) throws SQLException {
		System.out.println("buscarIdHuesped " + busquedaIdHuesped);
		String s = "ñ";
        System.out.println("IsNumeric: " + isNumeric(s));
		Connection con = new conecctionfactory().recuperaConexion();
		
		PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id_reserva = ?");
		statement.setString(1, busquedaIdHuesped);
		
		statement.execute();
		
		ResultSet resultSetBuscarH = statement.getResultSet();
		List<Map<String, String>> datosBuscarH = new ArrayList<Map<String, String>>();	
		while(resultSetBuscarH.next()) {
			Map<String, String> fila = new HashMap<String, String>();
			fila.put("id", String.valueOf(resultSetBuscarH.getInt("id")));
			fila.put("nombre", resultSetBuscarH.getString("nombre"));
			fila.put("apellido", resultSetBuscarH.getString("apellido"));
			fila.put("fecha_nacimiento", resultSetBuscarH.getString("fecha_nacimiento"));
			fila.put("nacionalidad", resultSetBuscarH.getString("nacionalidad"));
			fila.put("telefono", String.valueOf(resultSetBuscarH.getInt("telefono")));
			fila.put("id_reserva", String.valueOf(resultSetBuscarH.getInt("id_reserva")));
			datosBuscarH.add(fila);
			System.out.println("SALIENDO FILA " + fila);
		}
		con.close();
		return datosBuscarH;
	}

	
	
	public static List<Map<String, String>> buscarIdReserva(String busquedaIdReservas) throws SQLException {
		
		List<Map<String, String>> datosBuscarR = new ArrayList<Map<String, String>>();	
		
		System.out.println( "buscarIdReserva " + busquedaIdReservas);
		
		String letra = busquedaIdReservas;
        
        if(isNumeric(letra)) {
        
		Connection con = new conecctionfactory().recuperaConexion();
		
		PreparedStatement statement = con.prepareStatement("SELECT id, fecha_entrada, fechaSalida, valor, forma_de_pago FROM reservas WHERE id = ?");
		
		try {
			statement.setInt(1, Integer.parseInt(busquedaIdReservas));

		} catch (Exception e) {
			
		}
		
		statement.execute();
		
		ResultSet resultSetBuscarR = statement.getResultSet();
		//List<Map<String, String>> datosBuscarR = new ArrayList<Map<String, String>>();	
		while(resultSetBuscarR.next()) {
			Map<String, String> fila = new HashMap<String, String>();
			fila.put("id", String.valueOf(resultSetBuscarR.getInt("id")));
			fila.put("fecha_entrada", resultSetBuscarR.getString("fecha_entrada"));
			fila.put("fechaSalida", resultSetBuscarR.getString("fechaSalida"));
			fila.put("valor", String.valueOf(resultSetBuscarR.getInt("valor")));
			fila.put("forma_de_pago", resultSetBuscarR.getString("forma_de_pago"));
			datosBuscarR.add(fila);
			System.out.println("SALIENDO FILA Reserva " + fila);
		}
		con.close();
		
		return datosBuscarR;
	}
        return datosBuscarR;
	}
	
	
    
	
	public static boolean isNumeric(String s)
    {
        if (s == null || s.equals("")) {
            return false;
        }
 
        return s.chars().allMatch(Character::isDigit);
    }
}




























