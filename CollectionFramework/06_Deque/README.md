# Deque Interface

## 📘 Overview

`Deque<E>` (Double Ended Queue) is a linear collection that supports element insertion and removal at both ends. It can be used as both queue (FIFO) and stack (LIFO).

## 🔑 Key Characteristics

- **Ordering:** Insertion order
- **Duplicates:** ✅ Allowed
- **Null values:** Implementation dependent
- Extends `Queue<E>`
- Can function as **Queue** OR **Stack**

## 📋 Deque Methods

### Insert at head
- `addFirst(e)` / `offerFirst(e)`

### Insert at tail
- `addLast(e)` / `offerLast(e)`

### Remove from head
- `removeFirst()` / `pollFirst()`

### Remove from tail
- `removeLast()` / `pollLast()`

### Peek operations
- `getFirst()` / `peekFirst()`
- `getLast()` / `peekLast()`

### Stack operations
- `push(e)` - Add to front
- `pop()` - Remove from front
- `peek()` - View front

## 🔄 Deque Implementations

### ArrayDeque ⭐ (Recommended)
- **Best for:** General purpose stack/queue/deque
- **Backed by:** Resizable array
- **Performance:** Faster than LinkedList
- **Null:** ❌ Not allowed

### LinkedList
- **Best for:** When null elements needed
- **Backed by:** Doubly-linked list
- **Performance:** Slower than ArrayDeque
- **Null:** ✅ Allowed

## 💡 Best Practices

1. **Use ArrayDeque instead of Stack** (Stack is legacy)
2. **Use ArrayDeque instead of LinkedList** for queue operations (usually faster)
3. **Prefer Deque over Stack and LinkedList** for stack/queue needs

## 📝 Examples

Explore the subdirectories for complete examples:
- `ArrayDeque/` - Recommended implementation

