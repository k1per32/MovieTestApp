package com.example.demo.converter;

import com.example.demo.dto.MovieDto;

import com.example.demo.entity.Movie;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter {
    private final ModelMapper modelMapper;

    public MovieConverter() {
        this.modelMapper = new ModelMapper();
    }

    public MovieDto convertToDto(Movie entity) {
        return modelMapper.map(entity, MovieDto.class);
    }
}