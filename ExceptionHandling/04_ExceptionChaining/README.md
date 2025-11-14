# Exception Chaining

## 📘 Overview

Exception chaining (also called exception wrapping) is the practice of catching a low-level exception and wrapping it in a higher-level exception while preserving the original exception as the cause. This preserves the complete exception context through multiple application layers.

**Purpose:** Maintain exception context when translating between abstraction layers while preserving debugging information.

**When to Use:**
- ✅ Multi-layered applications (UI → Service → DAO → Database)
- ✅ When translating technical exceptions to business exceptions
- ✅ To preserve root cause for debugging
- ✅ When wrapping third-party exceptions

---

## 🔑 Key Concepts

### 1. Cause Relationship
- Original exception is preserved as "cause"
- New exception wraps the original
- Chain can be arbitrarily long

### 2. Methods for Chaining
- Constructor: `new Exception(message, cause)`
- Method: `exception.initCause(cause)`
- Retrieve: `exception.getCause()`

### 3. Stack Traces
- Both exceptions' stack traces preserved
- `printStackTrace()` shows entire chain
- Root cause accessible via `getCause()`

---

## 📝 Syntax

### Constructor Chaining
```java
try {
    // Low-level operation
    connection.execute(sql);
} catch (SQLException e) {
    // Wrap in higher-level exception
    throw new DataAccessException("Query failed", e);
}
```

### Using initCause()
```java
try {
    riskyOperation();
} catch (LowLevelException e) {
    HighLevelException wrapper = new HighLevelException("Operation failed");
    wrapper.initCause(e);
    throw wrapper;
}
```

### Getting Root Cause
```java
Throwable cause = exception.getCause();
while (cause != null) {
    System.out.println("Caused by: " + cause);
    cause = cause.getCause();
}
```

---

## 💻 Code Examples

### Example 1: Basic Chaining
```java
public void processData() throws ServiceException {
    try {
        database.query();
    } catch (SQLException e) {
        throw new ServiceException("Data processing failed", e);
    }
}

// Catching and inspecting
try {
    processData();
} catch (ServiceException e) {
    System.out.println("Error: " + e.getMessage());
    System.out.println("Caused by: " + e.getCause());
}
```

### Example 2: Multi-Layer Chain
```java
// Layer 3: Database
public void executeQuery() throws SQLException {
    throw new SQLException("Connection timeout");
}

// Layer 2: DAO
public void fetchData() throws DataAccessException {
    try {
        executeQuery();
    } catch (SQLException e) {
        throw new DataAccessException("Database error", e);
    }
}

// Layer 1: Service
public void processRequest() throws ServiceException {
    try {
        fetchData();
    } catch (DataAccessException e) {
        throw new ServiceException("Processing failed", e);
    }
}
```

---

## ✅ Best Practices

### DO ✅

1. **Always Preserve Original Exception**
   ```java
   // ✅ Good - preserves cause
   catch (IOException e) {
       throw new DataException("Failed to read", e);
   }
   ```

2. **Add Context at Each Layer**
   ```java
   // ✅ Good - adds layer-specific context
   catch (SQLException e) {
       throw new DataAccessException(
           "Failed to fetch user " + userId, e);
   }
   ```

3. **Use Appropriate Exception Types**
   ```java
   // ✅ Good - appropriate abstraction
   catch (SQLException e) {
       throw new UserNotFoundException("User not found", e);
   }
   ```

### DON'T ❌

1. **Don't Lose Original Exception**
   ```java
   // ❌ Bad - original exception lost
   catch (IOException e) {
       throw new DataException("Failed");  // No cause!
   }
   ```

2. **Don't Chain Unnecessarily**
   ```java
   // ❌ Bad - adds no value
   catch (MyException e) {
       throw new MyException(e.getMessage(), e);
   }
   ```

---

## 🎓 Interview Questions

### Q1: What is exception chaining?
**Answer:** Exception chaining is wrapping a lower-level exception in a higher-level exception while preserving the original as the cause, maintaining complete error context across application layers.

### Q2: How do you get the root cause of a chained exception?
**Answer:** 
```java
Throwable rootCause = exception;
while (rootCause.getCause() != null) {
    rootCause = rootCause.getCause();
}
```

### Q3: When should you use exception chaining?
**Answer:** Use when translating between abstraction layers (e.g., SQLException to DataAccessException), when wrapping third-party exceptions, or when adding context while preserving debugging information.

---

## 📚 Further Reading

- [Chained Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/chained.html)
- [Throwable Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)

---

## 📝 Code Example

See **[ExceptionChainingExample.java](ExceptionChainingExample.java)** for comprehensive examples.

---

**Related:** [Module README](../README.md) | [Previous: Custom Exceptions](../03_CustomExceptions/) | [Next: Try-With-Resources](../05_TryWithResources/)

