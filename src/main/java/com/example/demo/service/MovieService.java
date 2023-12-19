package com.example.demo.service;

import com.example.demo.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovie(int page);
}
