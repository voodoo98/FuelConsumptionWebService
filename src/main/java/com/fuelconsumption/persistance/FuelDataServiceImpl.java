package com.fuelconsumption.persistance;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.slf4j.Logger;

import com.fuelconsumption.annotations.Log;
import com.fuelconsumption.annotations.Query;
import com.fuelconsumption.persistance.entity.AggregatedFuel;
import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.entity.Fuel;
import com.fuelconsumption.persistance.entity.GasStation;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.parameter.FuelParameter;
import com.fuelconsumption.persistance.query.FuelQuery;
import com.fuelconsumption.utils.FuelDateUtils;

@Stateless
public class FuelDataServiceImpl implements FuelDataService {

    @PersistenceContext(unitName = "FuelService")
    private EntityManager entityManager;

    @Inject
    @Log
    private Logger LOGGER;

    @Inject
    @Query
    private EntityQueries entityQueries;

    @Override
    public List<Fuel> getFuels() {
        return this.entityManager.createNamedQuery(FuelQuery.GET_FUELS, Fuel.class).getResultList();
    }

    @Override
    public List<AggregatedFuel> getAggregatedFuels() {
        return this.entityManager.createNamedQuery(FuelQuery.GET_AGGREGATED_FUELS, AggregatedFuel.class).getResultList();
    }

    @Override
    public List<AggregatedFuel> getFuelsByPlate(final String plate) {
        return this.entityManager.createNamedQuery(FuelQuery.GET_FUELS_BY_PLATE, AggregatedFuel.class).//
                setParameter(FuelParameter.PLATE, plate).getResultList();
    }

    @Override
    public void addFuel(final Double quantity, final Double distance, final Double price, final String plate, final String gasStationName)
            throws PersistenceServiceException {
        final Car car = this.entityQueries.getCarByPlate(plate);
        final GasStation gasStation = this.entityQueries.getGasStationByName(gasStationName);
        final Fuel fuel = new Fuel(quantity, distance, price, car, gasStation);
        try {
            this.entityManager.persist(fuel);
            this.entityManager.flush();
        } catch (final Exception e) {
            throw new PersistenceServiceException(plate + " or " + gasStationName + " does not exist");
        }

    }

    @Override
    public List<AggregatedFuel> getFuelsByGasStation(final String gasstation) {
        return this.entityManager.createNamedQuery(FuelQuery.GET_FUELS_BY_GASSTATION, AggregatedFuel.class).//
                setParameter(FuelParameter.GAS_STATION, gasstation).getResultList();
    }

    @Override
    public List<AggregatedFuel> getFuels(final String from, final String to) {
        final Date fromLong = FuelDateUtils.parse(from);
        final Date toLong = FuelDateUtils.parse(to);
        return this.entityManager.createNamedQuery(FuelQuery.GET_FUELS_DATE_RANGE, AggregatedFuel.class)
                .setParameter(FuelParameter.DATE_FROM, fromLong, TemporalType.DATE).setParameter(FuelParameter.DATE_TO, toLong).getResultList();
    }

}
