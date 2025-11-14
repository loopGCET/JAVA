import java.util.*;

/**
 * Comprehensive example demonstrating LinkedHashSet in Java.
 * LinkedHashSet maintains insertion order while ensuring uniqueness.
 *
 * @author Collections Framework Tutorial
 */
public class LinkedHashSetExample {

    public static void main(String[] args) {
        System.out.println("=== LINKEDHASHSET COMPREHENSIVE EXAMPLES ===\n");

        example1_InsertionOrderPreserved();
        example2_RemoveDuplicatesKeepOrder();
        example3_SetOperationsWithOrder();
        example4_ComparisonWithOtherSets();
        example5_PracticalUseCases();
    }

    /**
     * Example 1: Insertion order is preserved
     */
    private static void example1_InsertionOrderPreserved() {
        System.out.println("1. INSERTION ORDER PRESERVED");
        System.out.println("-".repeat(50));

        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();
        HashSet<String> hashSet = new HashSet<>();

        // Add elements in specific order
        String[] items = {"Banana", "Apple", "Cherry", "Date", "Elderberry"};
        for (String item : items) {
            linkedSet.add(item);
            hashSet.add(item);
        }

        System.out.println("Insertion order: " + Arrays.toString(items));
        System.out.println("\nLinkedHashSet (preserves order):");
        System.out.println("  " + linkedSet);

        System.out.println("\nHashSet (no order guarantee):");
        System.out.println("  " + hashSet);

        System.out.println("\n✅ LinkedHashSet maintains insertion order!");

        System.out.println();
    }

    /**
     * Example 2: Remove duplicates while keeping order
     */
    private static void example2_RemoveDuplicatesKeepOrder() {
        System.out.println("2. REMOVE DUPLICATES WHILE KEEPING ORDER");
        System.out.println("-".repeat(50));

        List<String> listWithDuplicates = Arrays.asList(
            "Java", "Python", "C++", "Java", "JavaScript",
            "Python", "Ruby", "C++", "Go", "Java"
        );

        System.out.println("Original list:");
        System.out.println("  " + listWithDuplicates);

        // Using HashSet - loses order
        Set<String> hashSet = new HashSet<>(listWithDuplicates);
        System.out.println("\nUsing HashSet (order lost):");
        System.out.println("  " + hashSet);

        // Using LinkedHashSet - preserves order
        Set<String> linkedHashSet = new LinkedHashSet<>(listWithDuplicates);
        System.out.println("\nUsing LinkedHashSet (order preserved):");
        System.out.println("  " + linkedHashSet);

        // Convert back to list if needed
        List<String> uniqueList = new ArrayList<>(linkedHashSet);
        System.out.println("\nConvert back to List:");
        System.out.println("  " + uniqueList);

        System.out.println("\n✅ Perfect for removing duplicates while maintaining order!");

        System.out.println();
    }

    /**
     * Example 3: Set operations with order preservation
     */
    private static void example3_SetOperationsWithOrder() {
        System.out.println("3. SET OPERATIONS WITH ORDER");
        System.out.println("-".repeat(50));

        LinkedHashSet<Integer> set1 = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedHashSet<Integer> set2 = new LinkedHashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Union (preserves order from set1, then set2)
        LinkedHashSet<Integer> union = new LinkedHashSet<>(set1);
        union.addAll(set2);
        System.out.println("\nUnion: " + union);

        // Intersection (preserves order from set1)
        LinkedHashSet<Integer> intersection = new LinkedHashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference (preserves order from set1)
        LinkedHashSet<Integer> difference = new LinkedHashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);

        System.out.println("\n✅ All operations maintain insertion order!");

