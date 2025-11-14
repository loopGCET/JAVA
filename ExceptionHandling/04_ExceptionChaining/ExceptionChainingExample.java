import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Comprehensive demonstration of exception chaining in Java.
 *
 * @author GitHub Copilot
 * @topic Exception Handling - Exception Chaining
 */
public class ExceptionChainingExample {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  EXCEPTION CHAINING COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        example1_BasicChaining();
        System.out.println("─────────────────────────────────────────────────────\n");

        example2_MultiLayerChaining();
        System.out.println("─────────────────────────────────────────────────────\n");

        example3_InitCauseMethod();
        System.out.println("─────────────────────────────────────────────────────\n");

        example4_GettingRootCause();
        System.out.println("─────────────────────────────────────────────────────\n");

        example5_RealWorldExample();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    /**
     * Example 1: Basic Exception Chaining
     * Demonstrates: Wrapping exceptions with cause
     */
    public static void example1_BasicChaining() {
        System.out.println("--- Example 1: Basic Exception Chaining ---");

        try {
            readConfiguration("config.properties");
        } catch (ConfigurationException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Cause: " + e.getCause().getClass().getSimpleName());
            System.out.println("Cause message: " + e.getCause().getMessage());
        }

        System.out.println();
    }

    private static void readConfiguration(String filename) throws ConfigurationException {
        try {
            // Simulate file not found
            throw new FileNotFoundException("File not found: " + filename);
        } catch (FileNotFoundException e) {
            // Wrap in domain-specific exception
            throw new ConfigurationException("Failed to load configuration", e);
        }
    }

    /**
     * Example 2: Multi-Layer Exception Chaining
     * Demonstrates: Exception propagation through multiple layers
     */
    public static void example2_MultiLayerChaining() {
        System.out.println("--- Example 2: Multi-Layer Chaining ---");

        ApplicationService service = new ApplicationService();

        try {
            service.processUserRequest("user123");
        } catch (ServiceException e) {
            System.out.println("Top-level exception caught!");
            System.out.println("\nException chain:");

            int level = 1;
            Throwable current = e;
            while (current != null) {
                System.out.println("Level " + level + ": " +
                    current.getClass().getSimpleName() + " - " + current.getMessage());
                current = current.getCause();
                level++;
            }
        }

        System.out.println();
    }

    /**
     * Example 3: Using initCause() Method
     * Demonstrates: Alternative way to set exception cause
     */
    public static void example3_InitCauseMethod() {
        System.out.println("--- Example 3: initCause() Method ---");

        try {
            performOperationWithInitCause();
        } catch (BusinessException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Has cause: " + (e.getCause() != null));
            if (e.getCause() != null) {
                System.out.println("Cause: " + e.getCause().getMessage());
            }
        }

        System.out.println();
    }

    private static void performOperationWithInitCause() throws BusinessException {
        try {
            // Simulate error
            throw new IllegalStateException("Invalid state detected");
        } catch (IllegalStateException e) {
            // Create exception and set cause separately
            BusinessException business = new BusinessException("Business rule violated");
            business.initCause(e);
            throw business;
        }
    }

    /**
     * Example 4: Getting Root Cause
     * Demonstrates: Traversing exception chain to find root cause
     */
    public static void example4_GettingRootCause() {
        System.out.println("--- Example 4: Getting Root Cause ---");

        try {
            deeplyNestedOperation();
        } catch (Exception e) {
            System.out.println("Exception caught at top level");
            System.out.println("\nFull chain:");
            printExceptionChain(e);

            System.out.println("\nRoot cause:");
            Throwable root = getRootCause(e);
            System.out.println("Type: " + root.getClass().getSimpleName());
            System.out.println("Message: " + root.getMessage());
        }

        System.out.println();
    }

    private static void deeplyNestedOperation() throws Exception {
        try {
            level3Operation();
        } catch (Exception e) {
            throw new Exception("Level 1 error", e);
        }
    }

    private static void level3Operation() throws Exception {
        try {
            level2Operation();
        } catch (Exception e) {
            throw new Exception("Level 2 error", e);
        }
    }

    private static void level2Operation() throws Exception {
        try {
            level1Operation();
        } catch (Exception e) {
            throw new Exception("Level 3 error", e);
        }
    }

