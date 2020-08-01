package com.example.inmemorycache;

import static java.util.stream.Collectors.groupingBy;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final int productCacheCapacity = 100;

  /**
   * 카테고리에 대한 상품 목록 Cache
   */
  private Cache<Integer, List<Product>> listCache = new Cache<>(10, Duration.ofMinutes(1).toMillis());

  /**
   * 상품 정보 Cache
   */
  private Cache<Long, Product> productCache = new Cache<>(productCacheCapacity, Duration.ofMinutes(1).toMillis());

  @PostConstruct
  public void init() {
    log.debug("init");

    // 처음 구동 시 카테고리에 대한 상품 목록, 상품 정보는 cache 되지 않으므로 cache miss가 발생할 수 밖에 없음.
    // 따라서 처음 구동 시에 해당 정보를 조회하여 많이 조회되는 데이터를 caching 해두면 처음에 발생하는 cache miss를 줄일 수 있음.
    // 데이터의 수가 많지 않으므로 현재는 아래와 같이 간단하게 구현
    List<Product> products = productRepository.findAll();

    products.stream().collect(groupingBy(Product::getCategoryNo)).forEach((categoryNo, pList) -> listCache.put(categoryNo, pList));
    products.stream().limit(productCacheCapacity).forEach(product -> productCache.put(product.getProductNo(), product));
  }

  @Override
  public List<Product> list(Integer categoryNo) {
    List<Product> products = listCache.get(categoryNo);

    if (products == null) {
      products = productRepository.findByCategoryNo(categoryNo);
      listCache.put(categoryNo, products);
    }

    return products;
  }

  @Override
  public Optional<Product> get(Long productNo) {
    Optional<Product> product = Optional.ofNullable(productCache.get(productNo));

    if (product.isEmpty()) {
      product = productRepository.findById(productNo);
      product.ifPresent(p -> productCache.put(productNo, p));
    }

    return product;
  }

  @Override
  public Product create(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Optional<Product> update(Long productNo, Product product) {
    Optional<Product> updateProduct = productRepository.findById(productNo);
    updateProduct.ifPresent(p -> {
      if (product.getProductName() != null) {
        p.setProductName(product.getProductName());
      }

      if (product.getProductPrice() != null) {
        p.setProductPrice(product.getProductPrice());
      }

      productRepository.save(p);
    });
    return updateProduct;
  }

  @Override
  public void delete(Long productNo) {
    productRepository.findById(productNo).ifPresent(productRepository::delete);
  }

}