package com.fuelconsumption.webservice;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuelconsumption.adaptor.FuelFacade;
import com.fuelconsumption.adaptor.stubs.AggregatedFuelStub;
import com.fuelconsumption.adaptor.stubs.FuelByCarStub;
import com.fuelconsumption.adaptor.stubs.FuelByGasStation;
import com.fuelconsumption.adaptor.stubs.FuelStub;
import com.fuelconsumption.adaptor.stubs.input.FuelInputStub;
import com.fuelconsumption.annotations.JacksonMapper;
import com.fuelconsumption.annotations.Log;

@ApplicationScoped
public class FuelRestApiImpl implements FuelRestApi {

	@Inject
	@JacksonMapper
	private ObjectMapper mapper;

	@Inject
	private FuelFacade facade;

	@Inject
	@Log
	private Logger LOGGER;

	@Override
	public Response getVersion() {
		return Response.ok("{\"version\": \"0.0.4\"}").build();
	}

	@Override
	public Response getFuels() {
		final List<FuelStub> result = this.facade.getFuels();
		final JacksonStreamingOutput<List<FuelStub>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

	@Override
	public Response getAggregatedFuels() {
		final List<AggregatedFuelStub> result = this.facade.getAggregatedFuels();
		final JacksonStreamingOutput<List<AggregatedFuelStub>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

	@Override
	public Response getFuelsByCar(final String plate) {
		final List<FuelByCarStub> result = this.facade.getFuelsByCar(plate);
		final JacksonStreamingOutput<List<FuelByCarStub>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

	@Override
	public Response getFuelsByGasStation(final String gasstation) {
		final List<FuelByGasStation> result = this.facade.getFuelsByGasStation(gasstation);
		final JacksonStreamingOutput<List<FuelByGasStation>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

	@Override
	public Response addFuel(final FuelInputStub fuelInputStub) {
		final boolean success = this.facade.addFuel(fuelInputStub);
		final ResponseBuilder response = success ? Response.ok() : Response.status(Status.BAD_REQUEST);
		return response.build();
	}

	@Override
	public Response getFuels(final String from, final String to) {
		final List<AggregatedFuelStub> result = this.facade.getFuels(from, to);
		final JacksonStreamingOutput<List<AggregatedFuelStub>> stream = new JacksonStreamingOutput<>(this.mapper, result);
		return Response.ok(stream).build();
	}

}
