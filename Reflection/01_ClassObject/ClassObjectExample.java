import java.lang.reflect.*;

public class ClassObjectExample {

    public static void main(String[] args) {
        System.out.println("═══ CLASS OBJECT EXAMPLES ═══\n");

        gettingClassObjects();
        classInformation();
        classModifiers();

        System.out.println("\n═══ Complete! ═══");
    }

    static void gettingClassObjects() {
        System.out.println("--- Getting Class Objects ---");

        // Method 1: .class literal
        Class<?> clazz1 = String.class;
        System.out.println("Using .class: " + clazz1.getName());

        // Method 2: getClass()
        String str = "Hello";
        Class<?> clazz2 = str.getClass();
        System.out.println("Using getClass(): " + clazz2.getName());

        // Method 3: Class.forName()
        try {
            Class<?> clazz3 = Class.forName("java.util.ArrayList");
            System.out.println("Using forName(): " + clazz3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    static void classInformation() {
        System.out.println("--- Class Information ---");

        Class<?> clazz = Employee.class;

        System.out.println("Simple name: " + clazz.getSimpleName());
        System.out.println("Canonical name: " + clazz.getCanonicalName());
        System.out.println("Package: " + clazz.getPackage());
        System.out.println("Superclass: " + clazz.getSuperclass().getSimpleName());

        System.out.println("Interfaces:");
        for (Class<?> iface : clazz.getInterfaces()) {
            System.out.println("  - " + iface.getSimpleName());
        }

        System.out.println();
    }

    static void classModifiers() {
        System.out.println("--- Class Modifiers ---");

        Class<?> clazz = Employee.class;
        int modifiers = clazz.getModifiers();

        System.out.println("Is public: " + Modifier.isPublic(modifiers));
        System.out.println("Is abstract: " + Modifier.isAbstract(modifiers));
        System.out.println("Is final: " + Modifier.isFinal(modifiers));
        System.out.println("Is interface: " + Modifier.isInterface(modifiers));

        System.out.println();
    }
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}

interface Identifiable {
    String getId();
}

class Employee extends Person implements Identifiable {
    private String id;
    private String department;

    public Employee(String name, String id, String department) {
        super(name);
        this.id = id;
        this.department = department;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }
}

