package com.krakedev.inventario.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.exepciones.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class CategoriaBDD {
	
	
	public void insertar(Categoria categoria) throws KrakedevException {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("insert into categorias (nombre,categoria_padre) "
					+ "values (?,?);");
			ps.setString(1, categoria.getNombre());
			ps.setInt(2, categoria.getCategoriaPadre().getCodigo());
			ps.executeUpdate();

		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("error al consultar.detalle: " + e.getMessage());
		}
	}
	public void actualizar(Categoria categoria) throws KrakedevException {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("update categorias set nombre=?,categoria_padre =? where codigo_cat =? ");
			ps.setString(1, categoria.getNombre());
			ps.setInt(2, categoria.getCategoriaPadre().getCodigo());
			ps.setInt(3, categoria.getCodigo());
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
