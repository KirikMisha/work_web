package com.example.test30.repo;

import com.example.test30.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Long> {
}

