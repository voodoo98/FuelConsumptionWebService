package com.fuelconsumption.adaptor.stubs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GasStationStub {

	@JsonCreator
	public static GasStationStub create(final Double lat, final Double lng, final String name, final String address) {
		return new AutoValue_GasStationStub(lat, lng, name, address);
	}

	@JsonProperty("lat")
	public abstract Double getLat();

	@JsonProperty("lng")
	public abstract Double getLng();

	@JsonProperty("name")
	public abstract String getName();

	@JsonProperty("address")
	public abstract String getAddress();

}
