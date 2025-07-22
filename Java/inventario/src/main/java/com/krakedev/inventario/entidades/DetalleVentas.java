package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class DetalleVentas {
	private int codigo;
	private int cabeceraVentas;
	private Producto producto;
	private int cantidad;
	private BigDecimal precioVenta;
	private BigDecimal subTotal;
	private BigDecimal subTotalIva;
	
	public DetalleVentas() {
		super();
		
	}
	
	
	public DetalleVentas(int codigo, int cabeceraVentas, Producto producto, int cantidad, BigDecimal precioVenta,
			BigDecimal subTotal, BigDecimal subTotalIva) {
		super();
		this.codigo = codigo;
		this.cabeceraVentas = cabeceraVentas;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.subTotal = subTotal;
		this.subTotalIva = subTotalIva;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCabeceraVentas() {
		return cabeceraVentas;
	}
	public void setCabeceraVentas(int cabeceraVentas) {
		this.cabeceraVentas = cabeceraVentas;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getSubTotalIva() {
		return subTotalIva;
	}
	public void setSubTotalIva(BigDecimal subTotalIva) {
		this.subTotalIva = subTotalIva;
	}
	
	@Override
	public String toString() {
		return "DetalleVentas [codigo=" + codigo + ", cabeceraVentas=" + cabeceraVentas + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", subTotal=" + subTotal
				+ ", subTotalIva=" + subTotalIva + "]";
	}
	
	
	
	
}
