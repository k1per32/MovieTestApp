package com.example.demo.service;

import com.example.demo.dao.MovieRepository;
import com.example.demo.entity.Movie;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@Log4j2
public class MovieServiceImplementation implements MovieService {
    private final
    MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovie(int page) {
        log.info("Retrieved " + page + " with movies");
        return movieRepository.findAll(PageRequest.of(page, 15)).stream().toList();
    }
}
