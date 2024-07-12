package com.example.API.Domain.Eventos;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(String title, String description, long date, String city, String state, Boolean remote, String eventUrl, MultipartFile image) {

}
