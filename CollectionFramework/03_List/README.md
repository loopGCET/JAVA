# List Interface

## 📘 Overview

`List<E>` is an ordered collection (also known as a sequence) that allows duplicate elements. Elements can be accessed by their integer index (position in the list).

## 🔑 Key Characteristics

- ✅ **Ordered** - Maintains insertion order
- ✅ **Indexed** - Elements can be accessed by position
- ✅ **Duplicates allowed** - Can contain duplicate elements
- ✅ **Null elements** - Allows multiple null values
- Extends `Collection<E>`

## 📋 Interface Hierarchy

```
Collection<E>
    │
    └── List<E>
            ├── ArrayList<E>
            ├── LinkedList<E>
            └── Vector<E>
                    └── Stack<E>
```

## 🎯 List-Specific Methods

| Method | Description | Return Type |
|--------|-------------|-------------|
| `get(int index)` | Returns element at specified position | `E` |
| `set(int index, E element)` | Replaces element at position | `E` |
| `add(int index, E element)` | Inserts element at position | `void` |
| `remove(int index)` | Removes element at position | `E` |
| `indexOf(Object o)` | Returns first occurrence index | `int` |
| `lastIndexOf(Object o)` | Returns last occurrence index | `int` |
| `subList(int from, int to)` | Returns view of portion | `List<E>` |
| `listIterator()` | Returns bidirectional iterator | `ListIterator<E>` |
| `sort(Comparator<? super E> c)` | Sorts the list | `void` |

## 🔄 List Implementations

### ArrayList
- **Best for:** Random access, iteration
- **Backed by:** Resizable array
- **Time Complexity:** O(1) get, O(n) add/remove

### LinkedList
- **Best for:** Frequent insertions/deletions
- **Backed by:** Doubly-linked list
- **Time Complexity:** O(n) get, O(1) add/remove at ends

### Vector (Legacy)
- **Best for:** Thread-safe scenarios (though CopyOnWriteArrayList is better)
- **Backed by:** Synchronized resizable array
- **Note:** Consider using ArrayList with synchronization instead

### Stack (Legacy)
- **Best for:** LIFO operations (though ArrayDeque is better)
- **Backed by:** Extends Vector
- **Note:** Consider using ArrayDeque instead

## ✅ When to Use List

- Need to maintain insertion order
- Need to access elements by index
- Allow duplicate elements
- Need positional access and insertion

## 🎯 Common Use Cases

1. **Maintaining a sequence of items**
2. **Building a collection that allows duplicates**
3. **Index-based access to elements**
4. **Implementing history/undo functionality**
5. **Managing ordered data**

## 📊 Performance Comparison

| Operation | ArrayList | LinkedList | Vector |
|-----------|-----------|------------|--------|
| get(i) | O(1) | O(n) | O(1) |
| add(e) | O(1)* | O(1) | O(1)* |
| add(i, e) | O(n) | O(n) | O(n) |
| remove(i) | O(n) | O(n) | O(n) |
| Iterator.remove() | O(n) | O(1) | O(n) |
| ListIterator.add() | O(n) | O(1) | O(n) |

*Amortized time complexity

## 📝 Examples

Explore the subdirectories for complete examples:
- `ArrayList/` - Most commonly used List implementation
- `LinkedList/` - Good for frequent insertions/deletions
- `Vector/` - Legacy synchronized List
- `Stack/` - Legacy LIFO structure

## 💡 Best Practices

1. **Prefer ArrayList** for most use cases
2. **Use LinkedList** when you need frequent insertions/deletions at the beginning/middle
3. **Avoid Vector and Stack** - they're legacy classes with better alternatives
4. **Initialize with capacity** if you know the size: `new ArrayList<>(initialCapacity)`
5. **Use List interface** in declarations: `List<String> list = new ArrayList<>()`

