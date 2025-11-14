# Java Collections Framework - Quick Reference Card

## 🎯 Choose the Right Collection

### I need an ordered list that allows duplicates
```java
List<String> list = new ArrayList<>();        // ⭐ Default choice - fast random access
List<String> list = new LinkedList<>();       // Frequent insertions/deletions at ends
List<String> list = new Vector<>();           // ⚠️ Legacy - synchronized (use ArrayList)
Stack<String> stack = new Stack<>();          // ⚠️ Legacy - LIFO (use ArrayDeque)
```

### I need unique elements (no duplicates)
```java
Set<String> set = new HashSet<>();            // ⭐ Default choice - fastest, no order
Set<String> set = new LinkedHashSet<>();      // Maintains insertion order
Set<String> set = new TreeSet<>();            // Sorted order (natural or Comparator)
```

### I need a queue (FIFO)
```java
Queue<String> queue = new ArrayDeque<>();     // ⭐ Recommended
Queue<String> queue = new LinkedList<>();     // Alternative
Queue<String> queue = new PriorityQueue<>();  // Priority-based
```

### I need a stack (LIFO)
```java
Deque<String> stack = new ArrayDeque<>();     // ⭐ Recommended (NOT Stack!)
stack.push("item");
stack.pop();
```

### I need key-value pairs
```java
Map<K, V> map = new HashMap<>();              // ⭐ Default choice - fast O(1), no order
Map<K, V> map = new LinkedHashMap<>();        // Insertion/access order, LRU cache
Map<K, V> map = new TreeMap<>();              // Sorted by keys (NavigableMap)
Hashtable<K, V> table = new Hashtable<>();    // ⚠️ Legacy - synchronized (use HashMap/ConcurrentHashMap)
```

## 📊 Performance Cheat Sheet

| Collection | Get | Add | Remove | Contains | Notes |
|-----------|-----|-----|--------|----------|-------|
| **ArrayList** | O(1) | O(1)* | O(n) | O(n) | Best for random access |
| **LinkedList** | O(n) | O(1) | O(1) | O(n) | Best for add/remove at ends |
| **Vector** | O(1) | O(1)* | O(n) | O(n) | Legacy - synchronized overhead |
| **Stack** | O(1) | O(1) | O(1) | O(n) | Legacy - use ArrayDeque |
| **HashSet** | - | O(1) | O(1) | O(1) | Fast lookups, no order |
| **LinkedHashSet** | - | O(1) | O(1) | O(1) | Fast + insertion order |
| **TreeSet** | - | O(log n) | O(log n) | O(log n) | Sorted order |
| **HashMap** | O(1) | O(1) | O(1) | O(1) | Fast key lookups |
| **LinkedHashMap** | O(1) | O(1) | O(1) | O(1) | Fast + insertion/access order |
| **TreeMap** | O(log n) | O(log n) | O(log n) | O(log n) | Sorted keys |
| **Hashtable** | O(1) | O(1) | O(1) | O(1) | Legacy - synchronized |
| **PriorityQueue** | O(1)** | O(log n) | O(log n) | O(n) | Priority access |
| **ArrayDeque** | O(1)*** | O(1) | O(1) | O(n) | Stack/Queue/Deque |

\* Amortized, \*\* peek only, \*\*\* at ends

## 🔥 Most Common Operations

### ArrayList
```java
List<String> list = new ArrayList<>();
list.add("item");                    // Add to end
list.add(0, "first");                // Insert at index
list.get(0);                         // Access by index
list.set(0, "new");                  // Update by index
list.remove(0);                      // Remove by index
list.contains("item");               // Check existence
list.size();                         // Get size
Collections.sort(list);              // Sort
```

### HashSet
```java
Set<String> set = new HashSet<>();
set.add("item");                     // Add element
set.remove("item");                  // Remove element
set.contains("item");                // Check existence
set.size();                          // Get size
set.addAll(otherSet);               // Union
set.retainAll(otherSet);            // Intersection
set.removeAll(otherSet);            // Difference
```

### HashMap
```java
Map<String, Integer> map = new HashMap<>();
map.put("key", 100);                 // Add/update
map.get("key");                      // Get value
map.getOrDefault("key", 0);          // Get with default
map.remove("key");                   // Remove
map.containsKey("key");              // Check key
map.containsValue(100);              // Check value
map.keySet();                        // Get all keys
map.values();                        // Get all values
map.entrySet();                      // Get all entries

// Java 8+
map.putIfAbsent("key", 100);
map.computeIfAbsent("key", k -> new ArrayList<>());
map.merge("key", 1, Integer::sum);   // Increment
map.forEach((k, v) -> System.out.println(k + "=" + v));
```

### PriorityQueue
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();  // Min-heap
pq.offer(5);                         // Add element
pq.peek();                           // View top (don't remove)
pq.poll();                           // Remove and return top
pq.size();                           // Get size

// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

### ArrayDeque
```java
Deque<String> deque = new ArrayDeque<>();

// As Stack (LIFO)
deque.push("item");                  // Add to front
deque.pop();                         // Remove from front
deque.peek();                        // View front

// As Queue (FIFO)
deque.offer("item");                 // Add to end
deque.poll();                        // Remove from front
deque.peek();                        // View front

// As Deque (both ends)
deque.addFirst("item");              // Add to front
deque.addLast("item");               // Add to end
deque.removeFirst();                 // Remove from front
deque.removeLast();                  // Remove from end
```

### Vector (Legacy - Use ArrayList)
```java
Vector<String> vector = new Vector<>();
vector.add("item");                  // Same as ArrayList
vector.addElement("item");           // Legacy method
vector.elementAt(0);                 // Legacy get method
vector.capacity();                   // Current capacity
// ⚠️ All methods synchronized - slower than ArrayList
```

