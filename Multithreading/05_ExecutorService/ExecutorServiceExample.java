import java.util.concurrent.*;

/**
 * Executor Service Examples
 * @author GitHub Copilot
 * @topic Multithreading - Executor Service
 */
public class ExecutorServiceExample {

    public static void main(String[] args) {
        System.out.println("═══ EXECUTOR SERVICE EXAMPLES ═══\n");

        example1_FixedThreadPool();
        example2_CallableAndFuture();
        example3_ScheduledExecutor();

        System.out.println("\n═══ All examples completed! ═══");
    }

    public static void example1_FixedThreadPool() {
        System.out.println("--- Fixed Thread Pool ---");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void example2_CallableAndFuture() {
        System.out.println("--- Callable and Future ---");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(1000);
            return 42;
        });

        try {
            System.out.println("Waiting for result...");
            Integer result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println();
    }

    public static void example3_ScheduledExecutor() {
        System.out.println("--- Scheduled Executor ---");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> {
            System.out.println("Task executed after delay");
        }, 2, TimeUnit.SECONDS);

        scheduler.shutdown();
        try {
            scheduler.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

