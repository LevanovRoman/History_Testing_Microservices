package com.myapp.quizeservicemicroservice.repository;

import com.myapp.quizeservicemicroservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
