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

import com.fuelconsumption.persistance.parameter.GasStationParameter;
import com.fuelconsumption.persistance.query.GasStationQuery;

@Entity
@Table(name = "gasstations")
@NamedQueries(value = { //
        @NamedQuery(name = GasStationQuery.GET_GASSTATIONS, query = "SELECT g FROM GasStation g"), //
        @NamedQuery(name = GasStationQuery.GET_GASSTATION_BY_NAME, query = "SELECT g FROM GasStation g WHERE g.name=:" + GasStationParameter.NAME) //
})
public class GasStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "generatorGasStation", sequenceName = "gasstations_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorGasStation")
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public GasStation() {
    }

    public GasStation(final Double lat, final Double lng, final String name, final String address) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(final Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(final Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GasStation [id=" + this.id + ", lat=" + this.lat + ", lng=" + this.lng + ", name=" + this.name + ", address=" + this.address + "]";
    }

}