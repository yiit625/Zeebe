package com.example.zebeedemo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entity3 {

    @Id
    private long id;

    @Column(name = "microservis3")
    private String microServis3;

    public Entity3(long id, String microServis3) {
        this.id = id;
        this.microServis3 = microServis3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMicroServis3() {
        return microServis3;
    }

    public void setMicroServis3(String microServis3) {
        this.microServis3 = microServis3;
    }
}
