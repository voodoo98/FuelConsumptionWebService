package com.fuelconsumption.persistance.manager;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.fuelconsumption.annotations.Log;
import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.parameter.CarParameter;
import com.fuelconsumption.persistance.query.CarQuery;

public class CarServiceManager {

    private static final String ALREADY_EXISTS = " already exists!";
    private static final String DOES_NOT_EXIST = " does not exist!";

    @Inject
    @Log
    private Logger LOGGER;

    private final EntityManager entityManager;

    public CarServiceManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Car> getCars() {
        return this.entityManager.createNamedQuery(CarQuery.GET_CARS, Car.class).getResultList();
    }

    public void addCar(final String... params) throws PersistenceServiceException {
        final String plate = params[0];
        final Car existedCar = this.getCarByPlate(plate);
        if (existedCar == null) {
            final Car car = new Car(params);
            this.entityManager.persist(car);
            this.entityManager.flush();
        } else {
            throw new PersistenceServiceException(plate + ALREADY_EXISTS);
        }
    }

    public void modifyCar(final String oldPlate, final String newPlate) throws PersistenceServiceException {
        final Car oldCar = this.getCarByPlate(oldPlate);
        if (oldCar != null) {
            oldCar.setPlate(newPlate);
            this.entityManager.merge(oldCar);
            this.entityManager.flush();
        } else {
            throw new PersistenceServiceException(oldPlate + DOES_NOT_EXIST);
        }
    }

    public void deleteCar(final String plate) throws PersistenceServiceException {
        final Car car = this.getCarByPlate(plate);
        if (car != null) {
            this.entityManager.remove(car);
            this.entityManager.flush();
        } else {
            throw new PersistenceServiceException(plate + DOES_NOT_EXIST);
        }
    }

    private Car getCarByPlate(final String plate) throws PersistenceServiceException {
        final Long carId = this.getCarId(plate);
        return carId != null ? this.entityManager.find(Car.class, carId) : null;
    }

    private Long getCarId(final String plate) throws PersistenceServiceException {
        Long result = null;
        final TypedQuery<Car> createNamedQuery = this.entityManager.createNamedQuery(CarQuery.GET_CAR_BY_PLATE, Car.class);
        final TypedQuery<Car> setParameter = createNamedQuery.setParameter(CarParameter.PLATE, plate);
        final List<Car> list = setParameter.getResultList();
        if (!list.isEmpty()) {
            final Car car = list.get(0);
            result = car.getId();
        }
        return result;
    }

}
