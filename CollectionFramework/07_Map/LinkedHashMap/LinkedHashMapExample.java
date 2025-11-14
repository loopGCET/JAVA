import java.util.*;

/**
 * Comprehensive example demonstrating LinkedHashMap in Java.
 * LinkedHashMap maintains insertion order (or access order) with HashMap performance.
 *
 * @author Collections Framework Tutorial
 */
public class LinkedHashMapExample {

    public static void main(String[] args) {
        System.out.println("=== LINKEDHASHMAP COMPREHENSIVE EXAMPLES ===\n");

        example1_InsertionOrderPreserved();
        example2_AccessOrderMode();
        example3_LRUCacheImplementation();
        example4_ComparisonWithOtherMaps();
        example5_PracticalUseCases();
    }

    /**
     * Example 1: Insertion order is preserved (default)
     */
    private static void example1_InsertionOrderPreserved() {
        System.out.println("1. INSERTION ORDER PRESERVED");
        System.out.println("-".repeat(50));

        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Add in specific order
        String[] keys = {"Banana", "Apple", "Cherry", "Date", "Elderberry"};
        for (int i = 0; i < keys.length; i++) {
            linkedMap.put(keys[i], i + 1);
            hashMap.put(keys[i], i + 1);
        }

        System.out.println("LinkedHashMap (preserves insertion order):");
        linkedMap.forEach((key, value) ->
            System.out.println("  " + key + " = " + value)
        );

        System.out.println("\nHashMap (no order guarantee):");
        hashMap.forEach((key, value) ->
            System.out.println("  " + key + " = " + value)
        );

        System.out.println("\n✅ LinkedHashMap maintains insertion order!");

        System.out.println();
    }

    /**
     * Example 2: Access order mode (for LRU behavior)
     */
    private static void example2_AccessOrderMode() {
        System.out.println("2. ACCESS ORDER MODE");
        System.out.println("-".repeat(50));

        // Create with access order = true
        LinkedHashMap<String, Integer> accessOrderMap =
            new LinkedHashMap<>(16, 0.75f, true);

        accessOrderMap.put("A", 1);
        accessOrderMap.put("B", 2);
        accessOrderMap.put("C", 3);
        accessOrderMap.put("D", 4);

        System.out.println("Initial order:");
        printMap(accessOrderMap);

        // Access some elements (moves them to end)
        System.out.println("\nAccessing 'B'...");
        accessOrderMap.get("B");
        System.out.println("After accessing 'B':");
        printMap(accessOrderMap);

        System.out.println("\nAccessing 'A'...");
        accessOrderMap.get("A");
        System.out.println("After accessing 'A':");
        printMap(accessOrderMap);

        // Update existing key (also moves to end)
        System.out.println("\nUpdating 'C' value...");
        accessOrderMap.put("C", 30);
        System.out.println("After updating 'C':");
        printMap(accessOrderMap);

        System.out.println("\n✅ Access order mode: Most recently used items move to end!");
        System.out.println("   Perfect for LRU (Least Recently Used) cache!");

        System.out.println();
    }

    /**
     * Example 3: LRU Cache implementation
     */
    private static void example3_LRUCacheImplementation() {
        System.out.println("3. LRU CACHE IMPLEMENTATION");
        System.out.println("-".repeat(50));

        // Create LRU cache with capacity 3
        LRUCache<String, String> cache = new LRUCache<>(3);

        System.out.println("LRU Cache (capacity = 3)\n");

        // Add items
        cache.put("user1", "Alice");
        System.out.println("Added user1: " + cache);

        cache.put("user2", "Bob");
        System.out.println("Added user2: " + cache);

        cache.put("user3", "Charlie");
        System.out.println("Added user3: " + cache);

        // Cache is full, add one more (removes eldest)
        cache.put("user4", "David");
        System.out.println("Added user4: " + cache);
        System.out.println("  ⚠️  user1 was automatically removed (LRU)!");

        // Access user2 (moves it to end, making it most recently used)
        cache.get("user2");
        System.out.println("\nAccessed user2: " + cache);

        // Add another (now user3 is least recently used)
        cache.put("user5", "Eve");
        System.out.println("Added user5: " + cache);
        System.out.println("  ⚠️  user3 was automatically removed (LRU)!");

        System.out.println("\n✅ LRU Cache automatically removes least recently used entries!");

        System.out.println();
    }

    /**
     * Example 4: Comparison with HashMap and TreeMap
     */
    private static void example4_ComparisonWithOtherMaps() {
        System.out.println("4. COMPARISON WITH OTHER MAPS");
        System.out.println("-".repeat(50));

        String[] data = {"Dog", "Cat", "Bird", "Ant", "Elephant"};

        // HashMap - no order
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            hashMap.put(data[i], i + 1);
        }
        System.out.println("HashMap (no guaranteed order):");
        System.out.println("  " + hashMap.keySet());

