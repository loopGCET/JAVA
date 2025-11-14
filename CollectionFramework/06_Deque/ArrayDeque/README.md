# ArrayDeque

## 📘 Overview

`ArrayDeque<E>` is a resizable array implementation of the Deque interface. It's the **recommended** choice for stack and queue operations in modern Java.

## 🔑 Key Characteristics

- **Backed by:** Resizable circular array
- **Ordering:** Insertion order
- **Duplicates:** ✅ Allowed
- **Null values:** ❌ Not allowed
- **Thread-safe:** ❌ No
- **Performance:** Faster than LinkedList and Stack

## ⚡ Time Complexity

| Operation | Time Complexity |
|-----------|----------------|
| `addFirst(e)` / `addLast(e)` | O(1) amortized |
| `removeFirst()` / `removeLast()` | O(1) |
| `getFirst()` / `getLast()` | O(1) |
| `push(e)` / `pop()` | O(1) |
| `offer(e)` / `poll()` | O(1) |

## ✅ When to Use

- ✅ **Instead of Stack** - ArrayDeque is faster and not legacy
- ✅ **Instead of LinkedList** for queue - Usually faster
- ✅ Need double-ended queue operations
- ✅ Implementing undo/redo functionality
- ✅ BFS/DFS algorithms

## ⭐ Why ArrayDeque Over Stack/LinkedList?

| Feature | ArrayDeque | Stack | LinkedList |
|---------|-----------|-------|------------|
| Performance | ✅ Fast | ❌ Synchronized overhead | ⚠️ Node allocation |
| Memory | ✅ Efficient | ❌ Vector overhead | ❌ Node overhead |
| Status | ✅ Modern | ❌ Legacy | ⚠️ OK |
| Null support | ❌ No | ✅ Yes | ✅ Yes |

## 🎯 Common Use Cases

1. **Stack operations** (push, pop, peek)
2. **Queue operations** (offer, poll, peek)
3. **Undo/Redo functionality**
4. **Browser history** (back/forward)
5. **BFS/DFS** algorithms
6. **Expression evaluation**

## 📝 Example

See `ArrayDequeExample.java` for comprehensive examples.

## 💡 Best Practices

1. **Use as Stack**:
   ```java
   Deque<Integer> stack = new ArrayDeque<>();
   stack.push(1);
   stack.pop();
   ```

2. **Use as Queue**:
   ```java
   Deque<Integer> queue = new ArrayDeque<>();
   queue.offer(1);
   queue.poll();
   ```

3. **Initialize with capacity** if size known:
   ```java
   Deque<Integer> deque = new ArrayDeque<>(1000);
   ```

