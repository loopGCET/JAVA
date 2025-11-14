# Iterable Interface

## 📘 Overview

`Iterable<T>` is the root interface of the Java Collections Framework hierarchy. Any class implementing this interface can be iterated using the enhanced for-loop (for-each loop).

## 🔑 Key Characteristics

- Root interface in the collection hierarchy
- Located in `java.lang` package
- Enables for-each loop functionality
- Returns an `Iterator` object

## 📋 Interface Definition

```java
public interface Iterable<T> {
    Iterator<T> iterator();
    
    // Default methods (Java 8+)
    default void forEach(Consumer<? super T> action);
    default Spliterator<T> spliterator();
}
```

## 🎯 Key Methods

| Method | Description | Return Type |
|--------|-------------|-------------|
| `iterator()` | Returns an iterator over elements | `Iterator<T>` |
| `forEach(Consumer)` | Performs action for each element | `void` |
| `spliterator()` | Creates a Spliterator over elements | `Spliterator<T>` |

## ✅ When to Use

- When you want to make your custom class iterable
- To enable for-each loop on your collection
- To provide a standard way to traverse elements

## 📝 Example

See `IterableExample.java` for a complete runnable example demonstrating:
- Using Iterable with built-in collections
- Creating custom Iterable classes
- Different iteration methods

## 🔗 Related Interfaces

- `Iterator<E>` - Provides iteration methods (hasNext(), next(), remove())
- `Collection<E>` - Extends Iterable

