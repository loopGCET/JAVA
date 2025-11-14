public class BuiltInAnnotationsExample {

    public static void main(String[] args) {
        System.out.println("═══ BUILT-IN ANNOTATIONS EXAMPLES ═══\n");

        overrideExample();
        deprecatedExample();
        suppressWarningsExample();
        functionalInterfaceExample();

        System.out.println("\n═══ Complete! ═══");
    }

    static void overrideExample() {
        System.out.println("--- @Override Example ---");
        Person person = new Person("Alice");
        System.out.println("Person toString: " + person.toString());
        System.out.println();
    }

    static void deprecatedExample() {
        System.out.println("--- @Deprecated Example ---");
        Calculator calc = new Calculator();
        System.out.println("Using deprecated method: " + calc.oldAdd(5, 3));
        System.out.println("Using new method: " + calc.add(5, 3));
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    static void suppressWarningsExample() {
        System.out.println("--- @SuppressWarnings Example ---");
        System.out.println("Suppressed unchecked warnings in this method");
        System.out.println();
    }

    static void functionalInterfaceExample() {
        System.out.println("--- @FunctionalInterface Example ---");
        Printer printer = (msg) -> System.out.println("Printing: " + msg);
        printer.print("Hello from functional interface!");
        System.out.println();
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}

class Calculator {
    @Deprecated
    public int oldAdd(int a, int b) {
        return a + b;
    }

    public int add(int a, int b) {
        return a + b;
    }
}

@FunctionalInterface
interface Printer {
    void print(String message);
}

