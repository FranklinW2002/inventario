package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class DetallePedido {
	private int codigo;
	private Pedido cabecera;
	private Producto producto;
	private int cantidadSolicitada;
	private BigDecimal subTotal;
	private int cantidadRecibida;
	
	public DetallePedido() {
		super();
	}
	
	public DetallePedido(int codigo, Producto producto, int cantidadSolicitada, BigDecimal subTotal,
			int cantidadRecibida) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.cantidadSolicitada = cantidadSolicitada;
		this.subTotal = subTotal;
		this.cantidadRecibida = cantidadRecibida;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public int getCantidadRecibida() {
		return cantidadRecibida;
	}
	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}
	@Override
	public String toString() {
		return "DetallePedido [codigo=" + codigo + ", producto=" + producto + ", cantidadSolicitada="
				+ cantidadSolicitada + ", subTotal=" + subTotal + ", cantidadRecibida=" + cantidadRecibida + "]";
	}

	public Pedido getCabecera() {
		return cabecera;
	}

	public void setCabecera(Pedido cabecera) {
		this.cabecera = cabecera;
	}
	
	
	
}
