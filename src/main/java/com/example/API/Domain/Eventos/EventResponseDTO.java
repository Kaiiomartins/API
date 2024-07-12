package com.example.API.Domain.Eventos;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public record EventResponseDTO(UUID id, String title, String description, Date date, String city, String state, Boolean remote, String eventUrl) {
    public Arrays getContent() {
        return null;
    }
}
