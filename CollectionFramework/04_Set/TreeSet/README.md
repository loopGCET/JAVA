# TreeSet

## 📘 Overview

`TreeSet<E>` is a NavigableSet implementation based on a TreeMap (Red-Black tree). It stores elements in **sorted order**.

## 🔑 Key Characteristics

- **Backed by:** Red-Black tree (self-balancing BST)
- **Ordering:** ✅ Sorted (natural or custom Comparator)
- **Duplicates:** ❌ Not allowed
- **Null values:** ❌ Not allowed (throws NullPointerException)
- **Thread-safe:** ❌ No
- **Performance:** O(log n) for add, remove, contains

## ⚡ Time Complexity

| Operation | Time Complexity |
|-----------|----------------|
| `add(e)` | O(log n) |
| `remove(e)` | O(log n) |
| `contains(e)` | O(log n) |
| `first()` | O(1) |
| `last()` | O(1) |
| `higher(e)` | O(log n) |
| `lower(e)` | O(log n) |

## ✅ When to Use

- ✅ Need elements in sorted order
- ✅ Need range operations (subSet, headSet, tailSet)
- ✅ Need ceiling, floor, higher, lower operations
- ✅ No duplicates required

## 🎯 Common Use Cases

1. **Maintaining sorted unique elements**
2. **Range queries**
3. **Finding closest elements**
4. **Priority-based scheduling**

## 📝 Example

See `TreeSetExample.java` for comprehensive examples.

