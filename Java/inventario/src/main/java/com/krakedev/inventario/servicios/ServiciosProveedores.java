package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.exepciones.KrakedevException;

@Path("proveedores")
public class ServiciosProveedores {

	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("sub") String subCadena) {

		ArrayList<Proveedor> proveedores = null;
		ProveedoresBDD provBDD = new ProveedoresBDD();
		try {
			proveedores = provBDD.buscar(subCadena);
			return Response.ok(proveedores).build();
		} catch (KrakedevException e) {

			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearProveedor(Proveedor proveedor) {

		ProveedoresBDD provBDD = new ProveedoresBDD();

		try {
			provBDD.agregarProveedor(proveedor);
			return Response.ok().build();
		} catch (KrakedevException e) {

			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@Path("buscarProveedor/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarProveedor(@PathParam("sub") String subCadena) {

		Proveedor p = null;
		ProveedoresBDD provBDD = new ProveedoresBDD();
		try {
			p = provBDD.buscarProveedor(subCadena);
			return Response.ok(p).build();
		} catch (KrakedevException e) {

			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
