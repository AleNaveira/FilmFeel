package com.FilmFeel_API.service;

import com.FilmFeel_API.DTO.ReviewDTO;
import com.FilmFeel_API.model.Film;
import com.FilmFeel_API.model.Review;
import com.FilmFeel_API.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FilmService filmService;

    @Override
    public List<Review> getReviewsByFilm(Long filmId){

        Film film =filmService.getFilmById(filmId);
        return reviewRepository.findByFilmId(filmId);

    }

    @Override
    public Review saveReview( Long filmId, ReviewDTO reviewDTO){
        Film film =filmService.getFilmById(filmId);


        Review review = new Review();
        review.setReviewTitle(reviewDTO.getReviewTitle());
        review.setReviewText(reviewDTO.getReviewText());
        review.setReviewDate(reviewDTO.getReviewDate() != null ? reviewDTO.getReviewDate() : LocalDate.now()); // Si no se especifica la fecha, se usa la actual
        review.setFilm(film);

        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId){

        Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(()-> new EntityNotFoundException("Reseña no encontrada con ID" + reviewId));
        reviewRepository.delete(review);
    }
}
