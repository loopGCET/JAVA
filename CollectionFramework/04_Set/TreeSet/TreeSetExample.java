import java.util.*;

/**
 * Comprehensive example demonstrating TreeSet in Java.
 * TreeSet maintains elements in sorted order using a Red-Black tree.
 *
 * @author Collections Framework Tutorial
 */
public class TreeSetExample {

    public static void main(String[] args) {
        System.out.println("=== TREESET COMPREHENSIVE EXAMPLES ===\n");

        example1_BasicOperations();
        example2_SortedOrder();
        example3_NavigableSetOperations();
        example4_RangeOperations();
        example5_CustomComparator();
    }

    /**
     * Example 1: Basic TreeSet operations
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("-".repeat(50));

        TreeSet<Integer> numbers = new TreeSet<>();

        // Adding elements (automatically sorted)
        numbers.add(50);
        numbers.add(20);
        numbers.add(80);
        numbers.add(10);
        numbers.add(40);
        System.out.println("After adding (auto-sorted): " + numbers);

        // Try adding duplicate
        boolean added = numbers.add(20);
        System.out.println("Adding duplicate 20: " + added);
        System.out.println("Set after duplicate attempt: " + numbers);

        // Contains
        System.out.println("Contains 40? " + numbers.contains(40));

        // Remove
        numbers.remove(20);
        System.out.println("After removing 20: " + numbers);

        // Size
        System.out.println("Size: " + numbers.size());

        System.out.println();
    }

    /**
     * Example 2: Demonstrating sorted order
     */
    private static void example2_SortedOrder() {
        System.out.println("2. SORTED ORDER");
        System.out.println("-".repeat(50));

        TreeSet<String> names = new TreeSet<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("David");
        names.add("Bob");

        System.out.println("Names (alphabetically sorted): " + names);

        // First and last elements
        System.out.println("First: " + names.first());
        System.out.println("Last: " + names.last());

        // Poll operations (retrieve and remove)
        System.out.println("Poll first: " + names.pollFirst());
        System.out.println("Poll last: " + names.pollLast());
        System.out.println("After polling: " + names);

        System.out.println();
    }

    /**
     * Example 3: NavigableSet operations
     */
    private static void example3_NavigableSetOperations() {
        System.out.println("3. NAVIGABLESET OPERATIONS");
        System.out.println("-".repeat(50));

        TreeSet<Integer> scores = new TreeSet<>(
            Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
        );
        System.out.println("Scores: " + scores);

        // ceiling() - Least element >= given element
        System.out.println("Ceiling of 45: " + scores.ceiling(45));  // 50
        System.out.println("Ceiling of 50: " + scores.ceiling(50));  // 50

        // floor() - Greatest element <= given element
        System.out.println("Floor of 45: " + scores.floor(45));      // 40
        System.out.println("Floor of 50: " + scores.floor(50));      // 50

        // higher() - Least element > given element
        System.out.println("Higher than 50: " + scores.higher(50));  // 60

        // lower() - Greatest element < given element
        System.out.println("Lower than 50: " + scores.lower(50));    // 40

        // Descending set
        NavigableSet<Integer> descending = scores.descendingSet();
        System.out.println("\nDescending order: " + descending);

        // Descending iterator
        System.out.print("Descending iterator: ");
        Iterator<Integer> descIter = scores.descendingIterator();
        while (descIter.hasNext()) {
            System.out.print(descIter.next() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Example 4: Range operations (subSet, headSet, tailSet)
     */
    private static void example4_RangeOperations() {
        System.out.println("4. RANGE OPERATIONS");
        System.out.println("-".repeat(50));

        TreeSet<Integer> numbers = new TreeSet<>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        );
        System.out.println("Numbers: " + numbers);

        // subSet(from, to) - Elements from 'from' (inclusive) to 'to' (exclusive)
        SortedSet<Integer> subset = numbers.subSet(3, 8);
        System.out.println("SubSet(3, 8): " + subset);

        // headSet(to) - Elements less than 'to'
        SortedSet<Integer> headSet = numbers.headSet(5);
        System.out.println("HeadSet(5): " + headSet);

        // tailSet(from) - Elements greater than or equal to 'from'
        SortedSet<Integer> tailSet = numbers.tailSet(7);
        System.out.println("TailSet(7): " + tailSet);

        // NavigableSet versions with inclusive/exclusive options
        NavigableSet<Integer> subset2 = numbers.subSet(3, true, 8, true);
        System.out.println("SubSet(3, true, 8, true): " + subset2);

        System.out.println();
    }

    /**
     * Example 5: Custom comparator for reverse order or custom sorting
     */
    private static void example5_CustomComparator() {
        System.out.println("5. CUSTOM COMPARATOR");
        System.out.println("-".repeat(50));

        // Reverse order
        TreeSet<Integer> reverseNumbers = new TreeSet<>(Collections.reverseOrder());
        reverseNumbers.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Reverse order: " + reverseNumbers);

        // Custom objects with natural ordering
        TreeSet<Task> tasks = new TreeSet<>();
        tasks.add(new Task("High priority", 1));
        tasks.add(new Task("Low priority", 3));
        tasks.add(new Task("Medium priority", 2));

        System.out.println("\nTasks (by priority):");
        tasks.forEach(System.out::println);

        // Custom comparator - by name length
        TreeSet<String> namesByLength = new TreeSet<>(
            Comparator.comparingInt(String::length)
                     .thenComparing(String::compareTo)
        );
        namesByLength.addAll(Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve"));
        System.out.println("\nNames by length: " + namesByLength);

        System.out.println();
    }
}

/**
 * Custom class implementing Comparable for natural ordering
 */
class Task implements Comparable<Task> {
    private String name;
    private int priority;  // 1 = highest, 3 = lowest

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + "}";
    }
}

