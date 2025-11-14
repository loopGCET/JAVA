# Try-Catch-Finally

## 📘 Overview

The `try-catch-finally` block is the fundamental mechanism for handling exceptions in Java. It allows you to catch and handle exceptions that might occur during program execution, and ensure that cleanup code always runs.

**Purpose:** Gracefully handle errors without crashing the program, and guarantee resource cleanup.

**When to Use:**
- ✅ When calling code that might throw exceptions
- ✅ When you need to guarantee cleanup code execution
- ✅ When you want to recover from expected errors
- ✅ When handling file I/O, network operations, or database access

---

## 🔑 Key Concepts

### 1. Try Block
- Contains code that might throw an exception
- Execution stops at the point where exception is thrown
- Control transfers to appropriate catch block

### 2. Catch Block
- Handles specific exception types
- Can have multiple catch blocks for different exceptions
- Executes only if matching exception is thrown

### 3. Finally Block
- Always executes, regardless of whether exception occurred
- Used for cleanup operations (closing files, releasing resources)
- Executes even if return statement is in try or catch

### 4. Multi-Catch (Java 7+)
- Handle multiple exception types in single catch block
- Reduces code duplication
- Use pipe (`|`) to separate exception types

---

## 📝 Syntax

### Basic Try-Catch
```java
try {
    // Code that might throw exception
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Handle the exception
    System.out.println("Cannot divide by zero");
}
```

### Try-Catch-Finally
```java
try {
    // Risky code
    FileReader file = new FileReader("data.txt");
} catch (FileNotFoundException e) {
    // Handle file not found
    System.out.println("File not found: " + e.getMessage());
} finally {
    // Cleanup code - always executes
    System.out.println("Cleanup complete");
}
```

### Multiple Catch Blocks
```java
try {
    // Code
    int[] array = {1, 2, 3};
    System.out.println(array[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Index out of bounds");
} catch (Exception e) {
    System.out.println("General exception: " + e.getMessage());
}
```

### Multi-Catch (Java 7+)
```java
try {
    // Code that might throw multiple exception types
    processData();
} catch (IOException | SQLException e) {
    // Handle both IOException and SQLException
    System.out.println("Error: " + e.getMessage());
}
```

### Nested Try-Catch
```java
try {
    // Outer try
    try {
        // Inner try
        int result = 10 / 0;
    } catch (ArithmeticException e) {
        System.out.println("Inner catch: " + e);
    }
} catch (Exception e) {
    System.out.println("Outer catch: " + e);
}
```

---

## 🔍 Important Features

| Feature | Description | Example |
|---------|-------------|---------|
| **Exception Object** | Contains error details | `e.getMessage()`, `e.printStackTrace()` |
| **Finally Execution** | Always runs | Used for cleanup |
| **Exception Hierarchy** | Catch from specific to general | Specific exceptions first |
| **Catch Order** | Matters! | Most specific first, general last |
| **Return in Finally** | Overrides try/catch return | ⚠️ Avoid this pattern |

---

## 💻 Code Examples

### Example 1: Basic Exception Handling
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
}
// Output: Error: / by zero
```

### Example 2: Finally Block Always Executes
```java
try {
    System.out.println("In try block");
    return;
} finally {
    System.out.println("In finally block");
}
// Output: 
// In try block
// In finally block
```

### Example 3: Multiple Catch Blocks
```java
try {
    String str = null;
    System.out.println(str.length());
} catch (NullPointerException e) {
    System.out.println("Null pointer: " + e.getMessage());
} catch (Exception e) {
    System.out.println("General exception: " + e.getMessage());
}
```

### Example 4: Multi-Catch (Java 7+)
```java
try {
    // Code that might throw different exceptions
    Files.readString(Paths.get("file.txt"));
} catch (IOException | SecurityException e) {
    System.out.println("File error: " + e.getMessage());
}
```

### Example 5: Nested Try-Catch
```java
try {
    try {
        int[] arr = {1, 2, 3};
        System.out.println(arr[5]);
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Index error");
        throw new RuntimeException("Re-throwing", e);
    }
} catch (RuntimeException e) {
    System.out.println("Outer catch: " + e.getMessage());
}
```

---

## ⚡ Time & Space Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| **Try block (no exception)** | O(1) | O(1) |
| **Catching exception** | O(1) | O(n) - stack trace |
| **Creating exception** | O(n) | O(n) - stack trace |
| **Finally block** | O(1) | O(1) |

**Note:** Exception creation is expensive due to stack trace generation.

---

## ✅ Best Practices

### DO ✅

1. **Catch Specific Exceptions**
   ```java
   // ✅ Good
   try {
       Files.readString(path);
   } catch (FileNotFoundException e) {
       // Handle specific case
   } catch (IOException e) {
       // Handle general I/O error
   }
   ```

2. **Use Finally for Cleanup**
   ```java
   // ✅ Good
   FileReader reader = null;
   try {
       reader = new FileReader("file.txt");
       // Read file
   } finally {
       if (reader != null) {
           reader.close();
       }
   }
   ```

3. **Log Exception Details**
   ```java
   // ✅ Good
   catch (IOException e) {
       logger.error("Failed to read file: " + path, e);
   }
   ```

4. **Order Catch Blocks Correctly**
   ```java
   // ✅ Good - specific to general
   catch (FileNotFoundException e) { }
   catch (IOException e) { }
   catch (Exception e) { }
   ```

### DON'T ❌

1. **Don't Catch and Ignore**
   ```java
   // ❌ Bad - silent failure
   try {
       riskyOperation();
   } catch (Exception e) {
       // Empty catch - don't do this!
   }
   ```

2. **Don't Catch Throwable**
   ```java
   // ❌ Bad - too broad
   try {
       code();
   } catch (Throwable t) {
       // Catches Error too!
   }
   ```

3. **Don't Return in Finally**
   ```java
   // ❌ Bad - overwrites try return
   try {
       return value1;
   } finally {
       return value2;  // This wins - confusing!
   }
   ```

4. **Don't Catch General Exception First**
   ```java
   // ❌ Bad - won't compile
   catch (Exception e) { }
   catch (IOException e) { }  // Unreachable code
   ```

---

## ⚠️ Common Pitfalls

### 1. Empty Catch Block
```java
// ❌ Problem: Silent failure
try {
    riskyOperation();
} catch (Exception e) {
    // Nothing here - error is hidden
}

