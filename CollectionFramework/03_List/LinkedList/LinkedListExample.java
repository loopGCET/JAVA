import java.util.*;

/**
 * Comprehensive example demonstrating LinkedList in Java.
 * LinkedList implements both List and Deque interfaces.
 *
 * @author Collections Framework Tutorial
 */
public class LinkedListExample {

    public static void main(String[] args) {
        System.out.println("=== LINKEDLIST COMPREHENSIVE EXAMPLES ===\n");

        example1_BasicListOperations();
        example2_DequeOperations();
        example3_QueueOperations();
        example4_Iteration();
        example5_PerformanceComparison();
    }

    /**
     * Example 1: Basic List operations
     */
    private static void example1_BasicListOperations() {
        System.out.println("1. BASIC LIST OPERATIONS");
        System.out.println("-".repeat(50));

        LinkedList<String> list = new LinkedList<>();

        // Adding elements
        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("JavaScript");
        System.out.println("After adding: " + list);

        // Insert at specific position
        list.add(1, "Ruby");
        System.out.println("After insert at index 1: " + list);

        // Get element
        System.out.println("Element at index 2: " + list.get(2));

        // Set element
        list.set(3, "Go");
        System.out.println("After set(3, 'Go'): " + list);

        // Remove by index
        list.remove(1);
        System.out.println("After remove(1): " + list);

        // Contains
        System.out.println("Contains 'Java'? " + list.contains("Java"));

        System.out.println();
    }

    /**
     * Example 2: Deque operations (Double-Ended Queue)
     */
    private static void example2_DequeOperations() {
        System.out.println("2. DEQUE OPERATIONS");
        System.out.println("-".repeat(50));

        LinkedList<Integer> deque = new LinkedList<>();

        // Add at both ends
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(25);
        System.out.println("After adding at both ends: " + deque);

        // Get first and last
        System.out.println("First element: " + deque.getFirst());
        System.out.println("Last element: " + deque.getLast());

        // Peek without removing
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());

        // Remove from both ends
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("After removing from ends: " + deque);

        // Poll (returns null if empty, doesn't throw exception)
        System.out.println("Poll first: " + deque.pollFirst());
        System.out.println("Poll last: " + deque.pollLast());
        System.out.println("After polling: " + deque);

        System.out.println();
    }

    /**
     * Example 3: Queue operations (FIFO)
     */
    private static void example3_QueueOperations() {
        System.out.println("3. QUEUE OPERATIONS (FIFO)");
        System.out.println("-".repeat(50));

        Queue<String> queue = new LinkedList<>();

        // Enqueue (add to end)
        queue.offer("Customer 1");
        queue.offer("Customer 2");
        queue.offer("Customer 3");
        queue.offer("Customer 4");
        System.out.println("Queue: " + queue);

        // Peek (view first without removing)
        System.out.println("Next in queue: " + queue.peek());

        // Dequeue (remove from front)
        System.out.println("Serving: " + queue.poll());
        System.out.println("Serving: " + queue.poll());
        System.out.println("Remaining queue: " + queue);

        // Check if empty
        System.out.println("Is empty? " + queue.isEmpty());
        System.out.println("Size: " + queue.size());

        System.out.println();
    }

    /**
     * Example 4: Iteration methods
     */
    private static void example4_Iteration() {
        System.out.println("4. ITERATION METHODS");
        System.out.println("-".repeat(50));

        LinkedList<String> list = new LinkedList<>(
            Arrays.asList("A", "B", "C", "D", "E")
        );

        // Forward iteration
        System.out.println("Forward iteration:");
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println("\n");

        // Backward iteration using descendingIterator
        System.out.println("Backward iteration:");
        Iterator<String> descIter = list.descendingIterator();
        while (descIter.hasNext()) {
            System.out.print(descIter.next() + " ");
        }
        System.out.println("\n");

        // Using ListIterator
        System.out.println("Using ListIterator:");
        ListIterator<String> listIter = list.listIterator();
        while (listIter.hasNext()) {
            System.out.println("  Index " + listIter.nextIndex() + ": " + listIter.next());
        }

        System.out.println();
    }

    /**
     * Example 5: Performance comparison with ArrayList
     */
    private static void example5_PerformanceComparison() {
        System.out.println("5. PERFORMANCE COMPARISON");
        System.out.println("-".repeat(50));

        int size = 100000;

        // LinkedList - Add at beginning
        LinkedList<Integer> linkedList = new LinkedList<>();
        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.addFirst(i);
        }
        long linkedListTime = System.nanoTime() - start;

        // ArrayList - Add at beginning
        ArrayList<Integer> arrayList = new ArrayList<>();
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(0, i);
        }
        long arrayListTime = System.nanoTime() - start;

        System.out.println("Adding " + size + " elements at beginning:");
        System.out.println("  LinkedList: " + linkedListTime / 1_000_000 + " ms");
        System.out.println("  ArrayList:  " + arrayListTime / 1_000_000 + " ms");
        System.out.println("  LinkedList is " + (arrayListTime / linkedListTime) + "x faster");

        // Random access comparison
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(size / 2);
        }
        long linkedListAccess = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(size / 2);
        }
        long arrayListAccess = System.nanoTime() - start;

        System.out.println("\nRandom access (1000 times at middle):");
        System.out.println("  LinkedList: " + linkedListAccess / 1_000_000 + " ms");
        System.out.println("  ArrayList:  " + arrayListAccess / 1_000_000 + " ms");
        System.out.println("  ArrayList is " + (linkedListAccess / arrayListAccess) + "x faster");

        System.out.println("\n✅ Use LinkedList for: frequent insertions/deletions at ends");
        System.out.println("✅ Use ArrayList for: random access and iteration");

        System.out.println();
    }
}

