# LinkedHashMap

## 📘 Overview

`LinkedHashMap<K,V>` is a hash table and doubly-linked list implementation of Map. It maintains a predictable **insertion order** (or optionally access order) while providing HashMap performance.

## 🔑 Key Characteristics

- **Backed by:** HashMap + doubly-linked list
- **Ordering:** ✅ Insertion order (or access order)
- **Duplicate keys:** ❌ Not allowed
- **Duplicate values:** ✅ Allowed
- **Null key:** ✅ One null key allowed
- **Null values:** ✅ Multiple null values allowed
- **Thread-safe:** ❌ No
- **Performance:** O(1) for basic operations (slightly slower than HashMap)

## ⚡ Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `put(k, v)` | O(1) average | Slightly slower than HashMap |
| `get(k)` | O(1) average | Can update access order |
| `remove(k)` | O(1) average | Updates linked list |
| `containsKey(k)` | O(1) average | Same as HashMap |
| **Iteration** | **O(n)** | Predictable order! |

## 💾 Memory

- **Memory overhead:** Higher than HashMap (maintains linked list pointers)
- **Each entry:** Stores before/after references
- **Trade-off:** More memory for predictable iteration order

## ✅ When to Use

- ✅ Need predictable iteration order (insertion or access)
- ✅ Implementing **LRU (Least Recently Used) cache**
- ✅ Need HashMap performance + ordering
- ✅ Debugging (consistent output order)

## ❌ When NOT to Use

- ❌ Order doesn't matter → Use HashMap (faster, less memory)
- ❌ Need sorted keys → Use TreeMap
- ❌ Memory is critical → Use HashMap

## 🎯 Common Use Cases

1. **LRU Cache implementation** ⭐ (most common)
2. **Maintaining insertion order for consistent output**
3. **Building ordered dictionaries**
4. **Session management** (track access order)
5. **Building ordered configuration maps**

## 📋 Constructors

```java
// 1. Default - insertion order
LinkedHashMap<String, Integer> map1 = new LinkedHashMap<>();

// 2. With initial capacity
LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>(100);

// 3. With initial capacity and load factor
LinkedHashMap<String, Integer> map3 = new LinkedHashMap<>(100, 0.75f);

// 4. With access order (for LRU cache)
LinkedHashMap<String, Integer> map4 = new LinkedHashMap<>(16, 0.75f, true);
//                                                                      ↑
//                                                                  true = access order
//                                                                  false = insertion order (default)
```

## 🔍 Important Modes

### Insertion Order (Default)
```java
LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
// Iteration order = insertion order
```

### Access Order (for LRU Cache)
```java
LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
// get() and put() on existing key moves it to end
// Perfect for LRU cache!
```

## 📝 Example

See `LinkedHashMapExample.java` for comprehensive examples including:
- Insertion order preservation
- Access order mode
- LRU Cache implementation
- Comparison with HashMap and TreeMap
- Practical use cases

## 💡 LRU Cache Implementation

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);  // access order = true
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;  // Remove oldest when size exceeds capacity
    }
}
```

## 📊 Comparison Table

| Feature | HashMap | LinkedHashMap | TreeMap |
|---------|---------|---------------|---------|
| Ordering | ❌ No | ✅ Insertion/Access | ✅ Sorted |
| Performance | O(1) | O(1) | O(log n) |
| Memory | Lower | Medium | Medium |
| Null key | Yes (1) | Yes (1) | No |
| Use case | Speed | Ordered | Sorted |

## ⚠️ Important Notes

1. **Slightly slower than HashMap** - Due to linked list maintenance
2. **More memory than HashMap** - Stores additional references
3. **Predictable iteration** - Unlike HashMap
4. **Access order mode** - Perfect for LRU caches
5. **removeEldestEntry()** - Override for automatic cleanup

## 🔗 Related Classes

- `HashMap` - Faster but no order guarantee
- `TreeMap` - Sorted order but slower (O(log n))
- `LinkedHashSet` - Set equivalent with insertion order
- `ConcurrentHashMap` - Thread-safe but no order

## 📈 When to Choose What?

```
Need Map with key-value pairs?
    ├─ Need specific order?
    │   ├─ Sorted by keys → TreeMap
    │   ├─ Insertion/Access order → LinkedHashMap ✅
    │   └─ No order needed → HashMap
    └─ Building LRU cache → LinkedHashMap (access order) ⭐
```

## 🎓 Learning Note

**LinkedHashMap is the perfect choice when you need HashMap's fast O(1) operations but also want predictable iteration order. It's especially powerful for implementing LRU caches using access order mode.**

## 💎 Pro Tip: Custom removeEldestEntry()

```java
LinkedHashMap<K, V> map = new LinkedHashMap<K, V>(16, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIES;  // Auto-remove oldest
    }
};
```

This makes LinkedHashMap perfect for:
- LRU caches
- Fixed-size history tracking
- Automatic cleanup of old entries

