package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Data
@Entity
@ToString
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movies", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @ManyToMany
    @JoinTable(name = "favourites",
            joinColumns = @JoinColumn(name = "id_movies_fav"),
            inverseJoinColumns = @JoinColumn(name = "id_users_fav")
    )
    @JsonIgnore
    private List<Users> usersList;

}
