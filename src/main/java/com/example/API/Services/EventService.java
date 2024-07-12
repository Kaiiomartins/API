package com.example.API.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.API.Domain.Coupon.Coupon;
import com.example.API.Domain.Eventos.Event;
import com.example.API.Domain.Eventos.EventDetailsDTO;
import com.example.API.Domain.Eventos.EventResponseDTO;
import com.example.API.Domain.Eventos.EventRequestDTO;
import com.example.API.Repositorios.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private CouponService couponService;
    private EventRepository reposity;

    private AddressService AddresService;
    private EventRepository repository;

    public Event createEvent(EventRequestDTO data) {


        String imgUrl = null;

        if (data.image() != null) {
            imgUrl = this.uploading(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.image());
        newEvent.setDescrition(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());
        reposity.save(newEvent);

        if(!data.remote()){
            this.AddresService.createAddress(data, newEvent);
        }


        return newEvent;
    }

    public List<EventResponseDTO> getFilterEvents(int page, int size, String title, String city, String uf, Date startDate, Date endDate) {
       title = (title != null)? title: "";
       city = (city != null)? city : "";
       uf = (uf != null )? uf: "";
       startDate = (startDate != null )? startDate : new Date (0);
       endDate = (endDate != null )? endDate : new Date ();

        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.reposity.findFilteredEvents( title, city, uf, startDate, endDate, pageable);
        return eventsPage.getContent().stream()
                .map(event -> new EventResponseDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getDate(),
                        event.getAddress() != null ? (String) event.getAddress().getCity() : "",
                        event.getAddress() != null ? (String) event.getAddress().getUf() : "",
                        event.isRemote(),
                        event.getImgUrl()
                ))
                .collect(Collectors.toList());
    }


    

    public List<EventResponseDTO>getUpcomingEvents(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = (Page<Event>) this.reposity.findUpcomingEvents( new Date(),pageable);
        List<EventResponseDTO> eventResponseDTOList = eventsPage.getContent().stream()
                .map(event -> new EventResponseDTO(event.getId()
                        , event.getTitle()
                        , event.getDescription()
                        , event.getDate()
                        , event.getAddress()!= null ?event.getAddress().getCity : ""
                        , event.getAddress()!= null ?event.getAddress().getUf : ""
                        , event.isRemote()
                        , event.getImgUrl())).collect(Collectors.toList());



        return eventResponseDTOList;
    }
    public EventDetailsDTO getEventDetails (UUID eventId){
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        List<Coupon> coupons = couponService.consulCoupons(eventId, new Date());
        List<EventDetailsDTO.CouponDTO> couponDTOs = coupons.stream().map(coupon -> new EventDetailsDTO.CouponDTO(
                coupon.getCode(),
                coupon.getDiscount(),
                coupon.getValid()
        )).collect(Collectors.toList());

        return new EventDetailsDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getAddress() != null ? (String) event.getAddress().getCity() : "",
                event.getAddress() != null ? (String) event.getAddress().getUf() : "",
                event.getImgUrl(),
                event.getEventUrl(),
                couponDTOs);


    }


    private String uploading(MultipartFile multipatfile) {
        String filename= UUID.randomUUID()+"-"+ multipatfile.getOriginalFilename();
                try{
                   File file =  this.convertMultipartToFile(multipatfile);
                   s3Client.putObject(bucketName, filename, file);
                   file.delete();

                    return s3Client.getUrl(bucketName, filename).toString();
                }catch(Exception e){
System.out.println("Erro ao subir o arquivo");
return "";
                }

    }
private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
File convFile  = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    FileOutputStream fos  = new FileOutputStream(convFile);
    fos.write(multipartFile.getBytes());
    fos.close();
    return convFile;

}

    public Event updateEventDate(UUID eventId, Date newDate) {
        return null;
    }
}

