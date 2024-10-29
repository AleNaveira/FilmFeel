package com.FilmFeel_API.repository;

import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository <Review, Long>{


    List<Review> findByFilmId(Long filmId);

    void deleteByFilm(Film film);
}
