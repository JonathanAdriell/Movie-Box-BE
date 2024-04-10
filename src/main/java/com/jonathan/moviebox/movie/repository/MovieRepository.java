package com.jonathan.moviebox.movie.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jonathan.moviebox.movie.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

  Optional<Movie> findMovieByImdbId(String imdbId);

}
