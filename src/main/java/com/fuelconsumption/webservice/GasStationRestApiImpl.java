package com.fuelconsumption.webservice;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuelconsumption.adaptor.GasStationFacadeImpl;
import com.fuelconsumption.adaptor.stubs.GasStationStub;
import com.fuelconsumption.adaptor.stubs.input.GasStationInputStub;
import com.fuelconsumption.annotations.JacksonMapper;
import com.fuelconsumption.annotations.Log;

@ApplicationScoped
public class GasStationRestApiImpl implements GasStationRestApi {

	@Inject
	@JacksonMapper
	private ObjectMapper mapper;

	@Inject
	private GasStationFacadeImpl facade;

	@Inject
	@Log
	private Logger LOGGER;

	@Override
	public Response getGasStations() {
		final List<GasStationStub> result = this.facade.getGasStations();
		final JacksonStreamingOutput<List<GasStationStub>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

	@Override
	public Response addGasStation(final GasStationInputStub stub) {
		final boolean success = this.facade.addGasStation(stub.getLat(), stub.getLng(), stub.getName(), stub.getAddress());
		final ResponseBuilder response = success ? Response.ok() : Response.status(Status.BAD_REQUEST);
		return response.build();
	}

	@Override
	public Response modifyGasStation(final String oldName, final String newName) {
		final boolean success = this.facade.modifyGasStation(oldName, newName);
		final ResponseBuilder response = success ? Response.ok() : Response.noContent();
		return response.build();
	}

	@Override
	public Response deleteGasStation(final String name) {
		final boolean success = this.facade.deleteGasStation(name);
		final ResponseBuilder response = success ? Response.ok() : Response.noContent();
		return response.build();
	}

	@Override
	public Response getQuantities() {
		final Double sum = this.facade.listQuantities();
		return Response.ok("{\"sum_quantity\": " + sum + "}").build();
	}

	@Override
	public Response getQuantities(final String name) {
		final Double sum = this.facade.listQuantities(name);
		return Response.ok("{\"sum_quantity\": " + sum + "}").build();
	}

}
