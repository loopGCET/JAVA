# PriorityQueue

## 📘 Overview

`PriorityQueue<E>` is an unbounded priority queue based on a priority heap. Elements are ordered according to their natural ordering or by a Comparator.

## 🔑 Key Characteristics

- **Backed by:** Binary heap (complete binary tree)
- **Ordering:** Priority order (min-heap by default)
- **Duplicates:** ✅ Allowed
- **Null values:** ❌ Not allowed
- **Thread-safe:** ❌ No
- **Performance:** O(log n) for add/poll, O(1) for peek

## ⚡ Time Complexity

| Operation | Time Complexity |
|-----------|----------------|
| `offer(e)` / `add(e)` | O(log n) |
| `poll()` / `remove()` | O(log n) |
| `peek()` / `element()` | O(1) |
| `remove(Object)` | O(n) |
| `contains(Object)` | O(n) |

## ✅ When to Use

- ✅ Need to process elements by priority
- ✅ Always want access to minimum/maximum element
- ✅ Implementing algorithms like Dijkstra, A*, Huffman coding
- ✅ Task scheduling

## 🎯 Common Use Cases

1. **Task scheduling** (process by priority)
2. **Dijkstra's shortest path algorithm**
3. **Huffman coding** (data compression)
4. **Merge K sorted lists**
5. **Finding K largest/smallest elements**
6. **CPU scheduling**

## 📝 Example

See `PriorityQueueExample.java` for comprehensive examples.

## 💡 Best Practices

1. **Elements must be Comparable** or provide Comparator
2. **Don't rely on iteration order** - only head is guaranteed
3. **Use peek()** to see highest priority element
4. **For max-heap**, use `Collections.reverseOrder()`

