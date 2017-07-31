/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 23, 2017 11:44:26 PM
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "route_seq", sequenceName = "route_seq", allocationSize = 1)
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    private Long id;
    private String latitudeOrigin;
    private String longitudeOrigin;
    private String cityNameOrigin;
    private String latitudeDestination;
    private String longitudeDestination;
    private String cityNameDestination;
    private String distance;
    private String arrivalTime;

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitudeOrigin() {
        return latitudeOrigin;
    }

    public void setLatitudeOrigin(String latitudeOrigin) {
        this.latitudeOrigin = latitudeOrigin;
    }

    public String getLongitudeOrigin() {
        return longitudeOrigin;
    }

    public void setLongitudeOrigin(String longitudeOrigin) {
        this.longitudeOrigin = longitudeOrigin;
    }

    public String getLatitudeDestination() {
        return latitudeDestination;
    }

    public void setLatitudeDestination(String latitudeDestination) {
        this.latitudeDestination = latitudeDestination;
    }

    public String getLongitudeDestination() {
        return longitudeDestination;
    }

    public void setLongitudeDestination(String longitudeDestination) {
        this.longitudeDestination = longitudeDestination;
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

    public String getCityNameOrigin() {
        return cityNameOrigin;
    }

    public void setCityNameOrigin(String cityNameOrigin) {
        this.cityNameOrigin = cityNameOrigin;
    }

    public String getCityNameDestination() {
        return cityNameDestination;
    }

    public void setCityNameDestination(String cityNameDestination) {
        this.cityNameDestination = cityNameDestination;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Route other = (Route) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Route{" + "id=" + id + ", latitudeOrigin=" + latitudeOrigin + ", longitudeOrigin=" + longitudeOrigin + ", latitudeDestination=" + latitudeDestination + ", longitudeDestination=" + longitudeDestination + ", distance=" + distance + ", arrivalTime=" + arrivalTime + '}';
    }

}
