package com.example.demo.dao;

import com.example.demo.entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
   List<Favourites> findAll();
   Optional<Favourites> findFirstByIdUsersFavAndIdMoviesFav(int idUsersFav, int idMoviesFav);
   List<Favourites> findAllByIdUsersFav(Integer idUsersFav);

    List<Favourites> findAllByIdMoviesFav(Integer idMoviesFav);
}
