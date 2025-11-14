# LinkedHashSet

## 📘 Overview

`LinkedHashSet<E>` is a hash table and linked list implementation of the Set interface. It maintains insertion order while ensuring uniqueness like HashSet.

## 🔑 Key Characteristics

- **Backed by:** LinkedHashMap (hash table + doubly-linked list)
- **Ordering:** ✅ Maintains insertion order
- **Duplicates:** ❌ Not allowed
- **Null values:** ✅ Allows one null element
- **Thread-safe:** ❌ No
- **Performance:** Slightly slower than HashSet but maintains order

## ⚡ Time Complexity

| Operation | Average | Worst Case |
|-----------|---------|------------|
| `add(e)` | O(1) | O(n) |
| `remove(e)` | O(1) | O(n) |
| `contains(e)` | O(1) | O(n) |
| `size()` | O(1) | O(1) |
| **Iteration** | **O(n)** | **O(n)** - predictable! |

## 💾 Memory

- **Memory overhead:** Higher than HashSet (maintains linked list)
- **Each entry:** Stores references to previous and next entries
- **Trade-off:** More memory for predictable iteration order

## ✅ When to Use

- ✅ Need unique elements **with insertion order**
- ✅ Want predictable iteration order
- ✅ Remove duplicates while preserving order
- ✅ Need better iteration performance than HashSet

## ❌ When NOT to Use

- ❌ Order doesn't matter → Use HashSet (faster, less memory)
- ❌ Need sorted order → Use TreeSet
- ❌ Memory is critical → Use HashSet

## 🎯 Common Use Cases

1. **Removing duplicates while preserving order**
2. **Cache with predictable iteration (LRU-like)**
3. **Maintaining unique ordered sequences**
4. **Building ordered sets from user input**
5. **Implementing algorithms that need ordered unique elements**

## 📋 Constructors

```java
// 1. Default constructor
LinkedHashSet<String> set1 = new LinkedHashSet<>();

// 2. With initial capacity
LinkedHashSet<String> set2 = new LinkedHashSet<>(100);

// 3. With initial capacity and load factor
LinkedHashSet<String> set3 = new LinkedHashSet<>(100, 0.75f);

// 4. From another collection (removes duplicates, keeps order)
LinkedHashSet<String> set4 = new LinkedHashSet<>(Arrays.asList("A", "B", "C", "A"));
```

## 📊 Comparison Table

| Feature | HashSet | LinkedHashSet | TreeSet |
|---------|---------|---------------|---------|
| Ordering | ❌ No order | ✅ Insertion order | ✅ Sorted order |
| Performance | O(1) | O(1) | O(log n) |
| Memory | Lower | Medium | Medium |
| Iteration | Unpredictable | Predictable | Sorted |
| Null allowed | Yes (1) | Yes (1) | No |

## 🔍 Important Methods

All methods from Set interface:
- `add(E e)` - Add element (maintains order)
- `remove(Object o)` - Remove element
- `contains(Object o)` - Check existence
- `size()` - Get size
- `clear()` - Remove all
- `isEmpty()` - Check if empty
- `iterator()` - Iterator in insertion order

## 📝 Example

See `LinkedHashSetExample.java` for comprehensive examples including:
- Basic operations with order preservation
- Removing duplicates from list while keeping order
- Iteration order demonstration
- Comparison with HashSet and TreeSet
- Use cases and best practices

## 💡 Best Practices

1. **Use when order matters**:
   ```java
   Set<String> orderedSet = new LinkedHashSet<>();
   ```

2. **Remove duplicates while preserving order**:
   ```java
   List<String> list = Arrays.asList("A", "B", "A", "C", "B");
   Set<String> unique = new LinkedHashSet<>(list);  // [A, B, C]
   ```

3. **Initialize with capacity for large sets**:
   ```java
   Set<String> set = new LinkedHashSet<>(1000);
   ```

4. **Override equals() and hashCode()** for custom objects

## ⚠️ Important Notes

1. **Slightly slower than HashSet** - Due to linked list maintenance
2. **More memory than HashSet** - Stores additional references
3. **Predictable iteration** - Unlike HashSet
4. **Insertion order preserved** - Order of first add() call
5. **Not sorted** - Use TreeSet for sorting

## 🔗 Related Classes

- `HashSet` - Faster but no order guarantee
- `TreeSet` - Sorted order but slower (O(log n))
- `LinkedHashMap` - Map equivalent with insertion order
- `ArrayList` - Allows duplicates but maintains order

## 📈 When to Choose What?

```
Need unique elements?
    ├─ Order matters?
    │   ├─ Yes - Insertion order?
    │   │   ├─ Yes → LinkedHashSet ✅
    │   │   └─ No (Sorted) → TreeSet
    │   └─ No → HashSet
    └─ Duplicates allowed → ArrayList/LinkedList
```

## 🎓 Learning Note

**LinkedHashSet is the middle ground between HashSet (fast, no order) and TreeSet (sorted, slower). Choose it when you need both fast operations AND predictable iteration order.**

