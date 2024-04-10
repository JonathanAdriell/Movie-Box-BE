package com.jonathan.moviebox.review.exception;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {
  public ReviewNotFoundException(ObjectId id) {
    super("Review with id " + id + " does not exist");
  }
}
