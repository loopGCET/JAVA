import java.util.*;
import java.util.stream.Collectors;

/**
 * Comprehensive example demonstrating ArrayList in Java.
 * ArrayList is a resizable array implementation of the List interface.
 *
 * @author Collections Framework Tutorial
 */
public class ArrayListExample {

    public static void main(String[] args) {
        System.out.println("=== ARRAYLIST COMPREHENSIVE EXAMPLES ===\n");

        // Example 1: Creating and initializing
        example1_Creation();

        // Example 2: Adding elements
        example2_Adding();

        // Example 3: Accessing elements
        example3_Accessing();

        // Example 4: Modifying elements
        example4_Modifying();

        // Example 5: Removing elements
        example5_Removing();

        // Example 6: Searching elements
        example6_Searching();

        // Example 7: Sorting
        example7_Sorting();

        // Example 8: Iteration methods
        example8_Iteration();

        // Example 9: Bulk operations
        example9_BulkOperations();

        // Example 10: ArrayList with custom objects
        example10_CustomObjects();
    }

    /**
     * Example 1: Different ways to create ArrayLists
     */
    private static void example1_Creation() {
        System.out.println("1. CREATING ARRAYLISTS");
        System.out.println("-".repeat(50));

        // Method 1: Default constructor
        ArrayList<String> list1 = new ArrayList<>();
        System.out.println("Empty ArrayList: " + list1);

        // Method 2: With initial capacity
        ArrayList<Integer> list2 = new ArrayList<>(100);
        System.out.println("With capacity 100: " + list2 + " (capacity: 100)");

        // Method 3: From another collection
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        System.out.println("From Arrays.asList: " + list3);

        // Method 4: Using List.of() (Java 9+) - Creates immutable, then copy
        List<String> immutable = List.of("X", "Y", "Z");
        ArrayList<String> list4 = new ArrayList<>(immutable);
        System.out.println("From List.of: " + list4);

        // Method 5: Using Collections.nCopies
        ArrayList<Integer> list5 = new ArrayList<>(Collections.nCopies(5, 0));
        System.out.println("5 zeros: " + list5);

        System.out.println();
    }

    /**
     * Example 2: Adding elements
     */
    private static void example2_Adding() {
        System.out.println("2. ADDING ELEMENTS");
        System.out.println("-".repeat(50));

        ArrayList<String> fruits = new ArrayList<>();

        // add(E e) - Append to end
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        System.out.println("After adding: " + fruits);

        // add(int index, E e) - Insert at specific position
        fruits.add(1, "Avocado");  // Insert at index 1
        System.out.println("After inserting at index 1: " + fruits);

        // addAll(Collection c) - Add all elements
        fruits.addAll(Arrays.asList("Date", "Elderberry"));
        System.out.println("After addAll: " + fruits);

        // addAll(int index, Collection c) - Insert all at position
        fruits.addAll(2, Arrays.asList("Fig", "Grape"));
        System.out.println("After addAll at index 2: " + fruits);

        System.out.println();
    }

    /**
     * Example 3: Accessing elements
     */
    private static void example3_Accessing() {
        System.out.println("3. ACCESSING ELEMENTS");
        System.out.println("-".repeat(50));

        ArrayList<String> colors = new ArrayList<>(
            Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple")
        );

        System.out.println("Colors: " + colors);

        // get(int index) - Access by index
        System.out.println("Element at index 0: " + colors.get(0));
        System.out.println("Element at index 2: " + colors.get(2));

        // First and last elements
        System.out.println("First element: " + colors.get(0));
        System.out.println("Last element: " + colors.get(colors.size() - 1));

        // size() - Number of elements
        System.out.println("Size: " + colors.size());

        // isEmpty() - Check if empty
        System.out.println("Is empty? " + colors.isEmpty());

        System.out.println();
    }

    /**
     * Example 4: Modifying elements
     */
    private static void example4_Modifying() {
        System.out.println("4. MODIFYING ELEMENTS");
        System.out.println("-".repeat(50));

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Original: " + numbers);

        // set(int index, E e) - Replace element at index
        numbers.set(2, 35);  // Replace 30 with 35
        System.out.println("After set(2, 35): " + numbers);

        // Modify using get and set
        int value = numbers.get(0);
        numbers.set(0, value * 2);
        System.out.println("After doubling first element: " + numbers);

        System.out.println();
    }

    /**
     * Example 5: Removing elements
     */
    private static void example5_Removing() {
        System.out.println("5. REMOVING ELEMENTS");
        System.out.println("-".repeat(50));

        ArrayList<String> languages = new ArrayList<>(
            Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby", "Go")
        );
        System.out.println("Original: " + languages);

        // remove(int index) - Remove by index
        String removed = languages.remove(2);
        System.out.println("Removed '" + removed + "' at index 2: " + languages);

        // remove(Object o) - Remove by object
        languages.remove("Ruby");
        System.out.println("After removing 'Ruby': " + languages);

        // removeAll(Collection c) - Remove multiple elements
        languages.removeAll(Arrays.asList("Python", "Go"));
        System.out.println("After removeAll: " + languages);

        // removeIf(Predicate) - Remove matching condition
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        nums.removeIf(n -> n % 2 == 0);  // Remove even numbers
        System.out.println("After removing even numbers: " + nums);

        // clear() - Remove all elements
        ArrayList<String> temp = new ArrayList<>(Arrays.asList("A", "B", "C"));
        temp.clear();
        System.out.println("After clear(): " + temp);

        System.out.println();
    }