// ✅ Solution: At minimum, log it
catch (Exception e) {
    logger.warn("Operation failed", e);
}
```

### 2. Catching Exception Instead of Specific Type
```java
// ❌ Problem: Too broad
try {
    Files.readString(path);
} catch (Exception e) {
    // Catches too much
}

// ✅ Solution: Be specific
catch (IOException e) {
    // Handle I/O errors specifically
}
```

### 3. Resource Leak in Finally
```java
// ❌ Problem: Close can throw exception
finally {
    connection.close();  // What if this throws?
}

// ✅ Solution: Nested try or try-with-resources
finally {
    try {
        if (connection != null) connection.close();
    } catch (SQLException e) {
        logger.error("Failed to close connection", e);
    }
}
```

### 4. Exception Masks Another Exception
```java
// ❌ Problem: Finally exception hides try exception
try {
    throw new Exception("First");
} finally {
    throw new Exception("Second");  // This one is thrown
}
```

---

## 🔄 Comparison: Try-Catch vs Throws

| Aspect | Try-Catch | Throws |
|--------|-----------|--------|
| **When to use** | Handle exception here | Let caller handle |
| **Syntax** | `try { } catch { }` | `throws Exception` |
| **Recovery** | Can recover | Propagates up |
| **Cleanup** | Finally block | Caller's responsibility |
| **Use case** | Expected errors | Caller decides handling |

---

## 🎓 Interview Questions

### Q1: What is the difference between final, finally, and finalize?
**Answer:**
- `final` - Keyword for constants, preventing inheritance, preventing method override
- `finally` - Block that always executes after try-catch
- `finalize()` - Method called by garbage collector before object destruction (deprecated)

### Q2: Will finally block execute if there's a return statement in try block?
**Answer:** Yes, finally block executes even if there's a return statement in try or catch block. The finally block executes before the method returns.

Example:
```java
try {
    return 1;
} finally {
    System.out.println("Finally executes");  // This runs first
}
```

### Q3: Can we have try without catch?
**Answer:** Yes, but you must have either catch or finally (or both). Valid combinations:
- try-catch
- try-finally
- try-catch-finally

### Q4: What happens if exception occurs in finally block?
**Answer:** The exception in finally block will be thrown, and it will mask any exception thrown in the try block. The original exception will be lost unless exception chaining is used.

### Q5: What is multi-catch and when was it introduced?
**Answer:** Multi-catch allows catching multiple exception types in a single catch block using pipe (`|`) separator. Introduced in Java 7. Useful when you want to handle different exceptions the same way.

```java
catch (IOException | SQLException e) {
    // Handle both
}
```

---

## 📚 Further Reading

### Official Documentation
- [Java Exception Handling Tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [The try Block](https://docs.oracle.com/javase/tutorial/essential/exceptions/try.html)
- [The catch Blocks](https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html)
- [The finally Block](https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html)

### Books
- "Effective Java" by Joshua Bloch - Item 69-77
- "Java: The Complete Reference" by Herbert Schildt

### Related Topics
- [Throws Keyword](../02_ThrowsKeyword/) - Declaring exceptions
- [Custom Exceptions](../03_CustomExceptions/) - Creating your own exceptions
- [Try-With-Resources](../05_TryWithResources/) - Modern resource management

---

## 💡 Tips

- 💡 Always catch the most specific exception first
- 💡 Use finally for cleanup, not business logic
- 💡 Don't catch exceptions you can't handle
- 💡 Log exceptions with full context
- 💡 Consider try-with-resources for automatic cleanup
- 💡 Never leave catch blocks empty
- 💡 Don't use exceptions for control flow

---

## 📝 Code Example

For a comprehensive, runnable example with 8+ demonstration methods, see:  
**[TryCatchFinallyExample.java](TryCatchFinallyExample.java)**

The example covers:
- Basic try-catch usage
- Multiple catch blocks
- Finally block execution
- Multi-catch syntax
- Nested try-catch
- Return statements with finally
- Real-world file I/O examples
- Exception object methods

---

**Related:** [Module README](../README.md) | [Quick Reference](../QUICK_REFERENCE.md) | [Next: Throws Keyword](../02_ThrowsKeyword/)

