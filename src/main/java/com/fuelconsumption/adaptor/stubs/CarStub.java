package com.fuelconsumption.adaptor.stubs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CarStub {

	@JsonCreator
	public static CarStub create(final String plate, final String type, final String color) {
		return new AutoValue_CarStub(plate, type, color);
	}

	@JsonProperty("plate")
	public abstract String getPlate();

	@JsonProperty("type")
	public abstract String getType();

	@JsonProperty("color")
	public abstract String getColor();

}
