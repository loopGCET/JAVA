import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {

    public static void main(String[] args) {
        System.out.println("═══ PREDICATE EXAMPLES ═══\n");

        basicPredicate();
        predicateChaining();
        predicateWithStreams();

        System.out.println("\n═══ Complete! ═══");
    }

    static void basicPredicate() {
        System.out.println("--- Basic Predicate ---");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isEmpty = String::isEmpty;

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
        System.out.println("Is '' empty? " + isEmpty.test(""));
        System.out.println();
    }

    static void predicateChaining() {
        System.out.println("--- Predicate Chaining ---");
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;

        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        Predicate<Integer> isPositiveOrEven = isPositive.or(isEven);

        System.out.println("Is 4 positive AND even? " + isPositiveAndEven.test(4));
        System.out.println("Is -2 positive OR even? " + isPositiveOrEven.test(-2));
        System.out.println();
    }

    static void predicateWithStreams() {
        System.out.println("--- Predicate with Streams ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> isEven = n -> n % 2 == 0;

        List<Integer> evens = numbers.stream()
            .filter(isEven)
            .collect(Collectors.toList());

        System.out.println("Even numbers: " + evens);
        System.out.println();
    }
}

