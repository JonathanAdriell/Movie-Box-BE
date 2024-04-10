package com.jonathan.moviebox.movie.service;

import java.util.List;
import java.util.Optional;

import com.jonathan.moviebox.movie.model.Movie;

public interface MovieService {
  List<Movie> getAllMovies();

  Optional<Movie> getMovieByImdbId(String id);
}
