package com.jonathan.moviebox.review.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "reviews")
@Data
@Builder
public class Review {
  @Id
  private ObjectId id;
  private String body;
}
