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
		 MenuPrincipal menu = new MenuPrincipal();
		 menu.setVisible(true);
		 menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	}
	 
}
