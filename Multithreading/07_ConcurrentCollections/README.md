# Concurrent Collections - Thread-Safe Data Structures

## 📘 Overview

Java provides thread-safe collection implementations in the `java.util.concurrent` package, designed for concurrent access without external synchronization.

**Purpose:** Provide high-performance thread-safe collections.

**When to Use:**
- ✅ Multiple threads access shared collections
- ✅ Need better performance than Collections.synchronizedXXX
- ✅ Producer-Consumer patterns
- ✅ Concurrent data processing

---

## 🔑 Key Concepts

### 1. ConcurrentHashMap
Thread-safe HashMap with segment-based locking

### 2. CopyOnWriteArrayList
Thread-safe list optimized for read-heavy scenarios

### 3. BlockingQueue
Queue supporting operations that wait for queue to be non-empty or have space

---

## 📝 Common Collections

| Collection | Use Case | Performance |
|------------|----------|-------------|
| ConcurrentHashMap | General map | Excellent |
| CopyOnWriteArrayList | Read-heavy lists | Good for reads |
| ArrayBlockingQueue | Bounded queue | Good |
| LinkedBlockingQueue | Unbounded queue | Good |
| ConcurrentLinkedQueue | Non-blocking queue | Excellent |

---

## 💻 Code Examples

```java
// ConcurrentHashMap
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1);
map.putIfAbsent("key", 2); // Won't overwrite
map.computeIfAbsent("key2", k -> expensiveComputation());

// BlockingQueue for Producer-Consumer
BlockingQueue<Task> queue = new ArrayBlockingQueue<>(100);
queue.put(task); // Blocks if full
Task t = queue.take(); // Blocks if empty
```

---

## ✅ Best Practices

- ✅ Use ConcurrentHashMap instead of synchronized HashMap
- ✅ Use BlockingQueue for Producer-Consumer
- ✅ Choose collection based on access pattern

---

## 🎓 Interview Questions

**Q1: ConcurrentHashMap vs Hashtable?**  
**A:** ConcurrentHashMap uses fine-grained locking (better concurrency), Hashtable locks entire map.

**Q2: When to use CopyOnWriteArrayList?**  
**A:** When reads greatly outnumber writes - writes are expensive (full copy).

---

For examples, see: **[ConcurrentCollectionsExample.java](ConcurrentCollectionsExample.java)**

**Related:** [Previous: Locks](../06_Locks/) | [Next: Atomic Classes](../08_AtomicClasses/)

