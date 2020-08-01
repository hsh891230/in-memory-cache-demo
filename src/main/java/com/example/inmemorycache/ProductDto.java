package com.example.inmemorycache;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private Long productNo;
  
  private String brandName;
  
  private String productName;
  
  private BigDecimal productPrice;
  
  private Integer categoryNo;

  public Product toEntity() {
    return Product.builder()
      .productNo(productNo)
      .brandName(brandName)
      .productName(productName)
      .productPrice(productPrice)
      .categoryNo(categoryNo)
      .build();
  }
  
}