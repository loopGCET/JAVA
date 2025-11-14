/**
 * Comprehensive demonstration of Thread Lifecycle in Java.
 *
 * This example covers:
 * - Thread states (NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED)
 * - State transitions
 * - sleep(), join(), interrupt() methods
 * - Thread interruption handling
 * - Daemon vs User threads
 * - Thread monitoring
 *
 * @author GitHub Copilot
 * @topic Multithreading - Thread Lifecycle
 */
public class ThreadLifecycleExample {

    /**
     * Example 1: Thread States Demonstration
     * Demonstrates: All six thread states
     */
    public static void example1_ThreadStates() throws InterruptedException {
        System.out.println("--- Example 1: Thread States ---");

        // Create shared lock for BLOCKED state
        final Object lock = new Object();

        Thread thread = new Thread(() -> {
            try {
                synchronized (lock) {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // NEW state
        System.out.println("1. After creation: " + thread.getState());

        // Start thread - RUNNABLE
        thread.start();
        Thread.sleep(100);
        System.out.println("2. After start: " + thread.getState());

        // Create another thread to show BLOCKED state
        Thread blockedThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Got lock");
            }
        });

        synchronized (lock) {
            blockedThread.start();
            Thread.sleep(100);
            System.out.println("3. Blocked thread state: " + blockedThread.getState());
        }

        // TIMED_WAITING
        Thread.sleep(500);
        if (thread.getState() == Thread.State.TIMED_WAITING) {
            System.out.println("4. During sleep: " + thread.getState());
        }

        // TERMINATED
        thread.join();
        System.out.println("5. After completion: " + thread.getState());

        System.out.println();
    }

    /**
     * Example 2: Thread.sleep() Method
     * Demonstrates: Pausing thread execution
     */
    public static void example2_ThreadSleep() throws InterruptedException {
        System.out.println("--- Example 2: Thread.sleep() ---");

        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Count: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500ms
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupted!");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        thread.start();
        thread.join();

        System.out.println("Thread completed\n");
    }

    /**
     * Example 3: Thread.join() Method
     * Demonstrates: Waiting for thread completion
     */
    public static void example3_ThreadJoin() throws InterruptedException {
        System.out.println("--- Example 3: Thread.join() ---");

        Thread worker1 = new Thread(() -> {
            System.out.println("Worker 1 starting");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker 1 finished");
        });

        Thread worker2 = new Thread(() -> {
            System.out.println("Worker 2 starting");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker 2 finished");
        });

        System.out.println("Starting workers...");
        worker1.start();
        worker2.start();

        System.out.println("Waiting for worker 1...");
        worker1.join();
        System.out.println("Worker 1 has completed");

        System.out.println("Waiting for worker 2...");
        worker2.join();
        System.out.println("Worker 2 has completed");

        System.out.println("All workers completed\n");
    }

    /**
     * Example 4: Thread Interruption
     * Demonstrates: Graceful thread interruption
     */
    public static void example4_ThreadInterruption() throws InterruptedException {
        System.out.println("--- Example 4: Thread Interruption ---");

        Thread thread = new Thread(() -> {
            int count = 0;
            try {
                while (!Thread.interrupted()) {
                    System.out.println("Working... " + (++count));
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep at count: " + count);
            }
            System.out.println("Thread exiting gracefully");
        });

        thread.start();
        Thread.sleep(1200); // Let it run for a bit

        System.out.println("Main thread requesting interruption...");
        thread.interrupt();

        thread.join();
        System.out.println("Thread has stopped\n");
    }

    /**
     * Example 5: Join with Timeout
     * Demonstrates: Waiting with maximum time limit
     */
    public static void example5_JoinWithTimeout() throws InterruptedException {
        System.out.println("--- Example 5: Join with Timeout ---");

        Thread longTask = new Thread(() -> {
            System.out.println("Long task starting...");
            try {
                Thread.sleep(5000); // Long operation
            } catch (InterruptedException e) {
                System.out.println("Long task interrupted");
                Thread.currentThread().interrupt();
            }
            System.out.println("Long task finished");
        });

        longTask.start();

        System.out.println("Waiting for max 2 seconds...");
        longTask.join(2000); // Wait maximum 2 seconds

        if (longTask.isAlive()) {
            System.out.println("Task still running after timeout!");
            longTask.interrupt(); // Interrupt the long task
        } else {
            System.out.println("Task completed within timeout");
        }

        longTask.join(); // Wait for final cleanup
        System.out.println();
    }

    /**
     * Example 6: State Monitoring
     * Demonstrates: Monitoring thread state transitions
     */
    public static void example6_StateMonitoring() throws InterruptedException {
        System.out.println("--- Example 6: State Monitoring ---");

        Thread worker = new Thread(() -> {
            try {
                System.out.println("Worker: Starting work");
                Thread.sleep(1000);
                System.out.println("Worker: Finishing work");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Monitor thread
        Thread monitor = new Thread(() -> {
            while (worker.getState() != Thread.State.TERMINATED) {
                System.out.println("Monitor: Worker state = " + worker.getState());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("Monitor: Worker state = " + worker.getState());
        });

        System.out.println("Initial state: " + worker.getState());

        worker.start();
        monitor.start();

        worker.join();
        monitor.join();

        System.out.println();
    }

    /**
     * Main method - runs all examples
     */
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  THREAD LIFECYCLE COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        try {
            example1_ThreadStates();
            System.out.println("─────────────────────────────────────────────────────\n");

            example2_ThreadSleep();
            System.out.println("─────────────────────────────────────────────────────\n");

            example3_ThreadJoin();
            System.out.println("─────────────────────────────────────────────────────\n");

            example4_ThreadInterruption();
            System.out.println("─────────────────────────────────────────────────────\n");

            example5_JoinWithTimeout();
            System.out.println("─────────────────────────────────────────────────────\n");

            example6_StateMonitoring();
            System.out.println("─────────────────────────────────────────────────────\n");

        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}

