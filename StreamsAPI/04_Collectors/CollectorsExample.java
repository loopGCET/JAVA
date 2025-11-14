import java.util.*;
import java.util.stream.*;

public class CollectorsExample {

    static class Person {
        String name;
        String city;
        int age;

        Person(String name, String city, int age) {
            this.name = name;
            this.city = city;
            this.age = age;
        }

        String getName() { return name; }
        String getCity() { return city; }
        int getAge() { return age; }
    }

    public static void main(String[] args) {
        System.out.println("═══ COLLECTORS EXAMPLES ═══\n");

        joiningExample();
        groupingByExample();
        partitioningByExample();
        statisticsExample();

        System.out.println("\n═══ Complete! ===");
    }

    static void joiningExample() {
        System.out.println("--- Collectors.joining() ---");
        String joined = Stream.of("a", "b", "c")
            .collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);
        System.out.println();
    }

    static void groupingByExample() {
        System.out.println("--- Collectors.groupingBy() ---");
        List<Person> people = Arrays.asList(
            new Person("Alice", "NYC", 25),
            new Person("Bob", "LA", 30),
            new Person("Charlie", "NYC", 35)
        );

        Map<String, List<Person>> byCity = people.stream()
            .collect(Collectors.groupingBy(Person::getCity));

        byCity.forEach((city, list) ->
            System.out.println(city + ": " + list.size() + " people")
        );
        System.out.println();
    }

    static void partitioningByExample() {
        System.out.println("--- Collectors.partitioningBy() ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Map<Boolean, List<Integer>> partition = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Even: " + partition.get(true));
        System.out.println("Odd: " + partition.get(false));
        System.out.println();
    }

    static void statisticsExample() {
        System.out.println("--- Statistics Collectors ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        IntSummaryStatistics stats = numbers.stream()
            .collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
        System.out.println();
    }
}

