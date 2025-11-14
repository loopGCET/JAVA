# HashSet

## 📘 Overview

`HashSet<E>` is a hash table backed implementation of the `Set` interface. It stores unique elements with no specific ordering.

## 🔑 Key Characteristics

- **Backed by:** HashMap internally
- **Ordering:** ❌ No guaranteed order
- **Duplicates:** ❌ Not allowed
- **Null values:** ✅ Allows one null element
- **Thread-safe:** ❌ No
- **Performance:** O(1) for add, remove, contains (average case)

## ⚡ Time Complexity

| Operation | Average | Worst Case |
|-----------|---------|------------|
| `add(e)` | O(1) | O(n) |
| `remove(e)` | O(1) | O(n) |
| `contains(e)` | O(1) | O(n) |
| `size()` | O(1) | O(1) |

## ✅ When to Use

- ✅ Need to store unique elements
- ✅ Fast membership testing
- ✅ Order doesn't matter
- ✅ No duplicate values needed

## 🎯 Common Use Cases

1. **Removing duplicates from a collection**
2. **Fast membership checking**
3. **Implementing mathematical sets (union, intersection)**
4. **Caching unique values**

## 📝 Example

See `HashSetExample.java` for comprehensive examples.

## 💡 Best Practices

1. **Override equals() and hashCode()** for custom objects
2. **Use interface in declarations**:
   ```java
   Set<String> set = new HashSet<>();
   ```
3. **Initialize with capacity** for large sets:
   ```java
   Set<String> set = new HashSet<>(1000);
   ```

