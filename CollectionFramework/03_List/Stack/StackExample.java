import java.util.*;

/**
 * Comprehensive example demonstrating Stack in Java.
 * Stack is a legacy LIFO data structure extending Vector.
 *
 * ⚠️ Note: Stack is LEGACY - Use ArrayDeque instead!
 *
 * @author Collections Framework Tutorial
 */
public class StackExample {

    public static void main(String[] args) {
        System.out.println("=== STACK COMPREHENSIVE EXAMPLES ===");
        System.out.println("⚠️  Stack is LEGACY - Use ArrayDeque instead!\n");

        example1_BasicStackOperations();
        example2_StackMethods();
        example3_ExpressionEvaluation();
        example4_StackVsArrayDeque();
        example5_StackProblems();
    }

    /**
     * Example 1: Basic stack operations (LIFO)
     */
    private static void example1_BasicStackOperations() {
        System.out.println("1. BASIC STACK OPERATIONS (LIFO)");
        System.out.println("-".repeat(50));

        Stack<String> stack = new Stack<>();

        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        stack.push("Fourth");

        System.out.println("Stack after pushes: " + stack);
        System.out.println("Note: Shows bottom to top");

        // Peek - view top without removing
        System.out.println("\nTop element (peek): " + stack.peek());
        System.out.println("Stack still has: " + stack);

        // Pop - remove and return top
        System.out.println("\nPopping elements (LIFO order):");
        while (!stack.empty()) {
            System.out.println("  Popped: " + stack.pop());
        }

        System.out.println("\nStack is now empty: " + stack.empty());

        System.out.println();
    }

    /**
     * Example 2: All Stack methods
     */
    private static void example2_StackMethods() {
        System.out.println("2. STACK METHODS");
        System.out.println("-".repeat(50));

        Stack<Integer> stack = new Stack<>();

        // empty() - check if empty
        System.out.println("Is empty? " + stack.empty());

        // push() - add elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(20);  // Duplicate
        System.out.println("\nStack: " + stack);

        // peek() - view top
        System.out.println("Top element: " + stack.peek());

        // search() - find position from top (1-based)
        System.out.println("\nSearch results (1-based distance from top):");
        System.out.println("  Position of 20: " + stack.search(20));    // Last occurrence
        System.out.println("  Position of 40: " + stack.search(40));    // 2nd from top
        System.out.println("  Position of 10: " + stack.search(10));    // Bottom
        System.out.println("  Position of 99: " + stack.search(99));    // Not found (-1)

        // pop() - remove top
        System.out.println("\nPop top: " + stack.pop());
        System.out.println("After pop: " + stack);

        System.out.println();
    }

    /**
     * Example 3: Practical use case - Expression evaluation
     */
    private static void example3_ExpressionEvaluation() {
        System.out.println("3. EXPRESSION EVALUATION (Balanced Parentheses)");
        System.out.println("-".repeat(50));

        String[] expressions = {
            "{[()]}",
            "{[(])}",
            "((()))",
            "((())",
            "{[}]"
        };

        for (String expr : expressions) {
            boolean isBalanced = isBalancedParentheses(expr);
            System.out.println(expr + " → " + (isBalanced ? "✅ Balanced" : "❌ Not Balanced"));
        }

        System.out.println();
    }

    /**
     * Helper method to check balanced parentheses using Stack
     */
    private static boolean isBalancedParentheses(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            // Push opening brackets
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Check closing brackets
            else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    /**
     * Helper to check if brackets match
     */
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    /**
     * Example 4: Stack vs ArrayDeque comparison
     */
    private static void example4_StackVsArrayDeque() {
        System.out.println("4. STACK VS ARRAYDEQUE COMPARISON");
        System.out.println("-".repeat(50));

        int iterations = 100000;

        // Stack performance
        Stack<Integer> stack = new Stack<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            stack.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            stack.pop();
        }
        long stackTime = System.nanoTime() - startTime;

        // ArrayDeque performance
        Deque<Integer> deque = new ArrayDeque<>();
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            deque.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            deque.pop();
        }
        long dequeTime = System.nanoTime() - startTime;

        System.out.println("Stack operations (" + iterations + " push + " + iterations + " pop):");
        System.out.println("  Stack:      " + stackTime / 1_000_000 + " ms");
        System.out.println("  ArrayDeque: " + dequeTime / 1_000_000 + " ms");
        System.out.println("  ArrayDeque is " + (stackTime / dequeTime) + "x FASTER! ✅");

        System.out.println("\n💡 Recommendation: Use ArrayDeque for stack operations!");

        System.out.println();
    }

    /**
     * Example 5: Problems with Stack (Why it's deprecated)
     */
    private static void example5_StackProblems() {
        System.out.println("5. PROBLEMS WITH STACK (Why Use ArrayDeque)");
        System.out.println("-".repeat(50));

        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Stack after pushes: " + stack);

        // Problem 1: Stack extends Vector, exposing Vector methods
        System.out.println("\n❌ Problem 1: Can break stack semantics");
        stack.add(0, "INSERTED_AT_BOTTOM");  // Should not be possible in a stack!
        System.out.println("After add(0, ...): " + stack);
        System.out.println("Stack integrity violated! Bottom was modified!");

        // Problem 2: Can use get() to access middle elements
        System.out.println("\n❌ Problem 2: Can access middle elements");
        System.out.println("Element at index 1: " + stack.get(1));
        System.out.println("This violates stack's LIFO principle!");

        // Problem 3: Synchronized overhead even in single-threaded code
        System.out.println("\n❌ Problem 3: Synchronization overhead");
        System.out.println("All methods are synchronized, causing performance loss");
        System.out.println("Even when thread-safety is not needed!");

        // Show the better way
        System.out.println("\n✅ BETTER ALTERNATIVE: ArrayDeque");
        Deque<String> betterStack = new ArrayDeque<>();
        betterStack.push("A");
        betterStack.push("B");
        betterStack.push("C");

        System.out.println("ArrayDeque as stack: " + betterStack);
        System.out.println("  • Faster (no synchronization overhead)");
        System.out.println("  • No Vector methods exposed");
        System.out.println("  • Modern and recommended");
        System.out.println("  • Same push/pop/peek interface");

        // Migration example
        System.out.println("\n📝 MIGRATION GUIDE:");
        System.out.println("  OLD: Stack<String> stack = new Stack<>();");
        System.out.println("  NEW: Deque<String> stack = new ArrayDeque<>();");
        System.out.println("\n  Methods remain the same: push(), pop(), peek()");
        System.out.println("  Only change: empty() → isEmpty()");

        System.out.println();
    }
}

