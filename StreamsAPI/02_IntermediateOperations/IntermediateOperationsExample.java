import java.util.*;
import java.util.stream.*;

/**
 * Intermediate Operations Examples
 */
public class IntermediateOperationsExample {

    public static void main(String[] args) {
        System.out.println("═══ INTERMEDIATE OPERATIONS EXAMPLES ═══\n");

        filterExample();
        mapExample();
        flatMapExample();
        distinctAndSortedExample();
        limitAndSkipExample();

        System.out.println("\n═══ Complete! ═══");
    }

    static void filterExample() {
        System.out.println("--- filter() ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Evens: " + evens);
        System.out.println();
    }

    static void mapExample() {
        System.out.println("--- map() ---");
        List<String> words = Arrays.asList("hello", "world");
        List<Integer> lengths = words.stream()
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println("Lengths: " + lengths);
        System.out.println();
    }

    static void flatMapExample() {
        System.out.println("--- flatMap() ---");
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4)
        );
        List<Integer> flat = nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("Flattened: " + flat);
        System.out.println();
    }

    static void distinctAndSortedExample() {
        System.out.println("--- distinct() and sorted() ---");
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        List<Integer> result = numbers.stream()
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Distinct and sorted: " + result);
        System.out.println();
    }

    static void limitAndSkipExample() {
        System.out.println("--- limit() and skip() ---");
        List<Integer> numbers = IntStream.range(1, 11)
            .boxed()
            .collect(Collectors.toList());

        List<Integer> limited = numbers.stream()
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("First 5: " + limited);

        List<Integer> skipped = numbers.stream()
            .skip(5)
            .collect(Collectors.toList());
        System.out.println("Skip first 5: " + skipped);
        System.out.println();
    }
}

