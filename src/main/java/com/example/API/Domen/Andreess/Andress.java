package com.example.API.Domen.Andreess;

import io.micrometer.observation.Observation;
import jakarta.persistence.*;
import org.w3c.dom.events.Event;

import java.util.UUID;

@Table(name= "address")
@Entity

public class Andress {
    @Id
    @GeneratedValue
    private UUID id;
    private String city;
    @ManyToOne
    @JoinColumn(name = "event_id")

    private Event event;


}
