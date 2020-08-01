package com.example.inmemorycache;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  private final ProductService productService;

  @GetMapping
  public List<CategoryDto> list() {
    return categoryService.list().stream().map(Category::toDto).collect(toList());
  }

  @PostMapping
  public CategoryDto create(@RequestBody CategoryDto categoryDto) {
    return categoryService.create(categoryDto.toEntity()).toDto();
  }

  @PatchMapping("/{categoryNo}")
  public CategoryDto update(@PathVariable Integer categoryNo, @RequestBody CategoryDto categoryDto) {
    return categoryService.update(categoryNo, categoryDto.toEntity())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found."))
        .toDto();
  }

  @DeleteMapping("/{categoryNo}")
  public void delete(@PathVariable Integer categoryNo) {
    categoryService.delete(categoryNo);
  }

  @GetMapping("/{categoryNo}/products")
  public List<Product> products(@PathVariable Integer categoryNo) {
    return productService.list(categoryNo);
  }

}