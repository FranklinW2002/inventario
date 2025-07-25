package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.exepciones.KrakedevException;


@Path("tiposDocumentos")
public class ServiciosTipoDocumentos {

	
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarTipoDocumento(){
		
		ArrayList<TipoDocumento> tipoDocumento = null;
		ProveedoresBDD provBDD = new ProveedoresBDD();
		try {
			tipoDocumento = provBDD.buscarTiposDocumento();
			return Response.ok(tipoDocumento).build();
		} catch (KrakedevException e) {

			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
}
