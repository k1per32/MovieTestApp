package com.example.demo.service;

import com.example.demo.controller.UsersController;
import com.example.demo.dao.UsersRepository;
import com.example.demo.entity.Users;
import com.example.demo.exception.RestApiServiceException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@Log4j2
public class UsersServiceImplementation implements UsersService {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String LATIN_PATTERN = "[a-zA-Z]+";

    private final
    UsersRepository usersRepository;

    @Override
    public List<Users> getUsers() {
        if(usersRepository.findAll().isEmpty()){
            log.info("No user was found: ");
        }
        return usersRepository.findAll();
    }

    @Override
    public Users getUsersById(long s) {
        if(usersRepository.findById(s).isEmpty()){
            log.info("No user was found: ");
        }
            return usersRepository.findById(s).get();
    }

    @Override
    public Users changeUser(Integer id, Users users) {
        if(usersRepository.findById(Long.valueOf(id)).isEmpty()){
            log.info("No user was found: ");
        }
        Users user = usersRepository.findById(Long.valueOf(id)).get();
        if (users.getEmail().matches(EMAIL_PATTERN) &&
                users.getUsername().matches(LATIN_PATTERN)) {
            user.setUsername(users.getUsername());
            user.setName(users.getName());
            log.info("User changed to the database");
        }
        return usersRepository.save(user);
    }

    @Override
    public Users signUp(Users users) {
        if (users.getEmail().matches(EMAIL_PATTERN)
                && users.getUsername().matches(LATIN_PATTERN) &&
                usersRepository.findAllByEmail(users.getEmail()).isEmpty()) {
            log.info("The user is added to the database");
           return usersRepository.save(users);
        }else throw new RestApiServiceException();

    }


    @Override
    public void deleteUser(long s) {
        if(usersRepository.findById(s).isEmpty()){
            log.warn("No user was found: ");
        }
        usersRepository.deleteById(s);
    }


}
