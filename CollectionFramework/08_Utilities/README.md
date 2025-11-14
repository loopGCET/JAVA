# Collections Utilities

## 📘 Overview

This directory contains examples of utility classes and methods that work with collections.

## 🛠️ Utility Classes

### Collections (java.util.Collections)
Static utility methods for collections:
- Sorting
- Searching
- Reversing
- Shuffling
- Synchronization wrappers
- Unmodifiable wrappers
- Singleton collections

### Arrays (java.util.Arrays)
Static utility methods for arrays:
- Sorting
- Searching
- Copying
- Filling
- Converting to List
- Comparing

## 📝 Examples

- `CollectionsUtility.java` - Comprehensive examples of Collections utility methods
- `ArraysUtility.java` - Comprehensive examples of Arrays utility methods

## 🎯 Common Operations

### Collections Class
```java
// Sorting
Collections.sort(list);
Collections.sort(list, comparator);

// Searching
Collections.binarySearch(list, key);

// Reversing
Collections.reverse(list);

// Shuffling
Collections.shuffle(list);

// Min/Max
Collections.min(collection);
Collections.max(collection);

// Frequency
Collections.frequency(collection, element);

// Synchronization
List<String> syncList = Collections.synchronizedList(new ArrayList<>());

// Unmodifiable
List<String> unmodifiable = Collections.unmodifiableList(list);
```

### Arrays Class
```java
// Sorting
Arrays.sort(array);
Arrays.sort(array, comparator);

// Searching
Arrays.binarySearch(array, key);

// Copying
int[] copy = Arrays.copyOf(array, newLength);

// Filling
Arrays.fill(array, value);

// Converting to List
List<String> list = Arrays.asList("A", "B", "C");

// Comparing
boolean equal = Arrays.equals(array1, array2);

// Printing
System.out.println(Arrays.toString(array));
```

## 💡 Best Practices

1. **Use Collections.sort()** instead of manual sorting
2. **Use binarySearch() only on sorted collections**
3. **Synchronized wrappers** for thread-safety
4. **Unmodifiable collections** for defensive copies
5. **Arrays.asList()** creates fixed-size list (can't add/remove)

