package com.jonathan.moviebox.movie.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.jonathan.moviebox.review.model.Review;

import lombok.Builder;
import lombok.Data;

@Document(collection = "movies")
@Data
@Builder
public class Movie {
  @Id
  private ObjectId id;
  private String imdbId;
  private String title;
  private String releaseDate;
  private String trailerLink;
  private List<String> genres;
  private String poster;
  private List<String> backdrops;

  @DocumentReference
  private List<Review> reviews;
}
