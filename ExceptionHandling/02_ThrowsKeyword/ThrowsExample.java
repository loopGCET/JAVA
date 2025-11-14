import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

/**
 * Comprehensive demonstration of the throws keyword in Java.
 *
 * This example covers:
 * - Basic throws declaration
 * - Exception propagation through call stack
 * - Multiple exception types with throws
 * - Checked vs unchecked exceptions
 * - Method overriding with throws
 * - Real-world API design patterns
 * - Best practices and common patterns
 *
 * @author GitHub Copilot
 * @topic Exception Handling - Throws Keyword
 */
public class ThrowsExample {

    /**
     * Example 1: Basic Throws Declaration
     * Demonstrates: Declaring exceptions in method signature
     * Use case: File operations that might fail
     */
    public static void example1_BasicThrows() {
        System.out.println("--- Example 1: Basic Throws Declaration ---");

        // Calling method that throws exception - must handle
        try {
            readFileContent("test.txt");
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Method that declares it might throw IOException
     * Caller must handle or propagate this exception
     */
    private static String readFileContent(String filename) throws IOException {
        System.out.println("Attempting to read: " + filename);

        // This might throw IOException - we declare it, don't catch it
        return Files.readString(Paths.get(filename));
    }

    /**
     * Example 2: Exception Propagation Chain
     * Demonstrates: How exceptions propagate through multiple method calls
     * Shows: Three levels of method calls
     */
    public static void example2_ExceptionPropagation() {
        System.out.println("--- Example 2: Exception Propagation Chain ---");

        try {
            System.out.println("Starting chain of method calls...");
            level1_Entry();

        } catch (IOException e) {
            System.out.println("\nException caught at top level!");
            System.out.println("Exception type: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());

            // Show call stack
            System.out.println("\nCall stack:");
            StackTraceElement[] stack = e.getStackTrace();
            for (int i = 0; i < Math.min(5, stack.length); i++) {
                System.out.println("  at " + stack[i].getMethodName() + "()");
            }
        }

        System.out.println();
    }

    /**
     * Level 1: Entry point - propagates exception
     */
    private static void level1_Entry() throws IOException {
        System.out.println("  Level 1: Calling level 2...");
        level2_Middle();
    }

    /**
     * Level 2: Middle layer - propagates exception
     */
    private static void level2_Middle() throws IOException {
        System.out.println("  Level 2: Calling level 3...");
        level3_DeepLevel();
    }

    /**
     * Level 3: Deepest level - throws exception
     */
    private static void level3_DeepLevel() throws IOException {
        System.out.println("  Level 3: Throwing exception...");
        throw new IOException("Error at deepest level");
    }

    /**
     * Example 3: Multiple Exception Types
     * Demonstrates: Declaring multiple different exception types
     * Use case: Methods that perform multiple risky operations
     */
    public static void example3_MultipleExceptions() {
        System.out.println("--- Example 3: Multiple Exception Types ---");

        // Test with different scenarios
        System.out.println("Test 1: File operation");
        try {
            processDataFromFile("data.txt", "jdbc:mysql://localhost/db");
        } catch (IOException e) {
            System.out.println("  Caught IOException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("  Caught SQLException: " + e.getMessage());
        }

        System.out.println("\nTest 2: Database operation");
        try {
            processDataFromFile("validfile.txt", "invalid:connection");
        } catch (IOException e) {
            System.out.println("  Caught IOException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("  Caught SQLException: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Method that declares multiple exception types
     * Can throw either IOException or SQLException
     */
    private static void processDataFromFile(String filename, String dbUrl)
            throws IOException, SQLException {

        // Operation 1: File I/O - might throw IOException
        if (!filename.equals("validfile.txt")) {
            throw new IOException("File not found: " + filename);
        }

        // Operation 2: Database - might throw SQLException
        if (!dbUrl.startsWith("jdbc:mysql")) {
            throw new SQLException("Invalid database URL");
        }

        System.out.println("  Successfully processed data");
    }

    /**
     * Example 4: Checked vs Unchecked Exceptions
     * Demonstrates: Difference in handling requirements
     * Shows: Checked must be declared, unchecked are optional
     */
    public static void example4_CheckedVsUnchecked() {
        System.out.println("--- Example 4: Checked vs Unchecked Exceptions ---");

        // Checked exception: Must handle or declare
        System.out.println("Calling method with checked exception:");
        try {
            methodWithCheckedException();
        } catch (IOException e) {
            System.out.println("  Handled: " + e.getMessage());
        }

        // Unchecked exception: No requirement to handle
        System.out.println("\nCalling method with unchecked exception:");
        try {
            methodWithUncheckedException();
        } catch (RuntimeException e) {
            System.out.println("  Caught (optional): " + e.getMessage());
        }

        // Unchecked can also be declared (for documentation)
        System.out.println("\nCalling method that declares unchecked:");
        try {
            methodThatDeclaresUnchecked(0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Checked exception: MUST be declared or caught
     */
    private static void methodWithCheckedException() throws IOException {
        throw new IOException("This is a checked exception");
    }

    /**
     * Unchecked exception: No requirement to declare
     */
    private static void methodWithUncheckedException() {
        throw new RuntimeException("This is an unchecked exception");
    }

    /**
     * Unchecked exception declared for documentation
     * This is optional but can be good practice
     */
    private static void methodThatDeclaresUnchecked(int value)
            throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive");
        }
    }

    /**
     * Example 5: Method Overriding with Throws
     * Demonstrates: Rules for throws in inheritance
     * Shows: What's allowed and what's not
     */
    public static void example5_MethodOverriding() {
        System.out.println("--- Example 5: Method Overriding with Throws ---");

        // Using parent reference
        FileProcessor processor1 = new BasicFileProcessor();
        System.out.println("Using BasicFileProcessor:");
        try {
            processor1.processFile("test.txt");
        } catch (IOException e) {
            System.out.println("  Handled: " + e.getMessage());
        }

        // Using child that throws more specific exception
        FileProcessor processor2 = new AdvancedFileProcessor();
        System.out.println("\nUsing AdvancedFileProcessor:");
        try {
            processor2.processFile("test.txt");
        } catch (IOException e) {
            System.out.println("  Handled: " + e.getClass().getSimpleName()
                             + " - " + e.getMessage());
        }

        // Using child that doesn't throw
        FileProcessor processor3 = new SafeFileProcessor();
        System.out.println("\nUsing SafeFileProcessor:");
        try {
            processor3.processFile("test.txt");
        } catch (IOException e) {
            System.out.println("  This won't be reached");
        }

        System.out.println();
    }

    /**
     * Example 6: Real-World API Design
     * Demonstrates: Practical use of throws in API design
     * Use case: Building a data access layer
     */
    public static void example6_RealWorldAPI() {
        System.out.println("--- Example 6: Real-World API Design ---");

        UserRepository repo = new UserRepository();

        // Example 1: Finding user
        try {
            System.out.println("Finding user by ID:");
            User user = repo.findById(123);
            System.out.println("  Found: " + user);
        } catch (DataAccessException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Example 2: Saving user
        try {
            System.out.println("\nSaving new user:");
            User newUser = new User(999, "John Doe", "john@example.com");
            repo.save(newUser);
            System.out.println("  User saved successfully");
        } catch (ValidationException e) {
            System.out.println("  Validation Error: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Example 3: Invalid data
        try {
            System.out.println("\nSaving invalid user:");
            User invalidUser = new User(0, "", "");
            repo.save(invalidUser);
        } catch (ValidationException e) {
            System.out.println("  Validation Error: " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("  Data Access Error: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Example 7: Best Practices and Anti-Patterns
     * Demonstrates: Good and bad uses of throws
     * Educational: Shows what to do and what to avoid
     */
    public static void example7_BestPractices() {
        System.out.println("--- Example 7: Best Practices ---");

        // ✅ Good Practice 1: Specific exceptions
        System.out.println("Good Practice 1: Specific exceptions");
        try {
            goodMethod_SpecificExceptions("test.txt");
        } catch (FileNotFoundException e) {
            System.out.println("  ✅ Can handle file not found specifically");
        } catch (IOException e) {
            System.out.println("  ✅ Can handle other I/O errors");
        }

        // ❌ Anti-Pattern 1: Too broad
        System.out.println("\nAnti-Pattern 1: Generic Exception (avoid)");
        try {
            badMethod_TooGeneric("test.txt");
        } catch (Exception e) {
            System.out.println("  ❌ Can't tell what went wrong specifically");
        }

        // ✅ Good Practice 2: Document with Javadoc
        System.out.println("\nGood Practice 2: Well-documented method");
        try {
            wellDocumentedMethod(10, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ Exception was documented: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * ✅ Good: Declares specific exceptions
     */
    private static void goodMethod_SpecificExceptions(String filename)
            throws FileNotFoundException, IOException {
        throw new FileNotFoundException("File not found: " + filename);
    }

    /**
     * ❌ Bad: Declares generic Exception
     */
    private static void badMethod_TooGeneric(String filename) throws Exception {
        throw new Exception("Something went wrong");
    }

    /**
     * ✅ Good: Well documented with @throws tags
     *
     * @param dividend the number to divide
     * @param divisor the number to divide by
     * @return the result of division
     * @throws IllegalArgumentException if divisor is zero
     */
    private static int wellDocumentedMethod(int dividend, int divisor)
            throws IllegalArgumentException {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return dividend / divisor;
    }

    /**
     * Main method - runs all examples
     */
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  THROWS KEYWORD COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        example1_BasicThrows();
        System.out.println("─────────────────────────────────────────────────────\n");

        example2_ExceptionPropagation();
        System.out.println("─────────────────────────────────────────────────────\n");

        example3_MultipleExceptions();
        System.out.println("─────────────────────────────────────────────────────\n");

        example4_CheckedVsUnchecked();
        System.out.println("─────────────────────────────────────────────────────\n");

        example5_MethodOverriding();
        System.out.println("─────────────────────────────────────────────────────\n");

        example6_RealWorldAPI();
        System.out.println("─────────────────────────────────────────────────────\n");

        example7_BestPractices();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}

// ==================== Supporting Classes ====================

/**
 * Abstract parent class for demonstrating method overriding
 */
abstract class FileProcessor {
    /**
     * Process a file - may throw IOException
     */
    public abstract void processFile(String filename) throws IOException;
}

/**
 * Implementation that throws IOException
 */
class BasicFileProcessor extends FileProcessor {
    @Override
    public void processFile(String filename) throws IOException {
        throw new IOException("BasicFileProcessor: File error");
    }
}

/**
 * Implementation that throws more specific exception (allowed)
 */
class AdvancedFileProcessor extends FileProcessor {
    @Override
    public void processFile(String filename) throws FileNotFoundException {
        throw new FileNotFoundException("AdvancedFileProcessor: File not found");
    }
}

/**
 * Implementation that doesn't throw (allowed)
 */
class SafeFileProcessor extends FileProcessor {
    @Override
    public void processFile(String filename) {
        System.out.println("  SafeFileProcessor: Processing safely");
    }
}

/**
 * Simple User class for repository example
 */
class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

/**
 * Custom exception for data access errors
 */
class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }
}

/**
 * Custom exception for validation errors
 */
class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}

/**
 * Repository class demonstrating real-world API design with throws
 */
class UserRepository {

    /**
     * Find user by ID
     * @throws DataAccessException if database error occurs
     */
    public User findById(int id) throws DataAccessException {
        // Simulate database error
        throw new DataAccessException("User not found: " + id);
    }

    /**
     * Save user to database
     * @throws ValidationException if user data is invalid
     * @throws DataAccessException if database error occurs
     */
    public void save(User user) throws ValidationException, DataAccessException {
        // Validate user
        if (user.getId() <= 0 || user.getName().isEmpty()) {
            throw new ValidationException("Invalid user data");
        }

        // Simulate successful save
        System.out.println("  Saving user: " + user);
    }
}

