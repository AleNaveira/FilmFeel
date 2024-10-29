package com.FilmFeel_API.conroller;

import com.FilmFeel_API.DTO.ReviewDTO;
import com.FilmFeel_API.model.Review;
import com.FilmFeel_API.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/film/{filmId}")
    public List<Review> getReviewsByFilm(@PathVariable Long filmId){

        return reviewService.getReviewsByFilm(filmId);
    }



    @PostMapping("/film/{filmId}/nueva-review")
    public ResponseEntity<Review>addReview(@PathVariable Long filmId, @RequestBody ReviewDTO reviewDTO){
Review savedReview= reviewService.saveReview(filmId, reviewDTO);

return new ResponseEntity<>(savedReview, HttpStatus.CREATED);

    }

    @DeleteMapping("/borrar/{reviewId}")
    public ResponseEntity <Void> deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
