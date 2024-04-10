package com.jonathan.moviebox.review.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathan.moviebox.movie.exception.MovieNotFoundException;
import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.repository.MovieRepository;
import com.jonathan.moviebox.review.dto.CreateReviewDTO;
import com.jonathan.moviebox.review.exception.ReviewNotFoundException;
import com.jonathan.moviebox.review.model.Review;
import com.jonathan.moviebox.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private MovieRepository movieRepository;

  @Override
  public Review createReview(CreateReviewDTO createReviewDTO) {
    String imdbId = createReviewDTO.getImdbId();
    String body = createReviewDTO.getBody();

    Movie movie = movieRepository.findMovieByImdbId(imdbId)
        .orElseThrow(() -> new MovieNotFoundException(imdbId));

    return saveReviewAndLinkToMovie(body, movie);
  }

  @Override
  public Review updateReview(ObjectId id, String body) {
    Review review = getReviewById(id);
    review.setBody(body);

    return reviewRepository.save(review);
  }

  @Override
  public void deleteReview(ObjectId id) {
    getReviewById(id);

    reviewRepository.deleteById(id);
  }

  private Review saveReviewAndLinkToMovie(String body, Movie movie) {
    Review review = Review.builder().body(body).build();
    reviewRepository.insert(review);

    movie.getReviews().add(review);
    movieRepository.save(movie);

    return review;
  }

  private Review getReviewById(ObjectId id) {
    return reviewRepository.findById(id)
        .orElseThrow(() -> new ReviewNotFoundException(id));
  }

}
