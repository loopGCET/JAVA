import java.util.function.*;

public class FunctionalInterfacesExample {

    public static void main(String[] args) {
        System.out.println("═══ FUNCTIONAL INTERFACES EXAMPLES ═══\n");

        customFunctionalInterface();
        builtInInterfaces();

        System.out.println("\n═══ Complete! ═══");
    }

    static void customFunctionalInterface() {
        System.out.println("--- Custom Functional Interface ---");

        StringProcessor upper = s -> s.toUpperCase();
        StringProcessor lower = s -> s.toLowerCase();

        System.out.println(upper.process("hello")); // HELLO
        System.out.println(lower.process("WORLD")); // world
        System.out.println();
    }

    static void builtInInterfaces() {
        System.out.println("--- Built-in Functional Interfaces ---");

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> length = s -> s.length();
        Consumer<String> print = s -> System.out.println(s);
        Supplier<Double> random = () -> Math.random();

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Length of 'Java': " + length.apply("Java"));
        print.accept("Consumer prints this");
        System.out.println("Random: " + random.get());
        System.out.println();
    }
}

@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

