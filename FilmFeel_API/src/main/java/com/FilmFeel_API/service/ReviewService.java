package com.FilmFeel_API.service;

import com.FilmFeel_API.DTO.ReviewDTO;
import com.FilmFeel_API.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewsByFilm(Long filmId);

    Review saveReview(Long filmId, ReviewDTO reviewDTO);

    void deleteReview (Long reviewId);
}
