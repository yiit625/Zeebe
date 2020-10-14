package com.example.zebeedemo.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entity2 {

    @Id
    private long id;

    @Column(name = "microservis2")
    private String microServis2;

    public Entity2(long id, String microServis2) {
        this.id = id;
        this.microServis2 = microServis2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMicroServis2() {
        return microServis2;
    }

    public void setMicroServis2(String microServis2) {
        this.microServis2 = microServis2;
    }
}
