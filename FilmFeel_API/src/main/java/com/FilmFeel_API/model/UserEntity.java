package com.FilmFeel_API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_username")
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_image")
    private String image;
    @Column(name = "user_birthDate")
    private Date birthDate;
    @Column(name = "user_creationDate")
    private LocalDate creationDate;
    @Column(name = "user_lastLogin")
    private LocalDateTime lastLogin;
    @Column(name = "user_active")
    private boolean active;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List <Role> roles;

    @OneToMany(mappedBy = "userEntity")
    private List<Film> films;

    @OneToMany(mappedBy = "userEntity")
    private List<Review> reviews;

    @OneToMany(mappedBy = "userEntity")
    private List<Score> scores;


}
