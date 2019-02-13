package com.fuelconsumption.adaptor.stubs.input;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FuelInputStub implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonCreator
    public static FuelInputStub create(@JsonProperty("quantity") final Double quantity, @JsonProperty("distance") final Double distance,
            @JsonProperty("price") final Double price, @JsonProperty("car_plate") final String carPlate, @JsonProperty("gas_station") final String gasStation) {
        return new AutoValue_FuelInputStub(quantity, distance, price, carPlate, gasStation);
    }

    public abstract Double getQuantity();

    public abstract Double getDistance();

    public abstract Double getPrice();

    public abstract String getCarPlate();

    public abstract String getGasStation();

}
