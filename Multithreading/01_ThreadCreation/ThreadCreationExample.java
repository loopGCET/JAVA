/**
 * Comprehensive demonstration of Thread Creation in Java.
 *
 * This example covers:
 * - Extending Thread class
 * - Implementing Runnable interface
 * - Using Lambda expressions (Java 8+)
 * - Anonymous inner classes
 * - Thread naming and priority
 * - Daemon threads
 * - Multiple threads execution
 *
 * @author GitHub Copilot
 * @topic Multithreading - Thread Creation
 */
public class ThreadCreationExample {

    /**
     * Example 1: Extending Thread Class
     * Demonstrates: Basic thread creation by extending Thread
     * Note: Less preferred approach
     */
    public static void example1_ExtendingThread() {
        System.out.println("--- Example 1: Extending Thread Class ---");

        MyThread thread = new MyThread("Worker-1");
        thread.start();

        // Wait for thread to complete
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Example 2: Implementing Runnable Interface
     * Demonstrates: Preferred way to create threads
     * Advantage: Can implement other interfaces
     */
    public static void example2_ImplementingRunnable() {
        System.out.println("--- Example 2: Implementing Runnable ---");

        MyTask task = new MyTask("Task-1");
        Thread thread = new Thread(task);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Example 3: Using Lambda Expressions
     * Demonstrates: Concise thread creation (Java 8+)
     * Best for: Simple, one-time tasks
     */
    public static void example3_LambdaExpression() {
        System.out.println("--- Example 3: Lambda Expression ---");

        Thread thread = new Thread(() -> {
            System.out.println("Lambda task running in: " + Thread.currentThread().getName());
            for (int i = 1; i <= 3; i++) {
                System.out.println("  Count: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setName("Lambda-Thread");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Example 4: Anonymous Inner Class
     * Demonstrates: Creating thread without separate class
     * Use case: Quick, one-time thread creation
     */
    public static void example4_AnonymousClass() {
        System.out.println("--- Example 4: Anonymous Inner Class ---");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous task running in: " + Thread.currentThread().getName());
                System.out.println("Performing some work...");
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Example 5: Thread Naming and Priority
     * Demonstrates: Setting thread properties
     * Priority: 1 (MIN) to 10 (MAX), default is 5 (NORM)
     */
    public static void example5_NamingAndPriority() {
        System.out.println("--- Example 5: Thread Naming and Priority ---");

        Thread highPriority = new Thread(() -> {
            System.out.println("High priority thread: " + Thread.currentThread().getName());
            System.out.println("Priority: " + Thread.currentThread().getPriority());
        });

        Thread lowPriority = new Thread(() -> {
            System.out.println("Low priority thread: " + Thread.currentThread().getName());
            System.out.println("Priority: " + Thread.currentThread().getPriority());
        });

        // Set names
        highPriority.setName("HighPriority-Worker");
        lowPriority.setName("LowPriority-Worker");

        // Set priorities
        highPriority.setPriority(Thread.MAX_PRIORITY); // 10
        lowPriority.setPriority(Thread.MIN_PRIORITY);  // 1

        highPriority.start();
        lowPriority.start();

        try {
            highPriority.join();
            lowPriority.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Example 6: Daemon Threads
     * Demonstrates: Background threads that don't prevent JVM shutdown
     * Use case: Monitoring, garbage collection, etc.
     */
    public static void example6_DaemonThreads() {
        System.out.println("--- Example 6: Daemon Threads ---");

        // User thread
        Thread userThread = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("User thread: " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        userThread.setName("UserThread");

        // Daemon thread
        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("Daemon thread: " + (++count));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        daemonThread.setName("DaemonThread");
        daemonThread.setDaemon(true); // Must set before start()

        System.out.println("UserThread is daemon: " + userThread.isDaemon());
        System.out.println("DaemonThread is daemon: " + daemonThread.isDaemon());

        daemonThread.start();
        userThread.start();

        try {
            userThread.join();
            // Note: We don't wait for daemon thread
            // It will automatically terminate when user thread completes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread ending (daemon will stop too)\n");
    }

    /**
     * Example 7: Multiple Threads Execution
     * Demonstrates: Creating and running multiple threads
     * Shows: Concurrent execution and thread interleaving
     */
    public static void example7_MultipleThreads() {
        System.out.println("--- Example 7: Multiple Threads ---");

        // Create array of threads
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            final int threadNum = i + 1;
            threads[i] = new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    System.out.println("Thread-" + threadNum + ": Count " + j);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].setName("Worker-" + threadNum);
        }

        // Start all threads
        System.out.println("Starting all threads...");
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

        System.out.println("All threads completed\n");
    }

    /**
     * Main method - runs all examples
     */
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  THREAD CREATION COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        example1_ExtendingThread();
        System.out.println("─────────────────────────────────────────────────────\n");

        example2_ImplementingRunnable();
        System.out.println("─────────────────────────────────────────────────────\n");

        example3_LambdaExpression();
        System.out.println("─────────────────────────────────────────────────────\n");

        example4_AnonymousClass();
        System.out.println("─────────────────────────────────────────────────────\n");

        example5_NamingAndPriority();
        System.out.println("─────────────────────────────────────────────────────\n");

        example6_DaemonThreads();
        System.out.println("─────────────────────────────────────────────────────\n");

        example7_MultipleThreads();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}

// ==================== Supporting Classes ====================

/**
 * Example class extending Thread
 */
class MyThread extends Thread {
    private String taskName;

    public MyThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started in thread: " + getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + ": Step " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(taskName + " completed");
    }
}

/**
 * Example class implementing Runnable
 */
class MyTask implements Runnable {
    private String taskName;

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started in thread: " + Thread.currentThread().getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + ": Processing " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(taskName + " completed");
    }
}

