import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Comprehensive example demonstrating Hashtable in Java.
 * Hashtable is a legacy synchronized Map implementation.
 *
 * ⚠️ Note: Hashtable is LEGACY - Use HashMap or ConcurrentHashMap instead!
 *
 * @author Collections Framework Tutorial
 */
public class HashtableExample {

    public static void main(String[] args) {
        System.out.println("=== HASHTABLE COMPREHENSIVE EXAMPLES ===");
        System.out.println("⚠️  Hashtable is LEGACY - Use HashMap or ConcurrentHashMap!\n");

        example1_BasicOperations();
        example2_NullNotAllowed();
        example3_ThreadSafety();
        example4_PerformanceComparison();
        example5_MigrationGuide();
    }

    /**
     * Example 1: Basic Hashtable operations
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("-".repeat(50));

        Hashtable<String, Integer> table = new Hashtable<>();

        // Put key-value pairs
        table.put("Alice", 95);
        table.put("Bob", 87);
        table.put("Charlie", 92);
        table.put("David", 88);

        System.out.println("Hashtable: " + table);

        // Get value
        System.out.println("Alice's score: " + table.get("Alice"));

        // Contains key
        System.out.println("Contains 'Bob'? " + table.containsKey("Bob"));

        // Contains value
        System.out.println("Contains score 92? " + table.containsValue(92));

        // Size
        System.out.println("Size: " + table.size());

        // Remove
        table.remove("David");
        System.out.println("After removing David: " + table);

        // Iteration
        System.out.println("\nIterating (no guaranteed order):");
        table.forEach((key, value) ->
            System.out.println("  " + key + " = " + value)
        );

        System.out.println();
    }

    /**
     * Example 2: Null keys and values are NOT allowed
     */
    private static void example2_NullNotAllowed() {
        System.out.println("2. NULL NOT ALLOWED");
        System.out.println("-".repeat(50));

        Hashtable<String, String> table = new Hashtable<>();

        // Try null key
        System.out.println("Attempting to put null key...");
        try {
            table.put(null, "value");
            System.out.println("  Success");  // Won't reach here
        } catch (NullPointerException e) {
            System.out.println("  ❌ NullPointerException: Null keys not allowed!");
        }

        // Try null value
        System.out.println("\nAttempting to put null value...");
        try {
            table.put("key", null);
            System.out.println("  Success");  // Won't reach here
        } catch (NullPointerException e) {
            System.out.println("  ❌ NullPointerException: Null values not allowed!");
        }

        // Compare with HashMap (allows nulls)
        System.out.println("\n📝 Compare with HashMap:");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "value");
        hashMap.put("key", null);
        System.out.println("  HashMap with nulls: " + hashMap);
        System.out.println("  ✅ HashMap allows null key and null values");

        System.out.println("\n⚠️  Hashtable does NOT allow null keys or values!");

        System.out.println();
    }

    /**
     * Example 3: Thread-safety demonstration
     */
    private static void example3_ThreadSafety() {
        System.out.println("3. THREAD-SAFETY DEMONSTRATION");
        System.out.println("-".repeat(50));

        Hashtable<Integer, String> table = new Hashtable<>();

        System.out.println("Hashtable is thread-safe (all methods synchronized)");
        System.out.println("Testing concurrent access...\n");

        // Create multiple threads adding to Hashtable
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    table.put(threadNum * 100 + j, "Thread" + threadNum);
                }
                System.out.println("Thread " + threadNum + " completed");
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for completion
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nFinal size: " + table.size() + " (expected: 500)");
        System.out.println("✅ No data loss due to thread-safety!");

        System.out.println("\n⚠️  However, synchronization causes performance overhead!");
        System.out.println("💡 Better alternative: ConcurrentHashMap");

        System.out.println();
    }

    /**
     * Example 4: Performance comparison
     */
    private static void example4_PerformanceComparison() {
        System.out.println("4. PERFORMANCE COMPARISON");
        System.out.println("-".repeat(50));

        int iterations = 100000;

        // HashMap (no synchronization)
        HashMap<Integer, String> hashMap = new HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            hashMap.put(i, "value" + i);
        }
        long hashMapTime = System.nanoTime() - start;

        // Hashtable (synchronized)
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            hashtable.put(i, "value" + i);
        }
        long hashtableTime = System.nanoTime() - start;

        // ConcurrentHashMap (concurrent)
        ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            concurrentMap.put(i, "value" + i);
        }
        long concurrentMapTime = System.nanoTime() - start;

        System.out.println("Adding " + iterations + " entries:");
        System.out.println("  HashMap:           " + hashMapTime / 1_000_000 + " ms (fastest)");
        System.out.println("  Hashtable:         " + hashtableTime / 1_000_000 + " ms");
        System.out.println("  ConcurrentHashMap: " + concurrentMapTime / 1_000_000 + " ms");

        System.out.println("\n📊 Performance Ranking:");
        System.out.println("  1. ✅ HashMap (fastest - no synchronization)");
        System.out.println("  2. ✅ ConcurrentHashMap (fast - segment locking)");
        System.out.println("  3. ❌ Hashtable (slowest - coarse-grained locking)");

        System.out.println("\n💡 Recommendation:");
        System.out.println("   Single-threaded → HashMap");
        System.out.println("   Multi-threaded → ConcurrentHashMap");
        System.out.println("   Never → Hashtable (legacy)");

        System.out.println();
    }

    /**
     * Example 5: Migration guide from Hashtable
     */
    private static void example5_MigrationGuide() {
        System.out.println("5. MIGRATION GUIDE");
        System.out.println("-".repeat(50));

        System.out.println("❌ OLD WAY (Hashtable - Legacy):");
        System.out.println("   Hashtable<String, Integer> map = new Hashtable<>();");
        System.out.println("   map.put(\"key\", 100);");
        System.out.println("   • Thread-safe but slow");
        System.out.println("   • No null keys or values");
        System.out.println("   • Entire table locked");

        System.out.println("\n✅ NEW WAY 1 (HashMap - Single-threaded):");
        System.out.println("   Map<String, Integer> map = new HashMap<>();");
        System.out.println("   map.put(\"key\", 100);");
        System.out.println("   • Fast O(1) operations");
        System.out.println("   • Allows null key and values");
        System.out.println("   • Use when thread-safety not needed");

        System.out.println("\n✅ NEW WAY 2 (ConcurrentHashMap - Multi-threaded):");
        System.out.println("   Map<String, Integer> map = new ConcurrentHashMap<>();");
        System.out.println("   map.put(\"key\", 100);");
        System.out.println("   • Thread-safe AND fast");
        System.out.println("   • Segment-level locking");
        System.out.println("   • Better concurrency than Hashtable");
        System.out.println("   • No null keys or values");

        System.out.println("\n✅ NEW WAY 3 (Collections.synchronizedMap):");
        System.out.println("   Map<String, Integer> map = ");
        System.out.println("       Collections.synchronizedMap(new HashMap<>());");
        System.out.println("   • Thread-safe wrapper");
        System.out.println("   • More flexible than Hashtable");
        System.out.println("   • Can wrap any Map implementation");

        // Practical demonstration
        System.out.println("\n📝 PRACTICAL EXAMPLE:");

        // Hashtable way (legacy)
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);
        System.out.println("Hashtable: " + hashtable);

        // HashMap way (modern, single-threaded)
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        System.out.println("HashMap: " + hashMap);

        // ConcurrentHashMap way (modern, multi-threaded)
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        System.out.println("ConcurrentHashMap: " + concurrentMap);

        System.out.println("\n✅ All work the same way, but with different characteristics!");

        System.out.println();
    }
}

