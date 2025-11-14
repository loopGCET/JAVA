import java.util.*;
import java.util.stream.Collectors;

/**
 * Comprehensive example demonstrating HashMap in Java.
 * HashMap stores key-value pairs with fast O(1) lookups.
 *
 * @author Collections Framework Tutorial
 */
public class HashMapExample {

    public static void main(String[] args) {
        System.out.println("=== HASHMAP COMPREHENSIVE EXAMPLES ===\n");

        example1_BasicOperations();
        example2_Iteration();
        example3_Java8Methods();
        example4_FrequencyCounter();
        example5_CustomObjects();
    }

    /**
     * Example 1: Basic HashMap operations
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("-".repeat(50));

        Map<String, Integer> scores = new HashMap<>();

        // put() - Add key-value pairs
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("David", 88);
        System.out.println("Scores: " + scores);

        // get() - Get value by key
        System.out.println("Alice's score: " + scores.get("Alice"));
        System.out.println("Eve's score: " + scores.get("Eve"));  // Returns null

        // getOrDefault() - Get with fallback
        System.out.println("Eve's score (default): " + scores.getOrDefault("Eve", 0));

        // containsKey() - Check if key exists
        System.out.println("Contains 'Bob'? " + scores.containsKey("Bob"));
        System.out.println("Contains 'Eve'? " + scores.containsKey("Eve"));

        // containsValue() - Check if value exists
        System.out.println("Contains score 92? " + scores.containsValue(92));

        // size()
        System.out.println("Number of students: " + scores.size());

        // Update existing key (overwrite)
        scores.put("Alice", 98);
        System.out.println("After updating Alice: " + scores);

        // remove() - Remove key-value pair
        scores.remove("David");
        System.out.println("After removing David: " + scores);

        // isEmpty()
        System.out.println("Is empty? " + scores.isEmpty());

        System.out.println();
    }

    /**
     * Example 2: Different ways to iterate over HashMap
     */
    private static void example2_Iteration() {
        System.out.println("2. ITERATION METHODS");
        System.out.println("-".repeat(50));

        Map<String, String> countries = new HashMap<>();
        countries.put("US", "United States");
        countries.put("UK", "United Kingdom");
        countries.put("IN", "India");
        countries.put("JP", "Japan");

        // Method 1: Iterate over entrySet
        System.out.println("Method 1: EntrySet");
        for (Map.Entry<String, String> entry : countries.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // Method 2: Iterate over keySet
        System.out.println("\nMethod 2: KeySet");
        for (String key : countries.keySet()) {
            System.out.println("  " + key + " -> " + countries.get(key));
        }

        // Method 3: Iterate over values
        System.out.println("\nMethod 3: Values");
        for (String value : countries.values()) {
            System.out.println("  " + value);
        }

        // Method 4: forEach with lambda (Java 8+)
        System.out.println("\nMethod 4: forEach with Lambda");
        countries.forEach((key, value) ->
            System.out.println("  " + key + " -> " + value)
        );

        // Method 5: Stream API
        System.out.println("\nMethod 5: Stream API");
        countries.entrySet().stream()
                  .sorted(Map.Entry.comparingByKey())
                  .forEach(entry ->
                      System.out.println("  " + entry.getKey() + " -> " + entry.getValue())
                  );

        System.out.println();
    }

    /**
     * Example 3: Java 8+ methods
     */
    private static void example3_Java8Methods() {
        System.out.println("3. JAVA 8+ METHODS");
        System.out.println("-".repeat(50));

        Map<String, Integer> inventory = new HashMap<>();

        // putIfAbsent() - Add only if key doesn't exist
        inventory.put("Apple", 10);
        inventory.putIfAbsent("Apple", 20);  // Won't update
        inventory.putIfAbsent("Banana", 15);  // Will add
        System.out.println("After putIfAbsent: " + inventory);

        // compute() - Compute new value for key
        inventory.compute("Apple", (key, value) -> value + 5);
        System.out.println("After compute (Apple +5): " + inventory);

        // computeIfPresent() - Compute only if key exists
        inventory.computeIfPresent("Banana", (key, value) -> value * 2);
        inventory.computeIfPresent("Cherry", (key, value) -> value * 2);  // No effect
        System.out.println("After computeIfPresent: " + inventory);

        // computeIfAbsent() - Compute only if key doesn't exist
        inventory.computeIfAbsent("Cherry", key -> 8);
        inventory.computeIfAbsent("Apple", key -> 100);  // No effect
        System.out.println("After computeIfAbsent: " + inventory);

        // merge() - Merge values
        inventory.merge("Apple", 5, Integer::sum);  // 15 + 5 = 20
        inventory.merge("Date", 12, Integer::sum);  // New entry
        System.out.println("After merge: " + inventory);

        // replace() - Replace value for existing key
        inventory.replace("Banana", 100);
        inventory.replace("Grape", 50);  // No effect (key doesn't exist)
        System.out.println("After replace: " + inventory);

        // replaceAll() - Update all values
        inventory.replaceAll((key, value) -> value + 1);
        System.out.println("After replaceAll (+1 to all): " + inventory);

        System.out.println();
    }

    /**
     * Example 4: Using HashMap for frequency counting
     */
    private static void example4_FrequencyCounter() {
        System.out.println("4. FREQUENCY COUNTER");
        System.out.println("-".repeat(50));

        String text = "hello world hello java world";
        String[] words = text.split(" ");

        // Method 1: Traditional approach
        Map<String, Integer> wordCount1 = new HashMap<>();
        for (String word : words) {
            wordCount1.put(word, wordCount1.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word count (traditional): " + wordCount1);

        // Method 2: Using merge()
        Map<String, Integer> wordCount2 = new HashMap<>();
        for (String word : words) {
            wordCount2.merge(word, 1, Integer::sum);
        }
        System.out.println("Word count (merge): " + wordCount2);

        // Method 3: Using computeIfAbsent() for list of indices
        Map<String, List<Integer>> wordIndices = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            final int index = i;
            wordIndices.computeIfAbsent(words[i], k -> new ArrayList<>()).add(index);
        }
        System.out.println("\nWord indices: " + wordIndices);

        // Character frequency
        String str = "hello";
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : str.toCharArray()) {
            charCount.merge(c, 1, Integer::sum);
        }
        System.out.println("\nCharacter frequency in 'hello': " + charCount);

        // Find most frequent word
        String mostFrequent = wordCount1.entrySet().stream()
                                       .max(Map.Entry.comparingByValue())
                                       .map(Map.Entry::getKey)
                                       .orElse(null);
        System.out.println("\nMost frequent word: " + mostFrequent);

        System.out.println();
    }

    /**
     * Example 5: HashMap with custom objects as keys
     */
    private static void example5_CustomObjects() {
        System.out.println("5. CUSTOM OBJECTS AS KEYS");
        System.out.println("-".repeat(50));

        Map<Employee, String> employeeMap = new HashMap<>();

        Employee emp1 = new Employee(101, "Alice");
        Employee emp2 = new Employee(102, "Bob");
        Employee emp3 = new Employee(103, "Charlie");

        employeeMap.put(emp1, "Engineering");
        employeeMap.put(emp2, "Sales");
        employeeMap.put(emp3, "Marketing");

        System.out.println("Employee departments:");
        employeeMap.forEach((emp, dept) ->
            System.out.println("  " + emp + " -> " + dept)
        );

        // Lookup using equivalent object
        Employee searchEmp = new Employee(102, "Bob");
        String department = employeeMap.get(searchEmp);
        System.out.println("\nDepartment for " + searchEmp + ": " + department);

        // Demonstrating importance of hashCode and equals
        System.out.println("\nContains key " + searchEmp + "? " +
                          employeeMap.containsKey(searchEmp));

        System.out.println();
    }
}

/**
 * Custom class demonstrating proper hashCode and equals for HashMap keys
 */
class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}

