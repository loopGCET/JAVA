import java.util.*;

public class MethodReferencesExample {

    public static void main(String[] args) {
        System.out.println("═══ METHOD REFERENCES EXAMPLES ═══\n");

        staticMethodReference();
        instanceMethodReference();
        constructorReference();

        System.out.println("\n═══ Complete! ═══");
    }

    static void staticMethodReference() {
        System.out.println("--- Static Method Reference ---");
        List<Integer> numbers = Arrays.asList(-3, -1, 2, 4);

        // Lambda: numbers.forEach(n -> Math.abs(n))
        // Method reference:
        numbers.stream()
            .map(Math::abs)
            .forEach(System.out::println);
        System.out.println();
    }

    static void instanceMethodReference() {
        System.out.println("--- Instance Method Reference ---");
        List<String> words = Arrays.asList("hello", "world", "java");

        // Lambda: words.forEach(s -> s.toUpperCase())
        // Method reference:
        words.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
        System.out.println();
    }

    static void constructorReference() {
        System.out.println("--- Constructor Reference ---");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Lambda: names.stream().map(name -> new Person(name))
        // Constructor reference:
        List<Person> people = names.stream()
            .map(Person::new)
            .toList();

        people.forEach(System.out::println);
        System.out.println();
    }
}

class Person {
    String name;
    Person(String name) { this.name = name; }
    public String toString() { return "Person: " + name; }
}

