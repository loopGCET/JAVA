# Java Multithreading & Concurrency - Quick Reference Card

## 🎯 Choose the Right Approach

### I need to run tasks in parallel
```java
// ⭐ Recommended: Use ExecutorService (Thread Pool)
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> {
    // Task logic
});
executor.shutdown();

// ⚠️ Old way: Creating threads manually
Thread thread = new Thread(() -> {
    // Task logic
});
thread.start();
```

### I need a thread-safe counter
```java
// ⭐ Best: Atomic classes (lock-free)
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();

// ⚠️ Alternative: synchronized
private int counter = 0;
public synchronized void increment() {
    counter++;
}
```

### I need thread-safe collections
```java
// ⭐ For maps
ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

// ⭐ For lists (read-heavy)
CopyOnWriteArrayList<T> list = new CopyOnWriteArrayList<>();

// ⭐ For queues (producer-consumer)
BlockingQueue<T> queue = new ArrayBlockingQueue<>(100);
```

### I need to share data between threads
```java
// ⭐ For simple visibility
private volatile boolean flag = true;

// ⭐ For mutual exclusion
synchronized(lock) {
    // Critical section
}

// ⭐ Advanced: ReentrantLock
Lock lock = new ReentrantLock();
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock();
}
```

---

## 📊 Thread Creation Cheat Sheet

### Method 1: Extending Thread Class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in: " + getName());
    }
}

// Usage
MyThread thread = new MyThread();
thread.start();
```

### Method 2: Implementing Runnable (Preferred)
```java
class MyTask implements Runnable {
    @Override
    public void run() {
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

### Method 4: ExecutorService (Best for Multiple Tasks)
```java
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> {
    System.out.println("Task in pool");
});
executor.shutdown();
```

---

## 🔒 Synchronization Patterns

### Pattern 1: Synchronized Method
```java
public synchronized void method() {
    // Only one thread can execute at a time
}
```

### Pattern 2: Synchronized Block
```java
public void method() {
    synchronized(this) {
        // Critical section
    }
}
```

### Pattern 3: Static Synchronization
```java
public static synchronized void method() {
    // Class-level lock
}
```

### Pattern 4: Synchronized on Specific Object
```java
private final Object lock = new Object();

public void method() {
    synchronized(lock) {
        // Critical section
    }
}
```

---

## 🚀 Executor Service Quick Guide

### Fixed Thread Pool
```java
// Fixed number of threads
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> { /* task */ });
executor.shutdown();
```

### Cached Thread Pool
```java
// Creates threads as needed
ExecutorService executor = Executors.newCachedThreadPool();
```

### Single Thread Executor
```java
// Executes tasks sequentially
ExecutorService executor = Executors.newSingleThreadExecutor();
```

### Scheduled Thread Pool
```java
// For scheduled/periodic tasks
ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
executor.schedule(() -> { /* task */ }, 5, TimeUnit.SECONDS);
executor.scheduleAtFixedRate(() -> { /* task */ }, 0, 1, TimeUnit.SECONDS);
```

### Using Callable and Future
```java
ExecutorService executor = Executors.newFixedThreadPool(5);
Future<Integer> future = executor.submit(() -> {
    return 42; // Callable returns value
});

try {
    Integer result = future.get(); // Blocking call
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}
```

---

## 🔐 Lock Types Quick Reference

### ReentrantLock
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock(); // Must unlock in finally
}
```

### tryLock with Timeout
```java
Lock lock = new ReentrantLock();
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try {
        // Got lock
    } finally {
        lock.unlock();
    }
} else {
    // Couldn't acquire lock
}
```

### ReadWriteLock
```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();

// Multiple readers can read simultaneously
rwLock.readLock().lock();
try {
    // Read data
} finally {
    rwLock.readLock().unlock();
}

