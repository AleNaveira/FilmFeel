package com.FilmFeel_API.service;

import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Score;
import com.FilmFeel_API.repository.ScoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreRepository scoreRepository;


    @Autowired
    private FilmService filmService;

    @Override
    public List<Score> getScoresByFilm(Long filmId){
        //Verificamos si la película existe antes de buscar las puntuaciones
        Film film = filmService.getFilmById(filmId);
        return scoreRepository.findByFilmId(filmId);

    }

    @Override
    public Score saveScore (Long filmId, Score score){

        Film film= filmService.getFilmById(filmId);
        score.setFilm(film);

     return scoreRepository.save(score);

    }

    @Override
    public Double calculateAverageScore(Long filmId) {

        List <Score> scores = scoreRepository.findByFilmId(filmId);

        if(scores.isEmpty()){
            return 0.0; //Como no hay puntuaciones, retornamos 0.0
        }
        double total =0;
        for(Score score :scores){
            total+=score.getValue();

        }
        return total/ scores.size(); //Media de las puntuaciones
    }

    @Override
    public void deleteScore(Long scoreId){
        Score score = scoreRepository.findById(scoreId)
                        .orElseThrow(()-> new EntityNotFoundException("Puntuación no encontradacon ID" + scoreId));
       scoreRepository.delete(score);
    }
}