### Stack (Legacy - Use ArrayDeque)
```java
Stack<String> stack = new Stack<>();
stack.push("item");                  // Add to top
stack.pop();                         // Remove from top
stack.peek();                        // View top
stack.search("item");                // Position from top (1-based)
stack.empty();                       // Check if empty
// ⚠️ Use Deque<String> stack = new ArrayDeque<>() instead
```

### LinkedHashSet
```java
Set<String> set = new LinkedHashSet<>();
set.add("item");                     // Add element
set.remove("item");                  // Remove element
set.contains("item");                // Check existence
// Iteration order = insertion order ✅
// Remove duplicates while preserving order
Set<String> unique = new LinkedHashSet<>(listWithDuplicates);
```

### TreeMap
```java
TreeMap<Integer, String> map = new TreeMap<>();
map.put(10, "ten");                  // Add key-value
map.get(10);                         // Get value
map.firstKey();                      // Smallest key
map.lastKey();                       // Largest key
map.ceilingKey(15);                  // Least key >= 15
map.floorKey(15);                    // Greatest key <= 15
map.subMap(10, 50);                  // Range [10, 50)
// Keys automatically sorted ✅
```

### LinkedHashMap (LRU Cache)
```java
// Insertion order (default)
Map<String, Integer> map = new LinkedHashMap<>();

// Access order (for LRU cache)
Map<String, Integer> lru = new LinkedHashMap<>(16, 0.75f, true);

// LRU Cache implementation
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

### Hashtable (Legacy - Use HashMap/ConcurrentHashMap)
```java
Hashtable<String, Integer> table = new Hashtable<>();
table.put("key", 100);               // Add/update (no nulls!)
table.get("key");                    // Get value
// ⚠️ No null keys or values allowed
// ⚠️ All methods synchronized - use HashMap or ConcurrentHashMap
```

## 🔄 Iteration Patterns

```java
// For-each loop
for (String item : collection) {
    System.out.println(item);
}

// Iterator
Iterator<String> it = collection.iterator();
while (it.hasNext()) {
    String item = it.next();
    // it.remove(); // Safe removal during iteration
}

// Java 8 forEach
collection.forEach(item -> System.out.println(item));
collection.forEach(System.out::println);

// Stream API
collection.stream()
         .filter(item -> item.length() > 5)
         .map(String::toUpperCase)
         .forEach(System.out::println);

// Map iteration
map.forEach((key, value) -> System.out.println(key + "=" + value));

for (Map.Entry<K, V> entry : map.entrySet()) {
    K key = entry.getKey();
    V value = entry.getValue();
}
```

## 🛠️ Collections Utility Methods

```java
// Sorting
Collections.sort(list);
Collections.sort(list, Comparator.reverseOrder());
Collections.reverse(list);

// Searching (requires sorted list)
int index = Collections.binarySearch(list, "item");

// Min/Max
String min = Collections.min(collection);
String max = Collections.max(collection);

// Frequency
int count = Collections.frequency(collection, "item");

// Shuffle
Collections.shuffle(list);

// Fill
Collections.fill(list, "value");

// Thread-safe wrappers
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
Map<K, V> syncMap = Collections.synchronizedMap(new HashMap<>());

// Unmodifiable wrappers
List<String> unmodList = Collections.unmodifiableList(list);
Set<String> unmodSet = Collections.unmodifiableSet(set);
Map<K, V> unmodMap = Collections.unmodifiableMap(map);
```

## ⚠️ Common Pitfalls

### ConcurrentModificationException
```java
// ❌ WRONG - modifying during iteration
for (String item : list) {
    list.remove(item);  // Throws exception!
}

// ✅ CORRECT - use Iterator.remove()
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (shouldRemove(it.next())) {
        it.remove();
    }
}

// ✅ CORRECT - use removeIf
list.removeIf(item -> shouldRemove(item));
```

### Null Handling
```java
// HashMap allows null key/values
map.put(null, "value");  // ✅ OK
map.put("key", null);    // ✅ OK

// TreeMap does NOT allow null keys
treeMap.put(null, "value");  // ❌ NullPointerException

// TreeSet does NOT allow null elements
treeSet.add(null);  // ❌ NullPointerException
```

### Custom Objects
```java
// For HashSet/HashMap: Override equals() and hashCode()
@Override
public boolean equals(Object o) { /* ... */ }

@Override
public int hashCode() { return Objects.hash(field1, field2); }

// For TreeSet/TreeMap: Implement Comparable or provide Comparator
class Person implements Comparable<Person> {
    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}
```

## 💡 Best Practices

1. **Use interfaces in declarations**
   ```java
   List<String> list = new ArrayList<>();     // ✅ Good
   ArrayList<String> list = new ArrayList<>(); // ❌ Avoid
   ```

2. **Initialize with capacity if size known**
   ```java
   List<String> list = new ArrayList<>(1000);
   Map<String, Integer> map = new HashMap<>(1000);
   ```

3. **Use appropriate collection**
   - Need fast access? → ArrayList
   - Need unique elements? → HashSet
   - Need sorted? → TreeSet/TreeMap
   - Need stack/queue? → ArrayDeque

4. **Don't use legacy classes**
   - ❌ Vector → ✅ ArrayList (or Collections.synchronizedList if thread-safety needed)
   - ❌ Stack → ✅ ArrayDeque (faster and better designed)
   - ❌ Hashtable → ✅ HashMap (single-threaded) or ConcurrentHashMap (concurrent)

5. **Use Java 8+ methods**
   ```java
   map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
   map.merge(key, 1, Integer::sum);
   list.removeIf(item -> item.length() > 5);
   ```

---

**For full examples and documentation, see the CollectionFramework directory!**

