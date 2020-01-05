package model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Nationality")
public class Nationality {
    @Id

    private Integer Id;
    private String  name;
    private String capital;
    private String updatedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;

    public Nationality() {
    }

    public Nationality(Integer id, String name, String capital, String updatedBy, Date updatedOn) {
        Id = id;
        this.name = name;
        this.capital = capital;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
