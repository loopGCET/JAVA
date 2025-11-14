# Java Exception Handling - Quick Reference Card

## 🎯 Choose the Right Approach

### I need to handle an expected error
```java
try {
    // Code that might fail
    int result = parseUserInput(input);
} catch (NumberFormatException e) {
    // Handle gracefully
    System.out.println("Invalid input: " + e.getMessage());
}
```

### I want to propagate exceptions to caller
```java
public void readFile(String path) throws IOException {
    // Let caller handle IOException
    Files.readString(Paths.get(path));
}
```

### I need to clean up resources
```java
// ⭐ Modern way (Java 7+)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    return br.readLine();
}  // Auto-closed

// ⚠️ Old way
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("file.txt"));
    return br.readLine();
} finally {
    if (br != null) br.close();
}
```

### I need domain-specific exceptions
```java
// Create custom exception
public class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds: " + amount);
        this.amount = amount;
    }
}

// Use it
public void withdraw(double amount) throws InsufficientFundsException {
    if (balance < amount) {
        throw new InsufficientFundsException(amount);
    }
    balance -= amount;
}
```

### I need to preserve original exception
```java
try {
    // Low-level operation
    connection.execute(sql);
} catch (SQLException e) {
    // Wrap in higher-level exception
    throw new DataAccessException("Failed to execute query", e);
}
```

---

## 📊 Exception Hierarchy Cheat Sheet

```
Throwable
├── Error (Don't catch - JVM errors)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
│
└── Exception
    ├── RuntimeException (Unchecked)
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   ├── IndexOutOfBoundsException
    │   ├── ArithmeticException
    │   └── ClassCastException
    │
    └── Checked Exceptions (Must handle)
        ├── IOException
        ├── SQLException
        ├── ClassNotFoundException
        └── InterruptedException
```

---

## 🚀 Common Patterns

### Pattern 1: Basic Try-Catch
```java
try {
    // Risky code
} catch (SpecificException e) {
    // Handle specific exception
} catch (AnotherException e) {
    // Handle another exception
}
```

### Pattern 2: Multi-Catch (Java 7+)
```java
try {
    // Code
} catch (IOException | SQLException e) {
    // Handle multiple exceptions the same way
    log.error("Error: " + e);
}
```

### Pattern 3: Try-Catch-Finally
```java
try {
    // Main code
} catch (Exception e) {
    // Handle exception
} finally {
    // Always executes (cleanup)
}
```

### Pattern 4: Try-With-Resources (Java 7+)
```java
try (Resource1 r1 = new Resource1();
     Resource2 r2 = new Resource2()) {
    // Use resources
}  // Auto-closed in reverse order
```

### Pattern 5: Try-With-Resources + Catch
```java
try (BufferedReader br = new BufferedReader(new FileReader(path))) {
    return br.readLine();
} catch (IOException e) {
    log.error("Failed to read file", e);
    return null;
}
```

### Pattern 6: Exception Chaining
```java
try {
    lowLevelOperation();
} catch (LowLevelException e) {
    throw new HighLevelException("Operation failed", e);
}
```

### Pattern 7: Custom Exception
```java
public class ValidationException extends Exception {
    private String field;
    
    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
    
    public String getField() { return field; }
}
```

---

## 📋 Quick Decision Guide

| Scenario | Use This | Example |
|----------|----------|---------|
| Expected error (I/O, network) | try-catch | File not found |
| Programming error | RuntimeException | Null pointer |
| Resource management | try-with-resources | File, connection |
| Propagate to caller | throws | Method can't handle |
| Domain-specific error | Custom exception | InsufficientFundsException |
| Preserve context | Exception chaining | Wrap lower-level exception |
| Multiple exception types | Multi-catch | IOException \| SQLException |

---

## ⚡ Performance Tips

### Fast ✅
```java
// Check condition first
if (value != null) {
    return value.toString();
}
return "null";
```

### Slow ❌
```java
// Don't use exceptions for control flow
try {
    return value.toString();
} catch (NullPointerException e) {
    return "null";
}
```

### Optimization Tips
| Tip | Impact | Use Case |
|-----|--------|----------|
| Avoid exception for control flow | High | Use if-checks instead |
| Catch specific exceptions | Medium | Better performance, clarity |
| Reuse exception instances | Medium | Frequently thrown exceptions |
| Use try-with-resources | Low | Cleaner code, safe |
| Don't fill stack trace | High | Custom exceptions with no trace |

---

## 🎯 Best Practices Checklist

### Exception Handling DO ✅
```java
// ✅ Catch specific exceptions
try {
    Files.readString(path);
} catch (FileNotFoundException e) {
    log.error("File not found: " + path);
} catch (IOException e) {
    log.error("I/O error reading file", e);
}

// ✅ Use try-with-resources
try (Connection conn = getConnection()) {
    // Use connection
}

// ✅ Provide context in exception
throw new ValidationException("age", "Age must be positive: " + age);

// ✅ Document exceptions
/**
 * @throws IllegalArgumentException if amount is negative
 */
public void deposit(double amount) {
    if (amount < 0) {
        throw new IllegalArgumentException("Amount cannot be negative");
    }
}

// ✅ Clean exception messages
throw new Exception("Failed to process payment: insufficient funds");
```

