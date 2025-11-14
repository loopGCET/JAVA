# ArrayList

## 📘 Overview

`ArrayList<E>` is a resizable array implementation of the `List` interface. It's the most commonly used List implementation in Java.

## 🔑 Key Characteristics

- **Backed by:** Dynamic resizable array
- **Ordering:** Maintains insertion order
- **Duplicates:** Allows duplicate elements
- **Null values:** Allows null elements
- **Thread-safe:** ❌ No (use Collections.synchronizedList() or CopyOnWriteArrayList)
- **Performance:** Fast random access O(1), slower insertions O(n)

## ⚡ Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `get(index)` | O(1) | Direct array access |
| `add(element)` | O(1) amortized | O(n) when resize needed |
| `add(index, element)` | O(n) | Shifts elements |
| `remove(index)` | O(n) | Shifts elements |
| `contains(element)` | O(n) | Linear search |
| `indexOf(element)` | O(n) | Linear search |
| `size()` | O(1) | Stored as variable |

## 💾 Memory

- **Initial capacity:** 10 (default)
- **Growth factor:** 1.5x (50% increase)
- **Memory overhead:** ~50 bytes + (capacity × element size)

## ✅ When to Use

- ✅ Need fast random access by index
- ✅ Mostly reading/iterating over elements
- ✅ Adding elements at the end
- ✅ Don't know the size in advance

## ❌ When NOT to Use

- ❌ Frequent insertions/deletions in the middle
- ❌ Need thread-safe operations (use CopyOnWriteArrayList)
- ❌ Working with very large datasets with frequent modifications

## 🎯 Common Use Cases

1. **Storing and accessing ordered data**
2. **Building dynamic lists where size changes**
3. **Implementing collections that need random access**
4. **Caching data for quick retrieval**

## 📋 Constructors

```java
// 1. Default constructor (capacity = 10)
ArrayList<String> list1 = new ArrayList<>();

// 2. With initial capacity
ArrayList<String> list2 = new ArrayList<>(100);

// 3. From another collection
ArrayList<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C"));
```

## 🔍 Important Methods

### Adding Elements
- `add(E e)` - Append to end
- `add(int index, E e)` - Insert at position
- `addAll(Collection<? extends E> c)` - Add all elements

### Accessing Elements
- `get(int index)` - Get element at index
- `set(int index, E e)` - Replace element
- `indexOf(Object o)` - First occurrence
- `lastIndexOf(Object o)` - Last occurrence

### Removing Elements
- `remove(int index)` - Remove by index
- `remove(Object o)` - Remove first occurrence
- `removeAll(Collection<?> c)` - Remove matching elements
- `clear()` - Remove all

### Other Operations
- `size()` - Number of elements
- `isEmpty()` - Check if empty
- `contains(Object o)` - Check existence
- `sort(Comparator<? super E> c)` - Sort in place
- `toArray()` - Convert to array

## 📝 Example

See `ArrayListExample.java` for comprehensive examples including:
- Creating and initializing ArrayLists
- Adding, accessing, and removing elements
- Searching and sorting
- Iterating techniques
- Performance considerations
- Common pitfalls and best practices

## 💡 Best Practices

1. **Initialize with capacity** if size is known:
   ```java
   ArrayList<String> list = new ArrayList<>(1000);
   ```

2. **Use interface in declarations**:
   ```java
   List<String> list = new ArrayList<>();  // Good
   ArrayList<String> list = new ArrayList<>();  // Avoid
   ```

3. **Use `ensureCapacity()`** before bulk operations:
   ```java
   list.ensureCapacity(1000);
   ```

4. **Trim capacity** after bulk deletions:
   ```java
   list.trimToSize();
   ```

5. **Use `removeIf()`** for conditional removal:
   ```java
   list.removeIf(s -> s.length() > 5);
   ```

## ⚠️ Common Pitfalls

1. **ConcurrentModificationException** when modifying during iteration
2. **Index out of bounds** when accessing invalid indices
3. **Poor performance** when inserting at the beginning repeatedly
4. **Memory waste** if capacity is much larger than size

## 🔗 Related Classes

- `LinkedList` - Better for frequent insertions/deletions
- `Vector` - Thread-safe but legacy
- `CopyOnWriteArrayList` - Thread-safe for read-heavy scenarios

