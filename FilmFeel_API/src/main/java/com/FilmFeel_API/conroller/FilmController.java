package com.FilmFeel_API.conroller;

import com.FilmFeel_API.DTO.FilmDTO;
import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.repository.FilmRepository;
import com.FilmFeel_API.service.FilmService;

import com.FilmFeel_API.service.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/peliculas")
public class FilmController {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;


    @Autowired
    private StorageService storageService;


    @GetMapping("")
    public ResponseEntity<List<Film>> getFilms() {
        List<Film> films = filmService.getAllFilms();
        return ResponseEntity.ok(films);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> createFilm(@ModelAttribute FilmDTO filmDTO) {

        logger.info("Recibido FilmDTO: {}", filmDTO);


        if (filmDTO.getPoster() == null || filmDTO.getPoster().isEmpty()) {
            logger.error("El archivo está vacío o no se ha recibido.");
            return ResponseEntity.badRequest().body("El archivo no puede estar vacío");
        }


        logger.info("Nombre del archivo: {}", filmDTO.getPoster().getOriginalFilename());


        String filename = storageService.storage(filmDTO.getPoster());


        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setYear(filmDTO.getYear());
        film.setDuration(filmDTO.getDuration());
        film.setSynopsis(filmDTO.getSynopsis());
        film.setPosterRoute(filename);



        filmService.saveFilm(film);
        logger.info("Película creada con éxito: {}", film.getTitle());
        return ResponseEntity.ok("Película creada con éxito: " + film.getTitle());
    }


    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> updateFilm(@PathVariable Long id, @ModelAttribute FilmDTO filmDTO) {
        logger.info("Modificando película con ID: {}", id);


        Film existingFilm = filmService.getFilmById(id);

        if (existingFilm == null) {
            logger.error("Película no encontrada con ID: {}", id);
            return ResponseEntity.notFound().build();
        }


        logger.info("Datos recibidos en FilmDTO: title={}, year={}, poster={}",
                filmDTO.getTitle(), filmDTO.getYear(), filmDTO.getPoster() != null ? filmDTO.getPoster().getOriginalFilename() : "No file");


        existingFilm.setTitle(filmDTO.getTitle());
        existingFilm.setYear(filmDTO.getYear());
        existingFilm.setDuration(filmDTO.getDuration());
        existingFilm.setSynopsis(filmDTO.getSynopsis());


        if (filmDTO.getPoster() != null && !filmDTO.getPoster().isEmpty()) {
            String filename = storageService.storage(filmDTO.getPoster());
            existingFilm.setPosterRoute(filename);
        }


        filmService.saveFilm(existingFilm);
        logger.info("Película modificada con éxito: {}", existingFilm.getTitle());
        return ResponseEntity.ok("Película modificada con éxito: " + existingFilm.getTitle() + " (ID: " + existingFilm.getId() + ")");
    }





    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable Long id) {
        logger.info("Eliminando película con ID: {}", id);


        Film existingFilm = filmService.getAllFilms().stream()
                .filter(film -> film.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingFilm == null) {
            logger.error("Película no encontrada con ID: {}", id);
            return ResponseEntity.notFound().build();
        }


        filmService.deleteFilm(id);
        logger.info("Película eliminada con éxito: {}", existingFilm.getTitle());
        return ResponseEntity.ok("Película eliminada con éxito: " + existingFilm.getTitle());
    }


    @GetMapping("/{id}")
    public ResponseEntity <Film> getFilmDetails(@PathVariable Long id){
        logger.info("Accediendo a los detalles  de la película con ID {}", id);



        Film film = filmService.getFilmById(id);

        if (film==null){

            logger.error("Película no encontrada con ID {}", id);
            return ResponseEntity.notFound().build();
        }

    
        logger.info("Detalles de la película encontrados: {}", film.getTitle());
        return ResponseEntity.ok(film);

            }



}
