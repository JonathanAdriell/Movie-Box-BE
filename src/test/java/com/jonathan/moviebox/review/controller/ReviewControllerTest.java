package com.jonathan.moviebox.review.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
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

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ReviewService reviewService;

  private CreateReviewDTO createReviewDTO;
  private Review review;
  private String updatedBody;

  @BeforeEach
  void setup() {
    createReviewDTO = CreateReviewDTO.builder()
        .imdbId("12345")
        .body("Highly recommended!")
        .build();

    review = Review.builder()
        .id(new ObjectId())
        .body("Highly recommended!")
        .build();

    updatedBody = "Easy 10/10";
  }

  @Test
  public void testCreateReview() throws Exception {
    Mockito.when(reviewService.createReview(createReviewDTO)).thenReturn(review);
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createReviewDTO)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.handler().methodName("createReview"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").value(review.getBody()));

    verify(reviewService, atLeastOnce()).createReview(createReviewDTO);
  }

  @Test
  public void testUpdateReview() throws Exception {
    Review updatedReview = review;
    updatedReview.setBody(updatedBody);

    Mockito.when(reviewService.updateReview(review.getId(), updatedBody)).thenReturn(updatedReview);
    mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/review/update/" + review.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(updatedBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.handler().methodName("updateReview"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").value(updatedBody));

    verify(reviewService, atLeastOnce()).updateReview(review.getId(), updatedBody);
  }

  @Test
  public void testDeleteReview() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/review/delete/" + review.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.handler().methodName("deleteReview"));

    verify(reviewService, atLeastOnce()).deleteReview(review.getId());
  }

}
