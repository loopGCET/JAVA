# Vector

## 📘 Overview

`Vector<E>` is a synchronized, resizable array implementation of the `List` interface. It's similar to ArrayList but with synchronized methods for thread-safety.

## 🔑 Key Characteristics

- **Backed by:** Dynamic resizable array
- **Ordering:** Maintains insertion order
- **Duplicates:** ✅ Allows duplicate elements
- **Null values:** ✅ Allows null elements
- **Thread-safe:** ✅ Yes (all methods synchronized)
- **Performance:** Slower than ArrayList due to synchronization overhead
- **Status:** ⚠️ Legacy class (since Java 1.0)

## ⚡ Time Complexity

| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| `get(index)` | O(1) | Direct array access |
| `add(element)` | O(1) amortized | Synchronized overhead |
| `add(index, element)` | O(n) | Shifts elements |
| `remove(index)` | O(n) | Shifts elements |
| `contains(element)` | O(n) | Linear search |
| `size()` | O(1) | Stored as variable |

## 💾 Memory

- **Initial capacity:** 10 (default)
- **Growth factor:** 2x (100% increase - doubles the capacity)
- **Memory overhead:** Higher than ArrayList due to synchronization

## ✅ When to Use

- ⚠️ **Legacy code maintenance** only
- ⚠️ **Thread-safe List** needed (but prefer CopyOnWriteArrayList)

## ❌ When NOT to Use (Prefer Alternatives)

- ❌ **Single-threaded applications** → Use ArrayList instead
- ❌ **Modern multi-threaded code** → Use CopyOnWriteArrayList or Collections.synchronizedList()
- ❌ **New projects** → Vector is legacy, use modern alternatives

## 🎯 Common Use Cases

1. **Legacy applications** (maintaining old code)
2. **Thread-safe List operations** (though better alternatives exist)

## 📋 Constructors

```java
// 1. Default constructor (capacity = 10)
Vector<String> vector1 = new Vector<>();

// 2. With initial capacity
Vector<String> vector2 = new Vector<>(100);

// 3. With initial capacity and capacity increment
Vector<String> vector3 = new Vector<>(100, 50);

// 4. From another collection
Vector<String> vector4 = new Vector<>(Arrays.asList("A", "B", "C"));
```

## 🔍 Important Methods

### Vector-Specific Methods (Legacy)
- `addElement(E obj)` - Add element (legacy method)
- `removeElement(Object obj)` - Remove element (legacy method)
- `removeAllElements()` - Remove all (legacy method)
- `elementAt(int index)` - Get element (legacy method)
- `setElementAt(E obj, int index)` - Set element (legacy method)
- `capacity()` - Current capacity
- `ensureCapacity(int minCapacity)` - Ensure capacity

### Standard List Methods
- `add(E e)`, `remove(Object o)`, `get(int index)`, `set(int index, E e)`
- All inherited from List interface

## 📝 Example

See `VectorExample.java` for comprehensive examples including:
- Vector operations
- Thread-safety demonstration
- Comparison with ArrayList
- Legacy methods
- Performance comparison

## 💡 Modern Alternatives

### Instead of Vector, use:

1. **ArrayList for single-threaded**:
   ```java
   List<String> list = new ArrayList<>();
   ```

2. **Collections.synchronizedList() for thread-safety**:
   ```java
   List<String> syncList = Collections.synchronizedList(new ArrayList<>());
   ```

3. **CopyOnWriteArrayList for read-heavy scenarios**:
   ```java
   List<String> cowList = new CopyOnWriteArrayList<>();
   ```

## ⚠️ Important Notes

1. **Legacy class** - Avoid in new code
2. **Synchronization overhead** - Slower than ArrayList
3. **Every method is synchronized** - Even when not needed
4. **Capacity doubles** - vs ArrayList which grows by 50%
5. **Use ArrayList + external synchronization** - More flexible

## 🔗 Related Classes

- `ArrayList` - Modern, non-synchronized alternative
- `CopyOnWriteArrayList` - Thread-safe for read-heavy scenarios
- `Collections.synchronizedList()` - Synchronized wrapper for any List
- `Stack` - Extends Vector (also legacy)

## 📊 Vector vs ArrayList

| Feature | Vector | ArrayList |
|---------|--------|-----------|
| Thread-safe | ✅ Yes | ❌ No |
| Performance | ⚠️ Slower | ✅ Faster |
| Growth | 2x (100%) | 1.5x (50%) |
| Legacy | ✅ Yes | ❌ No |
| Recommended | ❌ No | ✅ Yes |

## 🎓 Learning Note

**Vector is included for completeness and legacy code understanding. For new projects, always prefer ArrayList or other modern alternatives.**

