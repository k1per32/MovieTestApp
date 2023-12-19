package com.example.demo.dto;

import com.example.demo.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    int id;
    String name;
    String url;
    @JsonProperty("usersList")
    List<Users> usersListDto;
}
