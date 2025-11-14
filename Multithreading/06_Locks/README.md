# Locks - Advanced Synchronization

## 📘 Overview

The Lock interface and its implementations provide more flexible and powerful synchronization mechanisms than the synchronized keyword.

**Purpose:** Provide advanced locking features like try-lock, timeout, and interruptible locks.

**When to Use:**
- ✅ Need tryLock with timeout
- ✅ Interruptible lock acquisition
- ✅ Read-write scenarios (ReadWriteLock)
- ✅ Condition variables

---

## 🔑 Key Concepts

### 1. ReentrantLock
More flexible alternative to synchronized

### 2. ReadWriteLock
Separate locks for read and write operations

### 3. Condition
More flexible than wait/notify

---

## 📝 Syntax

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock(); // Must unlock in finally
}

// Try lock with timeout
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try {
        // Got lock
    } finally {
        lock.unlock();
    }
}
```

---

## ✅ Best Practices

- ✅ Always unlock in finally block
- ✅ Use tryLock for timeout scenarios
- ✅ Consider ReadWriteLock for read-heavy loads

---

## 🎓 Interview Questions

**Q1: ReentrantLock vs synchronized?**  
**A:** ReentrantLock offers tryLock, timeout, interruptible locks, and fairness policies. synchronized is simpler.

**Q2: When to use ReadWriteLock?**  
**A:** When reads significantly outnumber writes - allows multiple concurrent readers.

---

For examples, see: **[LocksExample.java](LocksExample.java)**

**Related:** [Previous: Executor Service](../05_ExecutorService/) | [Next: Concurrent Collections](../07_ConcurrentCollections/)

