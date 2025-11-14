import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExample {

    public static void main(String[] args) {
        System.out.println("═══ FUNCTION EXAMPLES ═══\n");

        basicFunction();
        functionComposition();
        functionWithStreams();

        System.out.println("\n═══ Complete! ═══");
    }

    static void basicFunction() {
        System.out.println("--- Basic Function ---");
        Function<String, Integer> length = s -> s.length();
        Function<Integer, Integer> square = n -> n * n;

        System.out.println("Length of 'Java': " + length.apply("Java"));
        System.out.println("Square of 5: " + square.apply(5));
        System.out.println();
    }

    static void functionComposition() {
        System.out.println("--- Function Composition ---");
        Function<Integer, Integer> multiplyBy2 = n -> n * 2;
        Function<Integer, Integer> add10 = n -> n + 10;

        Function<Integer, Integer> composed = multiplyBy2.andThen(add10);

        System.out.println("(5 * 2) + 10 = " + composed.apply(5));
        System.out.println();
    }

    static void functionWithStreams() {
        System.out.println("--- Function with Streams ---");
        List<String> words = Arrays.asList("hello", "world", "java");

        List<Integer> lengths = words.stream()
            .map(String::length)
            .collect(Collectors.toList());

        System.out.println("Lengths: " + lengths);
        System.out.println();
    }
}

