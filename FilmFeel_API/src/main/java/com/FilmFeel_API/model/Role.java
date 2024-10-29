package com.FilmFeel_API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;
    @Column(name="role_name")
    private String name;



    @ManyToMany(mappedBy = "roles")
    private List <UserEntity> usersEntity;
}

