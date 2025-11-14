# Thread Lifecycle

## 📘 Overview

The thread lifecycle describes the various states a thread goes through from creation to termination. Understanding thread states and transitions is crucial for effective multithreading programming and debugging.

**Purpose:** Understand how threads transition between states and control thread execution flow.

**When to Use:**
- ✅ Debugging multithreading issues
- ✅ Coordinating thread execution
- ✅ Managing thread resources
- ✅ Understanding thread behavior

---

## 🔑 Key Concepts

### 1. Thread States
Java threads can be in one of six states defined in `Thread.State` enum:

| State | Description | How to Enter |
|-------|-------------|--------------|
| **NEW** | Thread created but not started | `new Thread()` |
| **RUNNABLE** | Executing or ready to execute | `thread.start()` |
| **BLOCKED** | Waiting for monitor lock | Entering synchronized block |
| **WAITING** | Waiting indefinitely | `wait()`, `join()` |
| **TIMED_WAITING** | Waiting with timeout | `sleep()`, `wait(timeout)` |
| **TERMINATED** | Completed execution | `run()` finished |

### 2. State Transitions
```
NEW → start() → RUNNABLE
RUNNABLE → synchronized → BLOCKED → RUNNABLE
RUNNABLE → wait()/join() → WAITING → notify()/finish → RUNNABLE
RUNNABLE → sleep(ms) → TIMED_WAITING → timeout → RUNNABLE
RUNNABLE → run() ends → TERMINATED
```

### 3. Thread Control Methods
- **sleep()**: Pauses thread for specified time
- **join()**: Wait for another thread to finish
- **interrupt()**: Request thread interruption
- **yield()**: Hint to scheduler to allow other threads

---

## 📝 Syntax

### Getting Thread State
```java
Thread thread = new Thread(() -> {
    // Task
});
System.out.println(thread.getState()); // NEW
thread.start();
System.out.println(thread.getState()); // RUNNABLE
```

### Thread.sleep()
```java
try {
    Thread.sleep(1000); // Sleep for 1 second
} catch (InterruptedException e) {
    // Handle interruption
    Thread.currentThread().interrupt();
}
```

### Thread.join()
```java
Thread thread = new Thread(() -> {
    // Long task
});
thread.start();
thread.join(); // Wait for thread to finish
System.out.println("Thread completed");
```

### Thread.interrupt()
```java
Thread thread = new Thread(() -> {
    while (!Thread.interrupted()) {
        // Work
    }
});
thread.start();
Thread.sleep(100);
thread.interrupt(); // Request interruption
```

---

## 🔍 Important Methods

| Method | Description | Blocking | Releases Lock |
|--------|-------------|----------|---------------|
| `start()` | Starts thread | No | N/A |
| `run()` | Execute task | No | N/A |
| `sleep(ms)` | Pause execution | Yes | No |
| `join()` | Wait for thread | Yes | No |
| `join(ms)` | Wait with timeout | Yes | No |
| `interrupt()` | Interrupt thread | No | N/A |
| `interrupted()` | Check/clear flag | No | N/A |
| `isInterrupted()` | Check flag | No | N/A |
| `yield()` | Hint to scheduler | No | N/A |
| `getState()` | Get current state | No | N/A |

---

## 💻 Code Examples

### Example 1: Thread States
```java
Thread thread = new Thread(() -> {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});

System.out.println("State: " + thread.getState()); // NEW
thread.start();
System.out.println("State: " + thread.getState()); // RUNNABLE
Thread.sleep(100);
System.out.println("State: " + thread.getState()); // TIMED_WAITING
thread.join();
System.out.println("State: " + thread.getState()); // TERMINATED
```

### Example 2: Using join()
```java
Thread worker = new Thread(() -> {
    System.out.println("Worker starting");
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("Worker finished");
});

worker.start();
System.out.println("Waiting for worker...");
worker.join(); // Wait for worker to complete
System.out.println("Worker has completed");
```

