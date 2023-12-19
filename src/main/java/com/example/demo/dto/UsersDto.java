package com.example.demo.dto;

import com.example.demo.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    int id;
    String email;
    String username;
    String name;
    @JsonProperty("movieList")
    List<Movie> movieListDto;
}
