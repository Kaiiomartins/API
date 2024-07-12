package com.example.API.Controller;

import com.example.API.Domain.Eventos.Event;
import com.example.API.Domain.Eventos.EventDetailsDTO;
import com.example.API.Domain.Eventos.EventResponseDTO;
import com.example.API.Domain.Eventos.EventRequestDTO;
import com.example.API.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")

public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("date") long date,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("remote") Boolean remote,
            @RequestParam("eventUrl") String eventUrl,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        // Criação do objeto EventRequestIDTO com os parâmetros recebidos
        EventRequestDTO body = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, image);

        // Chamada do serviço para criar o evento usando o objeto EventRequestIDTO
        Event newEvent = this.eventService.createEvent(body);

        // Retornando a resposta HTTP 200 OK com o evento criado
        return ResponseEntity.ok(newEvent);
    }

    @PutMapping("/{eventId}/date")
    public ResponseEntity<Event> updateEventDate(
            @PathVariable UUID eventId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date newDate) {

        Event updatedEvent = this.eventService.updateEventDate(eventId, newDate);
        return ResponseEntity.ok(updatedEvent);
    }

    @GetMapping("/eventId")
    public ResponseEntity<EventDetailsDTO>getEventDetails(@PathVariable UUID eventId){
        EventDetailsDTO eventDetails = eventService.getEventDetails(eventId);

        return ResponseEntity.ok(eventDetails);
    }


    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(allEvents);
    }

    public ResponseEntity<Page<EventResponseDTO>> getFilterEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        List<EventResponseDTO> events = eventService.getFilterEvents(page, size, title, city, uf, startDate, endDate);
        return ResponseEntity.ok((Page<EventResponseDTO>) events);
    }
}