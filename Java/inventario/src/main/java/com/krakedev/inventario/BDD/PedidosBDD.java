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

import com.krakedev.inventario.entidades.DetallePedido;
import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventario.exepciones.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar(Pedido pedido) throws KrakedevException{
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psT = null;
		Date fechaActual = new Date();
		ResultSet rsClave = null;
		int codigoCabecera =0;
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime()); 
		
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("insert into cabecera_pedido(proveedores,fecha,estado)"
					+ "values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");
			ps.executeUpdate();
			rsClave = ps.getGeneratedKeys();
			
			if(rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			
			ArrayList<DetallePedido> detalles = pedido.getDetalles();
			DetallePedido det= null;
			for(int i =0;i<detalles.size();i++) {
				det = detalles.get(i);
				psT = con.prepareStatement("insert into detalle_pedido (cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida) "
						+ "values (?,?,?,?,?);");
				psT.setInt(1, codigoCabecera);
				psT.setInt(2,det.getProducto().getCodigo());
				psT.setInt(3, det.getCantidadSolicitada());
				
				//subtotal
				
				BigDecimal precioVenta =det.getProducto().getPrecioVenta();
				BigDecimal cantidad  = new BigDecimal(det.getCantidadSolicitada());
				BigDecimal subTotal =precioVenta.multiply(cantidad);
				
				psT.setBigDecimal(4, subTotal);
				psT.setInt(5, 0);
				psT.executeUpdate();
			}
			
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("error al consultar.detalle: " + e.getMessage());
		}
	}
	
public void recibir(Pedido pedido) throws KrakedevException{
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psT = null;
		PreparedStatement psS = null;
		
		
		
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("update cabecera_pedido set estado = ? where numero = ?");
			
			ps.setString(1, "R");
			ps.setInt(2, pedido.getCodigo());
		
			ps.executeUpdate();
			
			
			
			
			ArrayList<DetallePedido> detalles = pedido.getDetalles();
			DetallePedido det= null;
			for(int i =0;i<detalles.size();i++) {
				det = detalles.get(i);
				psT = con.prepareStatement("update detalle_pedido set subtotal = ?,cantidad_recibida = ? "
						+ "where codigo = ?");
				
				
				//subtotal
				
				BigDecimal precioVenta =det.getProducto().getPrecioVenta();
				BigDecimal cantidad  = new BigDecimal(det.getCantidadRecibida());
				BigDecimal subTotal =precioVenta.multiply(cantidad);
				
				psT.setBigDecimal(1, subTotal);
				psT.setInt(2, det.getCantidadRecibida());
				psT.setInt(3, det.getCodigo());
				psT.executeUpdate();
				
				Date fechaActual = new Date();
				Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());
				//historial Stock
				psS = con.prepareStatement("insert into historia_Stock (fecha,referecia,producto,cantidad) "
						+ "values (?,?,?,?)");
				psS.setTimestamp(1, fechaHoraActual);
				psS.setString(2, "pedido " + pedido.getCodigo());
				psS.setInt(3,det.getProducto().getCodigo());
				psS.setInt(4, det.getCantidadRecibida());
				psS.executeUpdate(); 
			}
			
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("error al consultar.detalle: " + e.getMessage());
		}
	}
	
}
