package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@ToString
public class Users {
    @Id
    @Column(name = "id_users", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "favourites",
            joinColumns = @JoinColumn(name = "id_users_fav"),
            inverseJoinColumns = @JoinColumn(name = "id_movies_fav")
    )
    @JsonIgnore
    private List<Movie> movieList;


}
