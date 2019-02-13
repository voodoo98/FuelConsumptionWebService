package com.fuelconsumption.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fuelconsumption.annotations.Query;
import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.entity.GasStation;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.parameter.CarParameter;
import com.fuelconsumption.persistance.parameter.GasStationParameter;
import com.fuelconsumption.persistance.query.CarQuery;
import com.fuelconsumption.persistance.query.GasStationQuery;

@Query
public class EntityQueries {

    @PersistenceContext(unitName = "FuelService")
    private EntityManager entityManager;

    public Car getCarByPlate(final String plate) throws PersistenceServiceException {
        final Long carId = this.getCarId(plate);
        return carId != null ? this.entityManager.find(Car.class, carId) : null;
    }

    public Long getCarId(final String plate) throws PersistenceServiceException {
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

    public GasStation getGasStationByName(final String name) throws PersistenceServiceException {
        final Long id = this.getId(name);
        return id != null ? this.entityManager.find(GasStation.class, id) : null;
    }

    public Long getId(final String gasStation) throws PersistenceServiceException {
        Long result = null;
        final TypedQuery<GasStation> createNamedQuery = this.entityManager.createNamedQuery(GasStationQuery.GET_GASSTATION_BY_NAME, GasStation.class);
        final TypedQuery<GasStation> setParameter = createNamedQuery.setParameter(GasStationParameter.NAME, gasStation);
        final List<GasStation> list = setParameter.getResultList();
        if (!list.isEmpty()) {
            result = list.get(0).getId();
        }
        return result;
    }
}
