package com.example.inmemorycache;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "product")
@Data
public class Product {

  @Id
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
  
}