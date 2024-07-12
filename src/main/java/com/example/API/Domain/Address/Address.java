package com.example.API.Domain.Address;

import jakarta.persistence.*;
import  org.springframework.data.repository.CrudRepository;
import com.example.API.Domain.Eventos.Event; // Corrigindo a importação para a entidade Event correta

import java.util.UUID;

@Table(name= "address")
@Entity
public class Address {
    public String getCity;
    public String getUf;
    @Id
    @GeneratedValue
    private UUID id;
    private String city;


    @ManyToOne
    @JoinColumn(name = "event_id")

    private Event event; // Associando corretamente com a entidade Event
    private String state;

    public void setCity(String city) {
        this.city  = city;
    }

    public void setUf(String state) {
        this.state = state;


    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Object getUf() {
        return null;
    }

    public Object getCity() {
        return null;
    }

    // Getters e setters, construtores, etc.
}