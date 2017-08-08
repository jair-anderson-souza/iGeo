/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 23, 2017 11:44:12 PM
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "ride_seq", sequenceName = "ride_seq", allocationSize = 1)
public class Ride implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ride_seq")
    private Long id;
    private String date;
    private String departureTime;
    private String oil;
    private String distance;
    private String arrivalTime;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private City cityOrigin;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private City cityDestiny;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<City> cities;

    public Ride() {
        this.cities = new ArrayList<>();
        this.cityOrigin = new City();
        this.cityDestiny = new City();
    }

    public Ride(Long id, String date, String departureTime) {
        this.id = id;
        this.date = date;
        this.departureTime = departureTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public City getCityOrigin() {
        return cityOrigin;
    }

    public void setCityOrigin(City cityOrigin) {
        this.cityOrigin = cityOrigin;
    }

    public City getCityDestiny() {
        return cityDestiny;
    }

    public void setCityDestiny(City cityDestiny) {
        this.cityDestiny = cityDestiny;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ride other = (Ride) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Ride{" + "id=" + id + ", date=" + date + ", departureTime=" + departureTime + ", oil=" + oil + ", distance=" + distance + ", arrivalTime=" + arrivalTime + ", cityOrigin=" + cityOrigin + ", cityDestiny=" + cityDestiny + ", cities=" + cities + '}';
    }

}
