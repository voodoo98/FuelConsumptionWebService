package com.fuelconsumption.persistance;

import java.util.List;

import com.fuelconsumption.persistance.entity.AggregatedFuel;
import com.fuelconsumption.persistance.entity.Fuel;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;

public interface FuelDataService {

	List<Fuel> getFuels();

	List<AggregatedFuel> getAggregatedFuels();

	List<AggregatedFuel> getFuelsByPlate(String plate);

	List<AggregatedFuel> getFuelsByGasStation(String gasstation);

	void addFuel(Double quantity, Double distance, Double price, String plate, String gasStationName) throws PersistenceServiceException;

	List<AggregatedFuel> getFuels(String from, String to);

}