// Only one writer at a time
rwLock.writeLock().lock();
try {
    // Write data
} finally {
    rwLock.writeLock().unlock();
}
```

---

## 📦 Concurrent Collections

| Collection | Thread-Safe | Use Case | Performance |
|------------|-------------|----------|-------------|
| **ConcurrentHashMap** | ✅ | General map | Excellent |
| **CopyOnWriteArrayList** | ✅ | Read-heavy lists | Good (reads) |
| **ConcurrentLinkedQueue** | ✅ | Non-blocking queue | Excellent |
| **ArrayBlockingQueue** | ✅ | Bounded queue | Good |
| **LinkedBlockingQueue** | ✅ | Unbounded queue | Good |
| **PriorityBlockingQueue** | ✅ | Priority queue | Good |
| **SynchronousQueue** | ✅ | Hand-off | Good |

### ConcurrentHashMap
```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1);
map.computeIfAbsent("key", k -> expensiveComputation());
map.putIfAbsent("key", 1);
```

### BlockingQueue (Producer-Consumer)
```java
BlockingQueue<Task> queue = new ArrayBlockingQueue<>(100);

// Producer
queue.put(task); // Blocks if full

// Consumer
Task task = queue.take(); // Blocks if empty
```

### CopyOnWriteArrayList
```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("item"); // Thread-safe
```

---

## ⚛️ Atomic Classes

| Class | Use Case | Operations |
|-------|----------|------------|
| **AtomicInteger** | Integer counter | get, set, incrementAndGet, compareAndSet |
| **AtomicLong** | Long counter | Same as AtomicInteger |
| **AtomicBoolean** | Boolean flag | get, set, compareAndSet |
| **AtomicReference** | Object reference | get, set, compareAndSet |

### AtomicInteger
```java
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();          // ++counter
counter.getAndIncrement();          // counter++
counter.addAndGet(5);               // counter += 5
counter.compareAndSet(5, 10);       // CAS operation
```

### AtomicBoolean
```java
AtomicBoolean flag = new AtomicBoolean(false);
flag.set(true);
if (flag.compareAndSet(false, true)) {
    // Successfully changed from false to true
}
```

### AtomicReference
```java
AtomicReference<User> userRef = new AtomicReference<>(new User());
userRef.set(newUser);
User current = userRef.get();
```

---

## 🔄 Wait/Notify Pattern

### Basic Wait/Notify
```java
synchronized(lock) {
    while (!condition) {
        lock.wait(); // Release lock and wait
    }
    // Condition met, proceed
}

synchronized(lock) {
    condition = true;
    lock.notify(); // Wake one waiting thread
    // or lock.notifyAll(); // Wake all waiting threads
}
```

### Producer-Consumer with wait/notify
```java
class Buffer {
    private Queue<Item> queue = new LinkedList<>();
    private final int capacity = 10;
    
    public synchronized void produce(Item item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Wait if full
        }
        queue.add(item);
        notifyAll(); // Notify consumers
    }
    
    public synchronized Item consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait if empty
        }
        Item item = queue.remove();
        notifyAll(); // Notify producers
        return item;
    }
}
```

---

## 🎯 Thread Control Methods

| Method | Description | Blocking | Releases Lock |
|--------|-------------|----------|---------------|
| **start()** | Starts thread | No | N/A |
| **run()** | Execute task | No | N/A |
| **sleep(ms)** | Pause execution | Yes | No |
| **join()** | Wait for thread | Yes | No |
| **interrupt()** | Interrupt thread | No | N/A |
| **yield()** | Hint to scheduler | No | No |
| **wait()** | Wait for notify | Yes | Yes |
| **notify()** | Wake one thread | No | N/A |

### Thread Control Examples
```java
// Sleep
Thread.sleep(1000); // Sleep 1 second

// Join
thread.join(); // Wait for thread to finish
thread.join(5000); // Wait max 5 seconds

// Interrupt
thread.interrupt(); // Request interruption

