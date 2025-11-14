import java.util.*;

/**
 * Comprehensive example demonstrating HashSet in Java.
 * HashSet stores unique elements with no specific ordering.
 *
 * @author Collections Framework Tutorial
 */
public class HashSetExample {

    public static void main(String[] args) {
        System.out.println("=== HASHSET COMPREHENSIVE EXAMPLES ===\n");

        example1_BasicOperations();
        example2_NoDuplicates();
        example3_SetOperations();
        example4_Iteration();
        example5_CustomObjects();
    }

    /**
     * Example 1: Basic HashSet operations
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("-".repeat(50));

        Set<String> set = new HashSet<>();

        // Adding elements
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        System.out.println("After adding: " + set);

        // Size
        System.out.println("Size: " + set.size());

        // Contains
        System.out.println("Contains 'Apple'? " + set.contains("Apple"));
        System.out.println("Contains 'Grape'? " + set.contains("Grape"));

        // Remove
        set.remove("Banana");
        System.out.println("After removing 'Banana': " + set);

        // Is empty
        System.out.println("Is empty? " + set.isEmpty());

        // Clear
        Set<String> temp = new HashSet<>(set);
        temp.clear();
        System.out.println("After clear: " + temp);

        System.out.println();
    }

    /**
     * Example 2: Demonstrating no duplicates allowed
     */
    private static void example2_NoDuplicates() {
        System.out.println("2. NO DUPLICATES ALLOWED");
        System.out.println("-".repeat(50));

        Set<Integer> numbers = new HashSet<>();

        // Try adding duplicates
        System.out.println("Adding 10: " + numbers.add(10));
        System.out.println("Adding 20: " + numbers.add(20));
        System.out.println("Adding 10 again: " + numbers.add(10));  // Returns false
        System.out.println("Adding 30: " + numbers.add(30));

        System.out.println("Set contents: " + numbers);
        System.out.println("Size: " + numbers.size() + " (duplicate not added)");

        // Using HashSet to remove duplicates from list
        List<String> listWithDuplicates = Arrays.asList("A", "B", "A", "C", "B", "D", "A");
        System.out.println("\nList with duplicates: " + listWithDuplicates);

        Set<String> uniqueSet = new HashSet<>(listWithDuplicates);
        System.out.println("After removing duplicates: " + uniqueSet);

        System.out.println();
    }

    /**
     * Example 3: Set operations (union, intersection, difference)
     */
    private static void example3_SetOperations() {
        System.out.println("3. SET OPERATIONS");
        System.out.println("-".repeat(50));

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("\nUnion: " + union);

        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference (set1 - set2)
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (Set1 - Set2): " + difference);

        // Symmetric difference
        Set<Integer> symDiff = new HashSet<>(union);
        symDiff.removeAll(intersection);
        System.out.println("Symmetric Difference: " + symDiff);

        // Subset check
        Set<Integer> subset = new HashSet<>(Arrays.asList(1, 2, 3));
        System.out.println("\nIs " + subset + " subset of Set1? " + set1.containsAll(subset));

        System.out.println();
    }

    /**
     * Example 4: Iteration methods
     */
    private static void example4_Iteration() {
        System.out.println("4. ITERATION METHODS");
        System.out.println("-".repeat(50));

        Set<String> colors = new HashSet<>(
            Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple")
        );

        System.out.println("Set: " + colors);
        System.out.println("Note: Order is not guaranteed!\n");

        // For-each loop
        System.out.println("Method 1: For-each loop");
        for (String color : colors) {
            System.out.println("  " + color);
        }

        // Iterator
        System.out.println("\nMethod 2: Iterator");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            System.out.println("  " + iterator.next());
        }

        // forEach with lambda
        System.out.println("\nMethod 3: forEach with lambda");
        colors.forEach(color -> System.out.println("  " + color));

        // Stream API
        System.out.println("\nMethod 4: Stream API");
        colors.stream()
              .sorted()  // Sort for display
              .forEach(color -> System.out.println("  " + color));

        System.out.println();
    }

    /**
     * Example 5: HashSet with custom objects
     */
    private static void example5_CustomObjects() {
        System.out.println("5. HASHSET WITH CUSTOM OBJECTS");
        System.out.println("-".repeat(50));

        Set<Student> students = new HashSet<>();

        students.add(new Student(1, "Alice"));
        students.add(new Student(2, "Bob"));
        students.add(new Student(3, "Charlie"));
        students.add(new Student(1, "Alice"));  // Duplicate based on equals/hashCode

        System.out.println("Students (duplicates removed):");
        students.forEach(System.out::println);
        System.out.println("Size: " + students.size());

        // Contains check with custom object
        Student searchStudent = new Student(2, "Bob");
        System.out.println("\nContains " + searchStudent + "? " + students.contains(searchStudent));

        System.out.println();
    }
}

/**
 * Custom class demonstrating proper hashCode and equals implementation
 */
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

