import java.util.*;

/**
 * Comprehensive example demonstrating TreeMap in Java.
 * TreeMap stores key-value pairs in sorted order by keys.
 *
 * @author Collections Framework Tutorial
 */
public class TreeMapExample {

    public static void main(String[] args) {
        System.out.println("=== TREEMAP COMPREHENSIVE EXAMPLES ===\n");

        example1_SortedKeyOrder();
        example2_NavigableMapOperations();
        example3_RangeOperations();
        example4_LeaderboardExample();
        example5_CustomComparator();
    }

    /**
     * Example 1: Keys are automatically sorted
     */
    private static void example1_SortedKeyOrder() {
        System.out.println("1. SORTED KEY ORDER");
        System.out.println("-".repeat(50));

        TreeMap<String, Integer> scores = new TreeMap<>();

        // Add in random order
        scores.put("Charlie", 85);
        scores.put("Alice", 92);
        scores.put("David", 78);
        scores.put("Bob", 88);
        scores.put("Eve", 95);

        System.out.println("TreeMap (auto-sorted by keys):");
        scores.forEach((name, score) ->
            System.out.println("  " + name + ": " + score)
        );

        // Compare with HashMap
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.putAll(scores);

        System.out.println("\nHashMap (no guaranteed order):");
        hashMap.forEach((name, score) ->
            System.out.println("  " + name + ": " + score)
        );

        System.out.println("\n✅ TreeMap maintains sorted key order!");

        System.out.println();
    }

    /**
     * Example 2: NavigableMap operations
     */
    private static void example2_NavigableMapOperations() {
        System.out.println("2. NAVIGABLEMAP OPERATIONS");
        System.out.println("-".repeat(50));

        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "Ten");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");

        System.out.println("Map: " + map);

        // First and last
        System.out.println("\nFirst/Last operations:");
        System.out.println("  First key: " + map.firstKey());
        System.out.println("  Last key: " + map.lastKey());
        System.out.println("  First entry: " + map.firstEntry());
        System.out.println("  Last entry: " + map.lastEntry());

        // Ceiling and floor
        System.out.println("\nCeiling/Floor operations (for key 25):");
        System.out.println("  Ceiling key (>=): " + map.ceilingKey(25));    // 30
        System.out.println("  Floor key (<=): " + map.floorKey(25));        // 20
        System.out.println("  Ceiling entry: " + map.ceilingEntry(25));
        System.out.println("  Floor entry: " + map.floorEntry(25));

        // Higher and lower
        System.out.println("\nHigher/Lower operations (for key 30):");
        System.out.println("  Higher key (>): " + map.higherKey(30));       // 40
        System.out.println("  Lower key (<): " + map.lowerKey(30));         // 20

        // Poll operations
        System.out.println("\nPoll operations:");
        System.out.println("  Poll first: " + map.pollFirstEntry());
        System.out.println("  Poll last: " + map.pollLastEntry());
        System.out.println("  After polling: " + map);

        // Descending map
        System.out.println("\nDescending map (reverse order):");
        NavigableMap<Integer, String> descendingMap = map.descendingMap();
        System.out.println("  " + descendingMap);

