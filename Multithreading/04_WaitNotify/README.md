# Wait/Notify - Inter-Thread Communication

## 📘 Overview

The wait/notify mechanism allows threads to communicate with each other by suspending execution until specific conditions are met. It's fundamental for implementing coordination patterns like Producer-Consumer.

**Purpose:** Enable efficient inter-thread communication and coordination.

**When to Use:**
- ✅ Producer-Consumer patterns
- ✅ Thread coordination
- ✅ Conditional waiting
- ✅ Event-driven synchronization

---

## 🔑 Key Concepts

### 1. wait()
- Releases lock and waits for notification
- Must be called within synchronized block
- Can be interrupted

### 2. notify() / notifyAll()
- `notify()`: Wakes one waiting thread
- `notifyAll()`: Wakes all waiting threads
- Must be called within synchronized block

### 3. Producer-Consumer Pattern
Classic pattern where producers create data and consumers process it, coordinated via wait/notify.

---

## 📝 Syntax

```java
synchronized(lock) {
    while (!condition) {
        lock.wait(); // Release lock and wait
    }
    // Condition met, proceed
}

synchronized(lock) {
    condition = true;
    lock.notify(); // Wake one thread
    // or lock.notifyAll(); // Wake all threads
}
```

---

## 💻 Code Example

```java
class SharedBuffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 5;
    
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Wait if full
        }
        queue.add(item);
        notifyAll(); // Notify consumers
    }
    
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait if empty
        }
        int item = queue.remove();
        notifyAll(); // Notify producers
        return item;
    }
}
```

---

## ✅ Best Practices

- ✅ Always use while loop for wait condition
- ✅ Call wait/notify in synchronized context
- ✅ Use notifyAll() for multiple waiting threads
- ✅ Handle InterruptedException properly

---

## 🎓 Interview Questions

**Q1: Why use while loop instead of if with wait()?**  
**A:** To handle spurious wakeups - thread may wake up without being notified.

**Q2: Difference between notify() and notifyAll()?**  
**A:** notify() wakes one thread, notifyAll() wakes all waiting threads. Use notifyAll() when multiple threads wait on different conditions.

---

For detailed examples, see: **[WaitNotifyExample.java](WaitNotifyExample.java)**

**Related:** [Previous: Synchronization](../03_Synchronization/) | [Next: Executor Service](../05_ExecutorService/)

