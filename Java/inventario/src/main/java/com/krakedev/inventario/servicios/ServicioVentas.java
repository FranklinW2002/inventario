package com.krakedev.inventario.servicios;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.krakedev.inventario.BDD.VentasBDD;
import com.krakedev.inventario.entidades.CabeceraVentas;
import com.krakedev.inventario.exepciones.KrakedevException;


@Path("ventas")
public class ServicioVentas {

	
	@Path("guardar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(CabeceraVentas cabecera) {
		VentasBDD venta = new VentasBDD();
		
		try {
			venta.insertar(cabecera);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
}
