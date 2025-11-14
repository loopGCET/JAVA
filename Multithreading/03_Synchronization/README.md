# Synchronization

## 📘 Overview

Synchronization is a mechanism that ensures only one thread can access a shared resource at a time. It prevents race conditions and maintains data consistency in multithreaded applications.

**Purpose:** Provide thread safety by coordinating access to shared mutable resources.

**When to Use:**
- ✅ Multiple threads access shared mutable data
- ✅ Need to prevent race conditions
- ✅ Ensuring atomic operations
- ✅ Maintaining data consistency

---

## 🔑 Key Concepts

### 1. Race Condition
Occurs when multiple threads access shared data simultaneously, leading to unpredictable results.

### 2. Critical Section
Code block that accesses shared resources and must be executed by only one thread at a time.

### 3. Monitor Lock
Every object in Java has an intrinsic lock. The `synchronized` keyword uses this lock.

### 4. Thread Safety
Property where code works correctly when accessed by multiple threads simultaneously.

---

## 📝 Syntax

### Synchronized Method
```java
public synchronized void method() {
    // Only one thread can execute this at a time
    // Lock is on 'this' object
}
```

### Synchronized Block
```java
public void method() {
    synchronized(this) {
        // Critical section
        // More fine-grained control
    }
}
```

### Static Synchronized Method
```java
public static synchronized void method() {
    // Lock is on Class object
    // Only one thread per class (not per instance)
}
```

### Synchronized on Specific Object
```java
private final Object lock = new Object();

public void method() {
    synchronized(lock) {
        // Critical section using dedicated lock
    }
}
```

---

## 🔍 Important Concepts

| Concept | Description | Example |
|---------|-------------|---------|
| **Mutual Exclusion** | Only one thread in critical section | `synchronized` |
| **Visibility** | Changes visible to other threads | `volatile`, `synchronized` |
| **Atomicity** | Operation completes without interruption | `synchronized block` |
| **Reentrancy** | Thread can reacquire lock it owns | Supported in Java |
| **Monitor** | Object's intrinsic lock | Every Java object |

---

## 💻 Code Examples

### Example 1: Race Condition
```java
class Counter {
    private int count = 0;
    
    // ❌ Not thread-safe
    public void increment() {
        count++; // Read-Modify-Write - not atomic!
    }
    
    // ✅ Thread-safe
    public synchronized void incrementSafe() {
        count++;
    }
}
```

### Example 2: Synchronized Block
```java
class BankAccount {
    private double balance = 1000;
    private final Object lock = new Object();
    
    public void withdraw(double amount) {
        synchronized(lock) {
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }
}
```

### Example 3: Class-Level Synchronization
```java
class IDGenerator {
    private static int nextID = 1;
    
    public static synchronized int getNextID() {
        return nextID++;
    }
}
```

---

## ⚡ Performance Considerations

| Aspect | Impact | Notes |
|--------|--------|-------|
| **Lock contention** | High | Multiple threads waiting for lock |
| **synchronized overhead** | Medium | Acquiring/releasing locks |
| **Block size** | Critical | Smaller blocks = better performance |
| **Lock granularity** | Important | Fine-grained locks reduce contention |

---

## ✅ Best Practices

### DO ✅

1. **Minimize Synchronized Scope**
   ```java
   // ✅ Good - only critical section synchronized
   public void method() {
       // Non-critical code
       synchronized(lock) {
           // Only critical section
       }
       // More non-critical code
   }
   ```

2. **Use Dedicated Lock Objects**
   ```java
   // ✅ Good - private lock object
   private final Object lock = new Object();
   
   public void method() {
       synchronized(lock) {
           // Critical section
       }
   }
   ```

3. **Document Thread Safety**
   ```java
   /**
    * Thread-safe counter class.
    * All methods are synchronized.
    */
   public class SafeCounter {
       // ...
   }
   ```

### DON'T ❌

1. **Don't Synchronize on this in Public Classes**
   ```java
   // ❌ Bad - external code can synchronize on this
   public synchronized void method() { }
   
   // ✅ Better - use private lock
   private final Object lock = new Object();
   public void method() {
       synchronized(lock) { }
   }
   ```

2. **Don't Hold Locks During I/O**
   ```java
   // ❌ Bad - blocks other threads during I/O
   synchronized(lock) {
       readFromFile(); // Slow operation
   }
   
   // ✅ Better - minimize lock scope
   String data = readFromFile();
   synchronized(lock) {
       // Use data
   }
   ```

---

## ⚠️ Common Pitfalls

### 1. Forgetting to Synchronize
```java
// ❌ Problem: Race condition
private int counter = 0;
public void increment() {
    counter++; // Not atomic!
}

// ✅ Solution: Synchronize
public synchronized void increment() {
    counter++;
}
```

### 2. Inconsistent Synchronization
```java
// ❌ Problem: Some methods synchronized, some not
public synchronized void increment() { count++; }
public int getCount() { return count; } // Not synchronized!

// ✅ Solution: Synchronize all access
public synchronized int getCount() { return count; }
```

### 3. Deadlock
```java
// ❌ Problem: Circular wait
Thread 1: synchronized(a) { synchronized(b) { } }
Thread 2: synchronized(b) { synchronized(a) { } }

// ✅ Solution: Consistent lock ordering
Both: synchronized(a) { synchronized(b) { } }
```

---

## 🎓 Interview Questions

### Q1: What is synchronization and why is it needed?
**Answer:** Synchronization coordinates access to shared resources among multiple threads, preventing race conditions and ensuring data consistency.

### Q2: What's the difference between synchronized method and synchronized block?
**Answer:** 
- **Synchronized method:** Entire method is critical section, lock on `this` or Class
- **Synchronized block:** More fine-grained, can specify lock object, better performance

### Q3: Can two threads execute two different synchronized methods simultaneously?
**Answer:** No, if both methods synchronize on the same object. The lock is per object, not per method.

### Q4: What is a race condition?
**Answer:** When multiple threads access shared data simultaneously without proper synchronization, leading to unpredictable results.

### Q5: What happens if a synchronized method calls another synchronized method?
**Answer:** Java locks are reentrant - a thread can reacquire a lock it already owns. This works fine.

---

## 📚 Further Reading

- [Synchronized Methods](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html)
- [Intrinsic Locks and Synchronization](https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html)
- [Thread Safety](https://docs.oracle.com/javase/tutorial/essential/concurrency/threadsafe.html)

---

## 💡 Tips

- 💡 Synchronize the smallest possible scope
- 💡 Use private lock objects in public classes
- 💡 Maintain consistent lock ordering to avoid deadlocks
- 💡 Consider using concurrent collections instead
- 💡 Document thread safety guarantees
- 💡 Test thoroughly with multiple threads

---

## 📝 Code Example

For comprehensive examples with 8+ methods, see:  
**[SynchronizationExample.java](SynchronizationExample.java)**

---

**Related:** [Module README](../README.md) | [Previous: Thread Lifecycle](../02_ThreadLifecycle/) | [Next: Wait/Notify](../04_WaitNotify/)

