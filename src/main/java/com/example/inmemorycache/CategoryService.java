package com.example.inmemorycache;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

  List<Category> list();

  Category create(Category category);

  Optional<Category> update(Integer categoryNo, Category category);

  void delete(Integer categoryNo);

}