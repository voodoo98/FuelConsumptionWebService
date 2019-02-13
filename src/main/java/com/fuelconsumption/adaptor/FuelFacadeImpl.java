package com.fuelconsumption.adaptor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.fuelconsumption.adaptor.stubs.AggregatedFuelStub;
import com.fuelconsumption.adaptor.stubs.FuelByCarStub;
import com.fuelconsumption.adaptor.stubs.FuelByGasStation;
import com.fuelconsumption.adaptor.stubs.FuelStub;
import com.fuelconsumption.adaptor.stubs.input.FuelInputStub;
import com.fuelconsumption.annotations.Log;
import com.fuelconsumption.persistance.FuelDataService;
import com.fuelconsumption.persistance.entity.AggregatedFuel;
import com.fuelconsumption.persistance.entity.Fuel;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;

public class FuelFacadeImpl implements FuelFacade {

	@Inject
	private FuelDataService service;

	@Inject
	@Log
	private Logger LOGGER;

	@Override
	public List<FuelStub> getFuels() {
		final List<Fuel> list = this.service.getFuels();
		final List<FuelStub> result = new ArrayList<>(list.size());
		for (final Fuel element : list) {
			result.add(FuelStub.create(element.getQuantity(), element.getDistance(), element.getPrice(), element.getCar().getId(), element.getGasStation().getId(), element.getTimeStamp()));
		}
		return result;
	}

	@Override
	public List<AggregatedFuelStub> getAggregatedFuels() {
		final List<AggregatedFuel> list = this.service.getAggregatedFuels();
		final List<AggregatedFuelStub> result = this.getFuelStubList(list);
		return result;
	}

	@Override
	public List<FuelByCarStub> getFuelsByCar(final String plate) {
		final List<AggregatedFuel> list = this.service.getFuelsByPlate(plate);
		final List<FuelByCarStub> result = new ArrayList<>(list.size());
		for (final AggregatedFuel element : list) {
			result.add(FuelByCarStub.builder().//
			        quantity(element.getQuantity()).//
			        distance(element.getDistance()).//
			        price(element.getPrice()).//
			        gasStationName(element.getGasStationName()).//
			        date(element.getDate()).//
			        build());
		}
		return result;
	}

	@Override
	public boolean addFuel(final FuelInputStub fuelInputStub) {
		boolean result;
		try {
			this.service.addFuel(fuelInputStub.getQuantity(), fuelInputStub.getDistance(), fuelInputStub.getPrice(), fuelInputStub.getCarPlate(), fuelInputStub.getGasStation());
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;
	}

	@Override
	public List<FuelByGasStation> getFuelsByGasStation(final String gasstation) {
		final List<AggregatedFuel> list = this.service.getFuelsByGasStation(gasstation);
		final List<FuelByGasStation> result = new ArrayList<>(list.size());
		for (final AggregatedFuel element : list) {
			result.add(FuelByGasStation.builder().//
			        quantity(element.getQuantity()).//
			        distance(element.getDistance()).//
			        price(element.getPrice()).//
			        carPlate(element.getCarPlate()).//
			        date(element.getDate()).//
			        build());
		}
		return result;
	}

	@Override
	public List<AggregatedFuelStub> getFuels(final String from, final String to) {
		final List<AggregatedFuel> list = this.service.getFuels(from, to);
		return this.getFuelStubList(list);
	}

	private List<AggregatedFuelStub> getFuelStubList(final List<AggregatedFuel> list) {
		final List<AggregatedFuelStub> result = new ArrayList<>(list.size());
		for (final AggregatedFuel element : list) {
			result.add(AggregatedFuelStub.builder().//
			        quantity(element.getQuantity()).//
			        distance(element.getDistance()).//
			        price(element.getPrice()).//
			        carPlate(element.getCarPlate()).//
			        gasStationName(element.getGasStationName()).//
			        date(element.getDate()).//
			        build());
		}
		return result;
	}

}