### Exception Handling DON'T ❌
```java
// ❌ Don't catch and ignore
try {
    riskyOperation();
} catch (Exception e) {
    // Silent failure - BAD!
}

// ❌ Don't catch Throwable
try {
    code();
} catch (Throwable t) {  // Too broad!
    // Catches Error too
}

// ❌ Don't throw generic Exception
public void method() throws Exception {  // Be specific!
    // ...
}

// ❌ Don't return in finally
try {
    return value;
} finally {
    return otherValue;  // Overwrites try return - CONFUSING!
}

// ❌ Don't log and rethrow
try {
    operation();
} catch (Exception e) {
    log.error("Error", e);
    throw e;  // Logged twice in stack
}
```

---

## 🔥 Common Exceptions Quick Reference

### NullPointerException
```java
String str = null;
// ❌ Don't
str.length();  // NPE

// ✅ Do
if (str != null) {
    str.length();
}
// Or use Objects.requireNonNull()
Objects.requireNonNull(str, "String cannot be null");
```

### IllegalArgumentException
```java
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative: " + age);
    }
    this.age = age;
}
```

### IOException
```java
try {
    Files.readString(Paths.get("file.txt"));
} catch (IOException e) {
    log.error("Failed to read file", e);
}
```

### SQLException
```java
try (Connection conn = DriverManager.getConnection(url)) {
    // Use connection
} catch (SQLException e) {
    log.error("Database error", e);
}
```

### ClassNotFoundException
```java
try {
    Class.forName("com.mysql.jdbc.Driver");
} catch (ClassNotFoundException e) {
    log.error("Driver not found", e);
}
```

---

## 🎓 Interview Quick Answers

### Q: Checked vs Unchecked exceptions?
**A:** 
- **Checked:** Must be declared/caught (IOException, SQLException)
- **Unchecked:** Runtime exceptions (NullPointerException, IllegalArgumentException)

### Q: When to use custom exceptions?
**A:** When you need domain-specific error handling with additional context.

### Q: What is exception chaining?
**A:** Wrapping a lower-level exception in a higher-level exception to preserve context.

### Q: Try-with-resources vs finally?
**A:** Try-with-resources is cleaner, safer, and automatically handles resource closing.

### Q: Can finally block prevent exception propagation?
**A:** Yes, if it returns a value or throws a new exception.

---

## 📊 Syntax Comparison Table

| Feature | Syntax | Java Version |
|---------|--------|--------------|
| Basic try-catch | `try { } catch (E e) { }` | All |
| Multiple catch | `catch (E1 e) { } catch (E2 e) { }` | All |
| Multi-catch | `catch (E1 \| E2 e) { }` | Java 7+ |
| Finally | `try { } finally { }` | All |
| Try-with-resources | `try (R r = new R()) { }` | Java 7+ |
| Multiple resources | `try (R1 r1 = new R1(); R2 r2 = new R2()) { }` | Java 7+ |
| Effective final | `try (resource) { }` | Java 9+ |

---

## 🛠️ Code Snippets

### Validation Pattern
```java
public void validate(User user) throws ValidationException {
    if (user == null) {
        throw new ValidationException("User cannot be null");
    }
    if (user.getAge() < 0) {
        throw new ValidationException("Age cannot be negative");
    }
    if (user.getEmail() == null || !user.getEmail().contains("@")) {
        throw new ValidationException("Invalid email");
    }
}
```

### Retry Pattern
```java
public <T> T retry(Callable<T> operation, int maxAttempts) throws Exception {
    for (int i = 0; i < maxAttempts; i++) {
        try {
            return operation.call();
        } catch (Exception e) {
            if (i == maxAttempts - 1) throw e;
            Thread.sleep(1000 * (i + 1));
        }
    }
    throw new IllegalStateException("Should not reach here");
}
```

### Resource Management Pattern
```java
public class CustomResource implements AutoCloseable {
    private Connection connection;
    
    public CustomResource(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }
    
    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

// Usage
try (CustomResource resource = new CustomResource(url)) {
    // Use resource
}  // Auto-closed
```

---

## 📚 Further Reading

- [Oracle Java Exceptions Tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Effective Java - Exception Guidelines](https://www.oracle.com/java/technologies/effective-java.html)
- [Try-With-Resources Specification](https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.20.3)

---

**Quick Navigation:**
- [Full README](README.md)
- [Project Summary](PROJECT_SUMMARY.md)
- [Testing Guide](TESTING.md)
- [File Index](INDEX.md)

