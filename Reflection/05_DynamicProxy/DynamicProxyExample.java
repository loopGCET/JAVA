import java.lang.reflect.*;

public class DynamicProxyExample {

    public static void main(String[] args) {
        System.out.println("═══ DYNAMIC PROXY EXAMPLES ═══\n");

        simpleProxy();
        loggingProxy();
        timingProxy();

        System.out.println("\n═══ Complete! ═══");
    }

    static void simpleProxy() {
        System.out.println("--- Simple Proxy ---");

        Calculator realCalculator = new CalculatorImpl();

        Calculator proxy = (Calculator) Proxy.newProxyInstance(
            Calculator.class.getClassLoader(),
            new Class<?>[] { Calculator.class },
            (p, method, args) -> {
                System.out.println("Calling method: " + method.getName());
                return method.invoke(realCalculator, args);
            }
        );

        int result = proxy.add(5, 3);
        System.out.println("Result: " + result);
        System.out.println();
    }

    static void loggingProxy() {
        System.out.println("--- Logging Proxy ---");

        UserService realService = new UserServiceImpl();

        UserService proxy = (UserService) Proxy.newProxyInstance(
            UserService.class.getClassLoader(),
            new Class<?>[] { UserService.class },
            new LoggingHandler(realService)
        );

        proxy.createUser("Alice");
        proxy.deleteUser("Bob");
        System.out.println();
    }

    static void timingProxy() {
        System.out.println("--- Timing Proxy ---");

        DataProcessor realProcessor = new DataProcessorImpl();

        DataProcessor proxy = (DataProcessor) Proxy.newProxyInstance(
            DataProcessor.class.getClassLoader(),
            new Class<?>[] { DataProcessor.class },
            (p, method, args) -> {
                long start = System.nanoTime();
                Object result = method.invoke(realProcessor, args);
                long end = System.nanoTime();
                System.out.println("  Execution time: " + (end - start) / 1000000.0 + " ms");
                return result;
            }
        );

        proxy.processData("Sample data");
        System.out.println();
    }
}

// Interfaces

interface Calculator {
    int add(int a, int b);
    int multiply(int a, int b);
}

interface UserService {
    void createUser(String name);
    void deleteUser(String name);
}

interface DataProcessor {
    void processData(String data);
}

// Implementations

class CalculatorImpl implements Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

class UserServiceImpl implements UserService {
    public void createUser(String name) {
        System.out.println("Creating user: " + name);
    }

    public void deleteUser(String name) {
        System.out.println("Deleting user: " + name);
    }
}

class DataProcessorImpl implements DataProcessor {
    public void processData(String data) {
        System.out.println("Processing: " + data);
        try {
            Thread.sleep(100); // Simulate processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Custom InvocationHandler

class LoggingHandler implements InvocationHandler {
    private Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[LOG] Before: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("[LOG] After: " + method.getName());
        return result;
    }
}

