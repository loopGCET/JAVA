# Try-With-Resources

## 📘 Overview

Try-with-resources is a Java 7+ feature that automatically manages resources (objects that must be closed after use). It ensures that each resource is closed at the end of the statement, even if exceptions occur.

**Purpose:** Automatic resource management with guaranteed cleanup, eliminating the need for explicit finally blocks.

**When to Use:**
- ✅ Working with files, streams, connections
- ✅ Any resource implementing AutoCloseable
- ✅ When you need guaranteed resource cleanup
- ✅ To write cleaner, safer resource management code

---

## 🔑 Key Concepts

### 1. AutoCloseable Interface
- Resources must implement `AutoCloseable` or `Closeable`
- `close()` method called automatically
- Happens even if exception occurs

### 2. Syntax (Java 7+)
```java
try (ResourceType resource = new ResourceType()) {
    // Use resource
}  // Automatically closed
```

### 3. Multiple Resources
- Separate with semicolons
- Closed in reverse order of creation

### 4. Suppressed Exceptions
- If both try and close() throw exceptions
- Close exception is suppressed
- Accessible via `getSuppressed()`

---

## 📝 Syntax

### Basic Try-With-Resources
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
}  // br.close() called automatically
```

### Multiple Resources
```java
try (FileInputStream in = new FileInputStream("input.txt");
     FileOutputStream out = new FileOutputStream("output.txt")) {
    // Use both resources
}  // Both closed automatically in reverse order
```

### With Catch and Finally
```java
try (Resource r = new Resource()) {
    // Use resource
} catch (IOException e) {
    // Handle exception
} finally {
    // Additional cleanup
}
```

### Effective Final Variables (Java 9+)
```java
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
try (br) {  // No need to redeclare
    // Use br
}
```

---

## 💻 Code Examples

### Example 1: File Reading
```java
// Old way (before Java 7)
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("file.txt"));
    System.out.println(br.readLine());
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (IOException e) {
            // Handle close exception
        }
    }
}

// New way (Java 7+)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
}  // Automatically closed, even if exception occurs
```

### Example 2: Custom AutoCloseable
```java
class DatabaseConnection implements AutoCloseable {
    public DatabaseConnection() {
        System.out.println("Opening connection");
    }
    
    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
    
    @Override
    public void close() {
        System.out.println("Closing connection");
    }
}

// Usage
try (DatabaseConnection conn = new DatabaseConnection()) {
    conn.query("SELECT * FROM users");
}  // close() called automatically
```

---

## ✅ Best Practices

### DO ✅

1. **Use for All AutoCloseable Resources**
   ```java
   // ✅ Good
   try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {
       // Use resources
   }
   ```

2. **Order Resources Correctly**
   ```java
   // ✅ Good - dependent resources declared in correct order
   try (FileInputStream fis = new FileInputStream("file");
        BufferedInputStream bis = new BufferedInputStream(fis)) {
       // Use streams
   }
   ```

### DON'T ❌

1. **Don't Use Finally for Resource Cleanup**
   ```java
   // ❌ Old way - unnecessary with try-with-resources
   Resource r = new Resource();
   try {
       r.use();
   } finally {
       r.close();
   }
   
   // ✅ Better
   try (Resource r = new Resource()) {
       r.use();
   }
   ```

---

## 🎓 Interview Questions

### Q1: What is try-with-resources?
**Answer:** A Java 7+ feature that automatically closes resources implementing AutoCloseable/Closeable at the end of try block, even if exceptions occur.

### Q2: What interface must a class implement for try-with-resources?
**Answer:** `AutoCloseable` (or its subinterface `Closeable`). The class must implement the `close()` method.

### Q3: What are suppressed exceptions?
**Answer:** When both try block and close() throw exceptions, the close exception is suppressed and accessible via `getSuppressed()` method.

---

## 📚 Further Reading

- [Try-With-Resources Statement](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)
- [AutoCloseable Interface](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html)

---

## 📝 Code Example

See **[TryWithResourcesExample.java](TryWithResourcesExample.java)** for comprehensive examples.

---

**Related:** [Module README](../README.md) | [Previous: Exception Chaining](../04_ExceptionChaining/)

