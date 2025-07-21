package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProductosBDD;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.exepciones.KrakedevException;


@Path("productos")
public class ServiciosProductos {

	@Path("buscarProductos/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarProductos(@PathParam("sub") String subCadena) {

		ArrayList<Producto> Productos = null;
		ProductosBDD prodBDD = new ProductosBDD();
		try {
			Productos = prodBDD.buscarProductos(subCadena);
			return Response.ok(Productos).build();
		} catch (KrakedevException e) {

			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
}
