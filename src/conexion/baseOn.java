package conexion;

import java.sql.Connection;

import java.sql.SQLException;

import javax.swing.JFrame;

import views.Busqueda;
import views.Login;
import views.MenuPrincipal;

public class baseOn {
	 public static void main(String[] args) throws SQLException{
		 
		 Busqueda control = new Busqueda();
		 control.setVisible(true);
		 control.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 MenuPrincipal menu = new MenuPrincipal();
		 menu.setVisible(true);
		 
		 ControlLogin.loginOn();
		 //ValidarLogin.validarIngreso();
		// String saludo = ValidarLogin.validarIngreso("wea");
		// String suma = ValidarLogin.validarIngreso("wea");
		// System.out.println(suma);
		 //Login.ValidarUsuario();
		 
		 
	}
	 
}