    private static void level1Operation() {
        throw new RuntimeException("Root cause: Actual error at deepest level");
    }

    private static void printExceptionChain(Throwable e) {
        int level = 1;
        Throwable current = e;
        while (current != null) {
            System.out.println("  " + level + ". " + current.getClass().getSimpleName() +
                             ": " + current.getMessage());
            current = current.getCause();
            level++;
        }
    }

    private static Throwable getRootCause(Throwable e) {
        Throwable cause = e;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    /**
     * Example 5: Real-World Multi-Tier Application
     * Demonstrates: Practical exception chaining in layered architecture
     */
    public static void example5_RealWorldExample() {
        System.out.println("--- Example 5: Real-World Multi-Tier Application ---");

        UserController controller = new UserController();

        // Scenario 1: Database connection failure
        System.out.println("Scenario 1: Database connection failure");
        try {
            controller.getUserProfile("user123");
        } catch (Exception e) {
            System.out.println("Controller caught: " + e.getClass().getSimpleName());
            System.out.println("User-friendly message: " + e.getMessage());
            System.out.println("\nTechnical details for logging:");
            printExceptionChain(e);
        }

        System.out.println("\nScenario 2: Data not found");
        try {
            controller.getUserProfile("nonexistent");
        } catch (Exception e) {
            System.out.println("Controller caught: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }

        System.out.println();
    }
}

// ==================== Custom Exception Classes ====================

class ConfigurationException extends Exception {
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

class DataAccessException extends Exception {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

class DatabaseException extends Exception {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}

class UserServiceException extends Exception {
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// ==================== Multi-Layer Application ====================

/**
 * Application Service Layer
 */
class ApplicationService {
    private DataLayer dataLayer = new DataLayer();

    public void processUserRequest(String userId) throws ServiceException {
        try {
            dataLayer.fetchUserData(userId);
        } catch (DataAccessException e) {
            throw new ServiceException("Service failed to process request", e);
        }
    }
}

/**
 * Data Access Layer
 */
class DataLayer {
    private DatabaseLayer dbLayer = new DatabaseLayer();

    public void fetchUserData(String userId) throws DataAccessException {
        try {
            dbLayer.executeQuery("SELECT * FROM users WHERE id = " + userId);
        } catch (DatabaseException e) {
            throw new DataAccessException("Failed to fetch user data", e);
        }
    }
}

/**
 * Database Layer
 */
class DatabaseLayer {
    public void executeQuery(String sql) throws DatabaseException {
        try {
            // Simulate database connection error
            throw new SQLException("Connection timeout after 30 seconds");
        } catch (SQLException e) {
            throw new DatabaseException("Database query failed", e);
        }
    }
}

// ==================== Real-World Example Classes ====================

/**
 * Controller Layer (Presentation)
 */
class UserController {
    private UserService userService = new UserService();

    public UserProfile getUserProfile(String userId) throws UserServiceException {
        try {
            return userService.findUser(userId);
        } catch (DataAccessException e) {
            // Log technical details
            logError(e);
            // Throw user-friendly exception
            throw new UserServiceException("Unable to retrieve user profile. Please try again later.", e);
        }
    }

    private void logError(Exception e) {
        // In real application, this would log to file/system
        System.out.println("  [LOG] Error occurred: " + e.getClass().getSimpleName());
    }
}

/**
 * Service Layer (Business Logic)
 */
class UserService {
    private UserRepository repository = new UserRepository();

    public UserProfile findUser(String userId) throws DataAccessException {
        try {
            return repository.findById(userId);
        } catch (DatabaseException e) {
            throw new DataAccessException("User service failed to access data", e);
        }
    }
}

/**
 * Repository Layer (Data Access)
 */
class UserRepository {
    public UserProfile findById(String userId) throws DatabaseException {
        if (userId.equals("nonexistent")) {
            throw new DatabaseException("User not found",
                new SQLException("No rows returned"));
        }

        // Simulate database connection error
        throw new DatabaseException("Database connection failed",
            new SQLException("Connection timeout"));
    }
}

/**
 * Simple User Profile class
 */
class UserProfile {
    private String id;
    private String name;

    public UserProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserProfile{id='" + id + "', name='" + name + "'}";
    }
}

