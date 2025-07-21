package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}
