import java.util.*;
import java.util.function.Consumer;

public class ConsumerExample {

    public static void main(String[] args) {
        System.out.println("═══ CONSUMER EXAMPLES ═══\n");

        basicConsumer();
        consumerChaining();
        consumerWithForEach();

        System.out.println("\n═══ Complete! ===");
    }

    static void basicConsumer() {
        System.out.println("--- Basic Consumer ---");
        Consumer<String> print = s -> System.out.println(s);
        Consumer<Integer> square = n -> System.out.println(n * n);

        print.accept("Hello from Consumer!");
        square.accept(5);
        System.out.println();
    }

    static void consumerChaining() {
        System.out.println("--- Consumer Chaining ---");
        Consumer<String> print = s -> System.out.print(s);
        Consumer<String> exclaim = s -> System.out.println("!");

        Consumer<String> printAndExclaim = print.andThen(exclaim);
        printAndExclaim.accept("Hello");
        System.out.println();
    }

    static void consumerWithForEach() {
        System.out.println("--- Consumer with forEach ---");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.forEach(name -> System.out.println("Hello, " + name));
        System.out.println();
    }
}

