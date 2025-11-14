import java.util.*;
import java.util.stream.*;

public class TerminalOperationsExample {

    public static void main(String[] args) {
        System.out.println("═══ TERMINAL OPERATIONS EXAMPLES ═══\n");

        collectExample();
        forEachExample();
        reduceExample();
        matchExample();
        findExample();

        System.out.println("\n═══ Complete! ═══");
    }

    static void collectExample() {
        System.out.println("--- collect() ---");
        List<String> list = Stream.of("a", "b", "c")
            .collect(Collectors.toList());
        System.out.println("Collected to list: " + list);
        System.out.println();
    }

    static void forEachExample() {
        System.out.println("--- forEach() ---");
        Stream.of(1, 2, 3).forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }

    static void reduceExample() {
        System.out.println("--- reduce() ---");
        int sum = Stream.of(1, 2, 3, 4, 5)
            .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);
        System.out.println();
    }

    static void matchExample() {
        System.out.println("--- anyMatch/allMatch/noneMatch() ---");
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8);

        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
        boolean noneOdd = numbers.stream().noneMatch(n -> n % 2 != 0);

        System.out.println("Any even: " + anyEven);
        System.out.println("All even: " + allEven);
        System.out.println("None odd: " + noneOdd);
        System.out.println();
    }

    static void findExample() {
        System.out.println("--- findFirst/findAny() ---");
        Optional<Integer> first = Stream.of(1, 2, 3).findFirst();
        Optional<Integer> any = Stream.of(1, 2, 3).findAny();

        System.out.println("First: " + first.orElse(-1));
        System.out.println("Any: " + any.orElse(-1));
        System.out.println();
    }
}

