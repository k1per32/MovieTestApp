package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favourites")
public class Favourites {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_users_fav")
    private int idUsersFav;

    @Column(name = "id_movies_fav")
    private int idMoviesFav;

    public Favourites(int idUsersFav, int idMoviesFav) {
        this.idUsersFav = idUsersFav;
        this.idMoviesFav = idMoviesFav;
    }
}
