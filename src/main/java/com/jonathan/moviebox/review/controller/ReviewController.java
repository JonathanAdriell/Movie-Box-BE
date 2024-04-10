package com.jonathan.moviebox.review.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PatchMapping("/update/{id}")
  public ResponseEntity<Review> updateReview(@PathVariable ObjectId id, @RequestBody String body) {
    return new ResponseEntity<Review>(reviewService.updateReview(id, body), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteReview(@PathVariable ObjectId id) {
    reviewService.deleteReview(id);
    return new ResponseEntity<Void>(HttpStatus.valueOf(204));
  }

}
