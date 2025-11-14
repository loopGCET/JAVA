import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Comprehensive demonstration of try-catch-finally blocks in Java.
 *
 * This example covers:
 * - Basic try-catch syntax
 * - Multiple catch blocks
 * - Finally block execution guarantees
 * - Multi-catch (Java 7+)
 * - Nested try-catch blocks
 * - Return statements with finally
 * - Exception object methods
 * - Real-world file I/O scenarios
 *
 * @author GitHub Copilot
 * @topic Exception Handling - Try-Catch-Finally
 */
public class TryCatchFinallyExample {

    /**
     * Example 1: Basic Try-Catch
     * Demonstrates: Simple exception catching
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static void example1_BasicTryCatch() {
        System.out.println("--- Example 1: Basic Try-Catch ---");

        // Scenario 1: Division by zero
        try {
            System.out.println("Attempting to divide by zero...");
            int result = 10 / 0;
            System.out.println("Result: " + result);  // This line won't execute
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        }

        System.out.println("Program continues normally after handling exception\n");

        // Scenario 2: Array index out of bounds
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Accessing index 5 in array of size 3...");
            int value = numbers[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        System.out.println("Exception handled, execution continues\n");
    }

    /**
     * Example 2: Multiple Catch Blocks
     * Demonstrates: Handling different exception types
     * Shows order matters: specific to general
     */
    public static void example2_MultipleCatchBlocks() {
        System.out.println("--- Example 2: Multiple Catch Blocks ---");

        // Test with different inputs to trigger different exceptions
        String[] testCases = {"123", "abc", null};

        for (String testCase : testCases) {
            try {
                System.out.println("Processing: " + testCase);

                // This could throw NumberFormatException
                int number = Integer.parseInt(testCase);

                // This could throw ArithmeticException
                int result = 100 / number;

                System.out.println("Result: " + result);

            } catch (NumberFormatException e) {
                // Specific exception - catches invalid number format
                System.out.println("  → NumberFormatException: Invalid number format");

            } catch (ArithmeticException e) {
                // Specific exception - catches division by zero
                System.out.println("  → ArithmeticException: " + e.getMessage());

            } catch (NullPointerException e) {
                // Specific exception - catches null reference
                System.out.println("  → NullPointerException: Input is null");

            } catch (Exception e) {
                // General exception - catches anything else
                System.out.println("  → General Exception: " + e.getClass().getSimpleName());
            }
        }
        System.out.println();
    }

    /**
     * Example 3: Finally Block - Always Executes
     * Demonstrates: Finally block execution in all scenarios
     * Use case: Resource cleanup, logging
     */
    public static void example3_FinallyBlock() {
        System.out.println("--- Example 3: Finally Block Execution ---");

        // Scenario 1: No exception
        System.out.println("Scenario 1: No exception");
        try {
            System.out.println("  Try block: Executing normally");
        } catch (Exception e) {
            System.out.println("  Catch block: This won't execute");
        } finally {
            System.out.println("  Finally block: Always executes\n");
        }

        // Scenario 2: Exception caught
        System.out.println("Scenario 2: Exception caught");
        try {
            System.out.println("  Try block: Throwing exception");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("  Catch block: Exception caught");
        } finally {
            System.out.println("  Finally block: Still executes\n");
        }

        // Scenario 3: Exception not caught (will be thrown after finally)
        System.out.println("Scenario 3: Return statement in try");
        String result = methodWithFinally();
        System.out.println("  Method returned: " + result + "\n");
    }

    /**
     * Helper method to demonstrate finally execution with return
     */
    private static String methodWithFinally() {
        try {
            System.out.println("  Try block: About to return");
            return "Value from try";
        } finally {
            System.out.println("  Finally block: Executes before return");
            // Note: Returning from finally overrides try return (bad practice)
        }
    }

