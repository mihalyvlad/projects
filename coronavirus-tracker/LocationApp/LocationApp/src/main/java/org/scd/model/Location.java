package org.scd.model;

import org.scd.model.security.Role;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Component
@Entity
@Table(name="LOCATION")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "LATITUDE", nullable = false, length = 45)
    private String latitude;
    @Column(name = "LONGITUDE", nullable = false, length = 45)
    private String longitude;
    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",nullable = false)
    private User user ;

    public Location() {
    }

    public Location(String latitude, String longitude, Date creationDate, User user) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
