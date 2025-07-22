package com.krakedev.inventario.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CabeceraVentas {
	
	private int codigo;
	private Date fecha; 
	private BigDecimal totalSinIva;
	private double iva;
	private BigDecimal total;
	private ArrayList<DetalleVentas> detalle;
	
	public CabeceraVentas() {
		super();
		
	}
	
	
	
	
	public CabeceraVentas(int codigo, Date fecha, BigDecimal totalSinIva, double iva, BigDecimal total,
			ArrayList<DetalleVentas> detalle) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.totalSinIva = totalSinIva;
		this.iva = iva;
		this.total = total;
		this.detalle = detalle;
	}




	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getTotalSinIva() {
		return totalSinIva;
	}
	public void setTotalSinIva(BigDecimal totalSinIva) {
		this.totalSinIva = totalSinIva;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public ArrayList<DetalleVentas> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<DetalleVentas> detalle) {
		this.detalle = detalle;
	}




	@Override
	public String toString() {
		return "CabeceraVentas [codigo=" + codigo + ", fecha=" + fecha + ", totalSinIva=" + totalSinIva + ", iva=" + iva
				+ ", total=" + total + ", detalle=" + detalle + "]";
	}
	
	
	
}