// Check interrupted
if (Thread.interrupted()) {
    // Handle interruption
}
```

---

## 🏃 Thread States

```
NEW → RUNNABLE → BLOCKED/WAITING/TIMED_WAITING → RUNNABLE → TERMINATED
```

| State | Description | How to Enter |
|-------|-------------|--------------|
| **NEW** | Thread created but not started | `new Thread()` |
| **RUNNABLE** | Executing or ready to execute | `thread.start()` |
| **BLOCKED** | Waiting for monitor lock | Entering synchronized |
| **WAITING** | Waiting indefinitely | `wait()`, `join()` |
| **TIMED_WAITING** | Waiting with timeout | `sleep()`, `wait(timeout)` |
| **TERMINATED** | Completed execution | `run()` finished |

---

## ⚡ Performance Tips

### DO ✅
```java
// ✅ Use thread pools
ExecutorService executor = Executors.newFixedThreadPool(10);

// ✅ Use concurrent collections
ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

// ✅ Use atomic for simple counters
AtomicInteger counter = new AtomicInteger(0);

// ✅ Minimize synchronization scope
synchronized(lock) {
    // Only critical code here
}

// ✅ Use volatile for visibility
private volatile boolean running = true;

// ✅ Always unlock in finally
lock.lock();
try {
    // Work
} finally {
    lock.unlock();
}
```

### DON'T ❌
```java
// ❌ Don't create too many threads
for (int i = 0; i < 10000; i++) {
    new Thread(() -> { /* work */ }).start(); // BAD!
}

// ❌ Don't synchronize on this in public classes
public synchronized void method() { } // Avoid

// ❌ Don't hold locks during I/O
synchronized(lock) {
    readFromFile(); // BAD - blocking I/O
}

// ❌ Don't ignore InterruptedException
catch (InterruptedException e) {
    // Empty catch - BAD!
}

// ❌ Don't forget to shutdown executor
// executor.shutdown(); // Must call this!
```

---

## 🐛 Common Pitfalls

### Pitfall 1: Race Condition
```java
// ❌ Bad
private int count = 0;
public void increment() {
    count++; // Not atomic!
}

// ✅ Good
private AtomicInteger count = new AtomicInteger(0);
public void increment() {
    count.incrementAndGet();
}
```

### Pitfall 2: Deadlock
```java
// ❌ Bad - can deadlock
Thread 1: synchronized(a) { synchronized(b) { } }
Thread 2: synchronized(b) { synchronized(a) { } }

// ✅ Good - consistent lock ordering
Thread 1: synchronized(a) { synchronized(b) { } }
Thread 2: synchronized(a) { synchronized(b) { } }
```

### Pitfall 3: Visibility Issues
```java
// ❌ Bad - changes may not be visible
private boolean running = true;

// ✅ Good - volatile ensures visibility
private volatile boolean running = true;
```

---

## 📊 Quick Decision Matrix

| Scenario | Solution |
|----------|----------|
| Run multiple tasks | ExecutorService |
| Thread-safe counter | AtomicInteger |
| Thread-safe map | ConcurrentHashMap |
| Producer-Consumer | BlockingQueue |
| Read-heavy data | ReadWriteLock |
| Simple flag | volatile boolean |
| Complex critical section | ReentrantLock |
| Periodic tasks | ScheduledExecutorService |

---

## 🎓 Interview Quick Answers

**Q: Thread vs Runnable?**  
A: Runnable is preferred - allows implementing other interfaces, better separation of concerns.

**Q: synchronized vs ReentrantLock?**  
A: ReentrantLock offers more features (tryLock, fairness, interruptible), synchronized is simpler.

**Q: When to use volatile?**  
A: For visibility-only variables (boolean flags), not for compound operations.

**Q: What is thread safety?**  
A: Code that works correctly when accessed by multiple threads simultaneously.

**Q: How to prevent deadlock?**  
A: Consistent lock ordering, use tryLock with timeout, avoid nested locks.

---

## 📚 Further Reading

- [Java Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [java.util.concurrent Package](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)
- "Java Concurrency in Practice" by Brian Goetz

---

**Quick Navigation:**
- [Full README](README.md)
- [File Index](INDEX.md)
- [Testing Guide](TESTING.md)

