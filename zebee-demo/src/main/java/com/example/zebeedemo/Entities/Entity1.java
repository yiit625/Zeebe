package com.example.zebeedemo.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entity1 {

    @Id
    private long id;

    @Column(name = "microservis1")
    private String microServis1;

    public Entity1(long id, String microServis1) {
        this.id = id;
        this.microServis1 = microServis1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMicroServis1() {
        return microServis1;
    }

    public void setMicroServis1(String microServis1) {
        this.microServis1 = microServis1;
    }
}
