package com.jonathan.moviebox.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
  private String imdbId;
  private String body;
}
