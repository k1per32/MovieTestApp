package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.dto.RestAPIObject;
import com.example.demo.entity.Movie;
import com.example.demo.exception.RestApiServiceException;
import com.example.demo.service.MovieService;
import com.example.demo.service.MovieServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Data
public class MoviesController {
    private final
    MovieService movieService;
    private final
    ModelMapper modelMapper;


    @Operation(
            summary = "Get page movies",
            tags = {"Movie service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("/movie/{page}")
    public ResponseEntity<List<Movie>> getAllMovie(@PathVariable(name = "page") int page) {
        return Optional.ofNullable(movieService.getAllMovie(page-1))
                .map(movies -> movies.stream().toList())
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);
    }


}


