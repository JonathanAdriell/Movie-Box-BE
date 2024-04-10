package com.jonathan.moviebox.review.service;

import com.jonathan.moviebox.review.dto.CreateReviewDTO;
import com.jonathan.moviebox.review.model.Review;

public interface ReviewService {

  Review createReview(CreateReviewDTO createReviewDTO);
  
}
