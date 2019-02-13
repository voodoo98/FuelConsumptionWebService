package com.fuelconsumption.adaptor;

import java.util.List;

import com.fuelconsumption.adaptor.stubs.CarStub;

public interface CarFacade {
	List<CarStub> getCars();

	boolean addCar(String plate, String type, String color);

	boolean modifyCar(String oldPlate, String newPlate);

	boolean deleteCar(String plate);

}
