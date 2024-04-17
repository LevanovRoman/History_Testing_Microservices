package com.myapp.historytestmicroservice.repository;

import com.myapp.historytestmicroservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Long countById(Integer id);

    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

    List<Question> findByCategory(String category);
    Question findById(int id);
}
