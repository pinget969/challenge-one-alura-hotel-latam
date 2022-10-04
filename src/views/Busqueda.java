package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ControlHotel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	//Valores de las Reservas:
	private void cargarTablaReserva() {
		try {
			List<Map<String, String>> reservas = ControlHotel.listar() ;
			
			try {
				reservas.forEach(reserva -> modelo.addRow(
			                new Object[] {
			                		reserva.get("id"),
			                		reserva.get("fecha_entrada"),
			                		reserva.get("fechaSalida"),
			                		reserva.get("valor"),
			                		reserva.get("forma_de_pago") }));
			} catch (Exception e){
				throw e;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	//Valores de los Huespedes:
	private void cargarTablaHuespedes() {
		try {
			List<Map<String, String>> reservas = ControlHotel.listar2() ;
			
			try {
				reservas.forEach(reserva -> modeloH.addRow(
			                new Object[] {
			                		reserva.get("id"),
			                		reserva.get("nombre"),
			                		reserva.get("apellido"),
			                		reserva.get("fecha_nacimiento"),
			                		reserva.get("nacionalidad"),
			                		reserva.get("telefono"),
			                		reserva.get("id_reserva") }));
			} catch (Exception e){
				throw e;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 380, 42);
		contentPane.add(lblNewLabel_4);
		
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtBuscar.setText("ID / Apellido");
		
		txtBuscar.setFont(new Font("Roboto Black", Font.BOLD, 13));
		
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
/*HOLA
		JLabel etiqueta = new JLabel();
		etiqueta.setText("hola");
		etiqueta.setBounds(10, 11, 865, 300);
		contentPane.add(etiqueta);
		*/
		
		tbReservas = new JTable();
		
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		JScrollPane paneScroll = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), paneScroll, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Entrada");
		modelo.addColumn("Fecha Salida");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		cargarTablaReserva(); 
				
				
		tbHuespedes = new JTable();
		tbHuespedes.setRowHeight(25);
		tbHuespedes.setSelectionBackground(new Color(173, 237, 205));
		tbHuespedes.setGridColor(new Color(148,115,122));
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		JScrollPane paneScroll2 = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), paneScroll2, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		cargarTablaHuespedes();
		
		DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
			
		// rendar1.setForeground(new Color(148,115,122));	gris
	    rendar1.setForeground(new Color(30,125,222));	  //Celeste
	    tbReservas.getColumnModel().getColumn(0).setCellRenderer(rendar1);
	    tbHuespedes.getColumnModel().getColumn(6).setCellRenderer(rendar1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		//Color LINEAS CUADRO
		tbReservas.setRowHeight(25);
		tbReservas.setSelectionBackground(new Color(173, 237, 205));
		tbReservas.setGridColor(new Color(148,115,122));
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100-01px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtBuscar.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Ingrese un Apellido o número ID de reserva",
							  "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
					
					limpiar();
					cargarTablaHuespedes();
					cargarTablaReserva();
				} else {
					try {
						limpiar();
						buscarApellidoHuesped();
						buscarIdReserva();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar();
			}
		});
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					eliminar();
					JOptionPane.showMessageDialog(null, "Reserva y Huesped borrado con exito",
							  "Información", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiar();
				cargarTablaReserva();
				cargarTablaHuespedes();
			}
		});
		
		
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	public void limpiar() {
		modeloH.setRowCount(0); 
		modelo.setRowCount(0);
	}
	public void eliminar() throws SQLException {
		System.out.println("ELIMINADO");
		int filaH = tbHuespedes.getSelectedRowCount(); //Nos dirá que fila es seleccionada de Huesped
		int filaR = tbReservas.getSelectedRowCount();
		//Boolean validarEliminar ;
		boolean validarEliminarHuesped=(filaH == 0); 
		boolean validarEliminarReserva=(filaR == 0); //TRUE al no seleccionar
		
		if(validarEliminarHuesped && validarEliminarReserva) {
			System.out.println("Debe selecionar el elemento que desea borrar ");
		} else if (filaH > 0) {
				 ControlHotel.deleteDatosHuesped(tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0).toString()); //Eliminando lo seleccionado 
				 ControlHotel.deleteDatosReservaViculada(tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
				 System.out.println("SALIENDO ID HUESPED "+ tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			
		} else if(filaR > 0) {
			System.out.println("Borrando Reserva y Huesped vinculado");
	
				ControlHotel.deleteDatosHuespedViculado(tbReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
				ControlHotel.deleteDatosReserva(tbReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
		}
		else {
			System.out.println("Error Inesperado");
		}
	}
	public void editar() {
		int row = tbReservas.getSelectedRow();
		int row2 = tbHuespedes.getSelectedRow();
		
		
		
		boolean validarEditarReserva=(tbReservas.getSelectedRow() >= 0);
		boolean validarEditarHuesped=(tbHuespedes.getSelectedRow()>= 0); //return False
		
		System.out.println("1 " +validarEditarReserva + tbReservas.getSelectedRow());
		
		if(!validarEditarReserva && !validarEditarHuesped) {
			
			
			JOptionPane.showMessageDialog(null, "Debe selecionar el elemento que desea Editar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
		} else if(validarEditarReserva) {
			System.out.println("2 " +validarEditarReserva + tbReservas.getSelectedRow());
		
		Map<String, String> modificarReserva = new HashMap<String, String>();
		String editarReservaFilaID = (String) tbReservas.getModel().getValueAt(row, 0);
		modificarReserva.put("id", editarReservaFilaID);
		modificarReserva.put("fecha_entrada", (String) tbReservas.getModel().getValueAt(row, 1));
		modificarReserva.put("fechaSalida", (String) tbReservas.getModel().getValueAt(row, 2));
		modificarReserva.put("valor", (String) tbReservas.getModel().getValueAt(row, 3));
		modificarReserva.put("forma_de_pago", (String) tbReservas.getModel().getValueAt(row, 4));
		try {
			System.out.println("3 " +validarEditarReserva);
			ControlHotel.editarReserva(modificarReserva);
			JOptionPane.showMessageDialog(null, "Modificación de Reserva exitosa",
					  "Informacion", JOptionPane.INFORMATION_MESSAGE);
			limpiar();
			cargarTablaReserva();
			cargarTablaHuespedes();
			
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		}else if(validarEditarHuesped) {
			Map<String, String> modificarHuesped = new HashMap<String, String>();
			String editarHuespedFilaID = (String) tbHuespedes.getModel().getValueAt(row2, 0);
			System.out.println("editando Huesped id "+ editarHuespedFilaID);
			System.out.println("aca 1 " +tbHuespedes.getModel().getValueAt(row2, 0));
			System.out.println("aca " +tbHuespedes.getModel().getValueAt(row2, 1));
			System.out.println("aca 3 " +tbHuespedes.getModel().getValueAt(row2, 2));
			System.out.println("aca " +tbHuespedes.getModel().getValueAt(row2, 3));
			System.out.println("aca 5 " +tbHuespedes.getModel().getValueAt(row2, 4));
			System.out.println("aca " +tbHuespedes.getModel().getValueAt(row2, 5));
			System.out.println("aca 7 " +tbHuespedes.getModel().getValueAt(row2, 6));
			
			modificarHuesped.put("id", editarHuespedFilaID);
			modificarHuesped.put("nombre", (String) tbHuespedes.getModel().getValueAt(row2, 1));
			modificarHuesped.put("apellido", (String) tbHuespedes.getModel().getValueAt(row2, 2));
			modificarHuesped.put("fecha_nacimiento", (String) tbHuespedes.getModel().getValueAt(row2, 3));
			modificarHuesped.put("nacionalidad", (String) tbHuespedes.getModel().getValueAt(row2, 4));
			modificarHuesped.put("telefono", (String) tbHuespedes.getModel().getValueAt(row2, 5));
			
			try {
				ControlHotel.editarHuesped(modificarHuesped);
				JOptionPane.showMessageDialog(null, "Modificación de Huesped exitoso",
						  "Información", JOptionPane.INFORMATION_MESSAGE);
				limpiar();
				cargarTablaReserva();
				cargarTablaHuespedes();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Error inesperado Edicion Huesped");
		}
	}
	
	
	public void buscarApellidoHuesped() throws SQLException{
		//APELLIDO Y Nº DE RESERVA
			String id_reserva ="0";
			String busquedaApellidoHuesped =  txtBuscar.getText();
			List<Map<String, String>> busquedaH = new ArrayList<Map<String, String>>();
			
			try {
				busquedaH = ControlHotel.buscarApellido(busquedaApellidoHuesped);
				busquedaH.forEach(busqueda -> modeloH.addRow(
						new Object[] {
								busqueda.get("id"),
								busqueda.get("nombre"),
								busqueda.get("apellido"),
								busqueda.get("fecha_nacimiento"),
								busqueda.get("nacionalidad"),
								busqueda.get("telefono"),
								busqueda.get("id_reserva"),
						}));
			}catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
				
			}
			
			try {
				 //EXTRACCIÓN ID_RESERVA DE HUESPED
				 for ( Map<String, String> key : busquedaH ) {
					 id_reserva =  key.get("id_reserva");
					 ControlHotel.buscarIdReserva(id_reserva);
			        }
				
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			List<Map<String, String>> busquedaRId = new ArrayList<Map<String, String>>();
			
			try {
				busquedaRId = ControlHotel.buscarIdReserva(id_reserva);
				busquedaRId.forEach(e -> modelo.addRow(
						new Object[] {
								e.get("id"),
								e.get("fecha_entrada"),
								e.get("fechaSalida"),
								e.get("valor"),
								e.get("forma_de_pago"),
						}));
				
			}catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
		}
	
	
	
	public void buscarIdReserva() {
		String busquedaIdReserva = txtBuscar.getText();
		
		List<Map<String, String>> busquedaR = new ArrayList<Map<String, String>>();
		
		try {
			busquedaR = ControlHotel.buscarIdReserva(busquedaIdReserva);
			busquedaR.forEach(e -> modelo.addRow(
					new Object[] {
							e.get("id"),
							e.get("fecha_entrada"),
							e.get("fechaSalida"),
							e.get("valor"),
							e.get("forma_de_pago"),
					}));
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		List<Map<String, String>> busquedaH = new ArrayList<Map<String, String>>();
		try {
			busquedaH = ControlHotel.buscarIdHuesped(busquedaIdReserva);
			busquedaH.forEach(busqueda -> modeloH.addRow(
					new Object[] {
							busqueda.get("id"),
							busqueda.get("nombre"),
							busqueda.get("apellido"),
							busqueda.get("fecha_nacimiento"),
							busqueda.get("nacionalidad"),
							busqueda.get("telefono"),
							busqueda.get("id_reserva"),
					}));
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
