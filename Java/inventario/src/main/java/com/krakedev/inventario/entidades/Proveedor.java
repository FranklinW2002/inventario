package com.krakedev.inventario.entidades;

public class Proveedor {
	
	private String identificador;
	private TipoDocumento tipoDocumeto;
	private String nombre;
	private String telefono;
	private String correo;
	private String Direccion;
	private String EstadoPedidoP;
	
	
	
	public Proveedor() {
		super();
	}
	
	
	
	public Proveedor(String identificador, String nombre, String telefono, String correo, String direccion,
			String estadoPedidoP) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		Direccion = direccion;
		EstadoPedidoP = estadoPedidoP;
	}



	public Proveedor(String identificador, TipoDocumento tipoDocumeto, String nombre, String telefono, String correo,
			String direccion) {
		super();
		this.identificador = identificador;
		this.tipoDocumeto = tipoDocumeto;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		Direccion = direccion;
	}



	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public TipoDocumento getTipoDocumeto() {
		return tipoDocumeto;
	}
	public void setTipoDocumeto(TipoDocumento tipoDocumeto) {
		this.tipoDocumeto = tipoDocumeto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}



	@Override
	public String toString() {
		return "Proveedor [identificador=" + identificador + ", tipoDocumeto=" + tipoDocumeto + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", correo=" + correo + ", Direccion=" + Direccion + "]";
	}



	public String getEstadoPedidoP() {
		return EstadoPedidoP;
	}



	public void setEstadoPedidoP(String estadoPedidoP) {
		EstadoPedidoP = estadoPedidoP;
	}
	
	
	
	
}