        System.out.println();
    }

    /**
     * Example 3: Range operations (subMap, headMap, tailMap)
     */
    private static void example3_RangeOperations() {
        System.out.println("3. RANGE OPERATIONS");
        System.out.println("-".repeat(50));

        TreeMap<Integer, String> ageGroups = new TreeMap<>();
        ageGroups.put(18, "Young Adult");
        ageGroups.put(25, "Adult");
        ageGroups.put(35, "Middle Age");
        ageGroups.put(50, "Senior");
        ageGroups.put(65, "Elderly");

        System.out.println("Complete map: " + ageGroups);

        // subMap - get range
        System.out.println("\nsubMap(25, 50) - ages 25 to 49:");
        SortedMap<Integer, String> subMap = ageGroups.subMap(25, 50);
        System.out.println("  " + subMap);

        // headMap - get all less than key
        System.out.println("\nheadMap(35) - ages less than 35:");
        SortedMap<Integer, String> headMap = ageGroups.headMap(35);
        System.out.println("  " + headMap);

        // tailMap - get all greater than or equal to key
        System.out.println("\ntailMap(35) - ages 35 and above:");
        SortedMap<Integer, String> tailMap = ageGroups.tailMap(35);
        System.out.println("  " + tailMap);

        // NavigableMap subMap with inclusive/exclusive options
        System.out.println("\nsubMap(25, true, 50, true) - inclusive on both:");
        NavigableMap<Integer, String> inclusiveSubMap =
            ageGroups.subMap(25, true, 50, true);
        System.out.println("  " + inclusiveSubMap);

        System.out.println();
    }

    /**
     * Example 4: Practical use case - Leaderboard
     */
    private static void example4_LeaderboardExample() {
        System.out.println("4. LEADERBOARD EXAMPLE");
        System.out.println("-".repeat(50));

        // TreeMap sorted by score (descending)
        TreeMap<Integer, String> leaderboard = new TreeMap<>(Collections.reverseOrder());

        leaderboard.put(9500, "Alice");
        leaderboard.put(8700, "Bob");
        leaderboard.put(9200, "Charlie");
        leaderboard.put(7800, "David");
        leaderboard.put(9800, "Eve");
        leaderboard.put(8900, "Frank");

        System.out.println("🏆 TOP PLAYERS LEADERBOARD:");
        int rank = 1;
        for (Map.Entry<Integer, String> entry : leaderboard.entrySet()) {
            System.out.printf("  %d. %-10s %d points%n",
                rank++, entry.getValue(), entry.getKey());
        }

        // Get top 3
        System.out.println("\n🥇 TOP 3 PLAYERS:");
        leaderboard.entrySet().stream()
                   .limit(3)
                   .forEach(entry ->
                       System.out.println("  " + entry.getValue() + ": " + entry.getKey())
                   );

        // Players with score >= 9000
        System.out.println("\n⭐ PLAYERS WITH 9000+ POINTS:");
        SortedMap<Integer, String> topPlayers = leaderboard.tailMap(9000);
        topPlayers.forEach((score, name) ->
            System.out.println("  " + name + ": " + score)
        );

        System.out.println();
    }

    /**
     * Example 5: Custom comparator for complex sorting
     */
    private static void example5_CustomComparator() {
        System.out.println("5. CUSTOM COMPARATOR");
        System.out.println("-".repeat(50));

        // Sort by string length, then alphabetically
        TreeMap<String, Integer> wordLengths = new TreeMap<>(
            Comparator.comparingInt(String::length)
                     .thenComparing(String::compareTo)
        );

        wordLengths.put("apple", 5);
        wordLengths.put("pie", 3);
        wordLengths.put("banana", 6);
        wordLengths.put("kiwi", 4);
        wordLengths.put("pear", 4);
        wordLengths.put("grape", 5);

        System.out.println("Sorted by length, then alphabetically:");
        wordLengths.forEach((word, length) ->
            System.out.println("  " + word + " (" + length + " letters)")
        );

        // Custom objects with TreeMap
        System.out.println("\nCustom objects - Event scheduler:");
        TreeMap<Event, String> schedule = new TreeMap<>();

        schedule.put(new Event("Meeting", 14), "Conference Room A");
        schedule.put(new Event("Lunch", 12), "Cafeteria");
        schedule.put(new Event("Presentation", 10), "Auditorium");
        schedule.put(new Event("Workshop", 15), "Lab 101");
        schedule.put(new Event("Break", 11), "Break Room");

        System.out.println("Events sorted by time:");
        schedule.forEach((event, location) ->
            System.out.println("  " + event + " → " + location)
        );

        // Find events in afternoon (12:00 - 17:00)
        System.out.println("\nAfternoon events (12:00 - 17:00):");
        Event startTime = new Event("", 12);
        Event endTime = new Event("", 17);
        SortedMap<Event, String> afternoonEvents = schedule.subMap(startTime, endTime);
        afternoonEvents.forEach((event, location) ->
            System.out.println("  " + event + " → " + location)
        );

        System.out.println();
    }
}

/**
 * Event class for scheduling example
 */
class Event implements Comparable<Event> {
    private String name;
    private int hour;  // 24-hour format

    public Event(String name, int hour) {
        this.name = name;
        this.hour = hour;
    }

    @Override
    public int compareTo(Event other) {
        return Integer.compare(this.hour, other.hour);
    }

    @Override
    public String toString() {
        return String.format("%02d:00 - %s", hour, name);
    }
}

