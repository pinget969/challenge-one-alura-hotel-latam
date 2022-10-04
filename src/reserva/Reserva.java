package reserva;

public class Reserva {
	private String fechaEntrada;
	private String fechaSalida;
	private int valor;
	private String forma_de_pago;
	private int id;
	
	public Reserva() {
	}

	public Reserva(String fechaEntrada, String fechaSalida, int valor, String forma_de_pago, int id) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.forma_de_pago = forma_de_pago;
		this.id = id;
	}


	public Reserva(String fechaEntrada, String fechaSalida, int valor, String forma_de_pago) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.forma_de_pago = forma_de_pago;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getForma_de_pago() {
		return forma_de_pago;
	}
	public void setForma_de_pago(String forma_de_pago) {
		this.forma_de_pago = forma_de_pago;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
