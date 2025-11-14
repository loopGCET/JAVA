import java.util.concurrent.locks.*;

/**
 * Locks Examples - ReentrantLock and ReadWriteLock
 * @author GitHub Copilot
 * @topic Multithreading - Locks
 */
public class LocksExample {

    public static void main(String[] args) {
        System.out.println("═══ LOCKS EXAMPLES ═══\n");

        example1_ReentrantLock();
        example2_TryLock();
        example3_ReadWriteLock();

        System.out.println("\n═══ All examples completed! ═══");
    }

    public static void example1_ReentrantLock() {
        System.out.println("--- ReentrantLock Example ---");

        Counter counter = new Counter();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
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

        System.out.println("Final count: " + counter.getCount());
        System.out.println();
    }

    public static void example2_TryLock() {
        System.out.println("--- TryLock Example ---");

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1 acquired lock");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Thread 1 released lock");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(500);
                if (lock.tryLock()) {
                    try {
                        System.out.println("Thread 2 acquired lock");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Thread 2 couldn't acquire lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void example3_ReadWriteLock() {
        System.out.println("--- ReadWriteLock Example ---");

        SharedResource resource = new SharedResource();

        // Readers
        Thread[] readers = new Thread[3];
        for (int i = 0; i < 3; i++) {
            readers[i] = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    System.out.println(Thread.currentThread().getName() + " reading: " + resource.read());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            readers[i].setName("Reader-" + (i + 1));
            readers[i].start();
        }

        // Writer
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                resource.write(i + 1);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        writer.setName("Writer");
        writer.start();

        for (Thread t : readers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

class Counter {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

class SharedResource {
    private int value = 0;
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public int read() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired read lock");
            return value;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void write(int newValue) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired write lock");
            value = newValue;
            System.out.println(Thread.currentThread().getName() + " wrote: " + value);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

