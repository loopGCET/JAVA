import java.util.*;

public class LambdaBasicsExample {

    public static void main(String[] args) {
        System.out.println("═══ LAMBDA BASICS EXAMPLES ═══\n");

        noParameterLambda();
        singleParameterLambda();
        multipleParametersLambda();
        blockLambda();
        comparatorExample();

        System.out.println("\n═══ Complete! ═══");
    }

    static void noParameterLambda() {
        System.out.println("--- No Parameter Lambda ---");
        Runnable r = () -> System.out.println("Hello from lambda!");
        r.run();
        System.out.println();
    }

    static void singleParameterLambda() {
        System.out.println("--- Single Parameter Lambda ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }

    static void multipleParametersLambda() {
        System.out.println("--- Multiple Parameters Lambda ---");
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println("5 + 3 = " + add.calculate(5, 3));
        System.out.println("5 * 3 = " + multiply.calculate(5, 3));
        System.out.println();
    }

    static void blockLambda() {
        System.out.println("--- Block Lambda ---");
        Calculator complex = (a, b) -> {
            int sum = a + b;
            int product = a * b;
            return sum + product;
        };
        System.out.println("Result: " + complex.calculate(3, 4));
        System.out.println();
    }

    static void comparatorExample() {
        System.out.println("--- Comparator with Lambda ---");
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");

        names.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted: " + names);
        System.out.println();
    }
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

