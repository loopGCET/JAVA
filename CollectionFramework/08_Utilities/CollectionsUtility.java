import java.util.*;

/**
 * Comprehensive examples of Collections utility class methods.
 * The Collections class provides static utility methods for collections.
 *
 * @author Collections Framework Tutorial
 */
public class CollectionsUtility {

    public static void main(String[] args) {
        System.out.println("=== COLLECTIONS UTILITY EXAMPLES ===\n");

        example1_SortingAndSearching();
        example2_ReverseAndShuffle();
        example3_MinMaxFrequency();
        example4_SynchronizedCollections();
        example5_UnmodifiableCollections();
        example6_SingletonAndEmpty();
    }

    /**
     * Example 1: Sorting and searching
     */
    private static void example1_SortingAndSearching() {
        System.out.println("1. SORTING AND SEARCHING");
        System.out.println("-".repeat(50));

        // Sorting
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Original: " + numbers);

        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);

        // Sorting with comparator
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("Reverse sorted: " + numbers);

        // Binary search (only works on sorted lists)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 5);
        System.out.println("\nBinary search for 5: index = " + index);

        int notFound = Collections.binarySearch(numbers, 7);
        System.out.println("Binary search for 7: " + notFound + " (negative = not found)");

        System.out.println();
    }

    /**
     * Example 2: Reverse and shuffle
     */
    private static void example2_ReverseAndShuffle() {
        System.out.println("2. REVERSE AND SHUFFLE");
        System.out.println("-".repeat(50));

        List<String> cards = new ArrayList<>(
            Arrays.asList("A♠", "K♠", "Q♠", "J♠", "10♠")
        );

        System.out.println("Original: " + cards);

        // Reverse
        Collections.reverse(cards);
        System.out.println("Reversed: " + cards);

        // Shuffle
        Collections.shuffle(cards);
        System.out.println("Shuffled: " + cards);

        // Shuffle with seed (reproducible)
        Collections.shuffle(cards, new Random(42));
        System.out.println("Shuffled (seed 42): " + cards);

        System.out.println();
    }

    /**
     * Example 3: Min, max, and frequency
     */
    private static void example3_MinMaxFrequency() {
        System.out.println("3. MIN, MAX, AND FREQUENCY");
        System.out.println("-".repeat(50));

        List<Integer> numbers = Arrays.asList(3, 7, 2, 9, 2, 5, 2, 8, 3);
        System.out.println("Numbers: " + numbers);

        // Min and max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));

        // Frequency
        System.out.println("Frequency of 2: " + Collections.frequency(numbers, 2));
        System.out.println("Frequency of 7: " + Collections.frequency(numbers, 7));

        // Min/max with comparator
        List<String> words = Arrays.asList("apple", "pie", "a", "banana");
        System.out.println("\nWords: " + words);
        System.out.println("Shortest word: " +
            Collections.min(words, Comparator.comparingInt(String::length)));
        System.out.println("Longest word: " +
            Collections.max(words, Comparator.comparingInt(String::length)));

        // Disjoint - no common elements
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(3, 4, 5);
        System.out.println("\nList1 and List2 disjoint? " + Collections.disjoint(list1, list2));
        System.out.println("List1 and List3 disjoint? " + Collections.disjoint(list1, list3));

        System.out.println();
    }

    /**
     * Example 4: Synchronized collections (thread-safe)
     */
    private static void example4_SynchronizedCollections() {
        System.out.println("4. SYNCHRONIZED COLLECTIONS");
        System.out.println("-".repeat(50));

        // Regular list (not thread-safe)
        List<String> regularList = new ArrayList<>();

        // Synchronized list (thread-safe)
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());

        // Synchronized set
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());

        // Synchronized map
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

        System.out.println("Created synchronized wrappers:");
        System.out.println("  - Synchronized List");
        System.out.println("  - Synchronized Set");
        System.out.println("  - Synchronized Map");

        // Important: Iteration still needs synchronization
        syncList.addAll(Arrays.asList("A", "B", "C", "D"));

        System.out.println("\nIterating over synchronized list:");
        synchronized(syncList) {
            for (String item : syncList) {
                System.out.println("  " + item);
            }
        }

        System.out.println("\n⚠️ Note: For better performance, use concurrent collections:");
        System.out.println("  - CopyOnWriteArrayList instead of synchronized List");
        System.out.println("  - ConcurrentHashMap instead of synchronized Map");

        System.out.println();
    }

    /**
     * Example 5: Unmodifiable collections (immutable views)
     */
    private static void example5_UnmodifiableCollections() {
        System.out.println("5. UNMODIFIABLE COLLECTIONS");
        System.out.println("-".repeat(50));

        // Original modifiable list
        List<String> modifiableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        System.out.println("Original list: " + modifiableList);

        // Unmodifiable view
        List<String> unmodifiableList = Collections.unmodifiableList(modifiableList);
        System.out.println("Unmodifiable view: " + unmodifiableList);

        // Try to modify original - affects unmodifiable view
        modifiableList.add("D");
        System.out.println("After modifying original: " + unmodifiableList);

        // Try to modify unmodifiable view - throws exception
        try {
            unmodifiableList.add("E");
        } catch (UnsupportedOperationException e) {
            System.out.println("\n❌ Cannot modify unmodifiable collection!");
        }

        // Unmodifiable set
        Set<Integer> unmodifiableSet = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(1, 2, 3))
        );
        System.out.println("\nUnmodifiable set: " + unmodifiableSet);

        // Unmodifiable map
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(
            new HashMap<String, Integer>() {{
                put("one", 1);
                put("two", 2);
            }}
        );
        System.out.println("Unmodifiable map: " + unmodifiableMap);

        System.out.println();
    }

    /**
     * Example 6: Singleton and empty collections
     */
    private static void example6_SingletonAndEmpty() {
        System.out.println("6. SINGLETON AND EMPTY COLLECTIONS");
        System.out.println("-".repeat(50));

        // Singleton collections (immutable, single element)
        List<String> singletonList = Collections.singletonList("OnlyElement");
        Set<Integer> singletonSet = Collections.singleton(42);
        Map<String, String> singletonMap = Collections.singletonMap("key", "value");

        System.out.println("Singleton list: " + singletonList);
        System.out.println("Singleton set: " + singletonSet);
        System.out.println("Singleton map: " + singletonMap);

        // Empty collections (immutable)
        List<String> emptyList = Collections.emptyList();
        Set<String> emptySet = Collections.emptySet();
        Map<String, String> emptyMap = Collections.emptyMap();

        System.out.println("\nEmpty list: " + emptyList);
        System.out.println("Empty set: " + emptySet);
        System.out.println("Empty map: " + emptyMap);

        // nCopies - immutable list with n copies
        List<String> copies = Collections.nCopies(5, "Hello");
        System.out.println("\n5 copies of 'Hello': " + copies);

        // Fill - replaces all elements
        List<String> fillList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.fill(fillList, "X");
        System.out.println("\nAfter fill with 'X': " + fillList);

        // Copy - copies one list to another
        List<String> source = Arrays.asList("1", "2", "3");
        List<String> dest = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Collections.copy(dest, source);
        System.out.println("\nAfter copy: " + dest);

        // Swap elements
        List<String> swapList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.swap(swapList, 0, 3);
        System.out.println("\nAfter swapping index 0 and 3: " + swapList);

        // Rotate
        List<Integer> rotateList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\nOriginal: " + rotateList);
        Collections.rotate(rotateList, 2);
        System.out.println("After rotate by 2: " + rotateList);

        System.out.println();
    }
}

