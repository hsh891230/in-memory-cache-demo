package com.example.inmemorycache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_no")
  private Integer categoryNo;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "parent_no")
  private Integer parentNo;
  
  @Column(name = "depth")
  private Integer depth;

  public CategoryDto toDto() {
    return CategoryDto.builder()
      .categoryNo(categoryNo)
      .categoryName(categoryName)
      .parentNo(parentNo)
      .depth(depth)
      .build();
  }
  
}