package com.example.inmemorycache;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_no")
  private Long productNo;
  
  @Column(name = "brand_name")
  private String brandName;
  
  @Column(name = "product_name")
  private String productName;
  
  @Column(name = "product_price", precision = 19, scale = 2)
  private BigDecimal productPrice;
  
  @Column(name = "category_no")
  private Integer categoryNo;

  public ProductDto toDto() {
    return ProductDto.builder()
      .productNo(productNo)
      .brandName(brandName)
      .productName(productName)
      .productPrice(productPrice)
      .categoryNo(categoryNo)
      .build();
  }
  
}