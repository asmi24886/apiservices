package com.twodarray.backend.apiservices.repository;

import com.twodarray.backend.apiservices.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String>
{
}
