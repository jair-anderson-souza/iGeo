/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.entity;

import static io.github.jass2125.igeo.core.entity.UserPrincipal_.count;
import io.github.jass2125.igeo.core.entity.enums.Status;
import io.github.jass2125.igeo.core.exceptions.EntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 23, 2017 11:43:57 PM
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
public class UserPrincipal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    private String phone;
    private String name;
//    @JsonSerialize(using = LocalDateSerializer.class)
//    @JsonDeserialize(using = LocalDateDeserializer.class)
    private String birthday;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private Count count;
    @Enumerated(EnumType.STRING)
    private Status profileStatus;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userprincipal_id", referencedColumnName = "id")
    private List<Ride> rides;

    public UserPrincipal() {
        this.rides = new ArrayList<>();
        this.count = new Count();
    }

    public UserPrincipal(Long id, String name, Status profileStatus) {
        this.id = id;
        this.name = name;
        this.profileStatus = profileStatus;
    }

    public UserPrincipal(Long id, String phone, String name, String birthday, Status profileStatus, Long idCount, String email, String password) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.birthday = birthday;
        this.profileStatus = profileStatus;
        this.profileStatus = profileStatus;
        this.count = new Count();
        this.count.setEmail(email);
        this.count.setPassword(password);
    }

    public UserPrincipal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean containsEntity(Ride ride) {
        return rides.contains(ride);
    }

    public String getBirthday() {
        return birthday;
    }

    public void addRide(Ride ride) throws EntityException {
        if (containsEntity(ride)) {
            throw new EntityException("Entidade já existe");
        }
        this.rides.add(ride);
    }

    public void removeRide(Ride ride) throws EntityException {
        if (!containsEntity(ride)) {
            throw new EntityException("Entity not exists");
        }
        this.rides.remove(ride);
    }

    public Status getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(Status profileStatus) {
        this.profileStatus = profileStatus;
    }

    public List<Ride> getRides() {
        return rides;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.count);
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
        final UserPrincipal other = (UserPrincipal) obj;
        return Objects.equals(this.count, other.count);
    }

    @Override
    public String toString() {
        return "UserPrincipal{" + "id=" + id + ", phone=" + phone + ", name=" + name + ", birthday=" + birthday + ", count=" + count + ", profileStatus=" + profileStatus + ", rides=" + rides + '}';
    }

}
