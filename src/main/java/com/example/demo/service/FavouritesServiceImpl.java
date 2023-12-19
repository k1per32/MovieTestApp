package com.example.demo.service;

import com.example.demo.dao.FavouritesRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.entity.Favourites;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Users;
import com.example.demo.exception.RestApiServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.QueryParam;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Log4j2
@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final EntityManager entityManager;
    private final MovieRepository movieRepository;
    private final FavouritesRepository favouritesRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<Movie> getNotInFavourites(@PathVariable Integer id, @QueryParam("loaderType") String loaderType) {
        List<Movie> list = new ArrayList<>();
        if (loaderType.equals("sql")) {
            log.info("Begin outputting movies using sql query");
            Session session = entityManager.unwrap(Session.class);
            Query<Movie> query = session.createQuery("SELECT name from Movie where id not in " +
                    "(SELECT idMoviesFav from Favourites where idUsersFav=:id)", Movie.class);
            query.setParameter("id", id);
            list = query.getResultList();
            log.info("End outputting movies using sql query");
        }
        if (loaderType.equals("inMemory")) {
            log.info("Begin outputting movies using in memory applications");
            List<Movie> movieList = movieRepository.findAll();
            List<Movie> movieListFromUsersOpt =
                    usersRepository.findById(Long.valueOf(id)).get().getMovieList();
            movieListFromUsersOpt = movieListFromUsersOpt.stream().sorted(Comparator.comparing(Movie::getId)).collect(Collectors.toList());
            movieList.removeAll(movieListFromUsersOpt);
            list.addAll(movieList);
            log.info("End outputting movies using in memory applications");
        }
        return list;
    }

    @Override
    public Favourites addFavourites(Favourites favourites) {
        log.info("Begin adding movie in favourites");
        Optional<Favourites> fav = favouritesRepository.findById((long) favourites.getId());
        if (fav.isEmpty()) {
            log.info("Begin adding movie in favourites was successful");
            return favouritesRepository.save(favourites);
        } else throw new RestApiServiceException();

    }

    @Override
    public void deleteFavourites(Favourites favourites) {
        if(favouritesRepository.findById((long) favourites.getId()).isPresent()){
            log.info("There is a favourites to delete");
            favouritesRepository.delete(favourites);
        }
    }
}
