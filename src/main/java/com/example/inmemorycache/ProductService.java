package com.example.inmemorycache;

import java.util.List;
import java.util.Optional;

public interface ProductService {

  List<Product> list(Integer categoryNo);

  Optional<Product> get(Long productNo);

  Product create(Product product);

  Optional<Product> update(Long productNo, Product product);

  void delete(Long productNo);

}