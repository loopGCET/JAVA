import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Comprehensive demonstration of try-with-resources in Java.
 *
 * @author GitHub Copilot
 * @topic Exception Handling - Try-With-Resources
 */
public class TryWithResourcesExample {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  TRY-WITH-RESOURCES COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        example1_BasicUsage();
        System.out.println("─────────────────────────────────────────────────────\n");

        example2_MultipleResources();
        System.out.println("─────────────────────────────────────────────────────\n");

        example3_CustomAutoCloseable();
        System.out.println("─────────────────────────────────────────────────────\n");

        example4_SuppressedExceptions();
        System.out.println("─────────────────────────────────────────────────────\n");

        example5_OldWayVsNewWay();
        System.out.println("─────────────────────────────────────────────────────\n");

        example6_WithCatchAndFinally();
        System.out.println("─────────────────────────────────────────────────────\n");

        example7_RealWorldDatabaseExample();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    /**
     * Example 1: Basic Try-With-Resources Usage
     * Demonstrates: Automatic resource closing
     */
    public static void example1_BasicUsage() {
        System.out.println("--- Example 1: Basic Usage ---");

        // Create test file
        String filename = "test_file.txt";
        createTestFile(filename, "Hello, try-with-resources!");

        // Read file using try-with-resources
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("File opened successfully");
            String line = reader.readLine();
            System.out.println("Content: " + line);
            System.out.println("Reader will be closed automatically");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("After try block - resource already closed");

        // Cleanup
        deleteTestFile(filename);
        System.out.println();
    }

