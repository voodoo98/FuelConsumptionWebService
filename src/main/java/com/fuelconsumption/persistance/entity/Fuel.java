package com.fuelconsumption.persistance.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fuelconsumption.persistance.parameter.FuelParameter;
import com.fuelconsumption.persistance.parameter.GasStationParameter;
import com.fuelconsumption.persistance.query.FuelQuery;
import com.fuelconsumption.persistance.query.GasStationQuery;

@Entity
@Table(name = "fuels")
@NamedQueries(value = { //
        @NamedQuery(name = FuelQuery.GET_FUELS, query = "SELECT f FROM Fuel f"), //
        @NamedQuery(name = FuelQuery.GET_FUELS_DATE_RANGE,
                    query = "SELECT new com.fuelconsumption.persistance.entity.AggregatedFuel(f.quantity, f.distance, f.price, f.car.plate, f.gasStation.name, f.timeStamp) FROM Fuel f WHERE f.timeStamp BETWEEN :"
                            + FuelParameter.DATE_FROM + " AND :" + FuelParameter.DATE_TO), //
        @NamedQuery(name = FuelQuery.GET_AGGREGATED_FUELS, //
                    query = "SELECT new com.fuelconsumption.persistance.entity.AggregatedFuel(f.quantity, f.distance, f.price, f.car.plate, f.gasStation.name, f.timeStamp) FROM Fuel f"), //
        @NamedQuery(name = FuelQuery.GET_FUELS_BY_PLATE, //
                    query = "SELECT new com.fuelconsumption.persistance.entity.AggregatedFuel(f.quantity, f.distance, f.price, f.car.plate, f.gasStation.name, f.timeStamp) FROM Fuel f WHERE f.car.plate =:"
                            + FuelParameter.PLATE), //
        @NamedQuery(name = FuelQuery.GET_FUELS_BY_GASSTATION, //
                    query = "SELECT new com.fuelconsumption.persistance.entity.AggregatedFuel(f.quantity, f.distance, f.price, f.car.plate, f.gasStation.name, f.timeStamp) FROM Fuel f WHERE f.gasStation.name =:"
                            + FuelParameter.GAS_STATION), //
        @NamedQuery(name = GasStationQuery.GET_QUANTITIES_SUM_BY_GAS_STATION, query = "SELECT sum(f.quantity) FROM Fuel f WHERE f.gasStation.name =:" + GasStationParameter.NAME), //
        @NamedQuery(name = GasStationQuery.GET_ALL_QUANTITIES_SUM, query = "SELECT sum(f.quantity) FROM Fuel f") //
})
public class Fuel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "generatorFuel", sequenceName = "fuels_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorFuel")
	@Column(name = "id", nullable = false, updatable = false, insertable = false)
	private Long id;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "distance")
	private Double distance;

	@Column(name = "price")
	private Double price;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
	private Car car;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "gs_id", referencedColumnName = "id", nullable = false)
	private GasStation gasStation;

	@Column(name = "fuel_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	public Fuel() {

	}

	public Fuel(final Double quantity, final Double distance, final Double price, final Car car, final GasStation gasStation) {
		this.quantity = quantity;
		this.distance = distance;
		this.price = price;
		this.car = car;
		this.gasStation = gasStation;
		this.timeStamp = new Date();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
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

	public Car getCar() {
		return this.car;
	}

	public void setCar(final Car car) {
		this.car = car;
	}

	public String getCarPlate() {
		return this.car.getPlate();
	}

	public GasStation getGasStation() {
		return this.gasStation;
	}

	public void setGasStation(final GasStation gasStation) {
		this.gasStation = gasStation;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(final Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Fuel [id=" + this.id + ", quantity=" + this.quantity + ", distance=" + this.distance + ", price=" + this.price + ", car=" + this.car + ", gasStation=" + this.gasStation + "]";
	}

}