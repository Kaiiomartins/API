package com.example.API.Domen.Eventos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.Descriptor;
import java.util.Date;
import java.util.UUID;

@Table(name ="envent")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    private UUID id;

    private String Description;

    private String Title;

    private String imaURL;

    private  String eventURL;

    private boolean remote;

    private Date data;
}
