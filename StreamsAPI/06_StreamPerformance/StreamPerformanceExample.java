import java.util.*;
import java.util.stream.*;

public class StreamPerformanceExample {

    public static void main(String[] args) {
        System.out.println("═══ STREAM PERFORMANCE EXAMPLES ═══\n");

        primitiveStreamsPerformance();
        methodReferenceVsLambda();
        shortCircuitOperations();

        System.out.println("\n═══ Complete! ═══");
    }

    static void primitiveStreamsPerformance() {
        System.out.println("--- Primitive Streams (Better Performance) ---");

        // Using boxed Integer (slower)
        long start = System.currentTimeMillis();
        long sum1 = IntStream.range(0, 1000000)
            .boxed()
            .mapToInt(Integer::intValue)
            .sum();
        long time1 = System.currentTimeMillis() - start;

        // Using IntStream directly (faster)
        start = System.currentTimeMillis();
        long sum2 = IntStream.range(0, 1000000)
            .sum();
        long time2 = System.currentTimeMillis() - start;

        System.out.println("Boxed stream time: " + time1 + "ms");
        System.out.println("Primitive stream time: " + time2 + "ms");
        System.out.println("Speedup: " + (double)time1/time2 + "x");
        System.out.println();
    }

    static void methodReferenceVsLambda() {
        System.out.println("--- Method Reference (Preferred) ---");

        List<String> words = Arrays.asList("hello", "world", "java", "streams");

        // Method reference (cleaner)
        List<String> upper1 = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());

        // Lambda (more verbose)
        List<String> upper2 = words.stream()
            .map(s -> s.toUpperCase())
            .collect(Collectors.toList());

        System.out.println("Both produce same result: " + upper1.equals(upper2));
        System.out.println("Method reference is cleaner and preferred");
        System.out.println();
    }

    static void shortCircuitOperations() {
        System.out.println("--- Short-Circuit Operations ---");

        List<Integer> numbers = IntStream.range(1, 1000000)
            .boxed()
            .collect(Collectors.toList());

        // findFirst stops after finding first match
        long start = System.currentTimeMillis();
        Optional<Integer> first = numbers.stream()
            .filter(n -> n > 100)
            .findFirst();
        long time = System.currentTimeMillis() - start;

        System.out.println("findFirst() found: " + first.get());
        System.out.println("Time: " + time + "ms (short-circuited)");
        System.out.println("Didn't process all " + numbers.size() + " elements");
        System.out.println();
    }
}

