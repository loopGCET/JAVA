import java.lang.reflect.*;

public class ConstructorsExample {

    public static void main(String[] args) {
        System.out.println("═══ CONSTRUCTORS REFLECTION EXAMPLES ═══\n");

        listingConstructors();
        creatingInstances();
        privateConstructors();

        System.out.println("\n═══ Complete! ═══");
    }

    static void listingConstructors() {
        System.out.println("--- Listing Constructors ---");

        Class<?> clazz = Employee.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println("Constructors in Employee class:");
        for (Constructor<?> constructor : constructors) {
            System.out.print("  Constructor(");
            Class<?>[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                System.out.print(paramTypes[i].getSimpleName());
                if (i < paramTypes.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }
        System.out.println();
    }

    static void creatingInstances() {
        System.out.println("--- Creating Instances ---");

        try {
            Class<?> clazz = Employee.class;

            // Using constructor with parameters
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class, double.class);
            Object instance = constructor.newInstance("John", "Engineering", 75000.0);

            System.out.println("Created instance: " + instance);

            // Using no-arg constructor
            constructor = clazz.getConstructor();
            instance = constructor.newInstance();
            System.out.println("Created with no-arg constructor: " + instance);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void privateConstructors() {
        System.out.println("--- Private Constructors ---");

        try {
            Class<?> clazz = Singleton.class;

            // Get private constructor
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            // Create instance using private constructor
            Object instance1 = constructor.newInstance();
            Object instance2 = constructor.newInstance();

            System.out.println("Created two instances using private constructor");
            System.out.println("Are they same? " + (instance1 == instance2));

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee() {
        this.name = "Unknown";
        this.department = "Unknown";
        this.salary = 0.0;
    }

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department +
               "', salary=" + salary + "}";
    }
}

class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

