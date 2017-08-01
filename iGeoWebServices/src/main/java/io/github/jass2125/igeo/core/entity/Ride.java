/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.entity;

import java.io.Serializable;
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
    private String date;
    private String departureTime;
    private String oil;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Route cityInTheMiddle;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Route routeOrigin;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Route routeDestiny;

    public Ride() {
        this.routeOrigin = new Route();
        this.routeDestiny = new Route();
        this.cityInTheMiddle = new Route();
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

    public Route getCityInTheMiddle() {
        return cityInTheMiddle;
    }

    public void setCityInTheMiddle(Route cityInTheMiddle) {
        this.cityInTheMiddle = cityInTheMiddle;
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
        return "Ride{" + "id=" + id + ", date=" + date + ", departureTime=" + departureTime + ", oil=" + oil + ", cityInTheMiddle=" + cityInTheMiddle + ", routeOrigin=" + routeOrigin + ", routeDestiny=" + routeDestiny + '}';
    }

}
