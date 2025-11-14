# Thread Creation

## 📘 Overview

Thread creation is the foundation of concurrent programming in Java. A thread is a lightweight subprocess that allows programs to perform multiple tasks simultaneously. Java provides multiple ways to create and start threads.

**Purpose:** Enable concurrent execution of code, allowing programs to perform multiple operations simultaneously and utilize multi-core processors efficiently.

**When to Use:**
- ✅ Need to perform tasks in parallel
- ✅ Want responsive UI while processing in background
- ✅ Need to handle multiple client connections
- ✅ Want to utilize multi-core processors

---

## 🔑 Key Concepts

### 1. Thread vs Process
- **Process:** Independent program with its own memory space
- **Thread:** Lightweight unit within a process, shares memory

### 2. Ways to Create Threads
- **Extending Thread class:** Simple but less flexible
- **Implementing Runnable:** Preferred, allows implementing other interfaces
- **Lambda expression:** Concise syntax (Java 8+)
- **Anonymous class:** Quick one-time use

### 3. Thread Properties
- **Name:** Identifier for the thread
- **Priority:** Hint to scheduler (1-10, default 5)
- **Daemon:** Background threads that don't prevent JVM shutdown

---

## 📝 Syntax

### Method 1: Extending Thread Class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        // Thread logic
        System.out.println("Running in: " + getName());
    }
}

// Usage
MyThread thread = new MyThread();
thread.start(); // Start the thread
```

### Method 2: Implementing Runnable (Preferred)
```java
class MyTask implements Runnable {
    @Override
    public void run() {
        // Task logic
        System.out.println("Task running");
    }
}

// Usage
Thread thread = new Thread(new MyTask());
thread.start();
```

### Method 3: Lambda Expression (Java 8+)
```java
Thread thread = new Thread(() -> {
    System.out.println("Lambda task");
});
thread.start();
```

### Method 4: Anonymous Inner Class
```java
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Anonymous task");
    }
});
thread.start();
```

---

## 🔍 Important Methods

| Method | Description | Example |
|--------|-------------|---------|
| `start()` | Starts thread execution | `thread.start()` |
| `run()` | Contains thread logic | Override this method |
| `getName()` | Gets thread name | `thread.getName()` |
| `setName()` | Sets thread name | `thread.setName("Worker")` |
| `getPriority()` | Gets priority (1-10) | `thread.getPriority()` |
| `setPriority()` | Sets priority | `thread.setPriority(Thread.MAX_PRIORITY)` |
| `isDaemon()` | Checks if daemon | `thread.isDaemon()` |
| `setDaemon()` | Sets as daemon | `thread.setDaemon(true)` |
| `currentThread()` | Gets current thread | `Thread.currentThread()` |

---

## 💻 Code Examples

### Example 1: Basic Thread Creation
```java
Thread thread = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("Count: " + i);
        Thread.sleep(1000);
    }
});
thread.start();
```

### Example 2: Multiple Threads
```java
for (int i = 1; i <= 3; i++) {
    final int threadNum = i;
    Thread thread = new Thread(() -> {
        System.out.println("Thread " + threadNum + " running");
    });
    thread.setName("Worker-" + i);
    thread.start();
}
```

### Example 3: Daemon Thread
```java
Thread daemon = new Thread(() -> {
    while (true) {
        System.out.println("Daemon working...");
        Thread.sleep(1000);
    }
});
daemon.setDaemon(true); // Must set before start()
daemon.start();
```

---

## ⚡ Performance Considerations

| Aspect | Impact | Notes |
|--------|--------|-------|
| **Thread creation** | Medium overhead | Use thread pools for better performance |
| **Context switching** | Can be expensive | Limit number of threads |
| **Memory per thread** | ~1MB | Too many threads = memory issues |

---

## ✅ Best Practices

### DO ✅

1. **Prefer Runnable over Thread**
   ```java
   // ✅ Good - can implement other interfaces
   class MyTask implements Runnable {
       @Override
       public void run() { /* work */ }
   }
   ```

2. **Use Meaningful Thread Names**
   ```java
   // ✅ Good - easy to debug
   thread.setName("DatabaseWorker-1");
   ```

3. **Use Thread Pools for Multiple Tasks**
   ```java
   // ✅ Good - better resource management
   ExecutorService executor = Executors.newFixedThreadPool(10);
   ```

### DON'T ❌

1. **Don't Call run() Directly**
   ```java
   // ❌ Bad - doesn't start new thread
   thread.run(); // Executes in current thread
   
   // ✅ Good - starts new thread
   thread.start();
   ```

2. **Don't Create Too Many Threads**
   ```java
   // ❌ Bad - can exhaust resources
   for (int i = 0; i < 10000; i++) {
       new Thread(() -> { /* work */ }).start();
   }
   ```

3. **Don't Ignore Thread Naming**
   ```java
   // ❌ Bad - hard to debug
   new Thread(() -> { /* work */ }).start();
   
   // ✅ Good - easy to identify
   Thread thread = new Thread(() -> { /* work */ });
   thread.setName("PaymentProcessor");
   thread.start();
   ```

---

## ⚠️ Common Pitfalls

### 1. Calling run() Instead of start()
```java
// ❌ Problem: Doesn't create new thread
thread.run(); // Executes in main thread

