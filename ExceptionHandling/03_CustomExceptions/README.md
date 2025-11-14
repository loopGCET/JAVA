# Custom Exceptions

## 📘 Overview

Custom exceptions are user-defined exception classes that extend either `Exception` (for checked exceptions) or `RuntimeException` (for unchecked exceptions). They allow you to create domain-specific error types that carry meaningful information about errors in your application.

**Purpose:** Create meaningful, domain-specific exceptions that clearly communicate what went wrong in your application context.

**When to Use:**
- ✅ When standard Java exceptions don't adequately describe your error
- ✅ To add business-specific context to exceptions
- ✅ To build exception hierarchies for your domain
- ✅ When you need to include custom fields or methods in exceptions

---

## 🔑 Key Concepts

### 1. Extending Exception vs RuntimeException
- **Extend Exception:** For checked exceptions (must be handled)
- **Extend RuntimeException:** For unchecked exceptions (optional handling)

### 2. Custom Constructors
- No-argument constructor
- Message constructor
- Message + cause constructor
- Custom field constructors

### 3. Exception Hierarchies
- Base application exception
- Specific exception types extending base
- Grouping related exceptions

### 4. Additional Fields
- Add context-specific information
- Validation details
- Error codes
- Timestamps

---

## 📝 Syntax

### Basic Custom Exception
```java
public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
```

### With Constructors
```java
public class DataValidationException extends Exception {
    // No-arg constructor
    public DataValidationException() {
        super();
    }
    
    // Message constructor
    public DataValidationException(String message) {
        super(message);
    }
    
    // Message + cause constructor
    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### With Custom Fields
```java
public class InsufficientFundsException extends Exception {
    private double requestedAmount;
    private double availableBalance;
    
    public InsufficientFundsException(double requested, double available) {
        super("Insufficient funds: requested " + requested 
              + ", available " + available);
        this.requestedAmount = requested;
        this.availableBalance = available;
    }
    
    public double getRequestedAmount() { return requestedAmount; }
    public double getAvailableBalance() { return availableBalance; }
}
```

---

## 💻 Code Examples

### Example 1: Simple Custom Exception
```java
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userId) {
        super("User not found: " + userId);
    }
}

// Usage
public User findUser(String id) throws UserNotFoundException {
    if (user == null) {
        throw new UserNotFoundException(id);
    }
    return user;
}
```

### Example 2: With Additional Context
```java
public class ValidationException extends Exception {
    private String fieldName;
    private Object invalidValue;
    
    public ValidationException(String field, Object value, String reason) {
        super(String.format("Validation failed for field '%s' with value '%s': %s",
                           field, value, reason));
        this.fieldName = field;
        this.invalidValue = value;
    }
    
    public String getFieldName() { return fieldName; }
    public Object getInvalidValue() { return invalidValue; }
}
```

### Example 3: Exception Hierarchy
```java
// Base exception
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}

// Specific exceptions
public class DatabaseException extends ApplicationException {
    public DatabaseException(String message) {
        super("Database error: " + message);
    }
}

public class ServiceException extends ApplicationException {
    public ServiceException(String message) {
        super("Service error: " + message);
    }
}
```

---

## ✅ Best Practices

### DO ✅

1. **Provide Multiple Constructors**
   ```java
   public class CustomException extends Exception {
       public CustomException() { super(); }
       public CustomException(String message) { super(message); }
       public CustomException(String message, Throwable cause) {
           super(message, cause);
       }
       public CustomException(Throwable cause) { super(cause); }
   }
   ```

2. **Name Exceptions Clearly**
   ```java
   // ✅ Good - clear and specific
   public class InvalidEmailFormatException extends Exception { }
   public class AccountLockedException extends Exception { }
   
   // ❌ Bad - vague
   public class MyException extends Exception { }
   public class ErrorException extends Exception { }
   ```

3. **Add Meaningful Context**
   ```java
   public class OrderProcessingException extends Exception {
       private String orderId;
       private String customerId;
       
       public OrderProcessingException(String orderId, String customerId, String reason) {
           super("Order " + orderId + " for customer " + customerId + ": " + reason);
           this.orderId = orderId;
           this.customerId = customerId;
       }
   }
   ```

---

## 🎓 Interview Questions

### Q1: When should you create a custom exception?
**Answer:** Create custom exceptions when:
- Standard exceptions don't convey the specific error in your domain
- You need to add additional context or fields
- You want to group related errors in an exception hierarchy
- You need to handle specific business rule violations differently

### Q2: Should custom exceptions extend Exception or RuntimeException?
**Answer:**
- Extend `Exception` for checked exceptions when the caller should handle the error
- Extend `RuntimeException` for unchecked exceptions for programming errors
- Use checked for business logic errors, unchecked for programming errors

### Q3: What constructors should a custom exception have?
**Answer:** Typically provide four constructors:
1. No-argument constructor
2. Message constructor
3. Message + cause constructor
4. Cause constructor

This aligns with standard Exception constructors.

---

## 📚 Further Reading

- [Creating Exception Classes](https://docs.oracle.com/javase/tutorial/essential/exceptions/creating.html)
- [Exception Class Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html)
- "Effective Java" by Joshua Bloch - Item 72-75

---

## 📝 Code Example

See **[CustomExceptionExample.java](CustomExceptionExample.java)** for comprehensive examples.

---

**Related:** [Module README](../README.md) | [Previous: Throws](../02_ThrowsKeyword/) | [Next: Exception Chaining](../04_ExceptionChaining/)

