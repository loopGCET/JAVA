# Java Learning Repository - Quick Reference

> One-page cheat sheet for all modules

---

## 🗂️ Collections Framework

### Common Collections

```java
// List - Ordered, allows duplicates
List<String> arrayList = new ArrayList<>();     // Fast random access
List<String> linkedList = new LinkedList<>();   // Fast insert/delete

// Set - No duplicates
Set<String> hashSet = new HashSet<>();          // Unordered
Set<String> linkedHashSet = new LinkedHashSet<>(); // Insertion order
Set<String> treeSet = new TreeSet<>();          // Sorted

// Queue
Queue<String> priorityQueue = new PriorityQueue<>(); // Priority heap
Deque<String> arrayDeque = new ArrayDeque<>();  // Stack/Queue

// Map - Key-value pairs
Map<String, Integer> hashMap = new HashMap<>(); // Unordered
Map<String, Integer> linkedHashMap = new LinkedHashMap<>(); // Insertion order
Map<String, Integer> treeMap = new TreeMap<>(); // Sorted keys
```

**When to Use:**
- `ArrayList`: Random access, iteration
- `LinkedList`: Frequent insertions/deletions
- `HashSet`: Unique elements, fast lookup
- `TreeSet`: Sorted unique elements
- `HashMap`: Fast key-value lookup
- `TreeMap`: Sorted keys

---

## 🛡️ Exception Handling

```java
// Try-Catch-Finally
try {
    // Code that may throw exception
} catch (SpecificException e) {
    // Handle specific exception
} catch (Exception e) {
    // Handle general exception
} finally {
    // Always executes
}

// Try-with-resources (Java 7+)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    // Use resource
} // Automatically closed

// Throws
public void method() throws IOException {
    // Method may throw IOException
}

// Custom Exception
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
```

---

## 🧵 Multithreading

```java
// Creating Threads
Thread t1 = new Thread(() -> System.out.println("Running"));
t1.start();

// ExecutorService
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> { /* task */ });
executor.shutdown();

// Synchronization
synchronized(lock) {
    // Critical section
}

public synchronized void method() {
    // Thread-safe method
}

// Wait/Notify
synchronized(lock) {
    while (!condition) lock.wait();
    // Do work
    lock.notifyAll();
}

// Locks
Lock lock = new ReentrantLock();
lock.lock();
try {
    // Critical section
} finally {
    lock.unlock();
}

// Atomic
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();

// Concurrent Collections
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
```

---

## 🌊 Streams API

```java
// Creating Streams
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream();
IntStream range = IntStream.range(1, 10);

// Intermediate Operations (lazy)
stream.filter(s -> s.length() > 3)
      .map(String::toUpperCase)
      .distinct()
      .sorted()
      .limit(10)
      .skip(2);

// Terminal Operations (eager)
long count = stream.count();
stream.forEach(System.out::println);
List<String> list = stream.collect(Collectors.toList());
Optional<String> first = stream.findFirst();
boolean anyMatch = stream.anyMatch(s -> s.startsWith("A"));

// Collectors
Collectors.toList()
Collectors.toSet()
Collectors.joining(", ")
Collectors.groupingBy(Person::getCity)
Collectors.partitioningBy(n -> n % 2 == 0)
Collectors.counting()
Collectors.summarizingInt(Person::getAge)

// Reduce
int sum = numbers.stream().reduce(0, Integer::sum);
```

---

## λ Lambdas & Functional Interfaces

```java
// Lambda Syntax
(parameters) -> expression
(parameters) -> { statements; }

// Examples
Runnable r = () -> System.out.println("Hello");
Predicate<Integer> isEven = n -> n % 2 == 0;
Function<String, Integer> length = s -> s.length();
Consumer<String> print = s -> System.out.println(s);
Supplier<Double> random = () -> Math.random();

// Method References
list.forEach(System.out::println);        // Instance method
list.stream().map(String::toUpperCase);   // Instance method
list.stream().map(Integer::parseInt);     // Static method
list.stream().map(Person::new);           // Constructor

// Built-in Functional Interfaces
Predicate<T>    // T -> boolean
Function<T,R>   // T -> R
Consumer<T>     // T -> void
Supplier<T>     // () -> T
BiFunction<T,U,R> // (T,U) -> R
```

---

## 🎁 Generics

```java
// Generic Class
class Box<T> {
    private T value;
    public T get() { return value; }
    public void set(T value) { this.value = value; }
}

// Generic Method
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}

// Bounded Types
<T extends Number>           // Upper bound
<T super Integer>            // Lower bound

// Wildcards
List<?>                      // Unbounded
List<? extends Number>       // Upper bounded
List<? super Integer>        // Lower bounded

// Usage
Box<String> stringBox = new Box<>();
Box<Integer> intBox = new Box<>();
```

---

## 📁 File I/O

```java
// Reading Files (NIO.2)
List<String> lines = Files.readAllLines(Paths.get("file.txt"));
Stream<String> stream = Files.lines(Paths.get("file.txt"));

// Writing Files
Files.write(Paths.get("file.txt"), "content".getBytes());
Files.write(Paths.get("file.txt"), lines);

// Byte Streams
try (FileInputStream fis = new FileInputStream("file.bin");
     FileOutputStream fos = new FileOutputStream("out.bin")) {
    int data;
    while ((data = fis.read()) != -1) {
        fos.write(data);
    }
}

// Character Streams
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"));
     BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        bw.write(line);
        bw.newLine();
    }
}

// Serialization
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("object.ser"))) {
    oos.writeObject(myObject);
}
```

---

## 🏷️ Annotations

