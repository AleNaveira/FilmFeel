package com.FilmFeel_API.conroller;


import com.FilmFeel_API.model.Score;
import com.FilmFeel_API.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntuaciones")

public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/film/{filmId}")
    public List<Score> getScoresByFilm(@PathVariable Long filmId){
        return scoreService.getScoresByFilm(filmId);

    }

    @PostMapping("/film/{filmId}/nueva-puntuacion")
    public ResponseEntity<Score> addScore(@PathVariable Long filmId, @RequestBody Score score){
        Score savedScore = scoreService.saveScore(filmId,score);
        return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
    }



    @DeleteMapping("/eliminar/{scoreId}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long scoreId){
        scoreService.deleteScore(scoreId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/media/{filmId}")
    public Double getAverageScore(@PathVariable Long filmId){

        return scoreService.calculateAverageScore(filmId);
    }


}
