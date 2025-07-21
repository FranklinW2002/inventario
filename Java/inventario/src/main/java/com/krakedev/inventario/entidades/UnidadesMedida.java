package com.krakedev.inventario.entidades;

public class UnidadesMedida {
	private String nombre;
	private String descripcion;
	private CategoriasUnidadMedidas CategoriaUDM;
	
	public UnidadesMedida() {
		super();
	}

	public UnidadesMedida(String nombre, String descripcion, CategoriasUnidadMedidas categoriaUDM) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		CategoriaUDM = categoriaUDM;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriasUnidadMedidas getCategoriaUDM() {
		return CategoriaUDM;
	}

	public void setCategoriaUDM(CategoriasUnidadMedidas categoriaUDM) {
		CategoriaUDM = categoriaUDM;
	}

	@Override
	public String toString() {
		return "UnidadesMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", CategoriaUDM=" + CategoriaUDM
				+ "]";
	}
}