### Example 3: Handling Interrupts
```java
Thread thread = new Thread(() -> {
    try {
        while (!Thread.interrupted()) {
            System.out.println("Working...");
            Thread.sleep(500);
        }
    } catch (InterruptedException e) {
        System.out.println("Interrupted during sleep");
    }
    System.out.println("Exiting gracefully");
});

thread.start();
Thread.sleep(2000);
thread.interrupt(); // Request interruption
```

---

## ⚡ Performance Considerations

| Aspect | Impact | Notes |
|--------|--------|-------|
| **sleep()** | Low overhead | Thread gives up CPU |
| **join()** | Blocks caller | Wait for completion |
| **interrupt()** | Very low | Just sets flag |
| **Context switching** | Can be expensive | OS-dependent |

---

## ✅ Best Practices

### DO ✅

1. **Always Handle InterruptedException**
   ```java
   try {
       Thread.sleep(1000);
   } catch (InterruptedException e) {
       Thread.currentThread().interrupt(); // Restore flag
       // Handle or propagate
   }
   ```

2. **Use join() with Timeout**
   ```java
   thread.join(5000); // Wait max 5 seconds
   if (thread.isAlive()) {
       thread.interrupt(); // Timed out
   }
   ```

3. **Check Interruption Status**
   ```java
   while (!Thread.interrupted()) {
       // Work
   }
   ```

### DON'T ❌

1. **Don't Ignore InterruptedException**
   ```java
   // ❌ Bad
   catch (InterruptedException e) {
       // Empty - interruption lost
   }
   
   // ✅ Good
   catch (InterruptedException e) {
       Thread.currentThread().interrupt();
       return; // or handle appropriately
   }
   ```

2. **Don't Call Thread.stop()**
   ```java
   // ❌ Deprecated and dangerous
   thread.stop();
   
   // ✅ Use interruption instead
   thread.interrupt();
   ```

---

## ⚠️ Common Pitfalls

### 1. Ignoring InterruptedException
```java
// ❌ Problem: Interrupt status lost
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    // Ignored
}

// ✅ Solution: Restore interrupt status
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

### 2. Busy Waiting
```java
// ❌ Problem: Wastes CPU
while (!condition) {
    // Busy wait
}

// ✅ Solution: Use wait/notify or sleep
while (!condition) {
    Thread.sleep(100);
}
```

---

## 🎓 Interview Questions

### Q1: What are the different thread states in Java?
**Answer:** NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED. Threads transition through these states based on their lifecycle and synchronization needs.

### Q2: What's the difference between sleep() and wait()?
**Answer:** 
- `sleep()`: Static method, doesn't release lock, waits for specified time
- `wait()`: Instance method, releases lock, waits for notify/notifyAll

### Q3: What happens when you call interrupt() on a thread?
**Answer:** Sets the thread's interrupt flag. If thread is in WAITING/TIMED_WAITING state (sleep, wait, join), it throws InterruptedException and clears the flag.

### Q4: Why should you restore interrupt status after catching InterruptedException?
**Answer:** To allow higher-level code to know the thread was interrupted. Simply catching and ignoring loses this information.

### Q5: What is the difference between interrupted() and isInterrupted()?
**Answer:** 
- `interrupted()`: Static method, checks and clears interrupt flag
- `isInterrupted()`: Instance method, checks flag without clearing

---

## 📚 Further Reading

- [Thread States](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html)
- [Thread Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [Pausing Execution with Sleep](https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html)
- [Interrupts](https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html)

---

## 💡 Tips

- 💡 Always handle InterruptedException properly
- 💡 Use join() with timeout to avoid indefinite waiting
- 💡 Check thread state for debugging
- 💡 Avoid busy waiting - use sleep or wait/notify
- 💡 Restore interrupt status after catching InterruptedException
- 💡 Use Thread.interrupted() to check and clear flag

---

## 📝 Code Example

For comprehensive examples with 6+ methods, see:  
**[ThreadLifecycleExample.java](ThreadLifecycleExample.java)**

---

**Related:** [Module README](../README.md) | [Previous: Thread Creation](../01_ThreadCreation/) | [Next: Synchronization](../03_Synchronization/)

