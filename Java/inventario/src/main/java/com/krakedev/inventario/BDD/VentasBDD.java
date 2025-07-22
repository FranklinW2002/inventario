package com.krakedev.inventario.BDD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventario.entidades.CabeceraVentas;
import com.krakedev.inventario.entidades.DetalleVentas;
import com.krakedev.inventario.exepciones.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class VentasBDD {

	
public void insertar(CabeceraVentas cabecera) throws KrakedevException{
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psT = null;
		PreparedStatement psA = null;
		PreparedStatement psS = null;
		BigDecimal cero=  new BigDecimal("0");
		BigDecimal totalSinIva=  new BigDecimal("0");
		BigDecimal ivaS=  new BigDecimal("0");
		BigDecimal total=  new BigDecimal("0");
		
		
		Date fechaActual = new Date();
		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());
		
		ResultSet rsClave = null;
		int codigoCabecera =0;
		
		
		
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("insert into caberas_ventas (fecha,total_sin_iva,iva,total) "
					+ "values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			ps.setTimestamp(1, fechaHoraActual);
			ps.setBigDecimal(2, cero);
			ps.setBigDecimal(3, cero);
			ps.setBigDecimal(4, cero);
			ps.executeUpdate();
			rsClave = ps.getGeneratedKeys();
			
			if(rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			ArrayList<DetalleVentas> ventas = cabecera.getDetalle();
			
			DetalleVentas det= null;
			
			for(int i =0;i<ventas.size();i++) {
				det = ventas.get(i);
				psT = con.prepareStatement("insert into detalle_ventas(cabecera_ventas,producto,cantidad,precio_venta,subtotal,subtotal_con_iva)"
						+ "values (?,?,?,?,?,?)");
				psT.setInt(1, codigoCabecera);
				psT.setInt(2, det.getProducto().getCodigo());
				psT.setInt(3, det.getCantidad());
				psT.setBigDecimal(4, det.getProducto().getPrecioVenta());
				
				BigDecimal precioVenta =det.getProducto().getPrecioVenta();
				BigDecimal cantidad  = new BigDecimal(det.getCantidad());
				BigDecimal subTotal =precioVenta.multiply(cantidad);
				totalSinIva = totalSinIva.add(subTotal);
				psT.setBigDecimal(5, subTotal);
				
				if(det.getProducto().isTieneIVA()) {
					BigDecimal iva  = new BigDecimal(0.15);
					BigDecimal TotalIva =subTotal.multiply(iva);
					BigDecimal subTotalIva = subTotal.add(TotalIva);
					ivaS = ivaS.add(TotalIva);
					total = total.add(subTotalIva);
					psT.setBigDecimal(6, subTotalIva);
				}else {
					psT.setBigDecimal(6, cero);
				}
				psT.executeUpdate();
				
				psS = con.prepareStatement("insert into historia_Stock (fecha,referecia,producto,cantidad) "
						+ "values (?,?,?,?)");
				psS.setTimestamp(1, fechaHoraActual);
				psS.setString(2, "venta " + codigoCabecera);
				psS.setInt(3, det.getProducto().getCodigo());
				psS.setInt(4, (-1)*det.getCantidad());
				psS.executeUpdate();
			}
			
			
			
			psA = con.prepareStatement("update caberas_ventas set total_sin_iva = ?,iva = ?,total=? "
					+ "where codigo = ?");
			psA.setBigDecimal(1, totalSinIva);
			psA.setBigDecimal(2, ivaS);
			psA.setBigDecimal(3, total);
			psA.setInt(4, codigoCabecera);
			psA.executeUpdate();
			
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("error al consultar.detalle: " + e.getMessage());
		}
	}
	
}
