package com.fuelconsumption.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fuelconsumption.adaptor.stubs.input.FuelInputStub;

@Path("/fuels")
public interface FuelRestApi {

	@GET
	@Path("/version")
	@Produces(MediaType.APPLICATION_JSON)
	Response getVersion();

	@GET
	@Path("/car/{plate}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getFuelsByCar(@PathParam("plate") String plate);

	@GET
	@Path("/gasstation/{gasstation}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getFuelsByGasStation(@PathParam("gasstation") String gasstation);

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	Response getFuels();

	@GET
	@Path("/list/from/{from}/to/{to}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getFuels(@PathParam("from") String from, @PathParam("to") String to);

	@GET
	@Path("/detailedlist")
	@Produces(MediaType.APPLICATION_JSON)
	Response getAggregatedFuels();

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response addFuel(final FuelInputStub fuelInputStub);

}
