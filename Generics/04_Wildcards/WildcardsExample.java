import java.util.*;

public class WildcardsExample {

    public static void main(String[] args) {
        System.out.println("═══ WILDCARDS EXAMPLES ═══\n");

        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);

        printList(integers);
        printList(doubles);

        System.out.println("\nSum of integers: " + sumNumbers(integers));
        System.out.println("Sum of doubles: " + sumNumbers(doubles));

        System.out.println("\n═══ Complete! ═══");
    }

    // Unbounded wildcard
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // Upper bounded wildcard
    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
}

