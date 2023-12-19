package com.example.demo.service;

import com.example.demo.entity.Favourites;
import com.example.demo.entity.Movie;

import java.util.List;

public interface FavouritesService {
    List<Movie> getNotInFavourites(Integer id, String loaderType);
    Favourites addFavourites(Favourites favourites);

    void deleteFavourites(Favourites favourites);
}
