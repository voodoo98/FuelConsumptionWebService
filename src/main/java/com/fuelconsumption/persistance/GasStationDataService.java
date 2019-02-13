package com.fuelconsumption.persistance;

import java.util.List;

import com.fuelconsumption.persistance.entity.GasStation;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;

public interface GasStationDataService {

	List<GasStation> getGasStations();

	void addGasStation(Double lat, Double lng, String name, String address) throws PersistenceServiceException;

	void modifyGasStation(String oldName, String newName) throws PersistenceServiceException;

	void deleteGasStation(String name) throws PersistenceServiceException;

	Double getQuantities(String name);

	Double getQuantities();

}
