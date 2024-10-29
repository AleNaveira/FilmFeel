package com.FilmFeel_API.service;

import com.FilmFeel_API.model.Person;
import com.FilmFeel_API.model.TypePersonEnum;

import java.util.List;

public interface PersonService {

    public List<Person> listAllPersons();
    public Person savePerson (Person person);

    public Person getPerson(Long id);
    public Person updatePerson(Person person);
    List<Person> findByTypePerson(TypePersonEnum typePerson);
    public List<Person> getAllPhotographers();
    public List<Person> getAllScriptwriters();
    public List<Person> getAllMusicians();
    public List<Person> getAllDirectors();
    public List<Person> getAllActors();



}
