package com.fuelconsumption.adaptor.stubs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fuelconsumption.adaptor.stubs.FuelByGasStation.Builder;
import com.fuelconsumption.utils.FuelDateUtils;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AggregatedFuelStub {

	@JsonCreator
	public static Builder builder() {
		return new AutoValue_AggregatedFuelStub.Builder();
	}

	@JsonProperty("quantity")
	public abstract Double quantity();

	@JsonProperty("distance")
	public abstract Double distance();

	@JsonProperty("price")
	public abstract Double price();

	@JsonProperty("car_plate")
	public abstract String carPlate();

	@JsonProperty("gasstation_name")
	public abstract String gasStationName();

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FuelDateUtils.FORMAT_DATE_TIME)
	public abstract Date date();

	@AutoValue.Builder
	public abstract static class Builder {

		public abstract Builder quantity(Double quantity);

		public abstract Builder distance(Double quantity);

		public abstract Builder price(Double quantity);

		public abstract Builder carPlate(String plate);

		public abstract Builder gasStationName(String gasStationName);

		public abstract Builder date(Date date);

		public abstract AggregatedFuelStub build();
	}

}
