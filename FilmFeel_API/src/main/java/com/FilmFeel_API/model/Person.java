package com.FilmFeel_API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_surname")
    private String surname;

    @Enumerated(EnumType.STRING)
    private TypePersonEnum typePerson;

    @JsonIgnore
    @OneToMany(mappedBy = "photographer")
    private List<Film> filmsPhotographers;


    @JsonIgnore
    @ManyToMany(mappedBy = "scriptwriters")
    private List<Film> scriptwriters;
    @JsonIgnore
    @ManyToMany(mappedBy = "filmsMusicians")
    private List<Film> filmsMusicians;
    @JsonIgnore
    @OneToMany(mappedBy = "directors")
    private List<Film> filmsDirector;
    @JsonIgnore
    @ManyToMany(mappedBy = "actors")
    private List<Film> filmsActors;

}
