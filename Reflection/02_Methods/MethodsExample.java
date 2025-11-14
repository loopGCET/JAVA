import java.lang.reflect.*;

public class MethodsExample {

    public static void main(String[] args) {
        System.out.println("═══ METHODS REFLECTION EXAMPLES ═══\n");

        listingMethods();
        invokingMethods();
        methodParameters();

        System.out.println("\n═══ Complete! ═══");
    }

    static void listingMethods() {
        System.out.println("--- Listing Methods ---");

        Class<?> clazz = Calculator.class;
        Method[] methods = clazz.getDeclaredMethods();

        System.out.println("Methods in Calculator class:");
        for (Method method : methods) {
            System.out.println("  " + method.getName() +
                             " - Return type: " + method.getReturnType().getSimpleName());
        }
        System.out.println();
    }

    static void invokingMethods() {
        System.out.println("--- Invoking Methods ---");

        try {
            Calculator calc = new Calculator();
            Class<?> clazz = calc.getClass();

            // Invoke add method
            Method addMethod = clazz.getMethod("add", int.class, int.class);
            Object result = addMethod.invoke(calc, 5, 3);
            System.out.println("add(5, 3) = " + result);

            // Invoke multiply method
            Method multiplyMethod = clazz.getMethod("multiply", int.class, int.class);
            result = multiplyMethod.invoke(calc, 4, 7);
            System.out.println("multiply(4, 7) = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void methodParameters() {
        System.out.println("--- Method Parameters ---");

        try {
            Class<?> clazz = Calculator.class;
            Method method = clazz.getMethod("add", int.class, int.class);

            System.out.println("Method: " + method.getName());
            System.out.println("Parameter count: " + method.getParameterCount());

            Class<?>[] paramTypes = method.getParameterTypes();
            System.out.println("Parameter types:");
            for (Class<?> paramType : paramTypes) {
                System.out.println("  - " + paramType.getSimpleName());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    private int secret() {
        return 42;
    }
}

