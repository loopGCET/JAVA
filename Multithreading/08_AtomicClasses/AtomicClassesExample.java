import java.util.concurrent.atomic.*;

/**
 * Atomic Classes Examples - Lock-Free Operations
 * @author GitHub Copilot
 * @topic Multithreading - Atomic Classes
 */
public class AtomicClassesExample {

    public static void main(String[] args) {
        System.out.println("═══ ATOMIC CLASSES EXAMPLES ═══\n");

        example1_AtomicInteger();
        example2_AtomicBoolean();
        example3_AtomicReference();
        example4_CompareAndSet();

        System.out.println("\n═══ All examples completed! ═══");
    }

    public static void example1_AtomicInteger() {
        System.out.println("--- AtomicInteger Example ---");

        AtomicInteger counter = new AtomicInteger(0);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final count: " + counter.get());
        System.out.println("Expected: 10000");
        System.out.println();
    }

    public static void example2_AtomicBoolean() {
        System.out.println("--- AtomicBoolean Example ---");

        AtomicBoolean flag = new AtomicBoolean(false);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                if (flag.compareAndSet(false, true)) {
                    System.out.println("Thread-" + threadNum + " set flag to true");
                } else {
                    System.out.println("Thread-" + threadNum + " couldn't set flag (already true)");
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final flag value: " + flag.get());
        System.out.println();
    }

    public static void example3_AtomicReference() {
        System.out.println("--- AtomicReference Example ---");

        AtomicReference<String> ref = new AtomicReference<>("initial");

        System.out.println("Initial value: " + ref.get());

        Thread updater = new Thread(() -> {
            String oldValue = ref.get();
            String newValue = "updated";
            if (ref.compareAndSet(oldValue, newValue)) {
                System.out.println("Updated from '" + oldValue + "' to '" + newValue + "'");
            }
        });

        updater.start();
        try {
            updater.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value: " + ref.get());
        System.out.println();
    }

    public static void example4_CompareAndSet() {
        System.out.println("--- Compare-And-Set Example ---");

        AtomicInteger value = new AtomicInteger(10);

        System.out.println("Initial value: " + value.get());

        // Successful CAS
        boolean success1 = value.compareAndSet(10, 20);
        System.out.println("CAS(10, 20): " + success1 + ", value: " + value.get());

        // Failed CAS
        boolean success2 = value.compareAndSet(10, 30);
        System.out.println("CAS(10, 30): " + success2 + ", value: " + value.get());

        // Successful CAS
        boolean success3 = value.compareAndSet(20, 30);
        System.out.println("CAS(20, 30): " + success3 + ", value: " + value.get());

        System.out.println();
    }
}

