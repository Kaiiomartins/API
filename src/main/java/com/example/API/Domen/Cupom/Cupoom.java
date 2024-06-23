package com.example.API.Domen.Cupom;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.events.Event;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cupoom {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private Integer  discount;
    private Data valid;

@ManyToOne
@JoinColumn(name = "event_id")

    private Event event;



}
