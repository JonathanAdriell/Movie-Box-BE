package com.jonathan.moviebox.review.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathan.moviebox.review.dto.CreateReviewDTO;
import com.jonathan.moviebox.review.model.Review;
import com.jonathan.moviebox.review.service.ReviewService;

@WebMvcTest(controllers = ReviewController.class)
public class ReviewControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReviewService reviewService;

  private CreateReviewDTO createReviewDTO;
  private Review review;

  @BeforeEach
  void setup() {
    createReviewDTO = CreateReviewDTO.builder()
        .imdbId("12345")
        .body("Highly recommended!")
        .build();

    review = Review.builder()
        .body("Highly recommended!")
        .build();
  }

  @Test
  public void testCreateReview() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    Mockito.when(reviewService.createReview(createReviewDTO)).thenReturn(review);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createReviewDTO)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.handler().methodName("createReview"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").value(review.getBody()));

    verify(reviewService, atLeastOnce()).createReview(createReviewDTO);
  }

}
