package com.example.demo.converter;

import com.example.demo.dto.UsersDto;
import com.example.demo.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {
    private final ModelMapper modelMapper;

    public UsersConverter() {
        this.modelMapper = new ModelMapper();
    }

    public UsersDto convertToDto(Users entity) {
        return modelMapper.map(entity, UsersDto.class);
    }
}
