package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservaDao {
	static int id = 0;
	
	public static void saveReserva(Reserva reserva) throws SQLException {
		
		Connection con = new conecctionfactory().recuperaConexion();
		Statement statement = con.createStatement();
		
		statement.execute("INSERT INTO reservas(fecha_entrada, fechaSalida, valor, forma_de_pago)"+ "VALUES ('"+
		reserva.getFechaEntrada()+"','"+ reserva.getFechaSalida() +"', '"+ reserva.getValor() + "','" +
				reserva.getForma_de_pago()+  "')", Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = statement.getGeneratedKeys();

		
		while(resultSet.next()) {
            System.out.println(String.format(
                    "El id de la reserva es : %d",
                    resultSet.getInt(1)));
            	id = resultSet.getInt(1);
        }
		con.close();
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		ReservaDao.id = id;
	}
	
}
