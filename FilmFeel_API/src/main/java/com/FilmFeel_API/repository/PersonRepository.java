package com.FilmFeel_API.repository;

import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Person;
import com.FilmFeel_API.model.TypePersonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {
    List<Person> findByTypePerson(TypePersonEnum typePerson );

}
