package com.jonathan.moviebox.review.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jonathan.moviebox.review.model.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId>{
  
}