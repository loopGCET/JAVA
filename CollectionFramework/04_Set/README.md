# Set Interface

## 📘 Overview

`Set<E>` is a collection that contains no duplicate elements. It models the mathematical set abstraction.

## 🔑 Key Characteristics

- ❌ **No duplicates** - Each element can appear at most once
- **Ordering:** Implementation dependent
- **Null values:** Implementation dependent (most allow one null)
- Extends `Collection<E>`

## 📋 Interface Hierarchy

```
Collection<E>
    │
    └── Set<E>
            ├── HashSet<E>
            ├── LinkedHashSet<E>
            └── SortedSet<E>
                    └── NavigableSet<E>
                            └── TreeSet<E>
```

## 🔄 Set Implementations

### HashSet ⭐ (Most Common)
- **Best for:** Fast lookups, no duplicates
- **Backed by:** HashMap
- **Ordering:** ❌ No order
- **Performance:** O(1) for add, remove, contains
- **Null:** ✅ One null allowed

### LinkedHashSet
- **Best for:** Unique elements with insertion order
- **Backed by:** LinkedHashMap
- **Ordering:** ✅ Insertion order
- **Performance:** O(1) for add, remove, contains
- **Null:** ✅ One null allowed

### TreeSet
- **Best for:** Sorted unique elements
- **Backed by:** TreeMap (Red-Black tree)
- **Ordering:** ✅ Sorted (natural or Comparator)
- **Performance:** O(log n) for add, remove, contains
- **Null:** ❌ Not allowed

## 📊 Comparison Table

| Feature | HashSet | LinkedHashSet | TreeSet |
|---------|---------|---------------|---------|
| Ordering | No | Insertion order | Sorted |
| Null allowed | Yes (1) | Yes (1) | No |
| Performance | O(1) | O(1) | O(log n) |
| Use case | General | Need order | Need sorting |

## 🎯 Common Use Cases

1. **Remove duplicates** from a collection
2. **Fast membership testing** (contains)
3. **Mathematical set operations** (union, intersection, difference)
4. **Store unique elements**

## ✅ When to Use Set

- Need to ensure uniqueness
- Frequently checking membership
- Don't need indexed access
- Implementing mathematical sets

## 💡 Set Operations

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

// Union
Set<Integer> union = new HashSet<>(set1);
union.addAll(set2);  // [1, 2, 3, 4, 5, 6, 7, 8]

// Intersection
Set<Integer> intersection = new HashSet<>(set1);
intersection.retainAll(set2);  // [4, 5]

// Difference
Set<Integer> difference = new HashSet<>(set1);
difference.removeAll(set2);  // [1, 2, 3]
```

## 📝 Examples

Explore the subdirectories for complete examples:
- `HashSet/` - Most commonly used Set
- `LinkedHashSet/` - Maintains insertion order
- `TreeSet/` - Sorted Set

## ⚠️ Important Notes

1. **Override equals() and hashCode()** for custom objects in HashSet
2. **Implement Comparable or provide Comparator** for TreeSet
3. **Elements must be immutable** (or at least not change while in Set)

