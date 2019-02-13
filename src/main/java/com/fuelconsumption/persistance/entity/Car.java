package com.fuelconsumption.persistance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fuelconsumption.persistance.parameter.CarParameter;
import com.fuelconsumption.persistance.query.CarQuery;

@Entity
@Table(name = "cars")
@NamedQueries(value = { //
        @NamedQuery(name = CarQuery.GET_CARS, query = "SELECT c FROM Car c"), //
        @NamedQuery(name = CarQuery.GET_CAR_BY_PLATE, query = "SELECT c FROM Car c WHERE c.plate=:" + CarParameter.PLATE) //
})
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "generatorCar", sequenceName = "cars_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCar")
	@Column(name = "id", nullable = false, updatable = false, insertable = false)
	private Long id;

	@Column(name = "plate")
	private String plate;

	@Column(name = "type")
	private String type;

	@Column(name = "color")
	private String color;

	public Car() {
	}

	public Car(final String plate, final String type, final String color) {
		this.plate = plate;
		this.type = type;
		this.color = color;
	}

	public Car(final String... params) {
		this(params[0], params[1], params[2]);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(final String plate) {
		this.plate = plate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [id=" + this.id + ", plate=" + this.plate + ", type=" + this.type + ", color=" + this.color + "]";
	}

}