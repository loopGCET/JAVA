import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SupplierExample {

    public static void main(String[] args) {
        System.out.println("═══ SUPPLIER EXAMPLES ═══\n");

        basicSupplier();
        supplierWithStreams();
        lazyEvaluation();

        System.out.println("\n═══ Complete! ═══");
    }

    static void basicSupplier() {
        System.out.println("--- Basic Supplier ---");
        Supplier<Double> random = () -> Math.random();
        Supplier<String> message = () -> "Hello from Supplier!";

        System.out.println("Random number: " + random.get());
        System.out.println(message.get());
        System.out.println();
    }

    static void supplierWithStreams() {
        System.out.println("--- Supplier with Streams ---");
        Supplier<Integer> counter = new Supplier<>() {
            int count = 0;
            public Integer get() { return count++; }
        };

        Stream.generate(counter)
            .limit(5)
            .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }

    static void lazyEvaluation() {
        System.out.println("--- Lazy Evaluation ---");
        Supplier<String> expensive = () -> {
            System.out.println("  Computing expensive value...");
            return "Expensive Result";
        };

        System.out.println("Supplier created (not executed yet)");
        System.out.println("Getting value:");
        String result = expensive.get();
        System.out.println("Result: " + result);
        System.out.println();
    }
}

