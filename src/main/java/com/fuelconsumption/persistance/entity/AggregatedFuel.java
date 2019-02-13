package com.fuelconsumption.persistance.entity;

import java.io.Serializable;
import java.util.Date;

public class AggregatedFuel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double quantity;
	private Double distance;
	private Double price;
	private String carPlate;
	private String gasStationName;
	private Date timeStamp;

	public AggregatedFuel() {
	}

	public AggregatedFuel(final Double quantity, final Double distance, final Double price, final String carPlate, final String gasStationName, final Date timeStamp) {
		this.quantity = quantity;
		this.distance = distance;
		this.price = price;
		this.carPlate = carPlate;
		this.gasStationName = gasStationName;
		this.timeStamp = timeStamp;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Double quantity) {
		this.quantity = quantity;
	}

	public Double getDistance() {
		return this.distance;
	}

	public void setDistance(final Double distance) {
		this.distance = distance;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public String getCarPlate() {
		return this.carPlate;
	}

	public void setCarPlate(final String carPlate) {
		this.carPlate = carPlate;
	}

	public String getGasStationName() {
		return this.gasStationName;
	}

	public void setGasStationName(final String gasStationName) {
		this.gasStationName = gasStationName;
	}

	public Date getDate() {
		return this.timeStamp;
	}

	public void setTimeStamp(final Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "AggregatedFuel [quantity=" + this.quantity + ", distance=" + this.distance + ", price=" + this.price + ", carPlate=" + this.carPlate + ", gasStationName=" + this.gasStationName + "]";
	}

}
