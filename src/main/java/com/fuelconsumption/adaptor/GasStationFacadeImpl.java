package com.fuelconsumption.adaptor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.fuelconsumption.adaptor.stubs.GasStationStub;
import com.fuelconsumption.annotations.Log;
import com.fuelconsumption.persistance.GasStationDataService;
import com.fuelconsumption.persistance.entity.GasStation;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;

public class GasStationFacadeImpl implements GasStationFacade {

	@Inject
	private GasStationDataService service;

	@Inject
	@Log
	private Logger LOGGER;

	@Override
	public List<GasStationStub> getGasStations() {
		final List<GasStation> list = this.service.getGasStations();
		final List<GasStationStub> result = new ArrayList<>(list.size());
		for (final GasStation element : list) {
			result.add(GasStationStub.create(element.getLat(), element.getLng(), element.getName(), element.getAddress()));
		}
		return result;
	}

	@Override
	public boolean addGasStation(final Double lat, final Double lng, final String name, final String address) {
		boolean result;
		try {
			this.service.addGasStation(lat, lng, name, address);
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean modifyGasStation(final String oldName, final String newName) {
		boolean result;
		try {
			this.service.modifyGasStation(oldName, newName);
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean deleteGasStation(final String name) {
		boolean result;
		try {
			this.service.deleteGasStation(name);
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;
	}

	@Override
	public Double listQuantities(final String name) {
		return this.service.getQuantities(name);
	}

	@Override
	public Double listQuantities() {
		return this.service.getQuantities();
	}

}
