package huesped;

public class Huesped {
	String nombre;
	String apellido;
	String fechaNacimiento;
	String nacionalidad;
	int telefono;
	int idReserva;
	
	
	public Huesped() {
		super();
	}
	public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, int telefonoHuesped, int idReserva) {
//	public Huesped(JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono, JDateChooser txtFechaN, JComboBox<Format> txtNacionalidad, int idReserva) {
	
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefonoHuesped;
		this.idReserva = idReserva;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	
}

