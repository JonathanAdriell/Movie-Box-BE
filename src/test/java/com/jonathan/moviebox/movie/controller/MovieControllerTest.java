package com.jonathan.moviebox.movie.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.service.MovieService;
import com.jonathan.moviebox.review.model.Review;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MovieService movieService;

  private Movie movie;
  private Review review;

  @BeforeEach
  void setup() {
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

    movie.setReviews(new ArrayList<Review>(Arrays.asList(review)));
  }

  @Test
  public void testGetAllMovies() throws Exception {
    Mockito.when(movieService.getAllMovies()).thenReturn(Arrays.asList(movie));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.handler().methodName("getAllMovies"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].imdbId").value(movie.getImdbId()));

    verify(movieService, atLeastOnce()).getAllMovies();
  }

  @Test
  public void testGetMovieByImdbId() throws Exception {
    Mockito.when(movieService.getMovieByImdbId("12345")).thenReturn(Optional.of(movie));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/12345")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.handler().methodName("getMovieByImdbId"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.imdbId").value(movie.getImdbId()));

    verify(movieService, atLeastOnce()).getMovieByImdbId("12345");
  }

}