// ✅ Solution: Use start()
thread.start(); // Creates and starts new thread
```

### 2. Starting Thread Twice
```java
// ❌ Problem: Throws IllegalThreadStateException
thread.start();
thread.start(); // Error!

// ✅ Solution: Create new thread for each execution
```

### 3. Setting Daemon After start()
```java
// ❌ Problem: Throws IllegalThreadStateException
thread.start();
thread.setDaemon(true); // Error!

// ✅ Solution: Set daemon before starting
thread.setDaemon(true);
thread.start();
```

---

## 🔄 Comparison: Thread vs Runnable

| Aspect | Thread | Runnable |
|--------|--------|----------|
| **Flexibility** | Can't extend other classes | Can implement multiple interfaces |
| **Reusability** | Thread tied to execution | Task can be reused |
| **Separation** | Tightly coupled | Better separation of concerns |
| **Best Practice** | ❌ Less preferred | ✅ Preferred |

---

## 🎓 Interview Questions

### Q1: What's the difference between extending Thread and implementing Runnable?
**Answer:** 
- **Extending Thread:** Simple but limits class hierarchy (can't extend other classes)
- **Implementing Runnable:** Preferred - better design, allows implementing other interfaces, separates task from execution mechanism

### Q2: What happens if you call run() instead of start()?
**Answer:** Calling `run()` directly executes the method in the current thread, not in a new thread. You must call `start()` to create and start a new thread.

### Q3: What are daemon threads?
**Answer:** Daemon threads are background threads that don't prevent JVM from exiting. When all user threads complete, JVM terminates even if daemon threads are still running. Set with `setDaemon(true)` before starting thread.

### Q4: Can you start a thread twice?
**Answer:** No. Calling `start()` on an already started thread throws `IllegalThreadStateException`. Create a new thread instance for each execution.

### Q5: What is thread priority and does it guarantee execution order?
**Answer:** Thread priority (1-10) is a hint to the scheduler, but doesn't guarantee execution order. It's platform-dependent and should not be relied upon for program correctness.

---

## 📚 Further Reading

### Official Documentation
- [Thread Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [Runnable Interface](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
- [Defining and Starting a Thread](https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html)

### Related Topics
- [Thread Lifecycle](../02_ThreadLifecycle/) - Understanding thread states
- [Executor Service](../05_ExecutorService/) - Modern thread management

---

## 💡 Tips

- 💡 Prefer Runnable over Thread for better design
- 💡 Always use meaningful thread names for debugging
- 💡 Consider thread pools for multiple tasks
- 💡 Be aware of thread creation overhead
- 💡 Set daemon status before starting thread
- 💡 Don't create thousands of threads manually

---

## 📝 Code Example

For comprehensive, runnable examples with 7+ methods, see:  
**[ThreadCreationExample.java](ThreadCreationExample.java)**

The example covers:
- Extending Thread class
- Implementing Runnable
- Lambda expressions
- Anonymous classes
- Thread naming and priority
- Daemon threads
- Multiple threads execution

---

**Related:** [Module README](../README.md) | [Quick Reference](../QUICK_REFERENCE.md) | [Next: Thread Lifecycle](../02_ThreadLifecycle/)

