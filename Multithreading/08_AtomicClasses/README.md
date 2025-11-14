# Atomic Classes - Lock-Free Operations

## 📘 Overview

Atomic classes provide lock-free thread-safe operations on single variables using Compare-And-Swap (CAS) instructions, offering better performance than synchronization for simple operations.

**Purpose:** Perform thread-safe operations without locking overhead.

**When to Use:**
- ✅ Simple counters
- ✅ High-performance scenarios
- ✅ Lock-free algorithms
- ✅ Reducing contention

---

## 🔑 Key Concepts

### 1. AtomicInteger/AtomicLong
Thread-safe integer/long operations

### 2. AtomicBoolean
Thread-safe boolean flag

### 3. AtomicReference
Thread-safe object reference

### 4. Compare-And-Swap (CAS)
Fundamental operation: compare value and swap if matches

---

## 📝 Common Atomic Classes

| Class | Use Case | Performance |
|-------|----------|-------------|
| AtomicInteger | Integer counter | Excellent |
| AtomicLong | Long counter | Excellent |
| AtomicBoolean | Boolean flag | Excellent |
| AtomicReference | Object reference | Excellent |
| AtomicIntegerArray | Integer array | Good |

---

## 💻 Code Examples

```java
// AtomicInteger
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();          // ++counter
counter.getAndIncrement();          // counter++
counter.addAndGet(5);               // counter += 5
counter.compareAndSet(5, 10);       // CAS operation

// AtomicBoolean
AtomicBoolean flag = new AtomicBoolean(false);
if (flag.compareAndSet(false, true)) {
    // Successfully changed from false to true
}

// AtomicReference
AtomicReference<String> ref = new AtomicReference<>("old");
ref.set("new");
String value = ref.get();
```

---

## ✅ Best Practices

- ✅ Use atomic classes for simple counters
- ✅ Prefer over synchronized for single variables
- ✅ Understand CAS semantics

---

## 🎓 Interview Questions

**Q1: When to use AtomicInteger vs synchronized?**  
**A:** AtomicInteger for simple counters (better performance), synchronized for complex operations.

**Q2: What is Compare-And-Swap?**  
**A:** Atomic operation that compares memory value with expected value and updates if they match.

---

For examples, see: **[AtomicClassesExample.java](AtomicClassesExample.java)**

**Related:** [Previous: Concurrent Collections](../07_ConcurrentCollections/) | [Module README](../README.md)

