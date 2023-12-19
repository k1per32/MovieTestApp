package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import org.springframework.http.ResponseEntity;

public interface MovieSchedulingService {
    MovieDto getMovie();
}
