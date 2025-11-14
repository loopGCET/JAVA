# Stream Basics

## 📘 Overview

Streams provide a functional approach to processing collections. They don't store data but operate on the source data structure.

**Purpose:** Enable declarative data processing with functional-style operations.

**When to Use:**
- ✅ Processing collections functionally
- ✅ Filtering and transforming data
- ✅ Avoiding explicit loops
- ✅ Pipeline data processing

---

## 🔑 Key Concepts

### 1. Stream Creation
Multiple ways to create streams from various sources

### 2. Stream Pipeline
Source → Intermediate Operations → Terminal Operation

### 3. Lazy Evaluation
Intermediate operations are lazy, terminal operations trigger execution

### 4. One-Time Use
Streams cannot be reused after terminal operation

---

## 📝 Syntax

```java
// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// From Array
Stream<String> stream = Arrays.stream(new String[]{"a", "b", "c"});

// Using Stream.of()
Stream<String> stream = Stream.of("a", "b", "c");

// Infinite streams
Stream<Integer> infinite = Stream.iterate(0, n -> n + 1);
Stream<Double> random = Stream.generate(Math::random);
```

---

## 💻 Code Examples

```java
// Basic stream pipeline
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)    // Intermediate
    .map(n -> n * 2)             // Intermediate
    .collect(Collectors.toList()); // Terminal
```

---

## ✅ Best Practices

- ✅ Use streams for declarative processing
- ✅ Prefer method references
- ✅ Keep pipelines readable
- ❌ Don't modify source during operation
- ❌ Don't reuse streams

---

## 🎓 Interview Questions

**Q: What is a Stream?**  
A: A sequence of elements supporting sequential and parallel aggregate operations.

**Q: Stream vs Collection?**  
A: Collections store data, Streams process data. Streams are lazy and one-time use.

---

For examples, see: **[StreamBasicsExample.java](StreamBasicsExample.java)**

**Related:** [Module README](../README.md) | [Next: Intermediate Operations](../02_IntermediateOperations/)

