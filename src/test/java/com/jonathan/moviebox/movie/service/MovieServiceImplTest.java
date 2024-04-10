package com.jonathan.moviebox.movie.service;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.repository.MovieRepository;
import com.jonathan.moviebox.review.model.Review;

@SpringBootTest
public class MovieServiceImplTest {
  @Autowired
  private MovieService movieService;

  @MockBean
  private MovieRepository movieRepository;

  private Movie movie;
  private Review review;

  @BeforeEach
  void setup() {
    movie = Movie.builder()
        .id(new ObjectId())
        .imdbId("12345")
        .title("Solar Eclipse")
        .releaseDate("2024-04-08")
        .trailerLink("www.youtube.com")
        .genres(Arrays.asList("Action", "Sci-Fi", "Thriller"))
        .poster("www.google.com")
        .backdrops(Arrays.asList("www.google.com", "www.google.com"))
        .build();

    review = Review.builder()
        .id(new ObjectId())
        .body("Highly recommended!")
        .build();

    movie.setReviews(new ArrayList<Review>(Arrays.asList(review)));

    Mockito.when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
    Mockito.when(movieRepository.findMovieByImdbId(Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(movieRepository.findMovieByImdbId("12345")).thenReturn(Optional.of(movie));
  }

  @Test
  public void testGetAllMovies() {
    List<Movie> result = movieService.getAllMovies();
    verify(movieRepository, atLeastOnce()).findAll();
    Assertions.assertEquals(result, Arrays.asList(movie));
  }

  @Test
  public void testGetMovieByImdbIdAndFound() {
    Optional<Movie> result = movieService.getMovieByImdbId("12345");
    verify(movieRepository, atLeastOnce()).findMovieByImdbId("12345");
    Assertions.assertEquals(result, Optional.of(movie));
  }

  @Test
  public void testGetMovieByImdbIdAndNotFound() {
    Optional<Movie> result = movieService.getMovieByImdbId("51234");
    verify(movieRepository, atLeastOnce()).findMovieByImdbId("51234");
    Assertions.assertEquals(result, Optional.empty());
  }
}
