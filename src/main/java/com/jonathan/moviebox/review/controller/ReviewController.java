package com.jonathan.moviebox.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathan.moviebox.review.dto.CreateReviewDTO;
import com.jonathan.moviebox.review.model.Review;
import com.jonathan.moviebox.review.service.ReviewService;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @PostMapping("/create")
  public ResponseEntity<Review> createReview(@RequestBody CreateReviewDTO createReviewDTO) {
    return new ResponseEntity<Review>(reviewService.createReview(createReviewDTO), HttpStatus.CREATED);
  }

}
