package com.myapp.quizeservicemicroservice.repository;

import com.myapp.quizeservicemicroservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