    /**
     * Example 4: Multi-Catch (Java 7+)
     * Demonstrates: Catching multiple exception types in one block
     * Use case: When handling different exceptions the same way
     */
    public static void example4_MultiCatch() {
        System.out.println("--- Example 4: Multi-Catch (Java 7+) ---");

        // Scenario 1: IOException (handles FileNotFoundException as subclass)
        try {
            System.out.println("Attempting to read non-existent file...");
            BufferedReader reader = new BufferedReader(new FileReader("nonexistent.txt"));
            String line = reader.readLine();
            reader.close();

        } catch (IOException e) {
            // Handles IOException and its subclasses (including FileNotFoundException)
            System.out.println("File error caught: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }

        System.out.println();

        // Scenario 2: Multiple arithmetic exceptions
        performOperation(10, 0, "divide");
        performOperation(10, 5, "invalid");
    }

    /**
     * Helper method demonstrating multi-catch with custom logic
     */
    private static void performOperation(int a, int b, String operation) {
        try {
            if ("divide".equals(operation)) {
                System.out.println("Result: " + (a / b));
            } else {
                throw new IllegalArgumentException("Unknown operation");
            }

        } catch (ArithmeticException | IllegalArgumentException e) {
            // Handle both exception types together
            System.out.println("Operation error: " + e.getClass().getSimpleName()
                             + " - " + e.getMessage());
        }
    }

    /**
     * Example 5: Nested Try-Catch
     * Demonstrates: Try-catch blocks within try-catch blocks
     * Use case: Different levels of exception handling
     */
    public static void example5_NestedTryCatch() {
        System.out.println("--- Example 5: Nested Try-Catch ---");

        try {
            System.out.println("Outer try: Starting");

            try {
                System.out.println("  Inner try: Dividing by zero");
                int result = 10 / 0;

            } catch (ArithmeticException e) {
                System.out.println("  Inner catch: Caught ArithmeticException");
                System.out.println("  Rethrowing as RuntimeException");
                throw new RuntimeException("Wrapped exception", e);
            }

        } catch (RuntimeException e) {
            System.out.println("Outer catch: Caught RuntimeException");
            System.out.println("Message: " + e.getMessage());

            // Access the original exception
            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println("Original cause: " + cause.getClass().getSimpleName());
            }
        }

        System.out.println();
    }

    /**
     * Example 6: Exception Object Methods
     * Demonstrates: Useful methods of Exception class
     * Methods: getMessage(), toString(), printStackTrace()
     */
    public static void example6_ExceptionMethods() {
        System.out.println("--- Example 6: Exception Object Methods ---");

        try {
            // Create a scenario with multiple method calls for stack trace
            methodA();

        } catch (Exception e) {
            // Method 1: getMessage() - returns error message
            System.out.println("getMessage(): " + e.getMessage());

            // Method 2: toString() - returns class name and message
            System.out.println("\ntoString(): " + e.toString());

            // Method 3: getClass() - returns exception class
            System.out.println("\ngetClass(): " + e.getClass().getName());

            // Method 4: printStackTrace() - prints full stack trace
            System.out.println("\nprintStackTrace():");
            e.printStackTrace();
        }

        System.out.println();
    }

    // Helper methods for stack trace demo
    private static void methodA() {
        methodB();
    }

    private static void methodB() {
        methodC();
    }

    private static void methodC() {
        throw new RuntimeException("Exception from methodC");
    }

    /**
     * Example 7: Return Statements with Try-Catch-Finally
     * Demonstrates: How return statements interact with finally
     * Important: Finally executes before method returns
     */
    public static void example7_ReturnStatements() {
        System.out.println("--- Example 7: Return Statements with Finally ---");

        // Test different return scenarios
        System.out.println("Test 1: Return from try");
        int result1 = returnFromTry();
        System.out.println("Final result: " + result1 + "\n");

        System.out.println("Test 2: Return from catch");
        int result2 = returnFromCatch();
        System.out.println("Final result: " + result2 + "\n");

        System.out.println("Test 3: Modified value in finally (doesn't affect return)");
        int result3 = returnWithModification();
        System.out.println("Final result: " + result3 + "\n");
    }

    private static int returnFromTry() {
        try {
            System.out.println("  Try: Returning 10");
            return 10;
        } catch (Exception e) {
            System.out.println("  Catch: Returning 20");
            return 20;
        } finally {
            System.out.println("  Finally: Executed before return");
            // Don't return here - it would override try/catch return
        }
    }

    private static int returnFromCatch() {
        try {
            System.out.println("  Try: Throwing exception");
            throw new RuntimeException("Test exception");
        } catch (Exception e) {
            System.out.println("  Catch: Returning 30");
            return 30;
        } finally {
            System.out.println("  Finally: Executed before return");
        }
    }

    private static int returnWithModification() {
        int value = 40;
        try {
            System.out.println("  Try: Returning " + value);
            return value;
        } finally {
            value = 50;  // This doesn't affect the return value!
            System.out.println("  Finally: Changed value to " + value);
        }
    }

    /**
     * Example 8: Real-World File I/O Example
     * Demonstrates: Practical exception handling with file operations
     * Use case: Reading file with proper error handling
     * Note: See Try-With-Resources for cleaner approach
     */
    public static void example8_RealWorldFileIO() {
        System.out.println("--- Example 8: Real-World File I/O ---");

        // Create a test file
        String filename = "test_exception.txt";
        createTestFile(filename);

        // Read file with proper exception handling
        BufferedReader reader = null;
        try {
            System.out.println("Opening file: " + filename);
            reader = new BufferedReader(new FileReader(filename));

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber++ + ": " + line);
            }

            System.out.println("File read successfully");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
            System.out.println("Please check if the file exists");

        } catch (IOException e) {
            System.out.println("Error: Failed to read file");
            System.out.println("Details: " + e.getMessage());

        } finally {
            // Cleanup: Close the file reader
            System.out.println("Closing file resources...");
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("File closed successfully");
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }

        // Cleanup: Delete test file
        deleteTestFile(filename);
        System.out.println();
    }

    /**
     * Helper method to create test file
     */
    private static void createTestFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("This is line 1");
            writer.println("This is line 2");
            writer.println("This is line 3");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating test file");
        }
    }

    /**
     * Helper method to delete test file
     */
    private static void deleteTestFile(String filename) {
        try {
            Files.deleteIfExists(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("Error deleting test file");
        }
    }

    /**
     * Main method - runs all examples
     */
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  TRY-CATCH-FINALLY COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Example 1: Basic try-catch
        example1_BasicTryCatch();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 2: Multiple catch blocks
        example2_MultipleCatchBlocks();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 3: Finally block
        example3_FinallyBlock();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 4: Multi-catch
        example4_MultiCatch();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 5: Nested try-catch
        example5_NestedTryCatch();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 6: Exception object methods
        example6_ExceptionMethods();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 7: Return statements
        example7_ReturnStatements();
        System.out.println("─────────────────────────────────────────────────────\n");

        // Example 8: Real-world file I/O
        example8_RealWorldFileIO();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}

