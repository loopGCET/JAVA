import java.util.*;

/**
 * Wait/Notify Examples - Producer-Consumer Pattern
 * @author GitHub Copilot
 * @topic Multithreading - Wait/Notify
 */
public class WaitNotifyExample {

    public static void main(String[] args) {
        System.out.println("═══ WAIT/NOTIFY EXAMPLES ═══\n");

        example1_ProducerConsumer();

        System.out.println("\n═══ All examples completed! ═══");
    }

    public static void example1_ProducerConsumer() {
        System.out.println("--- Producer-Consumer Pattern ---");

        SharedBuffer buffer = new SharedBuffer(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int item = buffer.consume();
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
    }
}

class SharedBuffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Buffer full, producer waiting...");
            wait();
        }
        queue.add(item);
        System.out.println("Produced: " + item + " (size: " + queue.size() + ")");
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty, consumer waiting...");
            wait();
        }
        int item = queue.remove();
        System.out.println("Consumed: " + item + " (size: " + queue.size() + ")");
        notifyAll();
        return item;
    }
}

