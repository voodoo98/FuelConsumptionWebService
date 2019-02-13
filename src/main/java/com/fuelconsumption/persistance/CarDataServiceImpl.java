package com.fuelconsumption.persistance;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.manager.CarServiceManager;

@Stateless
public class CarDataServiceImpl implements CarDataService {

    @PersistenceContext(unitName = "FuelService")
    private EntityManager entityManager;

    private CarServiceManager carServiceManager;

    @PostConstruct
    public void init() {
        this.carServiceManager = new CarServiceManager(this.entityManager);
    }

    @Override
    public List<Car> getCars() {
        return this.carServiceManager.getCars();
    }

    @Override
    public void addCar(final String... params) throws PersistenceServiceException {
        this.carServiceManager.addCar(params);
    }

    @Override
    public void modifyCar(final String oldPlate, final String newPlate) throws PersistenceServiceException {
        this.carServiceManager.modifyCar(oldPlate, newPlate);
    }

    @Override
    public void deleteCar(final String plate) throws PersistenceServiceException {
        this.carServiceManager.deleteCar(plate);
    }

}
