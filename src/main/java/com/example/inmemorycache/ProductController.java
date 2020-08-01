package com.example.inmemorycache;

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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/{productNo}")
  public Product get(@PathVariable Long productNo) {
    return productService.get(productNo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
  }

  @PostMapping
  public ProductDto create(@RequestBody ProductDto productDto) {
    return productService.create(productDto.toEntity()).toDto();
  }

  @PatchMapping("/{productNo}")
  public ProductDto update(@PathVariable Long productNo, @RequestBody ProductDto productDto) {
    return productService.update(productNo, productDto.toEntity())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."))
        .toDto();
  }

  @DeleteMapping("/{productNo}")
  public void delete(@PathVariable Long productNo) {
    productService.delete(productNo);
  }
  
}