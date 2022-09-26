package conexion;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;


public class ControlReserva {
	
	public static List<String> datosHuesped(JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono, JDateChooser txtFechaN, JComboBox<Format> txtNacionalidad){
		String nombreHuesped;
		String apellidoHuesped;
		String fechaNacimientoHuesped;
		String nacionalidadHuesped;
		String telefonoHuesped;
		
		//Número de reserva que fue generada anteriormente. en RESERVAS VIEW
		
		
		nombreHuesped = txtNombre.getText();
		apellidoHuesped = txtApellido.getText();
		SimpleDateFormat sdf = new SimpleDateFormat();
		fechaNacimientoHuesped = sdf.format(txtFechaN.getDate());
		nacionalidadHuesped = (String) txtNacionalidad.getSelectedItem();
		telefonoHuesped = txtTelefono.getText();
		//Numero RESERVA AQUI
		
		
		System.out.println(nombreHuesped);
		System.out.println(apellidoHuesped);
		System.out.println(nacionalidadHuesped);
		System.out.println(fechaNacimientoHuesped);
		System.out.println(telefonoHuesped);
		
		List<String> huesped = new ArrayList<String>();
		huesped.add(nombreHuesped);
		
		
		return huesped;
	}

}
