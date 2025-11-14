/**
 * Comprehensive demonstration of Synchronization in Java.
 *
 * @author GitHub Copilot
 * @topic Multithreading - Synchronization
 */
public class SynchronizationExample {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  SYNCHRONIZATION COMPREHENSIVE EXAMPLES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        example1_RaceCondition();
        System.out.println("─────────────────────────────────────────────────────\n");

        example2_SynchronizedMethod();
        System.out.println("─────────────────────────────────────────────────────\n");

        example3_SynchronizedBlock();
        System.out.println("─────────────────────────────────────────────────────\n");

        example4_ClassLevelSynchronization();
        System.out.println("─────────────────────────────────────────────────────\n");

        example5_BankAccount();
        System.out.println("─────────────────────────────────────────────────────\n");

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  All examples completed successfully!");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    public static void example1_RaceCondition() {
        System.out.println("--- Example 1: Race Condition (Unsafe) ---");

        UnsafeCounter counter = new UnsafeCounter();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final count (unsafe): " + counter.getCount());
        System.out.println("Expected: 10000");
        System.out.println();
    }

    public static void example2_SynchronizedMethod() {
        System.out.println("--- Example 2: Synchronized Method (Safe) ---");

        SafeCounter counter = new SafeCounter();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final count (safe): " + counter.getCount());
        System.out.println("Expected: 10000");
        System.out.println();
    }

    public static void example3_SynchronizedBlock() {
        System.out.println("--- Example 3: Synchronized Block ---");

        BlockSynchronizedCounter counter = new BlockSynchronizedCounter();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final count: " + counter.getCount());
        System.out.println();
    }

    public static void example4_ClassLevelSynchronization() {
        System.out.println("--- Example 4: Class-Level Synchronization ---");

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    int id = IDGenerator.getNextID();
                    System.out.println(Thread.currentThread().getName() + " got ID: " + id);
                }
            });
            threads[i].setName("Thread-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
    }

    public static void example5_BankAccount() {
        System.out.println("--- Example 5: Bank Account (Synchronized) ---");

        BankAccount account = new BankAccount(1000);

        Thread depositor = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(100);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread withdrawer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.withdraw(150);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        depositor.start();
        withdrawer.start();

        try {
            depositor.join();
            withdrawer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: $" + account.getBalance());
        System.out.println();
    }
}

// Supporting Classes

class UnsafeCounter {
    private int count = 0;

    public void increment() {
        count++; // Not atomic!
    }

    public int getCount() {
        return count;
    }
}

class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

class BlockSynchronizedCounter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized(lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized(lock) {
            return count;
        }
    }
}

class IDGenerator {
    private static int nextID = 1;

    public static synchronized int getNextID() {
        return nextID++;
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        System.out.println(Thread.currentThread().getName() + " depositing $" + amount);
        balance += amount;
        System.out.println("  New balance: $" + balance);
    }

    public synchronized void withdraw(double amount) {
        System.out.println(Thread.currentThread().getName() + " withdrawing $" + amount);
        if (balance >= amount) {
            balance -= amount;
            System.out.println("  New balance: $" + balance);
        } else {
            System.out.println("  Insufficient funds");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }
}

