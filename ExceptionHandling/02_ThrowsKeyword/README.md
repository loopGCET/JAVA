# Throws Keyword

## 📘 Overview

The `throws` keyword is used in method signatures to declare that a method might throw one or more exceptions. It's a way to propagate exception handling responsibility to the caller of the method.

**Purpose:** Declare exceptions that a method might throw, allowing caller to decide how to handle them.

**When to Use:**
- ✅ When your method can't handle an exception meaningfully
- ✅ When you want the caller to decide how to handle the error
- ✅ For checked exceptions that must be declared
- ✅ When building reusable library methods

---

## 🔑 Key Concepts

### 1. Checked vs Unchecked Exceptions
- **Checked Exceptions:** Must be declared with throws or caught (IOException, SQLException)
- **Unchecked Exceptions:** Don't need to be declared (RuntimeException and subclasses)

### 2. Exception Propagation
- Exception travels up the call stack
- Each method can catch or further propagate
- If no method catches it, program terminates

### 3. Multiple Exceptions
- Can declare multiple exception types
- Separate with commas
- Can declare superclass to cover multiple subclasses

### 4. Method Overriding Rules
- Overriding method can't throw broader checked exceptions
- Can throw fewer or more specific exceptions
- Can throw any unchecked exceptions

---

## 📝 Syntax

### Basic Throws Declaration
```java
public void readFile(String path) throws IOException {
    // Code that might throw IOException
    FileReader reader = new FileReader(path);
}
```

### Multiple Exceptions
```java
public void processData(String file) throws IOException, SQLException {
    // Code that might throw either exception
}
```

### In Method Signature
```java
public ReturnType methodName(Parameters) throws ExceptionType1, ExceptionType2 {
    // Method body
}
```

### Calling Method with Throws
```java
// Option 1: Catch the exception
try {
    readFile("data.txt");
} catch (IOException e) {
    // Handle exception
}

// Option 2: Propagate further
public void processDocument() throws IOException {
    readFile("data.txt");  // Exception propagates up
}
```

---

## 🔍 Important Concepts

| Concept | Description | Example |
|---------|-------------|---------|
| **Checked Exception** | Must be declared or caught | IOException, SQLException |
| **Unchecked Exception** | Optional to declare | RuntimeException, NullPointerException |
| **Propagation** | Exception moves up call stack | Method A → Method B → Caller |
| **Declaration** | Using throws keyword | `throws IOException` |
| **Multiple throws** | Declaring several exceptions | `throws IOException, SQLException` |

---

## 💻 Code Examples

### Example 1: Basic Throws Usage
```java
public void readFile(String filename) throws FileNotFoundException {
    FileReader reader = new FileReader(filename);
    // If file doesn't exist, FileNotFoundException is thrown
}

// Caller must handle it
public void processFile() {
    try {
        readFile("data.txt");
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    }
}
```

### Example 2: Exception Propagation
```java
// Level 1: Throws exception
public void databaseQuery() throws SQLException {
    // Database code
}

// Level 2: Propagates exception
public void processData() throws SQLException {
    databaseQuery();  // Don't catch, let it propagate
}

// Level 3: Final handler
public void mainMethod() {
    try {
        processData();
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
    }
}
```

### Example 3: Multiple Exceptions
```java
public void transferData(String sourceFile, String dbConnection) 
        throws IOException, SQLException {
    // Read file - might throw IOException
    BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
    
    // Write to database - might throw SQLException
    Connection conn = DriverManager.getConnection(dbConnection);
}
```

### Example 4: Method Overriding
```java
class Parent {
    public void method() throws IOException {
        // Parent method
    }
}

class Child extends Parent {
    // ✅ Valid: Throws more specific exception
    @Override
    public void method() throws FileNotFoundException {
        // FileNotFoundException is subclass of IOException
    }
    
    // ✅ Valid: Throws no exception
    @Override
    public void method() {
        // Don't throw anything
    }
    
    // ❌ Invalid: Can't throw broader exception
    // @Override
    // public void method() throws Exception {
    //     // Won't compile - Exception is broader than IOException
    // }
}
```

---

## ⚡ Performance Considerations

| Aspect | Impact | Notes |
|--------|--------|-------|
| **Declaration** | No overhead | Just metadata |
| **Propagation** | Minimal | Only if exception actually thrown |
| **Stack unwinding** | Moderate | When exception propagates up |

---

## ✅ Best Practices

### DO ✅

1. **Declare Specific Exceptions**
   ```java
   // ✅ Good - specific
   public void readFile() throws FileNotFoundException {
       // ...
   }
   
   // ❌ Bad - too general
   public void readFile() throws Exception {
       // ...
   }
   ```

2. **Document Exceptions with @throws**
   ```java
   /**
    * Reads user data from file
    * @param filename the file to read
    * @throws FileNotFoundException if file doesn't exist
    * @throws IOException if error reading file
    */
   public void readData(String filename) throws IOException {
       // ...
   }
   ```

3. **Use Throws When Caller Should Decide**
   ```java
   // ✅ Good - let caller decide what to do with missing file
   public String loadConfig(String path) throws IOException {
       return Files.readString(Paths.get(path));
   }
   ```

