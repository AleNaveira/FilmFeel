package com.FilmFeel_API.DTO;

import com.FilmFeel_API.model.TypePersonEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PersonDTO {

    private String name;
    private String surname;
    private TypePersonEnum typeEnum;
}
