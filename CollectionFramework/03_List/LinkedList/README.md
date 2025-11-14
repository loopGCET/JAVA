# LinkedList

## 📘 Overview

`LinkedList<E>` is a doubly-linked list implementation of both `List` and `Deque` interfaces. Each element is stored in a node that contains references to the previous and next nodes.

## 🔑 Key Characteristics

- **Backed by:** Doubly-linked list (each node has prev and next references)
- **Ordering:** Maintains insertion order
- **Duplicates:** Allows duplicate elements
- **Null values:** Allows null elements
- **Thread-safe:** ❌ No
- **Performance:** Slower random access O(n), fast insertions/deletions at ends O(1)
- **Implements:** Both `List<E>` and `Deque<E>`

## ⚡ Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `get(index)` | O(n) | Must traverse from head/tail |
| `add(element)` | O(1) | Add at end |
| `add(index, element)` | O(n) | Must find position first |
| `addFirst(element)` | O(1) | Direct head access |
| `addLast(element)` | O(1) | Direct tail access |
| `remove(index)` | O(n) | Must find position first |
| `removeFirst()` | O(1) | Direct head access |
| `removeLast()` | O(1) | Direct tail access |
| `contains(element)` | O(n) | Linear search |

## ✅ When to Use

- ✅ Frequent insertions/deletions at beginning or end
- ✅ Implementing queue or deque operations
- ✅ Don't need random access
- ✅ Memory overhead of nodes is acceptable

## ❌ When NOT to Use

- ❌ Need fast random access by index
- ❌ Mostly reading/accessing elements
- ❌ Memory is constrained (node overhead ~24 bytes per element)

## 🎯 Common Use Cases

1. **Implementing queues and deques**
2. **Undo/Redo functionality**
3. **Browser history (forward/back)**
4. **Music playlists**
5. **Any scenario with frequent add/remove at ends**

## 📊 ArrayList vs LinkedList

| Feature | ArrayList | LinkedList |
|---------|-----------|------------|
| Random Access | O(1) ✅ | O(n) ❌ |
| Add at end | O(1)* | O(1) ✅ |
| Add at beginning | O(n) | O(1) ✅ |
| Add in middle | O(n) | O(n) |
| Remove from end | O(1) | O(1) ✅ |
| Remove from beginning | O(n) | O(1) ✅ |
| Memory overhead | Low | High (node objects) |
| Cache locality | Good ✅ | Poor ❌ |

*Amortized

## 📝 Example

See `LinkedListExample.java` for comprehensive examples including:
- List operations
- Deque operations (addFirst, addLast, etc.)
- Queue operations
- Performance comparisons
- Use cases

## 💡 Best Practices

1. **Use for queue/deque operations**:
   ```java
   Deque<String> deque = new LinkedList<>();
   ```

2. **Consider ArrayDeque** for stack/queue (usually faster):
   ```java
   Deque<String> deque = new ArrayDeque<>();  // Often better
   ```

3. **Avoid get(index)** in loops - use Iterator instead

