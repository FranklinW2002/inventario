package com.krakedev.inventario.BDD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.entidades.UnidadesMedida;
import com.krakedev.inventario.exepciones.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProductosBDD {
	public ArrayList<Producto> buscarProductos(String subcadena) throws KrakedevException {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto p = null;
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("select p.codigo_cat, p.nombre as nombre_producto, "
					+ "udm.nombre as nombre_udm, udm.descripcion as descripcion_udm, "
					+ "cast(p.precio_venta as decimal(6,2)), p.tiene_iva, "
					+ "cast(p.coste as decimal (5,4)), "
					+ "p.categoria, c.nombre as nombre_categoria, "
					+ "strock "
					+ "from  Productos p, Unidades_de_medida udm, categorias c "
					+ "where p.udm = udm.nombre "
					+ "and p.categoria = c.codigo_cat "
					+ "and upper (p.nombre) like ?");
			ps.setString(1,"%" + subcadena.toUpperCase() + "%" );
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigoP = rs.getInt("codigo_cat");
				String nombreP = rs.getString("nombre_producto");
				String nombreUnidadMedida= rs.getString("nombre_udm");
				String descripcionUnidadMedida= rs.getString("descripcion_udm");
				BigDecimal precioventa =rs.getBigDecimal("precio_venta");
				boolean tieneIVA =rs.getBoolean("tiene_iva");
				BigDecimal costo =rs.getBigDecimal("coste");
				int codigoC = rs.getInt("categoria");
				String nombreC = rs.getString("nombre_categoria");
				int stock = rs.getInt("strock");
				
				UnidadesMedida udm =new UnidadesMedida();
				udm.setNombre(nombreUnidadMedida);
				udm.setDescripcion(descripcionUnidadMedida);
				
				Categoria c=new Categoria();
				c.setNombre(nombreC);
				c.setCodigo(codigoC);
				
				p=	new Producto(codigoP,nombreP,udm,precioventa,tieneIVA,costo,c,stock);

				productos.add(p);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("error al consultar.detalle: " + e.getMessage());
		}
		return productos;

	}
	
	
	public void insertarproductos(Producto producto) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("insert into Productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,strock) "
					+ "values (?,?,?,?,?,?,?);");
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUnidadMedida().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.isTieneIVA());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getStock());
	
			ps.executeUpdate();
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("error al consultar.detalle: " + e.getMessage());
		}

	}
}
