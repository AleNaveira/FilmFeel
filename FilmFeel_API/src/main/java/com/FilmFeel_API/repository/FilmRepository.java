package com.FilmFeel_API.repository;

import com.FilmFeel_API.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmRepository extends JpaRepository <Film, Long> {
}
