package com.myapp.quizeservicemicroservice.service;

import com.myapp.quizeservicemicroservice.model.Category;
import com.myapp.quizeservicemicroservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}