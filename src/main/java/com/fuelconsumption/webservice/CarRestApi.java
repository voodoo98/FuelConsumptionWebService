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

import com.fuelconsumption.adaptor.stubs.input.CarInputStub;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
public interface CarRestApi {

    @GET
    @Path("/list")
    Response getCars();

    @POST
    @Path("/{plate}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response addCar(@PathParam("plate") final String plate, final CarInputStub carInputStub);

    @PUT
    @Path("/{oldplate}/{newplate}")
    Response modifyCar(@PathParam("oldplate") final String oldPlate, @PathParam("newplate") final String newPlate);

    @DELETE
    @Path("/{plate}")
    Response deleteCar(@PathParam("plate") final String plate);

}
