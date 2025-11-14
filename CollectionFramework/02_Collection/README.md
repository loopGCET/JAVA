# Collection Interface

## 📘 Overview

`Collection<E>` is the root interface in the collection hierarchy. It represents a group of objects known as elements. All collection classes (except Map) implement this interface.

## 🔑 Key Characteristics

- Extends `Iterable<E>`
- Located in `java.util` package
- Defines basic collection operations
- Parent interface for List, Set, and Queue

## 📋 Interface Hierarchy

```
Iterable<E>
    │
    └── Collection<E>
            ├── List<E>
            ├── Set<E>
            └── Queue<E>
```

## 🎯 Common Methods

| Method | Description | Return Type |
|--------|-------------|-------------|
| `add(E e)` | Adds element to collection | `boolean` |
| `addAll(Collection<? extends E> c)` | Adds all elements | `boolean` |
| `remove(Object o)` | Removes single element | `boolean` |
| `removeAll(Collection<?> c)` | Removes all matching elements | `boolean` |
| `retainAll(Collection<?> c)` | Keeps only matching elements | `boolean` |
| `contains(Object o)` | Checks if element exists | `boolean` |
| `containsAll(Collection<?> c)` | Checks if all exist | `boolean` |
| `size()` | Returns number of elements | `int` |
| `isEmpty()` | Checks if empty | `boolean` |
| `clear()` | Removes all elements | `void` |
| `toArray()` | Converts to array | `Object[]` |
| `iterator()` | Returns iterator | `Iterator<E>` |

## 🆕 Java 8+ Methods

| Method | Description |
|--------|-------------|
| `stream()` | Returns a sequential stream |
| `parallelStream()` | Returns a parallel stream |
| `removeIf(Predicate)` | Removes elements matching predicate |
| `forEach(Consumer)` | Performs action for each element |

## ✅ When to Use

- When you need a generic collection reference
- When writing methods that work with any collection type
- When you don't need specific List/Set/Queue functionality

## 📝 Example

See `CollectionExample.java` for complete runnable examples demonstrating:
- All common operations
- Working with different collection types
- Stream operations
- Bulk operations

## 🔗 Direct Known Subinterfaces

- `List<E>` - Ordered collection
- `Set<E>` - No duplicates
- `Queue<E>` - FIFO operations
- `Deque<E>` - Double-ended queue

