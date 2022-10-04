package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import conexion.conecctionfactory;
import views.Login;

public class ControlLogin {
	public static boolean loginOn() throws SQLException{
		Connection con = new conecctionfactory().recuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("SELECT id, usuario, password FROM login");
		ResultSet resultSet = statement.getResultSet();
		System.out.println("conexcion correcta Login");
		
		boolean ingresoCorrecto = false;
		
		Login.ValidarUsuario();
		Map<String, String> admin;
		
		admin = Login.ValidarUsuario();

		while(resultSet.next()) {
			System.out.println("revisando la wea " +resultSet.getString("usuario")); 
				
			System.out.println(" USUARIO " +resultSet.getString("usuario")); 
			System.out.println(" USUARIO INGRESADO: " + admin.keySet());
			System.out.println("Comparando USUARIOS : " +admin.keySet().contains(resultSet.getString("usuario")));
			
			
			System.out.println("CONTRASEÑA " + resultSet.getString("password"));
			System.out.println("CONTRASEÑA INGRESADA " + admin.values());
			System.out.println("comparando pass : " + admin.values().contains(resultSet.getString("password")));
			
			if( admin.keySet().contains(resultSet.getString("usuario"))) {
				System.out.println("USUARIO CORRECTO");
				if(admin.values().contains(resultSet.getString("password"))) {
					System.out.println("PASS CORRECTA");
					System.out.println("INGRESO EXITOSO");
					ingresoCorrecto = true;
				}
			}		
			
		}
		
		con.close();
		
		return ingresoCorrecto;
	}
	
	
}

