package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Eliminar  {
	public static String eliminarHuesped(String id) throws SQLException {
		Connection con = new conecctionfactory().recuperaConexion();
		//Statement statement = con.createStatement();
		
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID = id");

			try (statement) {
				
				statement.setInt(1, Integer.parseInt(id));
				statement.execute();

				int updateCount = statement.getUpdateCount();
				System.out.println("ELIMINACION " + updateCount);
				return id;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO PUEDE ELIMINAR UNA RESERVA QUE TENGA ASOCIADOS HUÉSPEDES");
			return id;
		}
	}
	public void modificar() {
		
	}
	
}
