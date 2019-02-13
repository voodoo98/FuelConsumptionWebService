package com.fuelconsumption.persistance;

import java.util.List;

import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;

public interface CarDataService {

    List<Car> getCars();

    void addCar(String... params) throws PersistenceServiceException;

    void modifyCar(String oldPlate, String newPlate) throws PersistenceServiceException;

    void deleteCar(String plate) throws PersistenceServiceException;

}
