
package com.krakedev.inventario.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.krakedev.inventario.exepciones.KrakedevException;

public class ConexionBDD {
	
	public static Connection obtenerConeccion() throws KrakedevException {
		Context ctx=null;
		DataSource ds=null;
		Connection con=null;
			try {
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/ConexionInventario");
				con = ds.getConnection();
			} catch (NamingException | SQLException e) {
				
				e.printStackTrace();
				throw new KrakedevException("Error de coneccion");
				
			} 
			return con;
			
	}
}
