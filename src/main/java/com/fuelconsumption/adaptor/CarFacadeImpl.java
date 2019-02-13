package com.fuelconsumption.adaptor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.fuelconsumption.adaptor.stubs.CarStub;
import com.fuelconsumption.annotations.Log;
import com.fuelconsumption.persistance.CarDataService;
import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;

public class CarFacadeImpl implements CarFacade {

	@Inject
	private CarDataService service;

	@Inject
	@Log
	private Logger LOGGER;

	@Override
	public List<CarStub> getCars() {
		final List<Car> list = this.service.getCars();
		final List<CarStub> result = new ArrayList<>(list.size());
		for (final Car element : list) {
			result.add(CarStub.create(element.getPlate(), element.getType(), element.getColor()));
		}
		return result;
	}

	@Override
	public boolean addCar(final String plate, final String type, final String color) {
		boolean result;
		try {
			this.service.addCar(plate, type, color);
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;

	}

	@Override
	public boolean modifyCar(final String oldPlate, final String newPlate) {
		boolean result;
		try {
			this.service.modifyCar(oldPlate, newPlate);
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;

	}

	@Override
	public boolean deleteCar(final String plate) {
		boolean result;
		try {
			this.service.deleteCar(plate);
			result = true;
		} catch (final PersistenceServiceException e) {
			result = false;
			this.LOGGER.warn(e.getMessage());
		}
		return result;

	}

}
