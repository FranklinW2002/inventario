package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.CategoriaBDD;
import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.exepciones.KrakedevException;

@Path("categoria")
public class ServicioCategoria {

	

	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Categoria categoria) {
		
		CategoriaBDD CatBDD = new CategoriaBDD();
		try {
			CatBDD.insertar(categoria);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Categoria categoria) {
		
		CategoriaBDD CatBDD = new CategoriaBDD();
		try {
			CatBDD.actualizar(categoria);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
