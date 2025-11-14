import java.util.*;
import java.util.stream.*;

/**
 * Stream Basics Examples
 * @author GitHub Copilot
 * @topic Streams API - Basics
 */
public class StreamBasicsExample {

    public static void main(String[] args) {
        System.out.println("═══ STREAM BASICS EXAMPLES ═══\n");

        example1_CreatingStreams();
        example2_BasicPipeline();
        example3_StreamFromArray();
        example4_InfiniteStreams();
        example5_PrimitiveStreams();

        System.out.println("\n═══ All examples completed! ═══");
    }

    public static void example1_CreatingStreams() {
        System.out.println("--- Example 1: Creating Streams ---");

        // From Collection
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        Stream<String> stream1 = list.stream();
        System.out.println("From List: " + stream1.count() + " elements");

        // Using Stream.of()
        Stream<String> stream2 = Stream.of("one", "two", "three");
        System.out.println("Stream.of(): " + stream2.collect(Collectors.toList()));

        // From Array
        String[] array = {"a", "b", "c"};
        Stream<String> stream3 = Arrays.stream(array);
        System.out.println("From Array: " + stream3.collect(Collectors.toList()));

        // Empty Stream
        Stream<String> empty = Stream.empty();
        System.out.println("Empty stream count: " + empty.count());

        System.out.println();
    }

    public static void example2_BasicPipeline() {
        System.out.println("--- Example 2: Basic Stream Pipeline ---");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenDoubled = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .collect(Collectors.toList());

        System.out.println("Original: " + numbers);
        System.out.println("Even numbers doubled: " + evenDoubled);

        System.out.println();
    }

    public static void example3_StreamFromArray() {
        System.out.println("--- Example 3: Stream from Array ---");

        Integer[] numbers = {5, 2, 8, 1, 9};

        List<Integer> sorted = Arrays.stream(numbers)
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Original array: " + Arrays.toString(numbers));
        System.out.println("Sorted: " + sorted);

        System.out.println();
    }

    public static void example4_InfiniteStreams() {
        System.out.println("--- Example 4: Infinite Streams (Limited) ---");

        // Stream.iterate()
        List<Integer> first10Evens = Stream.iterate(0, n -> n + 2)
            .limit(10)
            .collect(Collectors.toList());
        System.out.println("First 10 even numbers: " + first10Evens);

        // Stream.generate()
        List<Double> random5 = Stream.generate(Math::random)
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("5 random numbers generated");

        System.out.println();
    }

    public static void example5_PrimitiveStreams() {
        System.out.println("--- Example 5: Primitive Streams ---");

        // IntStream
        int sum = IntStream.range(1, 11).sum();
        System.out.println("Sum of 1-10: " + sum);

        // LongStream
        long product = LongStream.rangeClosed(1, 5)
            .reduce(1, (a, b) -> a * b);
        System.out.println("Product of 1-5: " + product);

        // DoubleStream
        double average = DoubleStream.of(1.5, 2.5, 3.5)
            .average()
            .orElse(0.0);
        System.out.println("Average: " + average);

        System.out.println();
    }
}

