import java.util.*;

/**
 * Comprehensive example demonstrating ArrayDeque in Java.
 * ArrayDeque is the recommended implementation for stack and queue operations.
 *
 * @author Collections Framework Tutorial
 */
public class ArrayDequeExample {

    public static void main(String[] args) {
        System.out.println("=== ARRAYDEQUE COMPREHENSIVE EXAMPLES ===\n");

        example1_AsStack();
        example2_AsQueue();
        example3_AsDeque();
        example4_UndoRedo();
        example5_PerformanceComparison();
    }

    /**
     * Example 1: Using ArrayDeque as a Stack (LIFO)
     */
    private static void example1_AsStack() {
        System.out.println("1. ARRAYDEQUE AS STACK (LIFO)");
        System.out.println("-".repeat(50));

        Deque<String> stack = new ArrayDeque<>();

        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        stack.push("Fourth");

        System.out.println("Stack: " + stack);

        // Peek top element
        System.out.println("Top element: " + stack.peek());

        // Pop elements
        System.out.println("\nPopping elements:");
        while (!stack.isEmpty()) {
            System.out.println("  " + stack.pop());
        }

        System.out.println();
    }

    /**
     * Example 2: Using ArrayDeque as a Queue (FIFO)
     */
    private static void example2_AsQueue() {
        System.out.println("2. ARRAYDEQUE AS QUEUE (FIFO)");
        System.out.println("-".repeat(50));

        Deque<String> queue = new ArrayDeque<>();

        // Enqueue (add to end)
        queue.offer("Customer 1");
        queue.offer("Customer 2");
        queue.offer("Customer 3");
        queue.offer("Customer 4");

        System.out.println("Queue: " + queue);

        // Peek first element
        System.out.println("Next customer: " + queue.peek());

        // Dequeue (remove from front)
        System.out.println("\nServing customers:");
        while (!queue.isEmpty()) {
            System.out.println("  Serving: " + queue.poll());
        }

        System.out.println();
    }

    /**
     * Example 3: Using ArrayDeque as a Deque (both ends)
     */
    private static void example3_AsDeque() {
        System.out.println("3. ARRAYDEQUE AS DEQUE (BOTH ENDS)");
        System.out.println("-".repeat(50));

        Deque<Integer> deque = new ArrayDeque<>();

        // Add to both ends
        deque.addFirst(10);      // [10]
        deque.addLast(20);       // [10, 20]
        deque.addFirst(5);       // [5, 10, 20]
        deque.addLast(25);       // [5, 10, 20, 25]

        System.out.println("Deque: " + deque);

        // Peek both ends
        System.out.println("First: " + deque.peekFirst());
        System.out.println("Last: " + deque.peekLast());

        // Remove from both ends
        System.out.println("\nRemoving from both ends:");
        System.out.println("  Remove first: " + deque.removeFirst());
        System.out.println("  Remove last: " + deque.removeLast());
        System.out.println("  Remaining: " + deque);

        // OfferFirst and OfferLast (return boolean)
        deque.offerFirst(1);
        deque.offerLast(30);
        System.out.println("  After offering: " + deque);

        System.out.println();
    }

    /**
     * Example 4: Undo/Redo functionality using two stacks
     */
    private static void example4_UndoRedo() {
        System.out.println("4. UNDO/REDO FUNCTIONALITY");
        System.out.println("-".repeat(50));

        TextEditor editor = new TextEditor();

        // Perform actions
        editor.type("Hello");
        editor.type(" World");
        editor.type("!");
        System.out.println("Current text: " + editor.getText());

        // Undo
        editor.undo();
        System.out.println("After undo: " + editor.getText());

        editor.undo();
        System.out.println("After undo: " + editor.getText());

        // Redo
        editor.redo();
        System.out.println("After redo: " + editor.getText());

        // New action clears redo stack
        editor.type(" Java");
        System.out.println("After new action: " + editor.getText());

        editor.undo();
        System.out.println("After undo: " + editor.getText());

        System.out.println();
    }

    /**
     * Example 5: Performance comparison
     */
    private static void example5_PerformanceComparison() {
        System.out.println("5. PERFORMANCE COMPARISON");
        System.out.println("-".repeat(50));

        int iterations = 100000;

        // ArrayDeque as stack
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayDeque.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            arrayDeque.pop();
        }
        long arrayDequeTime = System.nanoTime() - start;

        // Stack (legacy)
        Stack<Integer> stack = new Stack<>();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            stack.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            stack.pop();
        }
        long stackTime = System.nanoTime() - start;

        // LinkedList as stack
        Deque<Integer> linkedList = new LinkedList<>();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedList.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            linkedList.pop();
        }
        long linkedListTime = System.nanoTime() - start;

        System.out.println("Stack operations (" + iterations + " push + " + iterations + " pop):");
        System.out.println("  ArrayDeque: " + arrayDequeTime / 1_000_000 + " ms");
        System.out.println("  Stack:      " + stackTime / 1_000_000 + " ms");
        System.out.println("  LinkedList: " + linkedListTime / 1_000_000 + " ms");

        System.out.println("\n✅ ArrayDeque is the FASTEST!");
        System.out.println("✅ Use ArrayDeque instead of Stack (legacy) and LinkedList");

        System.out.println();
    }
}

/**
 * Simple text editor with undo/redo functionality
 */
class TextEditor {
    private StringBuilder currentText;
    private Deque<String> undoStack;
    private Deque<String> redoStack;

    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new ArrayDeque<>();
        redoStack = new ArrayDeque<>();
    }

    public void type(String text) {
        undoStack.push(currentText.toString());
        currentText.append(text);
        redoStack.clear();  // New action clears redo history
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString());
            currentText = new StringBuilder(undoStack.pop());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString());
            currentText = new StringBuilder(redoStack.pop());
        }
    }

    public String getText() {
        return currentText.toString();
    }
}

