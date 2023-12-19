package com.example.demo.controller;

import com.example.demo.converter.UsersConverter;
import com.example.demo.dao.UsersRepository;
import com.example.demo.dto.RestAPIObject;
import com.example.demo.dto.UsersDto;
import com.example.demo.entity.Users;

import com.example.demo.exception.RestApiServiceException;
import com.example.demo.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Data
public class UsersController {
    private final
    UsersService usersService;
    private final
    UsersConverter usersConverter;


    @Operation(
            summary = "Sign up users",
            tags = {"Users service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class))))})
    @PostMapping("/users")
    public ResponseEntity<UsersDto> signUp(@RequestBody Users users) {
        return Optional.of(users)
                .map(usersService::signUp)
                .map(usersConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);
    }

    @Operation(
            summary = "Get list users",
            tags = {"Users service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class))))})
    @GetMapping("/users")
    public ResponseEntity<List<Users>> listUsers() {
        return Optional.of(usersService.getUsers())
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);

    }

    @Operation(
            summary = "Changes users",
            tags = {"Users service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class))))})
    @PutMapping("/users/{id}")
    public ResponseEntity<UsersDto> changeUser(@PathVariable Integer id, @RequestBody Users users) {
        return Optional.of(id)
                .map(usersService::getUsersById)
                .map(users1 -> usersService.changeUser(users1.getId(), users))
                .map(usersConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);
    }

    @Operation(
            summary = "Get users by ID",
            tags = {"Users service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class))))})
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable int id) {
        return Optional.of(id)
                .map(usersService::getUsersById)
                .map(usersConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElseThrow(RestApiServiceException::new);
    }

    @Operation(
            summary = "Delete users",
            tags = {"Users service"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = List.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestAPIObject.class))))})
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
         Optional.of(id)
                .map(usersService::getUsersById)
                .ifPresent(users -> usersService.deleteUser(users.getId()));
    }

}
