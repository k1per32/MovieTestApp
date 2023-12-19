package com.example.demo.dao;

import com.example.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByName(String name);

    Optional<Movie> findByName(String name);
}