    /**
     * Example 6: Searching for elements
     */
    private static void example6_Searching() {
        System.out.println("6. SEARCHING ELEMENTS");
        System.out.println("-".repeat(50));

        ArrayList<String> items = new ArrayList<>(
            Arrays.asList("Apple", "Banana", "Apple", "Cherry", "Banana", "Date")
        );
        System.out.println("Items: " + items);

        // contains(Object o) - Check if element exists
        System.out.println("Contains 'Apple'? " + items.contains("Apple"));
        System.out.println("Contains 'Grape'? " + items.contains("Grape"));

        // indexOf(Object o) - First occurrence
        System.out.println("First index of 'Apple': " + items.indexOf("Apple"));
        System.out.println("First index of 'Banana': " + items.indexOf("Banana"));

        // lastIndexOf(Object o) - Last occurrence
        System.out.println("Last index of 'Apple': " + items.lastIndexOf("Apple"));
        System.out.println("Last index of 'Banana': " + items.lastIndexOf("Banana"));

        // indexOf for non-existent element
        System.out.println("Index of 'Grape': " + items.indexOf("Grape") + " (-1 = not found)");

        System.out.println();
    }

    /**
     * Example 7: Sorting
     */
    private static void example7_Sorting() {
        System.out.println("7. SORTING");
        System.out.println("-".repeat(50));

        // Natural order sorting
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Original: " + numbers);
        Collections.sort(numbers);
        System.out.println("Sorted (ascending): " + numbers);

        // Reverse order
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("Sorted (descending): " + numbers);

        // Sorting strings
        ArrayList<String> names = new ArrayList<>(
            Arrays.asList("Charlie", "Alice", "Bob", "David")
        );
        System.out.println("\nNames: " + names);
        Collections.sort(names);
        System.out.println("Sorted alphabetically: " + names);

        // Using List.sort() with Comparator (Java 8+)
        names.sort(Comparator.reverseOrder());
        System.out.println("Sorted reverse: " + names);

        // Custom comparator - by length
        names.sort(Comparator.comparingInt(String::length));
        System.out.println("Sorted by length: " + names);

        System.out.println();
    }

    /**
     * Example 8: Different iteration methods
     */
    private static void example8_Iteration() {
        System.out.println("8. ITERATION METHODS");
        System.out.println("-".repeat(50));

        ArrayList<String> fruits = new ArrayList<>(
            Arrays.asList("Apple", "Banana", "Cherry", "Date")
        );

        // Method 1: For-each loop
        System.out.println("Method 1: For-each loop");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println("\n");

        // Method 2: Traditional for loop with index
        System.out.println("Method 2: Traditional for loop");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("  [" + i + "] = " + fruits.get(i));
        }
        System.out.println();

        // Method 3: Iterator
        System.out.println("Method 3: Iterator");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n");

        // Method 4: ListIterator (bidirectional)
        System.out.println("Method 4: ListIterator (backward)");
        ListIterator<String> listIterator = fruits.listIterator(fruits.size());
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println("\n");

        // Method 5: forEach with lambda (Java 8+)
        System.out.println("Method 5: forEach with lambda");
        fruits.forEach(fruit -> System.out.print(fruit + " "));
        System.out.println("\n");

        // Method 6: Stream API
        System.out.println("Method 6: Stream API");
        fruits.stream().forEach(System.out::println);

        System.out.println();
    }

    /**
     * Example 9: Bulk operations
     */
    private static void example9_BulkOperations() {
        System.out.println("9. BULK OPERATIONS");
        System.out.println("-".repeat(50));

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8));

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        // retainAll - Keep only common elements
        ArrayList<Integer> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        System.out.println("Intersection: " + intersection);

        // subList - Get a view of portion
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> subList = numbers.subList(2, 6);  // From index 2 to 5
        System.out.println("\nOriginal: " + numbers);
        System.out.println("SubList(2, 6): " + subList);

        // toArray - Convert to array
        String[] array = list1.stream()
                             .map(String::valueOf)
                             .toArray(String[]::new);
        System.out.println("\nConverted to array: " + Arrays.toString(array));

        System.out.println();
    }

    /**
     * Example 10: ArrayList with custom objects
     */
    private static void example10_CustomObjects() {
        System.out.println("10. ARRAYLIST WITH CUSTOM OBJECTS");
        System.out.println("-".repeat(50));

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        people.add(new Person("David", 28));

        System.out.println("People:");
        people.forEach(System.out::println);

        // Sorting by age
        people.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("\nSorted by age:");
        people.forEach(System.out::println);

        // Sorting by name
        people.sort(Comparator.comparing(Person::getName));
        System.out.println("\nSorted by name:");
        people.forEach(System.out::println);

        // Filtering with streams
        System.out.println("\nPeople older than 28:");
        people.stream()
              .filter(p -> p.getAge() > 28)
              .forEach(System.out::println);

        System.out.println();
    }
}

/**
 * Custom class for demonstration
 */
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

