package com.jonathan.moviebox.review.service;

import org.bson.types.ObjectId;

import com.jonathan.moviebox.review.dto.CreateReviewDTO;
import com.jonathan.moviebox.review.model.Review;

public interface ReviewService {

  Review createReview(CreateReviewDTO createReviewDTO);

  Review updateReview(ObjectId id, String body);

  void deleteReview(ObjectId id);
  
}
