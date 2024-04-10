package com.jonathan.moviebox.review.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jonathan.moviebox.movie.exception.MovieNotFoundException;
import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.repository.MovieRepository;
import com.jonathan.moviebox.review.dto.CreateReviewDTO;
import com.jonathan.moviebox.review.model.Review;
import com.jonathan.moviebox.review.repository.ReviewRepository;

@SpringBootTest
public class ReviewServiceImplTest {

  @Autowired
  private ReviewService reviewService;

  @MockBean
  private ReviewRepository reviewRepository;

  @MockBean
  private MovieRepository movieRepository;

  private CreateReviewDTO createValidReviewDTO;
  private CreateReviewDTO createInvalidReviewDTO;
  private Movie movie;
  private Review review;

  @BeforeEach
  void setup() {
    createValidReviewDTO = CreateReviewDTO.builder()
        .imdbId("12345")
        .body("Highly recommended!")
        .build();

    createInvalidReviewDTO = CreateReviewDTO.builder()
        .imdbId("51234")
        .body("Highly recommended!")
        .build();

    movie = Movie.builder()
        .imdbId("12345")
        .title("Solar Eclipse")
        .releaseDate("2024-04-08")
        .trailerLink("www.youtube.com")
        .genres(Arrays.asList("Action", "Sci-Fi", "Thriller"))
        .poster("www.google.com")
        .backdrops(Arrays.asList("www.google.com", "www.google.com"))
        .build();

    review = Review.builder()
        .body("Highly recommended!")
        .build();

    Mockito.when(movieRepository.findMovieByImdbId(Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(movieRepository.findMovieByImdbId("12345")).thenReturn(Optional.of(movie));
    Mockito.when(reviewRepository.insert(review)).thenReturn(review);
    movie.setReviews(new ArrayList<Review>(Arrays.asList(review)));
    Mockito.when(movieRepository.save(movie)).thenReturn(movie);
  }

  @Test
  public void testCreateReviewAndValid() {
    Review result = reviewService.createReview(createValidReviewDTO);

    verify(movieRepository, atLeastOnce()).findMovieByImdbId("12345");
    verify(reviewRepository, atLeastOnce()).insert(review);
    verify(movieRepository, atLeastOnce()).save(movie);

    Assertions.assertEquals(result, review);
  }

  @Test
  public void testCreateReviewAndInvalid() {
    Assertions.assertThrows(MovieNotFoundException.class, () -> {
      reviewService.createReview(createInvalidReviewDTO);
    });

    verify(movieRepository, atLeastOnce()).findMovieByImdbId("51234");
    verify(reviewRepository, never()).insert(any(Review.class));
    verify(movieRepository, never()).save(any(Movie.class));
  }

}
