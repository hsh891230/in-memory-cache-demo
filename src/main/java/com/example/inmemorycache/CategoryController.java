package com.example.inmemorycache;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryRepository categoryRepository;

  private final ProductRepository productRepository;

  @GetMapping
  public List<Category> list() {
    return categoryRepository.findAll();
  }

  @GetMapping("/{categoryNo}/products")
  public List<Product> products(@PathVariable Integer categoryNo) {
    return productRepository.findByCategoryNo(categoryNo);
  }
  
}