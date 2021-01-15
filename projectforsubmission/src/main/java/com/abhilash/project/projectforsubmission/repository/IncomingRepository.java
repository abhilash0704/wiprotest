package com.abhilash.project.projectforsubmission.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abhilash.project.projectforsubmission.model.OutputResponse;

public interface IncomingRepository extends MongoRepository<OutputResponse, Long> {

}
