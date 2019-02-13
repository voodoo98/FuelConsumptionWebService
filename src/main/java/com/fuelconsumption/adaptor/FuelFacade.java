package com.fuelconsumption.adaptor;

import java.util.List;

import com.fuelconsumption.adaptor.stubs.AggregatedFuelStub;
import com.fuelconsumption.adaptor.stubs.FuelByCarStub;
import com.fuelconsumption.adaptor.stubs.FuelByGasStation;
import com.fuelconsumption.adaptor.stubs.FuelStub;
import com.fuelconsumption.adaptor.stubs.input.FuelInputStub;

public interface FuelFacade {

	List<FuelStub> getFuels();

	List<AggregatedFuelStub> getAggregatedFuels();

	List<FuelByCarStub> getFuelsByCar(String plate);

	List<FuelByGasStation> getFuelsByGasStation(String gasstation);

	boolean addFuel(FuelInputStub fuelInputStub);

	List<AggregatedFuelStub> getFuels(String from, String to);

}
