package com.FilmFeel_API.service;

import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Person;

import com.FilmFeel_API.model.TypePersonEnum;
import com.FilmFeel_API.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

 @Autowired
    private PersonRepository personRepository;



    @Override
    public List<Person> listAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
        logger.info("Buscando persona con ID: {}", id);
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + id));
        logger.info("Persona encontrada: {} {}", person.getName(), person.getSurname());
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }





    public List <Person> findByTypePerson (TypePersonEnum typePersonEnum){
        return personRepository.findByTypePerson(typePersonEnum);
    }


    @Override
    public List<Person> getAllPhotographers() {
        return personRepository.findByTypePerson(TypePersonEnum.FOTOGRAFO);
    }

    @Override
    public List<Person> getAllScriptwriters() {
        return personRepository.findByTypePerson(TypePersonEnum.GUIONISTA);
    }

    @Override
    public List<Person> getAllMusicians() {

        return personRepository.findByTypePerson(TypePersonEnum.MUSICO);
    }

    @Override
    public List<Person> getAllDirectors() {
        return personRepository.findByTypePerson(TypePersonEnum.DIRECTOR);
    }

    @Override
    public List<Person> getAllActors() {
        return personRepository.findByTypePerson(TypePersonEnum.ACTOR);
    }



}
