package com.FilmFeel_API.service;

import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Person;

import java.util.List;

public interface FilmService {

    public void saveFilm(Film film);

    public List<Film> getAllFilms();

    public Film getFilmById(Long id);
    void deleteFilm(Long id);

    List<Film> findAllFilmsByPerson(Long personId);
}
