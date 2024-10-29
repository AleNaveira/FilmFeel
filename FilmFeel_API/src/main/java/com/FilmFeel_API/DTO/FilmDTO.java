package com.FilmFeel_API.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;




    @Data
    public class FilmDTO {
        private String title;
        private int year;
        private int duration;
        private String synopsis;
        private MultipartFile poster;

    }

