# Hashtable

## 📘 Overview

`Hashtable<K,V>` is a synchronized hash table implementation of the Map interface. It's similar to HashMap but with synchronized methods for thread-safety.

## 🔑 Key Characteristics

- **Backed by:** Hash table (array of buckets)
- **Ordering:** ❌ No guaranteed order
- **Duplicate keys:** ❌ Not allowed
- **Duplicate values:** ✅ Allowed
- **Null key:** ❌ Not allowed (throws NullPointerException)
- **Null values:** ❌ Not allowed (throws NullPointerException)
- **Thread-safe:** ✅ Yes (all methods synchronized)
- **Status:** ⚠️ Legacy class (since Java 1.0)

## ⚡ Time Complexity

| Operation | Average | Worst Case |
|-----------|---------|------------|
| `put(k, v)` | O(1) | O(n) |
| `get(k)` | O(1) | O(n) |
| `remove(k)` | O(1) | O(n) |
| `containsKey(k)` | O(1) | O(n) |
| `containsValue(v)` | O(n) | O(n) |

*Note: Performance degraded by synchronization overhead*

## ✅ When to Use

- ⚠️ **Legacy code maintenance** only
- ⚠️ **Thread-safe Map** needed (but prefer ConcurrentHashMap)

## ❌ When NOT to Use (Prefer Alternatives)

- ❌ **Single-threaded applications** → Use HashMap instead
- ❌ **Modern multi-threaded code** → Use ConcurrentHashMap
- ❌ **New projects** → Hashtable is legacy
- ❌ **Need null keys/values** → Hashtable doesn't allow them

## 🎯 Common Use Cases

1. **Legacy applications** (maintaining old code)
2. **Thread-safe key-value storage** (though better alternatives exist)

## 📋 Constructors

```java
// 1. Default constructor
Hashtable<String, Integer> table1 = new Hashtable<>();

// 2. With initial capacity
Hashtable<String, Integer> table2 = new Hashtable<>(100);

// 3. With initial capacity and load factor
Hashtable<String, Integer> table3 = new Hashtable<>(100, 0.75f);

// 4. From another map
Hashtable<String, Integer> table4 = new Hashtable<>(existingMap);
```

## 📝 Example

See `HashtableExample.java` for comprehensive examples including:
- Basic operations
- Thread-safety demonstration
- Null handling (throws exceptions)
- Comparison with HashMap and ConcurrentHashMap
- Why ConcurrentHashMap is better

## 💡 Modern Alternatives

### Instead of Hashtable, use:

1. **HashMap for single-threaded**:
   ```java
   Map<String, Integer> map = new HashMap<>();
   ```

2. **ConcurrentHashMap for thread-safety** ⭐:
   ```java
   Map<String, Integer> map = new ConcurrentHashMap<>();
   ```

3. **Collections.synchronizedMap() if needed**:
   ```java
   Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
   ```

## ⚠️ Hashtable Problems (Why It's Deprecated)

### 1. No Null Support
```java
Hashtable<String, String> table = new Hashtable<>();
table.put(null, "value");   // ❌ NullPointerException
table.put("key", null);     // ❌ NullPointerException
```

### 2. Synchronization Overhead
- Every method is synchronized
- Slower even in single-threaded code
- Locks entire table for each operation

### 3. Legacy Design
- Doesn't fit modern collection hierarchy well
- Named "Hashtable" (not "HashTable") - naming inconsistency
- Uses Enumeration instead of Iterator (old API)

### 4. Coarse-grained Locking
- Locks entire table for each operation
- ConcurrentHashMap uses segment locking (better)

## 📊 Comparison Table

| Feature | HashMap | Hashtable | ConcurrentHashMap |
|---------|---------|-----------|-------------------|
| Thread-safe | ❌ No | ✅ Yes | ✅ Yes |
| Performance | ✅ Fast | ⚠️ Slow | ✅ Fast |
| Null key | ✅ One | ❌ No | ❌ No |
| Null values | ✅ Yes | ❌ No | ❌ No |
| Legacy | ❌ No | ✅ Yes | ❌ No |
| Locking | None | Entire table | Segments |
| Recommended | ✅ Yes | ❌ No | ✅ Yes (concurrent) |

## 🔗 Related Classes

- `HashMap` - Modern, non-synchronized alternative
- `ConcurrentHashMap` - **Recommended thread-safe alternative**
- `Collections.synchronizedMap()` - Synchronized wrapper
- `Dictionary` - Abstract parent class (also legacy)

## 📈 Migration Guide

### From Hashtable to HashMap:
```java
// OLD CODE (Hashtable)
Hashtable<String, Integer> table = new Hashtable<>();
table.put("key", 1);

// NEW CODE (HashMap)
Map<String, Integer> map = new HashMap<>();
map.put("key", 1);
```

### From Hashtable to ConcurrentHashMap:
```java
// OLD CODE (Hashtable - thread-safe but slow)
Hashtable<String, Integer> table = new Hashtable<>();

// NEW CODE (ConcurrentHashMap - thread-safe and fast)
Map<String, Integer> map = new ConcurrentHashMap<>();
```

## ⚡ Why ConcurrentHashMap is Better

| Aspect | Hashtable | ConcurrentHashMap |
|--------|-----------|-------------------|
| Locking | Entire table | Segments (lock striping) |
| Concurrency | Low (one thread at a time) | High (multiple threads) |
| Throughput | Low | High |
| Reads | Blocked during writes | Non-blocking |
| Performance | ⚠️ Poor | ✅ Excellent |

## 🎓 Learning Note

**Hashtable is included for completeness and legacy code understanding. For all new projects:**
- Use **HashMap** for single-threaded applications
- Use **ConcurrentHashMap** for multi-threaded applications

**Never use Hashtable in new code!**

## 📚 Historical Context

- Introduced in Java 1.0 (1996)
- Predates the Collections Framework (Java 1.2)
- Retrofitted to implement Map interface
- Named "Hashtable" to match existing naming
- Kept for backward compatibility only

## 💎 Key Takeaways

1. ❌ **Don't use in new code**
2. ✅ **Use HashMap** for single-threaded
3. ✅ **Use ConcurrentHashMap** for concurrent access
4. ⚠️ **No null keys or values** allowed
5. ⚠️ **Synchronized overhead** hurts performance
6. ✅ **Understanding it helps** with legacy code maintenance

