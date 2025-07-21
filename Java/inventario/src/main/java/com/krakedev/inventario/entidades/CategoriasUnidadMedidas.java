package com.krakedev.inventario.entidades;

public class CategoriasUnidadMedidas {
	private String codigo;
	private String nombre;
	
	public CategoriasUnidadMedidas() {
		super();
	}
	
	public CategoriasUnidadMedidas(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "CategoriasUnidadMedidas [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