```java
// Built-in Annotations
@Override
@Deprecated
@SuppressWarnings("unchecked")
@FunctionalInterface

// Custom Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value();
    int priority() default 1;
}

// Usage
@MyAnnotation(value = "example", priority = 5)
public void method() { }

// Processing
if (method.isAnnotationPresent(MyAnnotation.class)) {
    MyAnnotation ann = method.getAnnotation(MyAnnotation.class);
    String value = ann.value();
}
```

---

## 🔍 Reflection

```java
// Getting Class Object
Class<?> clazz = MyClass.class;
Class<?> clazz = obj.getClass();
Class<?> clazz = Class.forName("com.example.MyClass");

// Methods
Method[] methods = clazz.getDeclaredMethods();
Method method = clazz.getMethod("methodName", paramTypes);
Object result = method.invoke(instance, args);

// Fields
Field[] fields = clazz.getDeclaredFields();
Field field = clazz.getDeclaredField("fieldName");
field.setAccessible(true);
Object value = field.get(instance);
field.set(instance, newValue);

// Constructors
Constructor<?> constructor = clazz.getConstructor(paramTypes);
Object instance = constructor.newInstance(args);

// Dynamic Proxy
Interface proxy = (Interface) Proxy.newProxyInstance(
    classLoader,
    new Class<?>[] { Interface.class },
    (proxy, method, args) -> {
        // Handle invocation
        return result;
    }
);
```

---

## 💾 JDBC

```java
// Connection
Connection conn = DriverManager.getConnection(url, user, password);

// Statement
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// PreparedStatement (preferred)
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, userId);
ResultSet rs = pstmt.executeQuery();

// Processing Results
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
}

// Transactions
conn.setAutoCommit(false);
try {
    stmt.executeUpdate("INSERT ...");
    stmt.executeUpdate("UPDATE ...");
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
}

// Cleanup
rs.close();
stmt.close();
conn.close();

// Try-with-resources (recommended)
try (Connection conn = DriverManager.getConnection(url, user, password);
     PreparedStatement pstmt = conn.prepareStatement(sql);
     ResultSet rs = pstmt.executeQuery()) {
    // Use resources
}
```

---

## 🌐 Networking

```java
// Socket Programming (TCP)
// Server
ServerSocket serverSocket = new ServerSocket(8080);
Socket clientSocket = serverSocket.accept();
BufferedReader in = new BufferedReader(
    new InputStreamReader(clientSocket.getInputStream()));
PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

// Client
Socket socket = new Socket("localhost", 8080);
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
BufferedReader in = new BufferedReader(
    new InputStreamReader(socket.getInputStream()));

// HTTP Client (Java 11+)
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .build();
HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());

// URL Connection
URL url = new URL("https://example.com");
URLConnection connection = url.openConnection();
BufferedReader in = new BufferedReader(
    new InputStreamReader(connection.getInputStream()));

// Datagram Sockets (UDP)
// Sender
DatagramSocket socket = new DatagramSocket();
byte[] buffer = message.getBytes();
DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
    InetAddress.getByName("localhost"), 9876);
socket.send(packet);

// Receiver
DatagramSocket socket = new DatagramSocket(9876);
byte[] buffer = new byte[1024];
DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
socket.receive(packet);
```

---

## 🎯 Best Practices Quick Guide

### Collections
- ✅ Use ArrayList for most cases
- ✅ Use HashMap for key-value lookups
- ✅ Initialize with capacity if size known
- ❌ Don't use Vector/Hashtable (legacy)

### Exception Handling
- ✅ Catch specific exceptions first
- ✅ Use try-with-resources for AutoCloseable
- ✅ Log exceptions properly
- ❌ Don't catch Exception unnecessarily
- ❌ Don't swallow exceptions silently

### Multithreading
- ✅ Use ExecutorService over manual threads
- ✅ Use concurrent collections
- ✅ Minimize synchronized scope
- ❌ Don't synchronize on this in public classes
- ❌ Avoid nested locks (deadlock risk)

### Streams
- ✅ Use method references when possible
- ✅ Use parallel streams for large datasets
- ✅ Use primitive streams (IntStream) for performance
- ❌ Don't modify source during stream operation
- ❌ Don't reuse streams

### Generics
- ✅ Use generics for type safety
- ✅ Prefer List<String> over raw types
- ✅ Use bounded wildcards for flexibility
- ❌ Don't use raw types
- ❌ Avoid unchecked warnings

---

## 📊 Performance Comparison

| Operation | ArrayList | LinkedList | HashSet | TreeSet |
|-----------|-----------|------------|---------|---------|
| Get | O(1) | O(n) | N/A | N/A |
| Add (end) | O(1) | O(1) | O(1) | O(log n) |
| Add (middle) | O(n) | O(1)* | O(1) | O(log n) |
| Remove | O(n) | O(1)* | O(1) | O(log n) |
| Contains | O(n) | O(n) | O(1) | O(log n) |

*Assuming you have the position

| Map Operation | HashMap | TreeMap | LinkedHashMap |
|--------------|---------|---------|---------------|
| Get | O(1) | O(log n) | O(1) |
| Put | O(1) | O(log n) | O(1) |
| Remove | O(1) | O(log n) | O(1) |
| Iteration Order | Random | Sorted | Insertion/Access |

---

## 🔗 Quick Links

- [Main README](README.md) - Complete guide
- [Index](INDEX.md) - Full navigation
- [Project Summary](PROJECT_SUMMARY.md) - Statistics
- [Structure](STRUCTURE.md) - Directory layout
- [Contributing](CONTRIBUTING.md) - How to contribute

---

**Last Updated:** November 2025  
**Java Version:** 8+  
**Status:** ✅ Complete

