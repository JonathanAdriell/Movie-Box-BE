package com.jonathan.moviebox.review.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jonathan.moviebox.review.dto.ReviewRequest;
import com.jonathan.moviebox.review.model.Review;
import com.jonathan.moviebox.review.service.ReviewService;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @PostMapping("/create")
  public ResponseEntity<Review> createReview(@RequestBody ReviewRequest createRequest) {
    Review createdReview = reviewService.createReview(createRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<Review> updateReview(@PathVariable("id") ObjectId id,
      @RequestBody ReviewRequest updateRequest) {
    Review updatedReview = reviewService.updateReview(id, updateRequest);
    return ResponseEntity.ok(updatedReview);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteReview(@PathVariable("id") ObjectId id) {
    reviewService.deleteReview(id);
    return ResponseEntity.noContent().build();
  }
}
