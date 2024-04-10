package com.jonathan.moviebox.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReviewDTO {

  private String imdbId;
  private String body;

}
