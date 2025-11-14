import java.util.*;
import java.util.stream.*;

public class ParallelStreamsExample {

    public static void main(String[] args) {
        System.out.println("═══ PARALLEL STREAMS EXAMPLES ═══\n");

        sequentialVsParallel();
        parallelPerformance();

        System.out.println("\n═══ Complete! ═══");
    }

    static void sequentialVsParallel() {
        System.out.println("--- Sequential vs Parallel ---");

        List<Integer> numbers = IntStream.range(1, 1000)
            .boxed()
            .collect(Collectors.toList());

        // Sequential
        long start = System.currentTimeMillis();
        long sum1 = numbers.stream()
            .mapToLong(i -> i * i)
            .sum();
        long time1 = System.currentTimeMillis() - start;

        // Parallel
        start = System.currentTimeMillis();
        long sum2 = numbers.parallelStream()
            .mapToLong(i -> i * i)
            .sum();
        long time2 = System.currentTimeMillis() - start;

        System.out.println("Sequential time: " + time1 + "ms, sum: " + sum1);
        System.out.println("Parallel time: " + time2 + "ms, sum: " + sum2);
        System.out.println();
    }

    static void parallelPerformance() {
        System.out.println("--- Parallel Stream Performance ---");

        List<Integer> largeList = IntStream.range(1, 10000)
            .boxed()
            .collect(Collectors.toList());

        // Process in parallel
        long count = largeList.parallelStream()
            .filter(n -> n % 2 == 0)
            .count();

        System.out.println("Processed " + largeList.size() + " elements");
        System.out.println("Found " + count + " even numbers");
        System.out.println("Using parallel stream with " +
            Runtime.getRuntime().availableProcessors() + " processors");
        System.out.println();
    }
}