        System.out.println();
    }

    /**
     * Example 4: Comparison with HashSet and TreeSet
     */
    private static void example4_ComparisonWithOtherSets() {
        System.out.println("4. COMPARISON: HASHSET vs LINKEDHASHSET vs TREESET");
        System.out.println("-".repeat(50));

        String[] data = {"Dog", "Cat", "Bird", "Ant", "Elephant"};

        // HashSet - no order
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(data));
        System.out.println("HashSet (no guaranteed order):");
        System.out.println("  " + hashSet);

        // LinkedHashSet - insertion order
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(data));
        System.out.println("\nLinkedHashSet (insertion order):");
        System.out.println("  " + linkedHashSet);

        // TreeSet - sorted order
        TreeSet<String> treeSet = new TreeSet<>(Arrays.asList(data));
        System.out.println("\nTreeSet (sorted order):");
        System.out.println("  " + treeSet);

        // Performance comparison
        System.out.println("\n📊 PERFORMANCE:");
        int iterations = 100000;

        long start = System.nanoTime();
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < iterations; i++) hs.add(i);
        long hashSetTime = System.nanoTime() - start;

        start = System.nanoTime();
        Set<Integer> lhs = new LinkedHashSet<>();
        for (int i = 0; i < iterations; i++) lhs.add(i);
        long linkedHashSetTime = System.nanoTime() - start;

        start = System.nanoTime();
        Set<Integer> ts = new TreeSet<>();
        for (int i = 0; i < iterations; i++) ts.add(i);
        long treeSetTime = System.nanoTime() - start;

        System.out.println("  Adding " + iterations + " elements:");
        System.out.println("    HashSet:       " + hashSetTime / 1_000_000 + " ms (fastest)");
        System.out.println("    LinkedHashSet: " + linkedHashSetTime / 1_000_000 + " ms");
        System.out.println("    TreeSet:       " + treeSetTime / 1_000_000 + " ms (slowest)");

        System.out.println("\n✅ LinkedHashSet: Good balance of speed and order!");

        System.out.println();
    }

    /**
     * Example 5: Practical use cases
     */
    private static void example5_PracticalUseCases() {
        System.out.println("5. PRACTICAL USE CASES");
        System.out.println("-".repeat(50));

        // Use case 1: Tracking visited pages in order
        System.out.println("Use Case 1: Browser History (unique pages in order)");
        LinkedHashSet<String> browserHistory = new LinkedHashSet<>();
        browserHistory.add("google.com");
        browserHistory.add("github.com");
        browserHistory.add("stackoverflow.com");
        browserHistory.add("google.com");  // Duplicate - not added
        browserHistory.add("linkedin.com");

        System.out.println("  History: " + browserHistory);
        System.out.println("  ✅ No duplicates, maintains visit order");

        // Use case 2: Unique tags in order
        System.out.println("\nUse Case 2: Blog Post Tags (unique, in order)");
        LinkedHashSet<String> tags = new LinkedHashSet<>();
        tags.add("Java");
        tags.add("Programming");
        tags.add("Tutorial");
        tags.add("Java");  // Duplicate
        tags.add("Collections");

        System.out.println("  Tags: " + tags);
        System.out.println("  ✅ Maintains tag order for display");

        // Use case 3: Processing queue with uniqueness
        System.out.println("\nUse Case 3: Task Queue (no duplicate tasks)");
        LinkedHashSet<Task> taskQueue = new LinkedHashSet<>();
        taskQueue.add(new Task(1, "Backup database"));
        taskQueue.add(new Task(2, "Send emails"));
        taskQueue.add(new Task(3, "Generate report"));
        taskQueue.add(new Task(1, "Backup database"));  // Duplicate - not added

        System.out.println("  Tasks to process:");
        for (Task task : taskQueue) {
            System.out.println("    " + task);
        }
        System.out.println("  ✅ Processes tasks in order, no duplicates");

        // Use case 4: Maintaining unique sequence
        System.out.println("\nUse Case 4: User Activity Log (unique events in order)");
        List<String> activities = Arrays.asList(
            "login", "view_profile", "edit_profile", "view_profile",
            "logout", "login", "view_profile"
        );

        LinkedHashSet<String> uniqueActivities = new LinkedHashSet<>(activities);
        System.out.println("  All activities: " + activities);
        System.out.println("  Unique activities: " + uniqueActivities);
        System.out.println("  ✅ Shows unique user actions in order");

        System.out.println();
    }
}

/**
 * Task class for demonstration
 */
class Task {
    private int id;
    private String name;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task #" + id + ": " + name;
    }
}

