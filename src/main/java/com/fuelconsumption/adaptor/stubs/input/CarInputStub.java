package com.fuelconsumption.adaptor.stubs.input;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CarInputStub implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonCreator
	public static CarInputStub create(@JsonProperty("type") final String type, @JsonProperty("color") final String color) {
		return new AutoValue_CarInputStub(type, color);
	}

	@JsonProperty("type")
	public abstract String getType();

	@JsonProperty("color")
	public abstract String getColor();

}
