package com.FilmFeel_API.conroller;


import com.FilmFeel_API.DTO.PersonDTO;
import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Person;
import com.FilmFeel_API.service.FilmPersonHelperService;
import com.FilmFeel_API.service.FilmService;
import com.FilmFeel_API.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personas")

public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

  @Autowired
    private PersonService personService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmPersonHelperService filmPersonHelperService;




    @PostMapping("/crear")
    public ResponseEntity<String> createPerson(@RequestBody PersonDTO personDTO){
        logger.info("Creando una nueva persona : {}", personDTO);

        Person person = new Person();
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setTypePerson(personDTO.getTypeEnum());

        personService.savePerson(person);

        return ResponseEntity.ok("Persona creada con éxito: " + person.getName() + " " + person.getSurname() );

    }




    @GetMapping("/listar")
    public ResponseEntity<List<Person>> listAllPersons(){
        List <Person> persons = personService.listAllPersons();
        return ResponseEntity.ok(persons);

    }


    @PostMapping("/{filmId}/asociar-pelicula/{personId}")
    public ResponseEntity<String> associatePersonWithFilm( @PathVariable Long filmId, @PathVariable Long personId){

        Film film =filmService.getFilmById(filmId);
        Person person = personService.getPerson(personId);

        if (film ==null  || person == null){

            return ResponseEntity.notFound().build();
        }



        filmPersonHelperService.addPersonToFilm(film.getId(), person.getId());

        return ResponseEntity.ok("Persona asociada con éxito: " + person.getName() + " " + person.getSurname());



    }




    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        logger.info("Eliminando persona con ID: {}", id);


        Person existingPerson = personService.getPerson(id);
        if (existingPerson == null) {
            logger.error("Persona no encontrada con ID: {}", id);
            return ResponseEntity.notFound().build();
        }


        filmPersonHelperService.deletePerson(id);
        logger.info("Persona eliminada con éxito: {}", existingPerson.getName() + " " + existingPerson.getSurname());
        return ResponseEntity.ok("Persona eliminada con éxito: " + existingPerson.getName() + " " + existingPerson.getSurname());
    }



}
