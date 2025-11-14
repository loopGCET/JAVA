# Stack

## 📘 Overview

`Stack<E>` is a LIFO (Last-In-First-Out) data structure that extends `Vector`. It represents a stack of objects with standard push and pop operations.

## 🔑 Key Characteristics

- **Extends:** Vector (inherits synchronized behavior)
- **Ordering:** LIFO (Last-In-First-Out)
- **Duplicates:** ✅ Allows duplicate elements
- **Null values:** ✅ Allows null elements
- **Thread-safe:** ✅ Yes (synchronized, inherits from Vector)
- **Performance:** Slower than Deque implementations
- **Status:** ⚠️ Legacy class (since Java 1.0)

## ⚡ Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `push(e)` | O(1) amortized | Synchronized overhead |
| `pop()` | O(1) | Synchronized overhead |
| `peek()` | O(1) | View top element |
| `search(e)` | O(n) | Linear search from top |
| `empty()` | O(1) | Check if empty |

## ✅ When to Use

- ⚠️ **Legacy code maintenance** only
- ⚠️ **Need thread-safe stack** (but prefer alternatives)

## ❌ When NOT to Use (Prefer Alternatives)

- ❌ **New projects** → Use ArrayDeque instead
- ❌ **Performance-critical code** → ArrayDeque is faster
- ❌ **Modern Java applications** → Stack is legacy

## 🎯 Common Use Cases (If You Must Use Stack)

1. **Legacy applications** (maintaining old code)
2. **Expression evaluation** (infix to postfix)
3. **Undo/Redo operations**
4. **Function call tracking**
5. **DFS (Depth-First Search)**

## 📋 Stack-Specific Methods

```java
E push(E item)          // Push item onto stack
E pop()                 // Pop top item (throws EmptyStackException if empty)
E peek()                // View top item without removing
boolean empty()         // Check if stack is empty
int search(Object o)    // Search for item (1-based position from top)
```

## 📝 Example

See `StackExample.java` for comprehensive examples including:
- Basic stack operations
- Expression evaluation
- Undo/Redo implementation
- Comparison with ArrayDeque
- Why ArrayDeque is better

## 💡 Modern Alternative: ArrayDeque ⭐

### Why ArrayDeque is Better:

| Feature | Stack | ArrayDeque |
|---------|-------|------------|
| Performance | ⚠️ Slower (synchronized) | ✅ Faster |
| Memory | ⚠️ Higher (Vector overhead) | ✅ Lower |
| Inheritance | ⚠️ Extends Vector (violates design) | ✅ Implements Deque |
| Modern | ❌ Legacy | ✅ Modern |
| Recommended | ❌ No | ✅ Yes |

### Use ArrayDeque Instead:

```java
// ❌ OLD WAY (Stack)
Stack<String> stack = new Stack<>();
stack.push("item");
String top = stack.pop();

// ✅ NEW WAY (ArrayDeque)
Deque<String> stack = new ArrayDeque<>();
stack.push("item");
String top = stack.pop();
```

## ⚠️ Stack Problems (Why It's Deprecated)

1. **Extends Vector** - Inherits all Vector methods (breaks encapsulation)
   ```java
   Stack<Integer> stack = new Stack<>();
   stack.push(1);
   stack.add(0, 999);  // ❌ Can break stack semantics!
   ```

2. **Synchronized overhead** - Slower even in single-threaded code

3. **Legacy design** - Doesn't fit modern collection hierarchy

4. **Vector inheritance** - Unnecessary List operations available

## 🔗 Related Classes

- `ArrayDeque` - **Recommended modern alternative**
- `Vector` - Parent class (also legacy)
- `Deque` - Interface for double-ended queues
- `LinkedList` - Can also work as stack (but slower than ArrayDeque)

## 📊 Stack vs ArrayDeque

| Feature | Stack | ArrayDeque |
|---------|-------|------------|
| Type | Class (extends Vector) | Class (implements Deque) |
| Thread-safe | ✅ Yes | ❌ No |
| Performance | ⚠️ Slower | ✅ Faster |
| Null elements | ✅ Allows | ❌ Not allowed |
| Legacy | ✅ Yes | ❌ No |
| Recommended | ❌ No | ✅ Yes |
| Vector methods | ✅ Inherited | ❌ Not available |

## 🎓 Migration Guide

### If you have Stack code:

```java
// OLD CODE (Stack)
Stack<Integer> stack = new Stack<>();
stack.push(1);
stack.push(2);
int top = stack.peek();
int popped = stack.pop();
boolean isEmpty = stack.empty();

// NEW CODE (ArrayDeque) - Just change declaration!
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);
stack.push(2);
int top = stack.peek();
int popped = stack.pop();
boolean isEmpty = stack.isEmpty();  // Note: isEmpty() not empty()
```

## 🎯 Learning Note

**Stack is included for completeness and legacy code understanding. For all new projects, use ArrayDeque as your stack implementation. It's faster, more memory-efficient, and follows better design principles.**

