# Executor Service - Modern Thread Management

## 📘 Overview

ExecutorService provides a high-level API for managing thread pools, eliminating the need to manually create and manage threads. It's the recommended way to handle concurrent tasks in modern Java.

**Purpose:** Manage thread lifecycle and task execution efficiently using thread pools.

**When to Use:**
- ✅ Running multiple tasks concurrently
- ✅ Need automatic thread management
- ✅ Want to control resource usage
- ✅ Scheduled or periodic tasks

---

## 🔑 Key Concepts

### 1. Thread Pool Types
- **FixedThreadPool**: Fixed number of threads
- **CachedThreadPool**: Creates threads as needed
- **SingleThreadExecutor**: Single worker thread
- **ScheduledThreadPool**: For delayed/periodic tasks

### 2. Callable vs Runnable
- **Runnable**: No return value
- **Callable**: Returns value, can throw exceptions

### 3. Future
Represents result of asynchronous computation

---

## 📝 Syntax

```java
ExecutorService executor = Executors.newFixedThreadPool(10);

// Submit Runnable
executor.submit(() -> {
    System.out.println("Task running");
});

// Submit Callable
Future<Integer> future = executor.submit(() -> {
    return 42;
});

// Shutdown
executor.shutdown();
```

---

## 💻 Code Examples

```java
// Fixed Thread Pool
ExecutorService executor = Executors.newFixedThreadPool(5);
for (int i = 0; i < 10; i++) {
    executor.submit(() -> {
        System.out.println("Task in: " + Thread.currentThread().getName());
    });
}
executor.shutdown();

// With Callable and Future
Future<String> future = executor.submit(() -> {
    Thread.sleep(1000);
    return "Result";
});
String result = future.get(); // Blocking
```

---

## ✅ Best Practices

- ✅ Always shutdown executor
- ✅ Use appropriate pool size
- ✅ Handle Future exceptions
- ✅ Use awaitTermination for graceful shutdown

---

## 🎓 Interview Questions

**Q1: Why use ExecutorService over creating threads manually?**  
**A:** Better resource management, reuses threads, provides higher-level API, easier task submission.

**Q2: What happens if you don't shutdown ExecutorService?**  
**A:** Application won't terminate as pool threads prevent JVM shutdown.

---

For detailed examples, see: **[ExecutorServiceExample.java](ExecutorServiceExample.java)**

**Related:** [Previous: Wait/Notify](../04_WaitNotify/) | [Next: Locks](../06_Locks/)

