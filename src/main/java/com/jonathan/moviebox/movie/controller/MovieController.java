package com.jonathan.moviebox.movie.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.service.MovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
  }
  
  @GetMapping("/{imdbId}")
  public ResponseEntity<Optional<Movie>> getMovieByImdbId(@PathVariable String imdbId) {
    return new ResponseEntity<Optional<Movie>>(movieService.getMovieByImdbId(imdbId), HttpStatus.OK);
  }

}
