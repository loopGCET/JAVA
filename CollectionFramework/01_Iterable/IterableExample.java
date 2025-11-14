import java.util.*;

/**
 * Comprehensive example demonstrating the Iterable interface in Java.
 * Iterable is the root interface that enables for-each loop functionality.
 *
 * @author Collections Framework Tutorial
 */
public class IterableExample {

    public static void main(String[] args) {
        System.out.println("=== ITERABLE INTERFACE EXAMPLES ===\n");

        // Example 1: Using Iterable with ArrayList
        example1_BasicIteration();

        // Example 2: Different ways to iterate
        example2_IterationMethods();

        // Example 3: Custom Iterable class
        example3_CustomIterable();

        // Example 4: forEach with lambda (Java 8+)
        example4_ForEachLambda();

        // Example 5: Iterator methods
        example5_IteratorMethods();
    }

    /**
     * Example 1: Basic iteration using for-each loop
     */
    private static void example1_BasicIteration() {
        System.out.println("1. BASIC ITERATION (For-Each Loop)");
        System.out.println("-".repeat(50));

        // Any Collection implements Iterable
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");

        // For-each loop (possible because List implements Iterable)
        System.out.println("Fruits:");
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }

        System.out.println();
    }

    /**
     * Example 2: Different ways to iterate over collections
     */
    private static void example2_IterationMethods() {
        System.out.println("2. DIFFERENT ITERATION METHODS");
        System.out.println("-".repeat(50));

        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        // Method 1: Enhanced for-loop (for-each)
        System.out.println("Method 1: For-Each Loop");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Method 2: Using Iterator explicitly
        System.out.println("Method 2: Using Iterator");
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");

        // Method 3: forEach with lambda (Java 8+)
        System.out.println("Method 3: forEach with Lambda");
        numbers.forEach(num -> System.out.print(num + " "));
        System.out.println("\n");

        // Method 4: forEach with method reference
        System.out.println("Method 4: forEach with Method Reference");
        numbers.forEach(System.out::print);
        System.out.println("\n\n");
    }

    /**
     * Example 3: Creating a custom Iterable class
     */
    private static void example3_CustomIterable() {
        System.out.println("3. CUSTOM ITERABLE CLASS");
        System.out.println("-".repeat(50));

        // Create a custom range
        NumberRange range = new NumberRange(1, 5);

        System.out.println("Iterating through custom range (1 to 5):");
        for (Integer num : range) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
    }

    /**
     * Example 4: Using forEach with various operations
     */
    private static void example4_ForEachLambda() {
        System.out.println("4. FOREACH WITH LAMBDA OPERATIONS");
        System.out.println("-".repeat(50));

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Simple print
        System.out.println("Names:");
        names.forEach(name -> System.out.println("  Hello, " + name + "!"));

        // With operations
        System.out.println("\nUppercase names:");
        names.forEach(name -> System.out.println("  " + name.toUpperCase()));

        // With filtering logic
        System.out.println("\nNames starting with 'A' or 'C':");
        names.forEach(name -> {
            if (name.startsWith("A") || name.startsWith("C")) {
                System.out.println("  ✓ " + name);
            }
        });

        System.out.println();
    }

    /**
     * Example 5: Iterator methods - hasNext(), next(), remove()
     */
    private static void example5_IteratorMethods() {
        System.out.println("5. ITERATOR METHODS");
        System.out.println("-".repeat(50));

        List<String> languages = new ArrayList<>(
            Arrays.asList("Java", "Python", "JavaScript", "C++", "Ruby")
        );

        System.out.println("Original list: " + languages);

        // Using iterator to remove elements
        Iterator<String> iterator = languages.iterator();
        System.out.println("\nRemoving languages containing 'a':");

        while (iterator.hasNext()) {
            String lang = iterator.next();
            if (lang.contains("a")) {
                System.out.println("  Removing: " + lang);
                iterator.remove(); // Safe removal during iteration
            }
        }

        System.out.println("\nAfter removal: " + languages);
        System.out.println();
    }
}

/**
 * Custom Iterable class - demonstrates how to make your own class iterable
 */
class NumberRange implements Iterable<Integer> {
    private int start;
    private int end;

    public NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int current = start;

            @Override
            public boolean hasNext() {
                return current <= end;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }
}

