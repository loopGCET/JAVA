# TreeMap

## 📘 Overview

`TreeMap<K,V>` is a Red-Black tree based implementation of the NavigableMap interface. It stores key-value pairs in **sorted order** by keys.

## 🔑 Key Characteristics

- **Backed by:** Red-Black tree (self-balancing BST)
- **Ordering:** ✅ Sorted by keys (natural or Comparator)
- **Duplicate keys:** ❌ Not allowed
- **Duplicate values:** ✅ Allowed
- **Null key:** ❌ Not allowed (throws NullPointerException)
- **Null values:** ✅ Allowed
- **Thread-safe:** ❌ No
- **Performance:** O(log n) for basic operations

## ⚡ Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `put(k, v)` | O(log n) | Tree insertion |
| `get(k)` | O(log n) | Tree search |
| `remove(k)` | O(log n) | Tree deletion |
| `containsKey(k)` | O(log n) | Tree search |
| `firstKey()` / `lastKey()` | O(log n) | Tree traversal |
| `ceilingKey(k)` / `floorKey(k)` | O(log n) | Tree navigation |

## ✅ When to Use

- ✅ Need keys in sorted order
- ✅ Need range operations (subMap, headMap, tailMap)
- ✅ Need ceiling, floor, higher, lower operations
- ✅ Frequently need min/max keys
- ✅ Need ordered iteration over keys

## ❌ When NOT to Use

- ❌ Don't need sorted order → Use HashMap (faster)
- ❌ Need fast lookups only → Use HashMap (O(1) vs O(log n))
- ❌ Keys can be null → TreeMap doesn't allow null keys

## 🎯 Common Use Cases

1. **Sorted dictionary/index**
2. **Range queries** (find all keys in range)
3. **Leaderboards** (sorted by score)
4. **Time-series data** (sorted by timestamp)
5. **Phone books** (sorted by name)
6. **Event scheduling** (sorted by time)

## 📋 Constructors

```java
// 1. Default constructor (natural ordering)
TreeMap<String, Integer> map1 = new TreeMap<>();

// 2. With custom comparator
TreeMap<String, Integer> map2 = new TreeMap<>(Comparator.reverseOrder());

// 3. From another map
TreeMap<String, Integer> map3 = new TreeMap<>(existingMap);

// 4. From SortedMap
TreeMap<String, Integer> map4 = new TreeMap<>(sortedMap);
```

## 🔍 NavigableMap Methods (TreeMap-specific)

### Ceiling/Floor Operations
```java
ceilingKey(K key)     // Least key >= given key
floorKey(K key)       // Greatest key <= given key
higherKey(K key)      // Least key > given key
lowerKey(K key)       // Greatest key < given key
```

### First/Last Operations
```java
firstKey()            // Lowest key
lastKey()             // Highest key
firstEntry()          // Lowest key-value pair
lastEntry()           // Highest key-value pair
pollFirstEntry()      // Remove and return first
pollLastEntry()       // Remove and return last
```

### Range Operations
```java
subMap(from, to)      // Keys in range
headMap(to)           // Keys less than 'to'
tailMap(from)         // Keys greater than or equal to 'from'
descendingMap()       // Reverse order view
```

## 📝 Example

See `TreeMapExample.java` for comprehensive examples including:
- Sorted key-value storage
- NavigableMap operations
- Range queries
- Leaderboard implementation
- Time-series data handling
- Comparison with HashMap

## 💡 Best Practices

1. **Keys must be Comparable or provide Comparator**:
   ```java
   // Natural ordering
   TreeMap<String, Integer> map = new TreeMap<>();
   
   // Custom ordering
   TreeMap<String, Integer> map = new TreeMap<>(
       Comparator.comparing(String::length)
   );
   ```

2. **Don't use null keys**:
   ```java
   map.put(null, "value");  // ❌ NullPointerException
   ```

3. **Use for range operations**:
   ```java
   SortedMap<Integer, String> range = map.subMap(10, 20);
   ```

4. **Initialize with comparator for custom sorting**:
   ```java
   TreeMap<Person, String> map = new TreeMap<>(
       Comparator.comparing(Person::getAge)
   );
   ```

## 📊 TreeMap vs HashMap

| Feature | HashMap | TreeMap |
|---------|---------|---------|
| Ordering | ❌ No order | ✅ Sorted by keys |
| Performance | O(1) average | O(log n) |
| Null key | ✅ One allowed | ❌ Not allowed |
| Null values | ✅ Allowed | ✅ Allowed |
| Memory | Lower | Higher (tree nodes) |
| Use when | Speed matters | Order matters |

## ⚠️ Important Notes

1. **Keys must be mutually comparable** - All keys must implement Comparable OR use Comparator
2. **No null keys** - Throws NullPointerException
3. **Null values allowed** - Can store null values
4. **Thread-unsafe** - Synchronize externally if needed
5. **Sorted iteration** - Iteration is always in sorted order

## 🔗 Related Classes

- `HashMap` - Faster but no order
- `LinkedHashMap` - Insertion order, not sorted
- `TreeSet` - Set equivalent (uses TreeMap internally)
- `NavigableMap` - Interface with navigation methods
- `SortedMap` - Parent interface

## 📈 When to Choose What?

```
Need Map with key-value pairs?
    ├─ Need sorted keys?
    │   ├─ Yes → TreeMap ✅
    │   └─ No → Need insertion order?
    │       ├─ Yes → LinkedHashMap
    │       └─ No → HashMap
    └─ Just fast operations → HashMap
```

## 🎓 Learning Note

**TreeMap is perfect when you need both Map functionality AND sorted key access. The O(log n) operations are acceptable trade-offs for sorted order and powerful navigation methods.**

