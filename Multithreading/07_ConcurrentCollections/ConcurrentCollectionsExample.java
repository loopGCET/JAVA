import java.util.concurrent.*;

/**
 * Concurrent Collections Examples
 * @author GitHub Copilot
 * @topic Multithreading - Concurrent Collections
 */
public class ConcurrentCollectionsExample {

    public static void main(String[] args) {
        System.out.println("═══ CONCURRENT COLLECTIONS EXAMPLES ═══\n");

        example1_ConcurrentHashMap();
        example2_BlockingQueue();
        example3_CopyOnWriteArrayList();

        System.out.println("\n═══ All examples completed! ═══");
    }

    public static void example1_ConcurrentHashMap() {
        System.out.println("--- ConcurrentHashMap Example ---");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Multiple threads updating map
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String key = "key" + j;
                    map.compute(key, (k, v) -> (v == null) ? 1 : v + 1);
                    System.out.println("Thread-" + threadNum + " updated " + key);
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

        System.out.println("Final map: " + map);
        System.out.println();
    }

    public static void example2_BlockingQueue() {
        System.out.println("--- BlockingQueue Example ---");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer item = queue.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void example3_CopyOnWriteArrayList() {
        System.out.println("--- CopyOnWriteArrayList Example ---");

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                list.add("Item-" + i);
                System.out.println("Added: Item-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Reader thread
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Reading list: " + list);
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final list: " + list);
        System.out.println();
    }
}

