# Java Streams API - Quick Reference

## 🎯 Stream Creation

```java
// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// From Array
String[] array = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(array);

// Using Stream.of()
Stream<String> stream = Stream.of("a", "b", "c");

// Infinite Streams
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);
Stream<Double> randomStream = Stream.generate(Math::random);

// Parallel Stream
Stream<String> parallelStream = list.parallelStream();
```

---

## 🔄 Intermediate Operations (Lazy)

### filter - Keep matching elements
```java
stream.filter(s -> s.startsWith("a"))
```

### map - Transform elements
```java
stream.map(String::toUpperCase)
stream.map(s -> s.length())
```

### flatMap - Flatten nested structures
```java
stream.flatMap(list -> list.stream())
```

### distinct - Remove duplicates
```java
stream.distinct()
```

### sorted - Sort elements
```java
stream.sorted()
stream.sorted(Comparator.reverseOrder())
```

### limit - Take first n elements
```java
stream.limit(10)
```

### skip - Skip first n elements
```java
stream.skip(5)
```

### peek - Debug/side effects
```java
stream.peek(System.out::println)
```

---

## 🎯 Terminal Operations (Eager)

### collect - Gather to collection
```java
List<String> list = stream.collect(Collectors.toList());
Set<String> set = stream.collect(Collectors.toSet());
```

### forEach - Process each element
```java
stream.forEach(System.out::println)
```

### reduce - Combine to single value
```java
Optional<Integer> sum = stream.reduce(Integer::sum);
Integer sum = stream.reduce(0, Integer::sum);
```

### count - Count elements
```java
long count = stream.count()
```

### anyMatch, allMatch, noneMatch
```java
boolean hasMatch = stream.anyMatch(s -> s.length() > 5);
boolean allMatch = stream.allMatch(s -> s != null);
boolean noneMatch = stream.noneMatch(String::isEmpty);
```

### findFirst, findAny
```java
Optional<String> first = stream.findFirst();
Optional<String> any = stream.findAny();
```

### min, max
```java
Optional<Integer> min = stream.min(Integer::compareTo);
Optional<Integer> max = stream.max(Integer::compareTo);
```

---

## 📦 Collectors

### To Collection
```java
Collectors.toList()
Collectors.toSet()
Collectors.toCollection(ArrayList::new)
```

### Joining Strings
```java
Collectors.joining()
Collectors.joining(", ")
Collectors.joining(", ", "[", "]")
```

### Counting
```java
Collectors.counting()
```

### Statistics
```java
Collectors.averagingInt(Person::getAge)
Collectors.summingInt(Person::getAge)
Collectors.summarizingInt(Person::getAge)
```

### Grouping
```java
Collectors.groupingBy(Person::getCity)
Collectors.groupingBy(Person::getCity, Collectors.counting())
```

### Partitioning
```java
Collectors.partitioningBy(n -> n % 2 == 0)
```

### To Map
```java
Collectors.toMap(Person::getId, Person::getName)
```

---

## 🚀 Common Patterns

### Filter and Transform
```java
List<String> result = list.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

### Sum/Average
```java
int sum = numbers.stream()
    .mapToInt(Integer::intValue)
    .sum();

double avg = numbers.stream()
    .mapToInt(Integer::intValue)
    .average()
    .orElse(0.0);
```

### Group By
```java
Map<String, List<Person>> byCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity));
```

### Partition
```java
Map<Boolean, List<Integer>> evenOdd = numbers.stream()
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
```

### Flatten List of Lists
```java
List<String> flat = listOfLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
```

### Find Max/Min
```java
Optional<Integer> max = numbers.stream()
    .max(Integer::compareTo);
```

### Distinct and Sorted
```java
List<Integer> result = numbers.stream()
    .distinct()
    .sorted()
    .collect(Collectors.toList());
```

---

## ⚡ Parallel Streams

```java
// Create parallel stream
list.parallelStream()
stream.parallel()

// Check if parallel
stream.isParallel()

// Convert to sequential
stream.sequential()

// Custom thread pool
ForkJoinPool customPool = new ForkJoinPool(4);
customPool.submit(() -> 
    list.parallelStream().forEach(System.out::println)
).get();
```

---

## 🎓 Performance Tips

### DO ✅
```java
// Use primitive streams for better performance
IntStream.range(0, 100).sum()

// Use parallel for large datasets and CPU-intensive operations
bigList.parallelStream()
    .filter(heavyComputation)
    .collect(Collectors.toList());

// Use method references
stream.map(String::toUpperCase)
```

### DON'T ❌
```java
// Don't modify source during stream operation
list.stream().forEach(list::remove); // ❌

// Don't use parallel for small datasets
smallList.parallelStream() // ❌ overhead > benefit

// Don't perform I/O in streams
stream.map(this::readFromDatabase) // ❌
```

---

## 📊 Quick Decision Guide

| Task | Use This |
|------|----------|
| Filter elements | `.filter(predicate)` |
| Transform elements | `.map(function)` |
| Flatten nested lists | `.flatMap(function)` |
| Remove duplicates | `.distinct()` |
| Sort | `.sorted()` |
| Take first n | `.limit(n)` |
| Skip first n | `.skip(n)` |
| Count | `.count()` |
| To List | `.collect(Collectors.toList())` |
| To Set | `.collect(Collectors.toSet())` |
| Join strings | `.collect(Collectors.joining())` |
| Group by | `.collect(Collectors.groupingBy())` |
| Sum numbers | `.mapToInt().sum()` |
| Find max/min | `.max()` / `.min()` |
| Any match | `.anyMatch(predicate)` |
| Process each | `.forEach(consumer)` |

---

## 🎓 Interview Quick Answers

**Q: Stream vs Collection?**  
A: Collections store data, Streams process data. Streams are lazy, one-time use, and functional.

**Q: Intermediate vs Terminal operations?**  
A: Intermediate are lazy (return Stream), Terminal are eager (return result/void).

**Q: When to use parallel streams?**  
A: Large datasets (1000+ elements), CPU-intensive operations, stateless operations.

**Q: Can you reuse a stream?**  
A: No, streams are one-time use. Create new stream for each operation.

---

**Quick Navigation:**
- [Full README](README.md)
- [File Index](INDEX.md)
- [Testing Guide](TESTING.md)