    /**
     * Example 2: Multiple Resources
     * Demonstrates: Managing multiple resources simultaneously
     */
    public static void example2_MultipleResources() {
        System.out.println("--- Example 2: Multiple Resources ---");

        String sourceFile = "source.txt";
        String destFile = "destination.txt";

        // Create source file
        createTestFile(sourceFile, "Data to copy");

        // Copy file using multiple resources
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destFile))) {

            System.out.println("Both resources opened");
            String line = reader.readLine();
            writer.write(line);
            System.out.println("Data copied: " + line);
            System.out.println("Both resources will be closed (in reverse order)");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("After try block - both resources closed");

        // Verify destination file
        try {
            String content = Files.readString(Paths.get(destFile));
            System.out.println("Verification - Destination file contains: " + content);
        } catch (IOException e) {
            System.out.println("Error reading destination");
        }

        // Cleanup
        deleteTestFile(sourceFile);
        deleteTestFile(destFile);
        System.out.println();
    }

    /**
     * Example 3: Custom AutoCloseable Resource
     * Demonstrates: Creating and using custom resources
     */
    public static void example3_CustomAutoCloseable() {
        System.out.println("--- Example 3: Custom AutoCloseable ---");

        System.out.println("Example 1: Single resource");
        try (CustomResource resource = new CustomResource("Resource-1")) {
            resource.doSomething();
            System.out.println("Using resource in try block");
        }

        System.out.println("\nExample 2: Multiple custom resources");
        try (CustomResource r1 = new CustomResource("Resource-A");
             CustomResource r2 = new CustomResource("Resource-B")) {
            r1.doSomething();
            r2.doSomething();
        }

        System.out.println("\nExample 3: With exception");
        try (CustomResource resource = new CustomResource("Resource-Error")) {
            resource.doSomething();
            throw new RuntimeException("Exception in try block");
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Resource was still closed!");
        }

        System.out.println();
    }

    /**
     * Example 4: Suppressed Exceptions
     * Demonstrates: How exceptions during close() are suppressed
     */
    public static void example4_SuppressedExceptions() {
        System.out.println("--- Example 4: Suppressed Exceptions ---");

        try (ResourceWithCloseException resource = new ResourceWithCloseException()) {
            System.out.println("Using resource");
            throw new IOException("Exception in try block");
        } catch (Exception e) {
            System.out.println("\nMain exception: " + e.getMessage());

            // Check for suppressed exceptions
            Throwable[] suppressed = e.getSuppressed();
            if (suppressed.length > 0) {
                System.out.println("\nSuppressed exceptions:");
                for (Throwable t : suppressed) {
                    System.out.println("  - " + t.getMessage());
                }
            }
        }

        System.out.println();
    }

    /**
     * Example 5: Old Way vs New Way
     * Demonstrates: Comparison of manual vs automatic resource management
     */
    public static void example5_OldWayVsNewWay() {
        System.out.println("--- Example 5: Old Way vs New Way ---");

        String filename = "comparison.txt";
        createTestFile(filename, "Test data");

        // Old way (before Java 7)
        System.out.println("Old Way (Manual resource management):");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            System.out.println("  Content: " + reader.readLine());
        } catch (IOException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("  Manually closed in finally");
                } catch (IOException e) {
                    System.out.println("  Error closing: " + e.getMessage());
                }
            }
        }

        // New way (Java 7+)
        System.out.println("\nNew Way (Try-with-resources):");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            System.out.println("  Content: " + br.readLine());
            System.out.println("  Automatically closed - cleaner code!");
        } catch (IOException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        deleteTestFile(filename);
        System.out.println();
    }

    /**
     * Example 6: Try-With-Resources with Catch and Finally
     * Demonstrates: Combining with catch and finally blocks
     */
    public static void example6_WithCatchAndFinally() {
        System.out.println("--- Example 6: With Catch and Finally ---");

        String filename = "test_catch_finally.txt";
        createTestFile(filename, "Sample content");

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("Reading file...");
            String content = reader.readLine();
            System.out.println("Content: " + content);

        } catch (FileNotFoundException e) {
            System.out.println("Catch: File not found - " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Catch: I/O error - " + e.getMessage());

        } finally {
            System.out.println("Finally: Additional cleanup or logging");
            System.out.println("(Resource already closed by try-with-resources)");
        }

        deleteTestFile(filename);
        System.out.println();
    }

    /**
     * Example 7: Real-World Database Connection Example
     * Demonstrates: Practical use case with database operations
     */
    public static void example7_RealWorldDatabaseExample() {
        System.out.println("--- Example 7: Real-World Database Example ---");

        DatabaseManager dbManager = new DatabaseManager();

        // Scenario 1: Single query
        System.out.println("Scenario 1: Single database query");
        dbManager.executeSingleQuery("SELECT * FROM users");

        // Scenario 2: Transaction with multiple operations
        System.out.println("\nScenario 2: Transaction");
        dbManager.executeTransaction();

        // Scenario 3: Nested resources
        System.out.println("\nScenario 3: Query with result processing");
        dbManager.queryAndProcess("SELECT id, name FROM products");

        System.out.println();
    }

    // Helper methods
    private static void createTestFile(String filename, String content) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(content);
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private static void deleteTestFile(String filename) {
        try {
            Files.deleteIfExists(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }
}

// ==================== Custom AutoCloseable Classes ====================

/**
 * Custom resource implementing AutoCloseable
 */
class CustomResource implements AutoCloseable {
    private String name;

    public CustomResource(String name) {
        this.name = name;
        System.out.println("  [" + name + "] Resource opened");
    }

    public void doSomething() {
        System.out.println("  [" + name + "] Doing work...");
    }

    @Override
    public void close() {
        System.out.println("  [" + name + "] Resource closed");
    }
}

/**
 * Resource that throws exception during close
 */
class ResourceWithCloseException implements AutoCloseable {
    public ResourceWithCloseException() {
        System.out.println("Resource created");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing resource (will throw exception)");
        throw new Exception("Exception during close()");
    }
}

/**
 * Simulated database connection
 */
class DatabaseConnection implements AutoCloseable {
    private String connectionId;

    public DatabaseConnection(String id) {
        this.connectionId = id;
        System.out.println("  [Connection " + id + "] Opening database connection");
    }

    public void executeQuery(String sql) {
        System.out.println("  [Connection " + connectionId + "] Executing: " + sql);
        System.out.println("  [Connection " + connectionId + "] Query completed");
    }

    @Override
    public void close() {
        System.out.println("  [Connection " + connectionId + "] Closing connection");
    }
}

/**
 * Simulated database statement
 */
class DatabaseStatement implements AutoCloseable {
    private String statementId;

    public DatabaseStatement(String id) {
        this.statementId = id;
        System.out.println("  [Statement " + id + "] Creating statement");
    }

    public ResultSetWrapper executeQuery(String sql) {
        System.out.println("  [Statement " + statementId + "] Executing query");
        return new ResultSetWrapper(statementId);
    }

    @Override
    public void close() {
        System.out.println("  [Statement " + statementId + "] Closing statement");
    }
}

/**
 * Simulated result set
 */
class ResultSetWrapper implements AutoCloseable {
    private String resultSetId;

    public ResultSetWrapper(String id) {
        this.resultSetId = id;
        System.out.println("  [ResultSet " + id + "] Opening result set");
    }

    public void processResults() {
        System.out.println("  [ResultSet " + resultSetId + "] Processing results");
    }

    @Override
    public void close() {
        System.out.println("  [ResultSet " + resultSetId + "] Closing result set");
    }
}

/**
 * Database manager demonstrating real-world usage
 */
class DatabaseManager {

    public void executeSingleQuery(String sql) {
        try (DatabaseConnection conn = new DatabaseConnection("DB-1")) {
            conn.executeQuery(sql);
        }
        System.out.println("  Connection automatically closed\n");
    }

    public void executeTransaction() {
        try (DatabaseConnection conn = new DatabaseConnection("DB-2");
             DatabaseStatement stmt = new DatabaseStatement("STMT-1")) {

            System.out.println("  Starting transaction");
            stmt.executeQuery("INSERT INTO accounts VALUES (1, 1000)");
            stmt.executeQuery("UPDATE accounts SET balance = 900 WHERE id = 1");
            System.out.println("  Transaction committed");

        }
        System.out.println("  All resources closed (statement, then connection)\n");
    }

    public void queryAndProcess(String sql) {
        try (DatabaseConnection conn = new DatabaseConnection("DB-3");
             DatabaseStatement stmt = new DatabaseStatement("STMT-2");
             ResultSetWrapper rs = stmt.executeQuery(sql)) {

            rs.processResults();

        }
        System.out.println("  All resources closed in reverse order\n");
    }
}

