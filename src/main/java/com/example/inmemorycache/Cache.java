package com.example.inmemorycache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class Cache<K, V> {

  private final LRUCache<K, Entry<K, V>> lruCache;

  private final long maxAge;

  public Cache(int capacity, long maxAge) {
    this.lruCache = new LRUCache<>(capacity);
    this.maxAge = maxAge;
  }

  public synchronized void put(K key, V value) {
    lruCache.put(key,
        Entry.<K, V>builder()
          .key(key)
          .value(value)
          .time(System.currentTimeMillis())
          .build());
  }

  public synchronized V get(K key) {
    Entry<K, V> entry = lruCache.get(key);

    if (entry == null) {
      return null;
    }

    // Expired cache
    if (System.currentTimeMillis() - entry.getTime() > maxAge) {
      lruCache.remove(key);
      return null;
    }

    return lruCache.get(key).getValue();
  }

  @Data
  @AllArgsConstructor
  @Builder
  public static class Entry<K, V> {
    private K key;
    private V value;
    private long time;
  }

}