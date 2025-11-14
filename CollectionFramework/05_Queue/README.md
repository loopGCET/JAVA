# Queue Interface

## 📘 Overview

`Queue<E>` is a collection designed for holding elements prior to processing. Queues typically order elements in FIFO (first-in-first-out) manner.

## 🔑 Key Characteristics

- **Ordering:** Usually FIFO (but can be priority-based)
- **Duplicates:** ✅ Allowed
- **Null values:** Implementation dependent
- Extends `Collection<E>`

## 📋 Queue Methods

### Operations that throw exception
- `add(e)` - Insert element
- `remove()` - Remove and return head
- `element()` - Return head without removing

### Operations that return special value
- `offer(e)` - Insert element (returns false if fails)
- `poll()` - Remove and return head (returns null if empty)
- `peek()` - Return head without removing (returns null if empty)

## 🔄 Queue Implementations

### PriorityQueue
- **Best for:** Priority-based processing
- **Backed by:** Heap (binary tree)
- **Ordering:** Natural order or Comparator
- **Time Complexity:** O(log n) add/poll

### LinkedList
- **Best for:** Standard FIFO queue
- **Backed by:** Doubly-linked list
- **Ordering:** Insertion order
- **Time Complexity:** O(1) add/poll

## 📝 Examples

Explore the subdirectories for complete examples:
- `PriorityQueue/` - Priority-based queue

## 💡 Best Practices

1. **Prefer offer/poll/peek** over add/remove/element
2. **Use PriorityQueue** for priority-based processing
3. **Use ArrayDeque** for simple FIFO needs (faster than LinkedList)

