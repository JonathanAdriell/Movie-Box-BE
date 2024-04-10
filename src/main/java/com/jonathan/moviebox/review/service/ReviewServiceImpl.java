package com.jonathan.moviebox.review.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathan.moviebox.movie.exception.MovieNotFoundException;
import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.repository.MovieRepository;
import com.jonathan.moviebox.review.dto.ReviewRequest;
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
  public Review createReview(ReviewRequest createRequest) {
    Movie movie = getMovieByImdbId(createRequest.getImdbId());

    return saveReviewAndLinkToMovie(createRequest.getBody(), movie);
  }

  @Override
  public Review updateReview(ObjectId id, ReviewRequest updateRequest) {
    Review review = getReviewById(id);
    review.setBody(updateRequest.getBody());

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

  private Movie getMovieByImdbId(String imdbId) {
    return movieRepository.findMovieByImdbId(imdbId)
        .orElseThrow(() -> new MovieNotFoundException(imdbId));
  }

  private Review getReviewById(ObjectId id) {
    return reviewRepository.findById(id)
        .orElseThrow(() -> new ReviewNotFoundException(id));
  }
}
