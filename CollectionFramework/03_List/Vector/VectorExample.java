import java.util.*;

/**
 * Comprehensive example demonstrating Vector in Java.
 * Vector is a legacy synchronized List implementation.
 *
 * ⚠️ Note: Vector is legacy. Use ArrayList for new code.
 *
 * @author Collections Framework Tutorial
 */
public class VectorExample {

    public static void main(String[] args) {
        System.out.println("=== VECTOR COMPREHENSIVE EXAMPLES ===");
        System.out.println("⚠️  Vector is LEGACY - Use ArrayList instead!\n");

        example1_BasicOperations();
        example2_LegacyMethods();
        example3_ThreadSafety();
        example4_CapacityManagement();
        example5_PerformanceComparison();
    }

    /**
     * Example 1: Basic Vector operations (similar to ArrayList)
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS");
        System.out.println("-".repeat(50));

        Vector<String> vector = new Vector<>();

        // Adding elements
        vector.add("Java");
        vector.add("Python");
        vector.add("C++");
        vector.add("JavaScript");
        System.out.println("After adding: " + vector);

        // Insert at specific position
        vector.add(1, "Ruby");
        System.out.println("After insert at index 1: " + vector);

        // Get element
        System.out.println("Element at index 2: " + vector.get(2));

        // Set element
        vector.set(3, "Go");
        System.out.println("After set(3, 'Go'): " + vector);

        // Remove by index
        vector.remove(1);
        System.out.println("After remove(1): " + vector);

        // Contains
        System.out.println("Contains 'Java'? " + vector.contains("Java"));

        // Size
        System.out.println("Size: " + vector.size());

        System.out.println();
    }

    /**
     * Example 2: Legacy Vector-specific methods
     */
    private static void example2_LegacyMethods() {
        System.out.println("2. LEGACY VECTOR METHODS");
        System.out.println("-".repeat(50));
        System.out.println("⚠️  These are legacy methods - use List interface methods instead\n");

        Vector<Integer> vector = new Vector<>();

        // addElement() - Legacy way to add
        vector.addElement(10);
        vector.addElement(20);
        vector.addElement(30);
        System.out.println("After addElement(): " + vector);

        // elementAt() - Legacy way to get
        System.out.println("Element at index 1: " + vector.elementAt(1));

        // setElementAt() - Legacy way to set
        vector.setElementAt(25, 1);
        System.out.println("After setElementAt(25, 1): " + vector);

        // firstElement() and lastElement()
        System.out.println("First element: " + vector.firstElement());
        System.out.println("Last element: " + vector.lastElement());

        // removeElement() - Legacy way to remove
        vector.removeElement(Integer.valueOf(25));
        System.out.println("After removeElement(25): " + vector);

        // removeAllElements() - Legacy way to clear
        Vector<String> temp = new Vector<>(Arrays.asList("A", "B", "C"));
        System.out.println("\nBefore removeAllElements(): " + temp);
        temp.removeAllElements();
        System.out.println("After removeAllElements(): " + temp);

        System.out.println("\n✅ Modern way: Use add(), get(), set(), remove(), clear() instead!");

        System.out.println();
    }

    /**
     * Example 3: Thread-safety demonstration
     */
    private static void example3_ThreadSafety() {
        System.out.println("3. THREAD-SAFETY DEMONSTRATION");
        System.out.println("-".repeat(50));

        Vector<Integer> vector = new Vector<>();

        System.out.println("Vector is thread-safe (all methods synchronized)");
        System.out.println("Demonstrating concurrent access...\n");

        // Create multiple threads adding to vector
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    vector.add(threadNum * 100 + j);
                }
                System.out.println("Thread " + threadNum + " completed");
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nFinal size: " + vector.size() + " (expected: 500)");
        System.out.println("No data loss due to thread-safety! ✅");

        System.out.println("\n⚠️  However, synchronization has performance cost!");
        System.out.println("💡 Better alternatives:");
        System.out.println("   - CopyOnWriteArrayList for read-heavy scenarios");
        System.out.println("   - Collections.synchronizedList(new ArrayList<>())");

        System.out.println();
    }

    /**
     * Example 4: Capacity management
     */
    private static void example4_CapacityManagement() {
        System.out.println("4. CAPACITY MANAGEMENT");
        System.out.println("-".repeat(50));

        // Create with initial capacity
        Vector<String> vector = new Vector<>(5);
        System.out.println("Initial capacity: " + vector.capacity());

        // Add elements
        for (int i = 1; i <= 5; i++) {
            vector.add("Item" + i);
        }
        System.out.println("After adding 5 elements: " + vector);
        System.out.println("Capacity: " + vector.capacity());

        // Add one more - capacity doubles
        vector.add("Item6");
        System.out.println("\nAfter adding 6th element: " + vector);
        System.out.println("Capacity doubled to: " + vector.capacity());

        // Create with capacity increment
        Vector<Integer> vector2 = new Vector<>(5, 3);
        System.out.println("\nVector with capacity 5, increment 3");

        for (int i = 1; i <= 6; i++) {
            vector2.add(i * 10);
        }
        System.out.println("After adding 6 elements: " + vector2);
        System.out.println("Capacity: " + vector2.capacity() + " (increased by 3)");

        // Ensure capacity
        vector.ensureCapacity(20);
        System.out.println("\nAfter ensureCapacity(20): " + vector.capacity());

        // Trim to size
        vector.trimToSize();
        System.out.println("After trimToSize(): capacity = " + vector.capacity() + " (same as size)");

        System.out.println("\n📝 Note: Vector doubles capacity (100% growth)");
        System.out.println("         ArrayList grows by 50%");

        System.out.println();
    }

    /**
     * Example 5: Performance comparison with ArrayList
     */
    private static void example5_PerformanceComparison() {
        System.out.println("5. PERFORMANCE COMPARISON: VECTOR VS ARRAYLIST");
        System.out.println("-".repeat(50));

        int iterations = 100000;

        // Vector performance
        Vector<Integer> vector = new Vector<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            vector.add(i);
        }
        long vectorTime = System.nanoTime() - startTime;

        // ArrayList performance
        ArrayList<Integer> arrayList = new ArrayList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        System.out.println("Adding " + iterations + " elements:");
        System.out.println("  Vector:    " + vectorTime / 1_000_000 + " ms");
        System.out.println("  ArrayList: " + arrayListTime / 1_000_000 + " ms");
        System.out.println("  Difference: " + (vectorTime / arrayListTime) + "x slower");

        // Access performance
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            vector.get(i % vector.size());
        }
        long vectorAccessTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            arrayList.get(i % arrayList.size());
        }
        long arrayListAccessTime = System.nanoTime() - startTime;

        System.out.println("\nRandom access (10000 gets):");
        System.out.println("  Vector:    " + vectorAccessTime / 1_000_000 + " ms");
        System.out.println("  ArrayList: " + arrayListAccessTime / 1_000_000 + " ms");

        System.out.println("\n📊 Conclusion:");
        System.out.println("   Vector is slower due to synchronization overhead");
        System.out.println("   ✅ Use ArrayList for single-threaded applications");
        System.out.println("   ✅ Use CopyOnWriteArrayList for concurrent read-heavy scenarios");
        System.out.println("   ✅ Use Collections.synchronizedList() when needed");

        System.out.println();
    }
}

