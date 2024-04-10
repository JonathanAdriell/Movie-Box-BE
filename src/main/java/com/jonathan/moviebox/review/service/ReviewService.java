package com.jonathan.moviebox.review.service;

import org.bson.types.ObjectId;

import com.jonathan.moviebox.review.dto.ReviewRequest;
import com.jonathan.moviebox.review.model.Review;

public interface ReviewService {
  Review createReview(ReviewRequest createRequest);

  Review updateReview(ObjectId id, ReviewRequest updateRequest);

  void deleteReview(ObjectId id);
}
