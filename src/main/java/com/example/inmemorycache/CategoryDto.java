package com.example.inmemorycache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

  private Integer categoryNo;

  private String categoryName;

  private Integer parentNo;
  
  private Integer depth;

  public Category toEntity() {
    return Category.builder()
      .categoryNo(categoryNo)
      .categoryName(categoryName)
      .parentNo(parentNo)
      .depth(depth)
      .build();
  }
  
}