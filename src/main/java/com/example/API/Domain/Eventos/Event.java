package com.example.API.Domain.Eventos;

import com.example.API.Domain.Address.Address;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Table(name ="event")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private UUID id;

    private String Description;

    private String Title;

    private String imgUrl;

    private  String eventURL;

    private boolean remote;

    private Date date;

    private MultipartFile image;
    @OneToOne(mappedBy =  "event", cascade = CascadeType.ALL)
    private Address address;


    public void setTitle(MultipartFile image) {
        this.image = image;
    }

    public void setDescrition(String description) {
        this.Description = description;
    }

    public void setEventUrl(String eventURL) {
        this.eventURL = eventURL;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEventUrl() {
        return eventURL;
    }
}
