package com.fuelconsumption.webservice;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuelconsumption.adaptor.CarFacade;
import com.fuelconsumption.adaptor.stubs.CarStub;
import com.fuelconsumption.adaptor.stubs.input.CarInputStub;
import com.fuelconsumption.annotations.JacksonMapper;
import com.fuelconsumption.annotations.Log;

@ApplicationScoped
public class CarRestApiImpl implements CarRestApi {

	@Inject
	@JacksonMapper
	private ObjectMapper mapper;

	@Inject
	private CarFacade facade;

	@Inject
	@Log
	private Logger LOGGER;

	@Override
	public Response getCars() {
		final List<CarStub> result = this.facade.getCars();
		final JacksonStreamingOutput<List<CarStub>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

	@Override
	public Response addCar(final String plate, final CarInputStub carInputStub) {
		final boolean success = this.facade.addCar(plate, carInputStub.getType(), carInputStub.getColor());
		final ResponseBuilder response = success ? Response.ok() : Response.status(Status.BAD_REQUEST);
		return response.build();
	}

	@Override
	public Response modifyCar(final String oldPlate, final String newPlate) {
		final boolean success = this.facade.modifyCar(oldPlate, newPlate);
		final ResponseBuilder response = success ? Response.ok() : Response.noContent();
		return response.build();
	}

	@Override
	public Response deleteCar(final String plate) {
		final boolean success = this.facade.deleteCar(plate);
		final ResponseBuilder response = success ? Response.ok() : Response.noContent();
		return response.build();
	}

}