4. **Propagate When You Can't Handle**
   ```java
   // ✅ Good - this layer can't meaningfully handle DB errors
   public User getUser(int id) throws SQLException {
       return database.findUser(id);
   }
   ```

### DON'T ❌

1. **Don't Declare RuntimeExceptions**
   ```java
   // ❌ Bad - unnecessary
   public int divide(int a, int b) throws ArithmeticException {
       return a / b;
   }
   
   // ✅ Good - don't declare unchecked exceptions
   public int divide(int a, int b) {
       return a / b;
   }
   ```

2. **Don't Throw Exception or Throwable**
   ```java
   // ❌ Bad - too general
   public void method() throws Exception {
       // ...
   }
   
   // ✅ Good - specific
   public void method() throws IOException, SQLException {
       // ...
   }
   ```

3. **Don't Use Throws to Avoid Handling**
   ```java
   // ❌ Bad - passing the buck unnecessarily
   public void simpleValidation() throws Exception {
       if (value < 0) {
           throw new Exception("Invalid");
       }
   }
   
   // ✅ Good - handle simple validation here
   public void simpleValidation() {
       if (value < 0) {
           throw new IllegalArgumentException("Invalid");
       }
   }
   ```

---

## ⚠️ Common Pitfalls

### 1. Catching and Rethrowing Without Purpose
```java
// ❌ Bad - pointless catch
public void method() throws IOException {
    try {
        readFile();
    } catch (IOException e) {
        throw e;  // Why catch if you just rethrow?
    }
}

// ✅ Good - just let it propagate
public void method() throws IOException {
    readFile();
}
```

### 2. Declaring Broad Exception Types
```java
// ❌ Bad - too broad
public void method() throws Exception {
    Files.readString(path);
}

// ✅ Good - specific
public void method() throws IOException {
    Files.readString(path);
}
```

### 3. Forgetting to Handle in Main
```java
// ❌ Bad - exception will terminate program
public static void main(String[] args) throws IOException {
    // If exception occurs, program crashes
}

// ✅ Good - handle in main
public static void main(String[] args) {
    try {
        // Your code
    } catch (IOException e) {
        System.err.println("Error: " + e.getMessage());
        System.exit(1);
    }
}
```

---

## 🔄 Comparison: Throws vs Try-Catch

| Aspect | Throws | Try-Catch |
|--------|--------|-----------|
| **Location** | Method signature | Inside method body |
| **Purpose** | Declare potential exceptions | Handle exceptions |
| **Responsibility** | Passes to caller | Handles locally |
| **Use when** | Can't handle meaningfully | Can recover from error |
| **Syntax** | `throws ExceptionType` | `try { } catch { }` |

---

## 🎓 Interview Questions

### Q1: What is the difference between throw and throws?
**Answer:**
- `throw` - Used to actually throw an exception object (inside method body)
- `throws` - Used to declare that method might throw exception (in method signature)

```java
public void method() throws IOException {  // declares
    if (error) {
        throw new IOException("Error");    // throws
    }
}
```

### Q2: Can we use throws for unchecked exceptions?
**Answer:** Yes, but it's not required. Unchecked exceptions (RuntimeException and subclasses) don't need to be declared, but you can declare them for documentation purposes.

### Q3: What happens if a method declares throws Exception but never actually throws?
**Answer:** It's legal but considered bad practice. The caller must handle the exception even though it never occurs, leading to unnecessary code.

### Q4: Can main() method throw exceptions?
**Answer:** Yes, main() can declare throws, but it will cause the program to terminate with a stack trace if exception occurs. Better to catch exceptions in main().

### Q5: What are the rules for throws in method overriding?
**Answer:**
- Overriding method can throw same, fewer, or more specific checked exceptions
- Cannot throw broader checked exceptions
- Can throw any unchecked exceptions
- Can choose not to throw any exceptions

---

## 📚 Further Reading

### Official Documentation
- [Java Exceptions Tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Specifying Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/declaring.html)
- [Checked vs Unchecked Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)

### Related Topics
- [Try-Catch-Finally](../01_TryCatchFinally/) - Handling exceptions
- [Custom Exceptions](../03_CustomExceptions/) - Creating your own
- [Exception Chaining](../04_ExceptionChaining/) - Preserving context

---

## 💡 Tips

- 💡 Use throws when the caller should decide how to handle
- 💡 Always document with @throws in Javadoc
- 💡 Declare specific exception types, not Exception
- 💡 Don't declare unchecked exceptions unless for documentation
- 💡 Consider if you can handle exception locally before using throws
- 💡 Remember overriding rules when extending classes
- 💡 Throws doesn't mean exception will be thrown, just might be

---

## 📝 Code Example

For a comprehensive, runnable example with 7+ demonstration methods, see:  
**[ThrowsExample.java](ThrowsExample.java)**

The example covers:
- Basic throws declaration
- Exception propagation through call stack
- Multiple exception types
- Checked vs unchecked exceptions
- Method overriding with throws
- Real-world use cases
- Best practices and anti-patterns

---

**Related:** [Module README](../README.md) | [Quick Reference](../QUICK_REFERENCE.md) | [Previous: Try-Catch](../01_TryCatchFinally/) | [Next: Custom Exceptions](../03_CustomExceptions/)

