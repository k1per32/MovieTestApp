package com.example.demo.service;

import com.example.demo.entity.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsersService {
     List<Users> getUsers();

     Users getUsersById(long s);

    Users changeUser(Integer id, Users users);


    Users signUp(Users users);

    void deleteUser(long s);


}
