package com.example.marketplace.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Item implements Serializable {
    public Item(Long id, String name, Boolean isSale){
        this.id = id;
        this.name = name;
        this.isSale = isSale;
    }
    public Item(){

    }
    public Item(String name){
        this.name = name;
    }
    private Long id;
    private String name;
    private Boolean isSale = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSale() {
        return isSale;
    }

    public void setSale(Boolean sale) {
        isSale = sale;
    }
}
