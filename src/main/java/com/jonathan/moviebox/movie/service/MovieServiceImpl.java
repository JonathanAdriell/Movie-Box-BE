package com.jonathan.moviebox.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathan.moviebox.movie.model.Movie;
import com.jonathan.moviebox.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
  @Autowired
  private MovieRepository movieRepository;

  @Override
  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  @Override
  public Optional<Movie> getMovieByImdbId(String id) {
    return movieRepository.findMovieByImdbId(id);
  }
}
