package com.example.inmemorycache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CacheTest {

  @Test
  public void testLRUCache() {
    LRUCache<Integer, String> cache = new LRUCache<>(3);

    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");
    
    cache.get(1);  // 가장 오래된 A 사용 -> B를 tail로 변경
    
    cache.put(4, "D");  // capacity를 넘도록 push
    
    assertEquals(3, cache.size());  // capacity 만큼의 size
    assertFalse(cache.containsKey(2));  // B 삭제
  }

  @Test
  public void testCache() throws InterruptedException {
    Cache<Integer, String> cache = new Cache<>(3, 5);

    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");

    cache.get(1);

    cache.put(4, "D");

    assertNotNull(cache.get(4));
    assertNull(cache.get(2));

    Thread.sleep(5);
    
    assertNull(cache.get(4));


  }
  
}