package com.FilmFeel_API.service;


import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Person;
import com.FilmFeel_API.repository.FilmRepository;
import com.FilmFeel_API.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmPersonHelperService {

    @Autowired
    private PersonRepository personRepository;
    private final FilmService filmService;
    private final PersonService personService;

    @Autowired
    public FilmPersonHelperService(FilmService filmService, PersonService personService) {
        this.filmService = filmService;
        this.personService = personService;
    }

    public void addPersonToFilm( Long filmId, Long personId){
        Film film = this.filmService.getFilmById(filmId);
        Person person = personService.getPerson(personId);

        if(film==null || person ==null){
            throw new IllegalArgumentException("Película o persona no encontrada");}

        switch (person.getTypePerson()){
            case GUIONISTA:
                film.getScriptwriters().add(person);
                break;
            case MUSICO:
                film.getFilmsMusicians().add(person);
                break;
            case FOTOGRAFO:
                if (film.getPhotographer() != null) {
                    throw new IllegalArgumentException("Ya existe un fotógrafo asignado a esa película. ");
                }
                film.setPhotographer(person);
                break;
            case ACTOR:
                film.getActors().add(person);
                break;

            case DIRECTOR:
                if(film.getDirectors() !=null){
                    throw new IllegalArgumentException("Ya existe un director asociado a esta película. ");
                }

                film.setDirectors(person);
                break;
            default:
                throw new IllegalArgumentException("Tipo de persona no reconocido.");
        }
        filmService.saveFilm(film);
    }

    public void deletePerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + personId));


        List<Film> films = filmService.findAllFilmsByPerson(personId);


        for (Film film : films) {
            if (film.getPhotographer() != null && film.getPhotographer().getId().equals(personId)) {
                film.setPhotographer(null);
            }
            film.getScriptwriters().removeIf(scriptwriter -> scriptwriter.getId().equals(personId));
            film.getFilmsMusicians().removeIf(musician -> musician.getId().equals(personId));
            film.getActors().removeIf(actor -> actor.getId().equals(personId));
            if (film.getDirectors() != null && film.getDirectors().getId().equals(personId)) {
                film.setDirectors(null);
            }
            filmService.saveFilm(film);
        }

        personRepository.delete(person);
    }


}

