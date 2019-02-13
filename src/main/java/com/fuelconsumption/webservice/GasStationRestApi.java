package com.fuelconsumption.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fuelconsumption.adaptor.stubs.input.GasStationInputStub;

@Path("/gasstation")
@Produces(MediaType.APPLICATION_JSON)
public interface GasStationRestApi {

	@GET
	@Path("/list")
	Response getGasStations();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	Response addGasStation(final GasStationInputStub stub);

	@PUT
	@Path("/{oldname}/{newname}")
	Response modifyGasStation(@PathParam("oldname") final String oldName, @PathParam("newname") final String newName);

	@DELETE
	@Path("/{name}")
	Response deleteGasStation(@PathParam("name") final String name);

	@GET
	@Path("/listquantities")
	Response getQuantities();

	@GET
	@Path("/listquantities/{name}")
	Response getQuantities(@PathParam("name") final String name);

}
