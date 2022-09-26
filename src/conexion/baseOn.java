package conexion;

import java.sql.Connection;

import java.sql.SQLException;

import javax.swing.JFrame;

import views.Busqueda;
import views.Login;
import views.MenuPrincipal;
import views.RegistroHuesped;
import views.ReservasView;

public class baseOn {
	 public static void main(String[] args) throws SQLException{
		 
		 //Busqueda control = new Busqueda();
		 //control.setVisible(true);
		
		 
		 MenuPrincipal menu = new MenuPrincipal();
		 menu.setVisible(true);
		 menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 //ControlLogin.loginOn(); //Prueba de login
		 //ValidarLogin.validarIngreso();
		// String saludo = ValidarLogin.validarIngreso("wea");
		// String suma = ValidarLogin.validarIngreso("wea");
		// System.out.println(suma);
		// Login.ValidarUsuario(); 
		//ReservasView.verificadorFecha();
		
		 //RegistroHuesped.datosHuesped();
		
		 
		 
		 
	}
	 
}
