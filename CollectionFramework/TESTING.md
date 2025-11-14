# Java Collections Framework Module - Test & Run Guide

## 🚀 Quick Start

### Compile All Examples

#### Windows PowerShell
```powershell
# From CollectionFramework directory
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { 
    Write-Host "Compiling $($_.FullName)..."
    javac $_.FullName 
}
```

#### Windows CMD
```cmd
for /r %f in (*.java) do javac "%f"
```

### Run Individual Examples

Navigate to any directory and run:

```bash
# Example: ArrayList
cd 03_List/ArrayList
javac ArrayListExample.java
java ArrayListExample

# Example: HashMap
cd 07_Map/HashMap
javac HashMapExample.java
java HashMapExample
```

## 📋 Complete List of Runnable Examples (16 Total)

### 01_Iterable
- `IterableExample.java` - Iterable interface, custom Iterable, iteration patterns

### 02_Collection
- `CollectionExample.java` - Collection interface operations, bulk operations

### 03_List (4 implementations)
- `ArrayList/ArrayListExample.java` - ArrayList comprehensive examples, CRUD, sorting
- `LinkedList/LinkedListExample.java` - LinkedList, Deque operations, performance
- `Vector/VectorExample.java` - Vector legacy class, thread-safety, capacity ⚠️
- `Stack/StackExample.java` - Stack LIFO, balanced parentheses, migration ⚠️

### 04_Set (3 implementations)
- `HashSet/HashSetExample.java` - HashSet, set operations, equals/hashCode
- `LinkedHashSet/LinkedHashSetExample.java` - Insertion order, remove duplicates
- `TreeSet/TreeSetExample.java` - TreeSet sorting, navigation, range queries

### 05_Queue
- `PriorityQueue/PriorityQueueExample.java` - Priority queue, heaps, task scheduling

### 06_Deque
- `ArrayDeque/ArrayDequeExample.java` - Stack/queue/deque operations, undo/redo

### 07_Map (4 implementations)
- `HashMap/HashMapExample.java` - HashMap with Java 8+ methods, frequency counting
- `LinkedHashMap/LinkedHashMapExample.java` - Insertion/access order, LRU cache
- `TreeMap/TreeMapExample.java` - Sorted keys, NavigableMap, leaderboard
- `Hashtable/HashtableExample.java` - Hashtable legacy, null handling, migration ⚠️

### 08_Utilities
- `CollectionsUtility.java` - Collections utility methods, sorting, synchronization

## 🧪 Test All Examples

Create this PowerShell script to test all examples:

```powershell
# test-all.ps1
$examples = @(
    "01_Iterable\IterableExample.java",
    "02_Collection\CollectionExample.java",
    "03_List\ArrayList\ArrayListExample.java",
    "03_List\LinkedList\LinkedListExample.java",
    "03_List\Vector\VectorExample.java",
    "03_List\Stack\StackExample.java",
    "04_Set\HashSet\HashSetExample.java",
    "04_Set\LinkedHashSet\LinkedHashSetExample.java",
    "04_Set\TreeSet\TreeSetExample.java",
    "05_Queue\PriorityQueue\PriorityQueueExample.java",
    "06_Deque\ArrayDeque\ArrayDequeExample.java",
    "07_Map\HashMap\HashMapExample.java",
    "07_Map\LinkedHashMap\LinkedHashMapExample.java",
    "07_Map\TreeMap\TreeMapExample.java",
    "07_Map\Hashtable\HashtableExample.java",
    "08_Utilities\CollectionsUtility.java"
)

foreach ($example in $examples) {
    Write-Host "`n========================================" -ForegroundColor Cyan
    Write-Host "Running: $example" -ForegroundColor Green
    Write-Host "========================================`n" -ForegroundColor Cyan
    
    $dir = Split-Path $example -Parent
    $file = Split-Path $example -Leaf
    $className = $file -replace '\.java$', ''
    
    if ($dir) {
        Push-Location $dir
    }
    
    javac $file
    if ($LASTEXITCODE -eq 0) {
        java $className
    } else {
        Write-Host "Compilation failed!" -ForegroundColor Red
    }
    
    if ($dir) {
        Pop-Location
    }
}
```

Save as `test-all.ps1` and run:
```powershell
.\test-all.ps1
```

## 📚 Learning Order

Follow this recommended order:

1. **Iterable** → Understand iteration fundamentals
2. **Collection** → Learn common collection operations
3. **List** → ArrayList → LinkedList
4. **Set** → HashSet → TreeSet
5. **Queue** → PriorityQueue
6. **Deque** → ArrayDeque
7. **Map** → HashMap
8. **Utilities** → Collections utility methods

## 🔍 What Each Example Demonstrates

| Example | Key Concepts |
|---------|--------------|
| IterableExample | for-each loop, Iterator, custom Iterable |
| CollectionExample | Common operations, bulk operations, streams |
| ArrayListExample | CRUD, sorting, searching, iteration |
| LinkedListExample | List vs Deque operations, performance |
| VectorExample | Synchronized List, capacity management, migration |
| StackExample | LIFO operations, balanced parentheses, migration |
| HashSetExample | Uniqueness, set operations, custom objects |
| LinkedHashSetExample | Insertion order, remove duplicates, use cases |
| TreeSetExample | Sorting, navigation, range operations |
| PriorityQueueExample | Min/max heap, priority scheduling |
| ArrayDequeExample | Stack, queue, deque, undo/redo |
| HashMapExample | Key-value operations, Java 8+ methods |
| LinkedHashMapExample | Insertion/access order, LRU cache |
| TreeMapExample | Sorted keys, NavigableMap, leaderboard |
| HashtableExample | Synchronized Map, null handling, migration |
| CollectionsUtility | Sorting, searching, synchronization |

## 💡 Tips

1. **Read the README** in each directory first
2. **Run examples** to see output
3. **Modify and experiment** with the code
4. **Read comments** in the code for explanations
5. **Check performance** comparisons

## 🐛 Troubleshooting

### Compilation Errors
```bash
# Ensure you're in the correct directory
# Make sure Java is installed
java -version
javac -version
```

### Class Not Found
```bash
# Make sure you compiled first
javac ClassName.java
# Then run
java ClassName
```

### Path Issues
```bash
# Use absolute paths if needed
javac "C:\Full\Path\To\File.java"
```

## 📖 Additional Resources

- Main README: `../README.md`
- Collections Guide: `../Java_Collections_Framework_Guide.md`
- Official Java Docs: https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html

