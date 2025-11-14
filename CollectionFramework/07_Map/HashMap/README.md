# HashMap

## 📘 Overview

`HashMap<K,V>` is a hash table based implementation of the `Map` interface. It stores key-value pairs and provides fast lookups.

## 🔑 Key Characteristics

- **Backed by:** Hash table (array of buckets)
- **Ordering:** ❌ No guaranteed order
- **Duplicate keys:** ❌ Not allowed (overwrites existing)
- **Duplicate values:** ✅ Allowed
- **Null key:** ✅ Allows one null key
- **Null values:** ✅ Allows multiple null values
- **Thread-safe:** ❌ No (use ConcurrentHashMap)
- **Performance:** O(1) for get/put (average case)

## ⚡ Time Complexity

| Operation | Average | Worst Case |
|-----------|---------|------------|
| `put(k, v)` | O(1) | O(n) |
| `get(k)` | O(1) | O(n) |
| `remove(k)` | O(1) | O(n) |
| `containsKey(k)` | O(1) | O(n) |
| `containsValue(v)` | O(n) | O(n) |

## ✅ When to Use

- ✅ Need fast key-value lookups
- ✅ Order doesn't matter
- ✅ Single-threaded environment
- ✅ Unique keys required

## 🎯 Common Use Cases

1. **Caching** - Store computed results
2. **Counting frequencies** - Word count, character frequency
3. **Indexing** - Quick lookups by ID
4. **Configuration storage** - Key-value settings
5. **Implementing graphs** - Adjacency lists

## 📋 Important Methods

### Basic Operations
- `put(K key, V value)` - Add/update key-value pair
- `get(Object key)` - Get value for key
- `remove(Object key)` - Remove key-value pair
- `containsKey(Object key)` - Check if key exists
- `containsValue(Object value)` - Check if value exists

### Bulk Operations
- `putAll(Map<? extends K, ? extends V> m)` - Add all mappings
- `clear()` - Remove all mappings

### Java 8+ Methods
- `putIfAbsent(K key, V value)` - Put if key doesn't exist
- `getOrDefault(Object key, V defaultValue)` - Get with fallback
- `compute(K key, BiFunction)` - Compute new value
- `merge(K key, V value, BiFunction)` - Merge values
- `forEach(BiConsumer)` - Iterate over entries

### Views
- `keySet()` - Set view of keys
- `values()` - Collection view of values
- `entrySet()` - Set view of key-value pairs

## 📝 Example

See `HashMapExample.java` for comprehensive examples including:
- Basic CRUD operations
- Iteration techniques
- Java 8+ methods
- Frequency counting
- Custom objects as keys

## 💡 Best Practices

1. **Override hashCode() and equals()** for custom key objects
2. **Use interface in declarations**:
   ```java
   Map<String, Integer> map = new HashMap<>();
   ```
3. **Initialize with capacity** for large maps:
   ```java
   Map<String, Integer> map = new HashMap<>(1000);
   ```
4. **Use computeIfAbsent()** for lazy initialization:
   ```java
   map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
   ```

## ⚠️ Common Pitfalls

1. **Don't modify keys** after adding to HashMap
2. **Null pointer** when key doesn't exist (use getOrDefault)
3. **ConcurrentModificationException** during iteration
4. **Poor hashCode()** implementation causes performance issues