        // LinkedHashMap - insertion order
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < data.length; i++) {
            linkedHashMap.put(data[i], i + 1);
        }
        System.out.println("\nLinkedHashMap (insertion order):");
        System.out.println("  " + linkedHashMap.keySet());

        // TreeMap - sorted order
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < data.length; i++) {
            treeMap.put(data[i], i + 1);
        }
        System.out.println("\nTreeMap (sorted order):");
        System.out.println("  " + treeMap.keySet());

        // Performance comparison
        System.out.println("\n📊 PERFORMANCE:");
        int iterations = 100000;

        long start = System.nanoTime();
        Map<Integer, String> hm = new HashMap<>();
        for (int i = 0; i < iterations; i++) hm.put(i, "value" + i);
        long hashMapTime = System.nanoTime() - start;

        start = System.nanoTime();
        Map<Integer, String> lhm = new LinkedHashMap<>();
        for (int i = 0; i < iterations; i++) lhm.put(i, "value" + i);
        long linkedHashMapTime = System.nanoTime() - start;

        start = System.nanoTime();
        Map<Integer, String> tm = new TreeMap<>();
        for (int i = 0; i < iterations; i++) tm.put(i, "value" + i);
        long treeMapTime = System.nanoTime() - start;

        System.out.println("  Adding " + iterations + " entries:");
        System.out.println("    HashMap:       " + hashMapTime / 1_000_000 + " ms (fastest)");
        System.out.println("    LinkedHashMap: " + linkedHashMapTime / 1_000_000 + " ms");
        System.out.println("    TreeMap:       " + treeMapTime / 1_000_000 + " ms (slowest)");

        System.out.println("\n✅ LinkedHashMap: Good balance of speed and order!");

        System.out.println();
    }

    /**
     * Example 5: Practical use cases
     */
    private static void example5_PracticalUseCases() {
        System.out.println("5. PRACTICAL USE CASES");
        System.out.println("-".repeat(50));

        // Use case 1: Configuration properties (ordered)
        System.out.println("Use Case 1: Configuration Properties");
        LinkedHashMap<String, String> config = new LinkedHashMap<>();
        config.put("database.host", "localhost");
        config.put("database.port", "5432");
        config.put("database.name", "mydb");
        config.put("database.user", "admin");

        System.out.println("  Configuration (maintains order):");
        config.forEach((key, value) ->
            System.out.println("    " + key + " = " + value)
        );

        // Use case 2: Recent searches/history
        System.out.println("\nUse Case 2: Search History (with LRU)");
        LinkedHashMap<String, Long> searchHistory = new LinkedHashMap<String, Long>(5, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, Long> eldest) {
                return size() > 5;  // Keep only last 5 searches
            }
        };

        searchHistory.put("Java tutorial", System.currentTimeMillis());
        searchHistory.put("Python basics", System.currentTimeMillis());
        searchHistory.put("Data structures", System.currentTimeMillis());
        searchHistory.put("Algorithms", System.currentTimeMillis());
        searchHistory.put("Design patterns", System.currentTimeMillis());

        System.out.println("  Recent searches:");
        searchHistory.keySet().forEach(search ->
            System.out.println("    - " + search)
        );

        searchHistory.put("Machine learning", System.currentTimeMillis());
        System.out.println("\n  After adding 6th search (oldest removed):");
        searchHistory.keySet().forEach(search ->
            System.out.println("    - " + search)
        );

        // Use case 3: Building JSON-like ordered output
        System.out.println("\nUse Case 3: Ordered API Response");
        LinkedHashMap<String, Object> apiResponse = new LinkedHashMap<>();
        apiResponse.put("status", "success");
        apiResponse.put("code", 200);
        apiResponse.put("message", "Data retrieved successfully");
        apiResponse.put("timestamp", System.currentTimeMillis());
        apiResponse.put("data", Arrays.asList("item1", "item2", "item3"));

        System.out.println("  API Response (predictable order):");
        apiResponse.forEach((key, value) ->
            System.out.println("    " + key + ": " + value)
        );

        System.out.println();
    }

    /**
     * Helper method to print map
     */
    private static void printMap(Map<String, Integer> map) {
        System.out.print("  ");
        map.forEach((key, value) -> System.out.print(key + "=" + value + " "));
        System.out.println();
    }
}

/**
 * LRU Cache implementation using LinkedHashMap
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);  // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;  // Remove oldest when exceeds capacity
    }
}

