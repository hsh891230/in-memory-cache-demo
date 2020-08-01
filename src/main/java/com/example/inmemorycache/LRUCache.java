package com.example.inmemorycache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap을 통하여 LRU Cache를 구현
 * 
 * 메모리의 크기가 정해져 있기 때문에 모든 데이터를 caching 할 수 없기 때문에
 * 정해진 capacity를 초과하는 경우 Eviction을 발생 시킴.
 * 
 * 이커머스 플랫폼의 특성 상 인기가 많은 상품(베스트 상품, 광고 유입 등)이 다시 조회될 확률이 높으므로
 * 최근에 사용이 가장 적은 데이터를 eviction 시키는 LRU Cache를 적용함.
 * 
 * @param <K> Key
 * @param <V> Value
 */
@SuppressWarnings("serial")
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

  /**
   * cache의 최대 수용 size
   */
  private final int capacity;

  public LRUCache(int capacity) {
    super(capacity, 0.75F, true);  // LinkedHashMap의 accessOrder를 true로 함으로써 순서를 보장
    this.capacity = capacity;
  }

  @Override
  public synchronized V put(K key, V value) {
    return super.put(key, value);
  }

  /**
   * 해당 메서드에서 size가 최대 수용 size를 넘는 경우 가장 오래된 entry를 제거함
   */
  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() > capacity;
  }

}