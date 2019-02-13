package com.fuelconsumption.adaptor.stubs.input;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GasStationInputStub {

	@JsonCreator
	public static GasStationInputStub create(@JsonProperty("lat") final Double lat, @JsonProperty("lng") final Double lng, @JsonProperty("name") final String name,
	        @JsonProperty("address") final String address) {
		return new AutoValue_GasStationInputStub(lat, lng, name, address);
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
