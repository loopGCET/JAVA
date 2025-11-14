import java.util.*;

/**
 * Comprehensive example demonstrating PriorityQueue in Java.
 * PriorityQueue maintains elements in priority order using a heap.
 *
 * @author Collections Framework Tutorial
 */
public class PriorityQueueExample {

    public static void main(String[] args) {
        System.out.println("=== PRIORITYQUEUE COMPREHENSIVE EXAMPLES ===\n");

        example1_BasicOperations();
        example2_MinHeapVsMaxHeap();
        example3_CustomObjects();
        example4_TaskScheduling();
        example5_FindKLargest();
    }

    /**
     * Example 1: Basic PriorityQueue operations (min-heap by default)
     */
    private static void example1_BasicOperations() {
        System.out.println("1. BASIC OPERATIONS (MIN-HEAP)");
        System.out.println("-".repeat(50));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements
        pq.offer(50);
        pq.offer(20);
        pq.offer(80);
        pq.offer(10);
        pq.offer(40);

        System.out.println("PriorityQueue: " + pq);
        System.out.println("Note: Internal array order, not priority order!");

        // peek() - View highest priority element (smallest)
        System.out.println("\nPeek (highest priority): " + pq.peek());

        // poll() - Remove and return highest priority
        System.out.println("\nPolling elements in priority order:");
        while (!pq.isEmpty()) {
            System.out.println("  " + pq.poll());
        }

        System.out.println();
    }

    /**
     * Example 2: Min-heap vs Max-heap
     */
    private static void example2_MinHeapVsMaxHeap() {
        System.out.println("2. MIN-HEAP VS MAX-HEAP");
        System.out.println("-".repeat(50));

        // Min-heap (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));

        System.out.println("Min-Heap (smallest first):");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // Max-heap (using reverse order comparator)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));

        System.out.println("\nMax-Heap (largest first):");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println("\n");
    }

    /**
     * Example 3: PriorityQueue with custom objects
     */
    private static void example3_CustomObjects() {
        System.out.println("3. CUSTOM OBJECTS WITH PRIORITY");
        System.out.println("-".repeat(50));

        PriorityQueue<Patient> emergencyRoom = new PriorityQueue<>();

        emergencyRoom.offer(new Patient("Alice", 3));    // Low priority
        emergencyRoom.offer(new Patient("Bob", 1));      // High priority
        emergencyRoom.offer(new Patient("Charlie", 2));  // Medium priority
        emergencyRoom.offer(new Patient("David", 1));    // High priority
        emergencyRoom.offer(new Patient("Eve", 4));      // Lowest priority

        System.out.println("Treating patients by priority:");
        while (!emergencyRoom.isEmpty()) {
            Patient patient = emergencyRoom.poll();
            System.out.println("  Treating: " + patient);
        }

        System.out.println();
    }

    /**
     * Example 4: Task scheduling simulation
     */
    private static void example4_TaskScheduling() {
        System.out.println("4. TASK SCHEDULING");
        System.out.println("-".repeat(50));

        // Custom comparator: higher priority number = more important
        PriorityQueue<ScheduledTask> taskQueue = new PriorityQueue<>(
            Comparator.comparingInt(ScheduledTask::getPriority).reversed()
                     .thenComparing(ScheduledTask::getName)
        );

        taskQueue.offer(new ScheduledTask("Database backup", 5));
        taskQueue.offer(new ScheduledTask("Update software", 7));
        taskQueue.offer(new ScheduledTask("Send report", 3));
        taskQueue.offer(new ScheduledTask("Security scan", 10));
        taskQueue.offer(new ScheduledTask("Clean logs", 2));

        System.out.println("Executing tasks by priority:");
        int taskNumber = 1;
        while (!taskQueue.isEmpty()) {
            ScheduledTask task = taskQueue.poll();
            System.out.println("  " + taskNumber + ". " + task);
            taskNumber++;
        }

        System.out.println();
    }

    /**
     * Example 5: Find K largest elements
     */
    private static void example5_FindKLargest() {
        System.out.println("5. FIND K LARGEST ELEMENTS");
        System.out.println("-".repeat(50));

        int[] numbers = {7, 10, 4, 3, 20, 15, 8, 2, 11, 5};
        int k = 3;

        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Find " + k + " largest elements\n");

        // Use min-heap of size k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : numbers) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // Remove smallest
            }
        }

        System.out.println(k + " largest elements: " + minHeap);

        // Get them in descending order
        List<Integer> result = new ArrayList<>(minHeap);
        result.sort(Collections.reverseOrder());
        System.out.println("In descending order: " + result);

        System.out.println();
    }
}

/**
 * Patient class for emergency room example
 */
class Patient implements Comparable<Patient> {
    private String name;
    private int priority;  // 1 = critical, 4 = minor

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + " (priority: " + priority + ")";
    }
}

/**
 * ScheduledTask class for task scheduling
 */
class ScheduledTask {
    private String name;
    private int priority;

    public ScheduledTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return name + " [Priority: " + priority + "]";
    }
}

