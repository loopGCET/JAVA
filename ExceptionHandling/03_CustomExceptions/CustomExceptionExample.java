import java.time.*;
import java.util.*;

/**
 * Comprehensive demonstration of custom exceptions in Java.
 *
 * @author GitHub Copilot
 * @topic Exception Handling - Custom Exceptions
 */
public class CustomExceptionExample {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  CUSTOM EXCEPTIONS COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        example1_BasicCustomException();
        System.out.println("─────────────────────────────────────────────────────\n");

        example2_ExceptionWithFields();
        System.out.println("─────────────────────────────────────────────────────\n");

        example3_ExceptionHierarchy();
        System.out.println("─────────────────────────────────────────────────────\n");

        example4_ValidationExceptions();
        System.out.println("─────────────────────────────────────────────────────\n");

        example5_CheckedVsUnchecked();
        System.out.println("─────────────────────────────────────────────────────\n");

        example6_RealWorldBankingSystem();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    /**
     * Example 1: Basic Custom Exception
     * Demonstrates: Creating simple custom exception
     */
    public static void example1_BasicCustomException() {
        System.out.println("--- Example 1: Basic Custom Exception ---");

        UserService userService = new UserService();

        try {
            User user = userService.findUser("user123");
            System.out.println("Found user: " + user);
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Example 2: Custom Exception with Additional Fields
     * Demonstrates: Adding context-specific fields
     */
    public static void example2_ExceptionWithFields() {
        System.out.println("--- Example 2: Exception with Additional Fields ---");

        BankAccount account = new BankAccount("ACC001", 1000.0);

        try {
            account.withdraw(1500.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Requested: $" + e.getRequestedAmount());
            System.out.println("Available: $" + e.getAvailableBalance());
            System.out.println("Shortfall: $" + (e.getRequestedAmount() - e.getAvailableBalance()));
        }

        System.out.println();
    }

    /**
     * Example 3: Exception Hierarchy
     * Demonstrates: Building exception hierarchies
     */
    public static void example3_ExceptionHierarchy() {
        System.out.println("--- Example 3: Exception Hierarchy ---");

        try {
            simulateDatabaseError();
        } catch (DatabaseConnectionException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            System.out.println("Application error: " + e.getMessage());
        }

        System.out.println();

        try {
            simulateServiceError();
        } catch (ApplicationException e) {
            System.out.println("Caught ApplicationException: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Example 4: Validation Exceptions
     * Demonstrates: Using custom exceptions for validation
     */
    public static void example4_ValidationExceptions() {
        System.out.println("--- Example 4: Validation Exceptions ---");

        // Test valid data
        try {
            validateUser("john@example.com", "password123", 25);
            System.out.println("✅ User validation passed");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        // Test invalid email
        try {
            validateUser("invalid-email", "password123", 25);
        } catch (ValidationException e) {
            System.out.println("\n❌ Validation failed:");
            System.out.println("  Field: " + e.getFieldName());
            System.out.println("  Value: " + e.getInvalidValue());
            System.out.println("  Reason: " + e.getMessage());
        }

        // Test invalid age
        try {
            validateUser("john@example.com", "password123", -5);
        } catch (ValidationException e) {
            System.out.println("\n❌ Validation failed:");
            System.out.println("  Field: " + e.getFieldName());
            System.out.println("  Value: " + e.getInvalidValue());
            System.out.println("  Reason: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Example 5: Checked vs Unchecked Custom Exceptions
     * Demonstrates: When to use each type
     */
    public static void example5_CheckedVsUnchecked() {
        System.out.println("--- Example 5: Checked vs Unchecked ---");

        // Checked exception - must handle
        System.out.println("Checked exception (business rule):");
        try {
            processPayment(150.0, 100.0);
        } catch (PaymentProcessingException e) {
            System.out.println("  Payment failed: " + e.getMessage());
        }

        // Unchecked exception - indicates programming error
        System.out.println("\nUnchecked exception (programming error):");
        try {
            configureSystem(null);
        } catch (InvalidConfigurationException e) {
            System.out.println("  Configuration error: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Example 6: Real-World Banking System
     * Demonstrates: Complete exception handling in business context
     */
    public static void example6_RealWorldBankingSystem() {
        System.out.println("--- Example 6: Real-World Banking System ---");

        BankingSystem bank = new BankingSystem();

        // Scenario 1: Successful transfer
        try {
            System.out.println("Scenario 1: Valid transfer");
            bank.transfer("ACC001", "ACC002", 100.0);
            System.out.println("  ✅ Transfer successful\n");
        } catch (BankingException e) {
            handleBankingException(e);
        }

        // Scenario 2: Insufficient funds
        try {
            System.out.println("Scenario 2: Insufficient funds");
            bank.transfer("ACC003", "ACC002", 5000.0);
        } catch (BankingException e) {
            handleBankingException(e);
        }

        // Scenario 3: Invalid account
        try {
            System.out.println("\nScenario 3: Invalid account");
            bank.transfer("INVALID", "ACC002", 100.0);
        } catch (BankingException e) {
            handleBankingException(e);
        }

        System.out.println();
    }

    // Helper methods
    private static void simulateDatabaseError() throws DatabaseException {
        throw new DatabaseConnectionException("Failed to connect to database");
    }

    private static void simulateServiceError() throws ApplicationException {
        throw new ServiceException("External service unavailable");
    }

    private static void validateUser(String email, String password, int age)
            throws ValidationException {
        if (!email.contains("@")) {
            throw new ValidationException("email", email, "Invalid email format");
        }
        if (age < 0 || age > 150) {
            throw new ValidationException("age", age, "Age must be between 0 and 150");
        }
    }

    private static void processPayment(double amount, double available)
            throws PaymentProcessingException {
        if (amount > available) {
            throw new PaymentProcessingException("Insufficient funds for payment");
        }
    }

    private static void configureSystem(String config) {
        if (config == null) {
            throw new InvalidConfigurationException("Configuration cannot be null");
        }
    }

    private static void handleBankingException(BankingException e) {
        System.out.println("  ❌ Banking error: " + e.getClass().getSimpleName());
        System.out.println("  Message: " + e.getMessage());
        if (e instanceof InsufficientFundsException) {
            InsufficientFundsException ife = (InsufficientFundsException) e;
            System.out.println("  Requested: $" + ife.getRequestedAmount());
            System.out.println("  Available: $" + ife.getAvailableBalance());
        }
    }
}

// ==================== Custom Exception Classes ====================

/**
 * Basic custom exception
 */
class UserNotFoundException extends Exception {
    public UserNotFoundException(String userId) {
        super("User not found: " + userId);
    }
}

/**
 * Custom exception with additional fields
 */
class InsufficientFundsException extends BankingException {
    private double requestedAmount;
    private double availableBalance;

    public InsufficientFundsException(double requested, double available) {
        super(String.format("Insufficient funds: requested $%.2f, available $%.2f",
                           requested, available));
        this.requestedAmount = requested;
        this.availableBalance = available;
    }

    public double getRequestedAmount() { return requestedAmount; }
    public double getAvailableBalance() { return availableBalance; }
}

/**
 * Base application exception
 */
class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Database exception hierarchy
 */
class DatabaseException extends ApplicationException {
    public DatabaseException(String message) {
        super("Database error: " + message);
    }
}

class DatabaseConnectionException extends DatabaseException {
    public DatabaseConnectionException(String message) {
        super("Connection failed - " + message);
    }
}

class ServiceException extends ApplicationException {
    public ServiceException(String message) {
        super("Service error: " + message);
    }
}

/**
 * Validation exception with field information
 */
class ValidationException extends Exception {
    private String fieldName;
    private Object invalidValue;

    public ValidationException(String field, Object value, String reason) {
        super(reason);
        this.fieldName = field;
        this.invalidValue = value;
    }

    public String getFieldName() { return fieldName; }
    public Object getInvalidValue() { return invalidValue; }
}

/**
 * Checked custom exception for business rules
 */
class PaymentProcessingException extends Exception {
    public PaymentProcessingException(String message) {
        super(message);
    }
}

/**
 * Unchecked custom exception for programming errors
 */
class InvalidConfigurationException extends RuntimeException {
    public InvalidConfigurationException(String message) {
        super(message);
    }
}

/**
 * Banking system exceptions
 */
class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

class InvalidAccountException extends BankingException {
    public InvalidAccountException(String accountId) {
        super("Invalid account: " + accountId);
    }
}

// ==================== Supporting Classes ====================

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "'}";
    }
}

class UserService {
    public User findUser(String userId) throws UserNotFoundException {
        // Simulate user not found
        throw new UserNotFoundException(userId);
    }
}

class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }

    public double getBalance() { return balance; }
}

class BankingSystem {
    private Map<String, Double> accounts = new HashMap<>();

    public BankingSystem() {
        accounts.put("ACC001", 1000.0);
        accounts.put("ACC002", 500.0);
        accounts.put("ACC003", 250.0);
    }

    public void transfer(String fromAccount, String toAccount, double amount)
            throws BankingException {

        if (!accounts.containsKey(fromAccount)) {
            throw new InvalidAccountException(fromAccount);
        }
        if (!accounts.containsKey(toAccount)) {
            throw new InvalidAccountException(toAccount);
        }

        double fromBalance = accounts.get(fromAccount);
        if (amount > fromBalance) {
            throw new InsufficientFundsException(amount, fromBalance);
        }

        accounts.put(fromAccount, fromBalance - amount);
        accounts.put(toAccount, accounts.get(toAccount) + amount);
    }
}

