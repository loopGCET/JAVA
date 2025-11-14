import java.util.*;
import java.util.stream.Collectors;

/**
 * Comprehensive example demonstrating the Collection interface in Java.
 * Collection is the root interface for List, Set, and Queue.
 *
 * @author Collections Framework Tutorial
 */
public class CollectionExample {

    public static void main(String[] args) {
        System.out.println("=== COLLECTION INTERFACE EXAMPLES ===\n");

        // Example 1: Basic operations
        example1_BasicOperations();

        // Example 2: Bulk operations
        example2_BulkOperations();

        // Example 3: Query operations
        example3_QueryOperations();

        // Example 4: Array operations
        example4_ArrayOperations();

        // Example 5: Java 8+ operations
        example5_Java8Operations();

        // Example 6: Polymorphic behavior
        example6_PolymorphicBehavior();
    }

    /**
     * Example 1: Basic Collection operations
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("-".repeat(50));

        Collection<String> collection = new ArrayList<>();

        // add() - Add single element
        System.out.println("Adding elements:");
        collection.add("Java");
        collection.add("Python");
        collection.add("JavaScript");
        System.out.println("Collection: " + collection);

        // size() - Get number of elements
        System.out.println("Size: " + collection.size());

        // isEmpty() - Check if empty
        System.out.println("Is empty? " + collection.isEmpty());

        // remove() - Remove single element
        collection.remove("Python");
        System.out.println("After removing 'Python': " + collection);

        // clear() - Remove all elements
        Collection<String> temp = new ArrayList<>(collection);
        temp.clear();
        System.out.println("After clear(): " + temp);
        System.out.println("Is empty now? " + temp.isEmpty());

        System.out.println();
    }

    /**
     * Example 2: Bulk operations
     */
    private static void example2_BulkOperations() {
        System.out.println("2. BULK OPERATIONS");
        System.out.println("-".repeat(50));

        Collection<Integer> col1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collection<Integer> col2 = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8));

        System.out.println("Collection 1: " + col1);
        System.out.println("Collection 2: " + col2);

        // addAll() - Add all elements from another collection
        Collection<Integer> union = new ArrayList<>(col1);
        union.addAll(col2);
        System.out.println("\nUnion (addAll): " + union);

        // retainAll() - Retain only common elements (intersection)
        Collection<Integer> intersection = new ArrayList<>(col1);
        intersection.retainAll(col2);
        System.out.println("Intersection (retainAll): " + intersection);

        // removeAll() - Remove all elements present in another collection
        Collection<Integer> difference = new ArrayList<>(col1);
        difference.removeAll(col2);
        System.out.println("Difference (removeAll): " + difference);

        System.out.println();
    }

    /**
     * Example 3: Query operations
     */
    private static void example3_QueryOperations() {
        System.out.println("3. QUERY OPERATIONS");
        System.out.println("-".repeat(50));

        Collection<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("C++");
        languages.add("JavaScript");

        System.out.println("Languages: " + languages);

        // contains() - Check if element exists
        System.out.println("\nContains 'Java'? " + languages.contains("Java"));
        System.out.println("Contains 'Ruby'? " + languages.contains("Ruby"));

        // containsAll() - Check if all elements exist
        Collection<String> subset = Arrays.asList("Java", "Python");
        System.out.println("\nContains all " + subset + "? " +
                          languages.containsAll(subset));

        Collection<String> notSubset = Arrays.asList("Java", "Ruby");
        System.out.println("Contains all " + notSubset + "? " +
                          languages.containsAll(notSubset));

        System.out.println();
    }

    /**
     * Example 4: Array operations
     */
    private static void example4_ArrayOperations() {
        System.out.println("4. ARRAY OPERATIONS");
        System.out.println("-".repeat(50));

        Collection<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        System.out.println("Collection: " + fruits);

        // toArray() - Convert to Object array
        Object[] objArray = fruits.toArray();
        System.out.println("\nObject Array:");
        for (Object obj : objArray) {
            System.out.println("  - " + obj);
        }

        // toArray(T[] a) - Convert to typed array
        String[] strArray = fruits.toArray(new String[0]);
        System.out.println("\nString Array:");
        for (String str : strArray) {
            System.out.println("  - " + str);
        }

        System.out.println();
    }

    /**
     * Example 5: Java 8+ operations
     */
    private static void example5_Java8Operations() {
        System.out.println("5. JAVA 8+ OPERATIONS");
        System.out.println("-".repeat(50));

        Collection<Integer> numbers = new ArrayList<>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        );

        System.out.println("Original: " + numbers);

        // forEach() - Perform action on each element
        System.out.println("\nforEach - Print all:");
        numbers.forEach(n -> System.out.print(n + " "));
        System.out.println();

        // removeIf() - Remove elements matching predicate
        Collection<Integer> evenRemoved = new ArrayList<>(numbers);
        evenRemoved.removeIf(n -> n % 2 == 0);
        System.out.println("\nAfter removeIf (even numbers): " + evenRemoved);

        // stream() - Get a stream
        System.out.println("\nStream operations:");
        long count = numbers.stream()
                           .filter(n -> n > 5)
                           .count();
        System.out.println("Count of numbers > 5: " + count);

        List<Integer> squared = numbers.stream()
                                      .map(n -> n * n)
                                      .collect(Collectors.toList());
        System.out.println("Squared: " + squared);

        // parallelStream() - Parallel processing
        int sum = numbers.parallelStream()
                        .mapToInt(Integer::intValue)
                        .sum();
        System.out.println("Sum (parallel): " + sum);

        System.out.println();
    }

    /**
     * Example 6: Polymorphic behavior - same method works with different collections
     */
    private static void example6_PolymorphicBehavior() {
        System.out.println("6. POLYMORPHIC BEHAVIOR");
        System.out.println("-".repeat(50));

        // Method works with any Collection type
        System.out.println("Testing with ArrayList:");
        printCollectionInfo(new ArrayList<>(Arrays.asList("A", "B", "C", "A")));

        System.out.println("\nTesting with HashSet:");
        printCollectionInfo(new HashSet<>(Arrays.asList("A", "B", "C", "A")));

        System.out.println("\nTesting with LinkedList:");
        printCollectionInfo(new LinkedList<>(Arrays.asList("A", "B", "C", "A")));

        System.out.println();
    }

    /**
     * Utility method that works with any Collection
     */
    private static void printCollectionInfo(Collection<String> collection) {
        System.out.println("  Type: " + collection.getClass().getSimpleName());
        System.out.println("  Elements: " + collection);
        System.out.println("  Size: " + collection.size());
        System.out.println("  Contains 'A': " + collection.contains("A"));
    }
}

