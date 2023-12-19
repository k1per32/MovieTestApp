package com.example.demo.controller;

import com.example.demo.dao.FavouritesRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.dto.RestAPIObject;
import com.example.demo.entity.Favourites;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Users;
import com.example.demo.exception.RestApiServiceException;
import com.example.demo.service.FavouritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@Data
public class FavouritesController {
    private final
    FavouritesRepository favouritesRepository;
    private final
    MovieRepository movieRepository;
    private final
    UsersRepository usersRepository;
    private final
    FavouritesService favouritesService;


    @Operation(
            summary = "Get not in favourites movie",
            tags = {"Favourites service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("/favourites/{id}")
    public ResponseEntity<List<Movie>> getNotInFavourites(@PathVariable(name = "id") final int id,
                                                          @QueryParam("loaderType") String loaderType) {
        return Optional.of(usersRepository.findById((long) id).get().getId())
                .map(integer -> favouritesService.getNotInFavourites(integer, loaderType))
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);
    }

    @Operation(
            summary = "Add favourite movie to users",
            tags = {"Favourites service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping("/favourites/{id}")
    public ResponseEntity<Favourites> addFavourites(@PathVariable(name = "id") final int id,
                                                    @RequestBody Movie movie) {
        return Optional.of(new Favourites(id, movie.getId()))
                .map(favouritesService::addFavourites)
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);
    }


    @Operation(
            summary = "Delete favourites movie of users",
            tags = {"Favourites service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @DeleteMapping("/favourites/{id}")
    public void deleteFavourites(@PathVariable(name = "id") final int id,
                                 @RequestBody Movie movie) {
        Optional.of(favouritesRepository
                        .findFirstByIdUsersFavAndIdMoviesFav(id, movie.getId()).get())
                .ifPresent(favouritesService::deleteFavourites);
    }
}

