package com.example.inmemorycache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "category")
@Data
public class Category {

  @Id
  @Column(name = "category_no", nullable = false)
  private Integer categoryNo;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "parent_no")
  private Long parentNo;
  
  @Column(name = "depth")
  private Integer depth;
  
}