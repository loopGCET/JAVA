public class GenericMethodsExample {

    public static void main(String[] args) {
        System.out.println("═══ GENERIC METHODS EXAMPLES ═══\n");

        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"A", "B", "C"};

        System.out.println("Integer array:");
        printArray(intArray);

        System.out.println("\nString array:");
        printArray(strArray);

        System.out.println("\n═══ Complete! ═══");
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

