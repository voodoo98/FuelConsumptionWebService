package com.fuelconsumption.adaptor;

import java.util.List;

import com.fuelconsumption.adaptor.stubs.GasStationStub;

public interface GasStationFacade {

	List<GasStationStub> getGasStations();

	boolean addGasStation(Double lat, Double lng, String name, String address);

	boolean modifyGasStation(String oldName, String newName);

	boolean deleteGasStation(String name);

	Double listQuantities(String name);

	Double listQuantities();

}
