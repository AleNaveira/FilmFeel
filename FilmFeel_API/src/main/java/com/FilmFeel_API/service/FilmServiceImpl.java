package com.FilmFeel_API.service;

import com.FilmFeel_API.model.Film;

import com.FilmFeel_API.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService{

    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);



    @Autowired
    private FilmRepository filmRepository;





    @Override
    public void saveFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film getFilmById(Long id) {
        logger.info("Buscando película con ID: {}", id);
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Película no encontrada con ID: " + id));
        logger.info("Película encontrada: {}", film.getTitle());
        return film;
    }

    @Override
    public void deleteFilm(Long id) {

        Film film = filmRepository.findById(id)
                        .orElseThrow(()->new EntityNotFoundException("Película no encontrada con ID: " + id));
        filmRepository.delete(film);
    }




    @Override
    public List<Film> findAllFilmsByPerson(Long personId) {
        return filmRepository.findAll().stream()
                .filter(film-> film.getPhotographer() !=null && film.getPhotographer().getId().equals(personId) ||
                        film.getScriptwriters().stream().anyMatch(scriptwriter->scriptwriter.getId().equals(personId)) ||
                        film.getFilmsMusicians().stream().anyMatch(musician->musician.getId().equals(personId)) ||
                        film.getActors().stream().anyMatch(actor->actor.getId().equals(personId)) ||
                        (film.getDirectors() != null && film.getDirectors().getId().equals(personId)))
                .collect(Collectors.toList());


    }
}
