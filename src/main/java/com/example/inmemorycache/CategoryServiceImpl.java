package com.example.inmemorycache;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private List<Category> cache;

  private long loadTime;

  private final long cacheDuration = Duration.ofMinutes(1).toMillis();

  @PostConstruct
  public void init() {
    reloadCache();
  }

  public void reloadCache() {
    log.debug("Reload cache");
    List<Category> categories = categoryRepository.findAll();
    Map<Integer, Category> categoryMap = categories.stream().collect(toMap(Category::getCategoryNo, Function.identity()));

    this.cache = categories.stream().map(category -> {
      if (category.getParentNo() != null) {
        Category parentCategory = categoryMap.get(category.getParentNo());
        category.setCategoryName(parentCategory.getCategoryName() + "-" + category.getCategoryName());
      }
      return category;
    }).collect(toList());

    this.loadTime = Instant.now().toEpochMilli();
  }

  @Override
  public List<Category> list() {
    if (System.currentTimeMillis() - loadTime > cacheDuration) {
      reloadCache();
    }
    return cache;
  }

  @Override
  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Optional<Category> update(Integer categoryNo, Category category) {
    Optional<Category> updateCategory = categoryRepository.findById(categoryNo);
    updateCategory.ifPresent(c -> {
      c.setCategoryName(category.getCategoryName());
      categoryRepository.save(c);
    });
    return updateCategory;
  }

  @Override
  public void delete(Integer categoryNo) {
    categoryRepository.findById(categoryNo).ifPresent(categoryRepository::delete);
  }
  
}