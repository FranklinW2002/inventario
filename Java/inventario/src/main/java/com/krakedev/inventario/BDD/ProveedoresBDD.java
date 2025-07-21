package com.krakedev.inventario.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.exepciones.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProveedoresBDD {

	
	public ArrayList<Proveedor> buscar(String subCadena) throws KrakedevException{
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs=null;
		Proveedor proveedor =null;
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("select prov.identificador,prov.tipo_documento,td.descripcion,prov.nombre,prov.telefono,prov.correo, prov.direccion "
					+ "from proveedores prov, tipo_documento td "
					+ "where prov.tipo_documento = td.codigo "
					+ "and upper (nombre) like ?");
			ps.setString(1,"%"+ subCadena.toUpperCase()+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				String identificador =rs.getString("identificador");
				String codigoTipoDocumento =rs.getString("tipo_documento");
				String descripcionTipoDocumento =rs.getString("descripcion"); 
				String nombre =rs.getString("nombre");
				String telefono =rs.getString("telefono");
				String correo =rs.getString("correo");
				String direccion =rs.getString("direccion");
				TipoDocumento tipoD = new TipoDocumento(codigoTipoDocumento,descripcionTipoDocumento);
				
				proveedor = new Proveedor(identificador, tipoD, nombre, telefono, correo, direccion);
				proveedores.add(proveedor);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw new KrakedevException("Error al consultar detalle:"+e.getMessage());
			
		}
		
		
		return proveedores;
	}
	
	
	public ArrayList<TipoDocumento> buscarTiposDocumento() throws KrakedevException{
		ArrayList<TipoDocumento> tiposDocumentos = new ArrayList<TipoDocumento>();
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs=null;
		TipoDocumento tipoDocumento ;
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("select codigo,descripcion from tipo_documento");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				String codigo =rs.getString("codigo");
				String descripcion =rs.getString("descripcion");
				tipoDocumento = new TipoDocumento(codigo, descripcion);
				tiposDocumentos.add(tipoDocumento);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw new KrakedevException("Error al consultar detalle:"+e.getMessage());
			
		}
		
		
		return tiposDocumentos;
	}
	
	
	public void agregarProveedor(Proveedor proveedor) throws KrakedevException{
		
		Connection con = null;
		PreparedStatement ps;
		
		
		try {
			con = ConexionBDD.obtenerConeccion();
			ps = con.prepareStatement("insert into proveedores (identificador,tipo_documento,nombre,telefono,correo,direccion) "
					+ " values (?,?,?,?,?,?)");
			ps.setString(1,proveedor.getIdentificador());
			ps.setString(2,proveedor.getTipoDocumeto().getCodigo());
			ps.setString(3,proveedor.getNombre());
			ps.setString(4,proveedor.getTelefono());
			ps.setString(5,proveedor.getCorreo());
			ps.setString(6,proveedor.getDireccion());
			
			ps.executeUpdate();
			
			
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw new KrakedevException("Error al consultar detalle:"+e.getMessage());
			
		}
		
		
		
	}
}
