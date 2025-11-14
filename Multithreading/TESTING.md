# Java Multithreading & Concurrency - Testing Guide

> Instructions for compiling, running, and testing all multithreading examples

[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Tests](https://img.shields.io/badge/Tests-Ready-success.svg)]()

---

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Testing Individual Examples](#testing-individual-examples)
- [Troubleshooting](#troubleshooting)

---

## 🔧 Prerequisites

### Required Software
- **JDK 8 or higher** - Java Development Kit
- **Command Line Tool** - Terminal, Command Prompt, or PowerShell

### Verify Java Installation
```bash
# Check Java version
java -version

# Check Java compiler
javac -version
```

**Expected Output:**
```
java version "1.8.0" or higher
javac version "1.8.0" or higher
```

---

## 🚀 Quick Start

### Run Individual Example
```bash
# Navigate to topic folder
cd 01_ThreadCreation

# Compile
javac ThreadCreationExample.java

# Run
java ThreadCreationExample

# Return to root
cd ..
```

---

## 📝 Testing Individual Examples

### Example 1: Thread Creation
```bash
cd 01_ThreadCreation
javac ThreadCreationExample.java
java ThreadCreationExample
cd ..
```

### Example 2: Thread Lifecycle
```bash
cd 02_ThreadLifecycle
javac ThreadLifecycleExample.java
java ThreadLifecycleExample
cd ..
```

### Example 3: Synchronization
```bash
cd 03_Synchronization
javac SynchronizationExample.java
java SynchronizationExample
cd ..
```

### Example 4: Wait/Notify
```bash
cd 04_WaitNotify
javac WaitNotifyExample.java
java WaitNotifyExample
cd ..
```

### Example 5: Executor Service
```bash
cd 05_ExecutorService
javac ExecutorServiceExample.java
java ExecutorServiceExample
cd ..
```

### Example 6: Locks
```bash
cd 06_Locks
javac LocksExample.java
java LocksExample
cd ..
```

### Example 7: Concurrent Collections
```bash
cd 07_ConcurrentCollections
javac ConcurrentCollectionsExample.java
java ConcurrentCollectionsExample
cd ..
```

### Example 8: Atomic Classes
```bash
cd 08_AtomicClasses
javac AtomicClassesExample.java
java AtomicClassesExample
cd ..
```

---

## 🐛 Troubleshooting

### Issue: "javac: command not found"
**Cause:** Java is not installed or not in PATH

**Solution:**
1. Install JDK 8 or higher
2. Add Java to PATH
3. Restart terminal
4. Verify: `javac -version`

---

### Issue: "Could not find or load main class"
**Cause:** Running from wrong directory or class not compiled

**Solution:**
1. Ensure you're in the correct directory
2. Compile first: `javac *.java`
3. Run with exact class name (no `.java` or `.class`)

---

### Issue: Threads not behaving as expected
**Cause:** Thread timing is non-deterministic

**Solution:**
- This is normal for concurrent programs
- Run multiple times to observe different behaviors
- Focus on understanding concepts, not exact output

---

## ✅ Verification Checklist

For each example, verify:
- [ ] Compiles without errors
- [ ] Runs to completion
- [ ] Produces output
- [ ] Demonstrates concepts

---

## 📚 Further Reading

- [Java Compilation](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html)
- [Running Java](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html)
- [Java Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)

---

**Last Updated:** November 2025  
**Java Version Required:** 8+  
**Total Examples:** 8

