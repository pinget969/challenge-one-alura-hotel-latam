package conexion;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HuespedDAO {
	static int id = 0;
	public static int saveReserva(Huesped huesped) throws SQLException {
		//private PreparedStatement PS;
		
		Connection con = new conecctionfactory().recuperaConexion();
		Statement statement = con.createStatement();
		
		
		statement.execute("INSERT INTO huespedes(nombre, apellido, fecha_nacimiento, nacionalidad,telefono, id_reserva)"+ "VALUES ('"+ 
		huesped.getNombre()+"','"+ huesped.getApellido() +"', '"+ huesped.getFechaNacimiento() +
		"','" +huesped.getNacionalidad()+ "','" +huesped.getTelefono()+ "','" +huesped.getIdReserva() + "')", Statement.RETURN_GENERATED_KEYS);
		
		//PS = null;
		
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		
		
		while(resultSet.next()) {
            System.out.println(String.format(
                    "El id de la reserva es : %d",
                    resultSet.getInt(1)));
            	id = resultSet.getInt(1);
        }
		con.close();
		
		return id;
	}
}
