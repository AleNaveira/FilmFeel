package com.FilmFeel_API.service;

import com.FilmFeel_API.model.Score;

import java.util.List;

public interface ScoreService {

    List<Score> getScoresByFilm(Long filmId);
    Score saveScore (Long filmId, Score score);

    Double calculateAverageScore(Long filmId);
    void deleteScore (Long scoreId);
}
