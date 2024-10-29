package com.FilmFeel_API.repository;

import com.FilmFeel_API.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByFilmId(Long filmId);
}
