package com.example.API.Domain.Coupon;
import  org.springframework.data.repository.CrudRepository;
import jakarta.persistence.*;
import lombok.*;
import com.example.API.Domain.Eventos.Event; // Corrigindo a importação para a entidade Event correta

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private Integer discount;
    private Date valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event; // Agora associando corretamente com a entidade Event

    // Getters e setters, construtores, etc.
}