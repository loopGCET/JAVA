# Map Interface

## 📘 Overview

`Map<K,V>` is an object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value. **Map is NOT part of the Collection hierarchy.**

## 🔑 Key Characteristics

- **Key-Value pairs** - Stores associations between keys and values
- **No duplicate keys** - Each key appears at most once
- **Duplicate values allowed** - Multiple keys can map to same value
- **Does NOT extend Collection** - Separate hierarchy
- **Ordering:** Implementation dependent

## 📋 Interface Hierarchy

```
Map<K,V>
    ├── HashMap<K,V>
    │   └── LinkedHashMap<K,V>
    ├── TreeMap<K,V>
    └── Hashtable<K,V> (Legacy)

SortedMap<K,V>
    └── NavigableMap<K,V>
            └── TreeMap<K,V>
```

## 🔄 Map Implementations

### HashMap ⭐ (Most Common)
- **Best for:** Fast key-value lookups
- **Backed by:** Hash table
- **Ordering:** ❌ No order
- **Performance:** O(1) average
- **Null:** ✅ One null key, multiple null values
- **Thread-safe:** ❌ No

### LinkedHashMap
- **Best for:** Maintain insertion order
- **Backed by:** HashMap + Linked list
- **Ordering:** ✅ Insertion order (or access order)
- **Performance:** O(1) average
- **Null:** ✅ One null key, multiple null values
- **Use case:** LRU cache

### TreeMap
- **Best for:** Sorted keys
- **Backed by:** Red-Black tree
- **Ordering:** ✅ Sorted by keys
- **Performance:** O(log n)
- **Null:** ❌ No null keys
- **Use case:** Range queries, sorted data

### Hashtable (Legacy)
- **Best for:** Legacy code only
- **Thread-safe:** ✅ Yes (synchronized)
- **Null:** ❌ No null keys or values
- **Alternative:** Use ConcurrentHashMap instead

## 📊 Comparison Table

| Feature | HashMap | LinkedHashMap | TreeMap | Hashtable |
|---------|---------|---------------|---------|-----------|
| Ordering | No | Insertion/Access | Sorted | No |
| Null key | Yes (1) | Yes (1) | No | No |
| Null values | Yes | Yes | Yes | No |
| Performance | O(1) | O(1) | O(log n) | O(1) |
| Thread-safe | No | No | No | Yes |

## 📋 Common Methods

### Basic Operations
```java
put(K key, V value)         // Add/update key-value pair
get(Object key)             // Get value for key
remove(Object key)          // Remove key-value pair
containsKey(Object key)     // Check if key exists
containsValue(Object value) // Check if value exists
size()                      // Number of mappings
isEmpty()                   // Check if empty
clear()                     // Remove all mappings
```

### Java 8+ Methods
```java
getOrDefault(K key, V defaultValue)
putIfAbsent(K key, V value)
compute(K key, BiFunction remappingFunction)
computeIfAbsent(K key, Function mappingFunction)
computeIfPresent(K key, BiFunction remappingFunction)
merge(K key, V value, BiFunction remappingFunction)
forEach(BiConsumer action)
replaceAll(BiFunction function)
```

### Views
```java
keySet()                    // Set of keys
values()                    // Collection of values
entrySet()                  // Set of Map.Entry<K,V>
```

## 🎯 Common Use Cases

1. **Caching** - Store computed results
2. **Counting frequencies** - Word count, character frequency
3. **Indexing** - Quick lookups by ID/key
4. **Configuration** - Store settings
5. **Graphs** - Adjacency lists
6. **Grouping data** - Group objects by property

## ✅ When to Use Map

- Need key-value associations
- Fast lookups by key
- Store unique keys
- Implement caching/memoization

## 💡 Common Patterns

### Frequency Counter
```java
Map<String, Integer> freq = new HashMap<>();
for (String word : words) {
    freq.merge(word, 1, Integer::sum);
}
```

### Grouping
```java
Map<String, List<Person>> byCity = new HashMap<>();
for (Person p : people) {
    byCity.computeIfAbsent(p.getCity(), k -> new ArrayList<>()).add(p);
}
```

### LRU Cache
```java
Map<K, V> cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
};
```

## 📝 Examples

Explore the subdirectories for complete examples:
- `HashMap/` - Most commonly used Map
- `LinkedHashMap/` - Maintains insertion order
- `TreeMap/` - Sorted Map
- `Hashtable/` - Legacy synchronized Map

## ⚠️ Important Notes

1. **Override equals() and hashCode()** for custom key objects
2. **Keys should be immutable** (or at least not change while in map)
3. **containsValue() is O(n)** - slow operation
4. **Use ConcurrentHashMap** for thread-safety, not Hashtable

