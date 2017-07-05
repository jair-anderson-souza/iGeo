/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String cityOrigin;
    private String cityDestiny;
    private String date;
    private String departureTime;
    private BigDecimal oil;
    private String distance;
    private String arrivalTime;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Route routeOrigin;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Route routeDestiny;

    public Ride() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityOrigin() {
        return cityOrigin;
    }

    public void setCityOrigin(String cityOrigin) {
        this.cityOrigin = cityOrigin;
    }

    public String getCityDestiny() {
        return cityDestiny;
    }

    public void setCityDestiny(String cityDestiny) {
        this.cityDestiny = cityDestiny;
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

    public BigDecimal getOil() {
        return oil;
    }

    public void setOil(BigDecimal oil) {
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

    public Route getRouteOrigin() {
        return routeOrigin;
    }

    public void setRouteOrigin(Route routeOrigin) {
        this.routeOrigin = routeOrigin;
    }

    public Route getRouteDestiny() {
        return routeDestiny;
    }

    public void setRouteDestiny(Route routeDestiny) {
        this.routeDestiny = routeDestiny;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        return "Ride{" + "id=" + id + ", cityOrigin=" + cityOrigin + ", cityDestiny=" + cityDestiny + ", date=" + date + ", departureTime=" + departureTime + ", oil=" + oil + ", distance=" + distance + ", arrivalTime=" + arrivalTime + ", routeOrigin=" + routeOrigin + ", routeDestiny=" + routeDestiny + '}';
    }

}
