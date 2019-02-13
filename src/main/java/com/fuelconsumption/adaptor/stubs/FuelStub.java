package com.fuelconsumption.adaptor.stubs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fuelconsumption.utils.FuelDateUtils;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FuelStub {

	@JsonCreator
	public static FuelStub create(final Double quantity, final Double distance, final Double price, final Long carId, final Long gasStationId, final Date date) {
		return new AutoValue_FuelStub(quantity, distance, price, carId, gasStationId, date);
	}

	@JsonProperty("quantity")
	public abstract Double getQuantity();

	@JsonProperty("distance")
	public abstract Double getDistance();

	@JsonProperty("price")
	public abstract Double getPrice();

	@JsonProperty("car_id")
	public abstract Long getCarId();

	@JsonProperty("gs_id")
	public abstract Long getGasStationId();

	@JsonProperty("fuel_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FuelDateUtils.FORMAT_DATE_TIME)
	public abstract Date getDate();

}
