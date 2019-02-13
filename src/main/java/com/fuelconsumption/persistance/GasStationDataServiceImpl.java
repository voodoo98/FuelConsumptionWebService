package com.fuelconsumption.persistance;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import com.fuelconsumption.annotations.Log;
import com.fuelconsumption.annotations.Query;
import com.fuelconsumption.persistance.entity.GasStation;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.parameter.GasStationParameter;
import com.fuelconsumption.persistance.query.GasStationQuery;

@Stateless
public class GasStationDataServiceImpl implements GasStationDataService {

    private static final String DOES_NOT_EXIST = " does not exist!";

    @PersistenceContext(unitName = "FuelService")
    private EntityManager entityManager;

    @Inject
    @Log
    private Logger LOGGER;

    @Inject
    @Query
    private EntityQueries entityQueries;

    @Override
    public List<GasStation> getGasStations() {
        return this.entityManager.createNamedQuery(GasStationQuery.GET_GASSTATIONS, GasStation.class).getResultList();
    }

    @Override
    public void addGasStation(final Double lat, final Double lng, final String name, final String address) throws PersistenceServiceException {
        final GasStation existedGasStation = this.entityQueries.getGasStationByName(name);
        if (existedGasStation == null) {
            final GasStation gasStation = new GasStation(lat, lng, name, address);
            this.entityManager.persist(gasStation);
            this.entityManager.flush();
        } else {
            throw new PersistenceServiceException(name + " already exists!");
        }
    }

    @Override
    public void modifyGasStation(final String oldName, final String newName) throws PersistenceServiceException {
        final GasStation oldGasStation = this.entityQueries.getGasStationByName(oldName);
        if (oldGasStation != null) {
            oldGasStation.setName(newName);
            this.entityManager.merge(oldGasStation);
            this.entityManager.flush();
        } else {
            throw new PersistenceServiceException(oldName + DOES_NOT_EXIST);
        }
    }

    @Override
    public void deleteGasStation(final String name) throws PersistenceServiceException {
        final GasStation gasStation = this.entityQueries.getGasStationByName(name);
        if (gasStation != null) {
            this.entityManager.remove(gasStation);
            this.entityManager.flush();
        } else {
            throw new PersistenceServiceException(name + DOES_NOT_EXIST);
        }
    }

    @Override
    public Double getQuantities(final String name) {
        return this.entityManager.createNamedQuery(GasStationQuery.GET_QUANTITIES_SUM_BY_GAS_STATION, Double.class).setParameter(GasStationParameter.NAME, name)
                .getSingleResult();
    }

    @Override
    public Double getQuantities() {
        return this.entityManager.createNamedQuery(GasStationQuery.GET_ALL_QUANTITIES_SUM, Double.class).getSingleResult();
    }

}
