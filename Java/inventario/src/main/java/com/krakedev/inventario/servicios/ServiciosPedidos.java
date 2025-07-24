package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.PedidosBDD;
import com.krakedev.inventario.entidades.Pedido;

import com.krakedev.inventario.exepciones.KrakedevException;

@Path("pedidos")
public class ServiciosPedidos {

	

	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Pedido pedido) {
		PedidosBDD pedBDD = new PedidosBDD();
		try {
			pedBDD.insertar(pedido);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("recibir")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recibir(Pedido pedido) {
		PedidosBDD pedBDD = new PedidosBDD();
		try {
			pedBDD.recibir(pedido);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("buscar/{prov}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("prov")String proeveedor) {
		PedidosBDD pedBDD = new PedidosBDD();
		ArrayList<Pedido> pedidos = null;
		try {
			pedidos = pedBDD.buscar(proeveedor);
			
			int i = 1;
			System.out.println(pedidos.indexOf(i));
			
			return Response.ok(pedidos).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
}
