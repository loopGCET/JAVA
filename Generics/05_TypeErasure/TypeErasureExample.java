public class TypeErasureExample {

    public static void main(String[] args) {
        System.out.println("═══ TYPE ERASURE EXAMPLES ═══\n");

        demonstrateTypeErasure();

        System.out.println("\n═══ Complete! ═══");
    }

    static void demonstrateTypeErasure() {
        System.out.println("--- Type Erasure Demonstration ---");

        // At compile time: Box<String>
        Box<String> stringBox = new Box<>("Hello");
        System.out.println("String box: " + stringBox.get());

        // At compile time: Box<Integer>
        Box<Integer> intBox = new Box<>(42);
        System.out.println("Integer box: " + intBox.get());

        // At runtime, both become Box (type information erased)
        System.out.println("\nBoth boxes have same class at runtime:");
        System.out.println("stringBox class: " + stringBox.getClass().getName());
        System.out.println("intBox class: " + intBox.getClass().getName());
        System.out.println("Same class? " + (stringBox.getClass() == intBox.getClass()));

        System.out.println();
    }
}

class Box<T> {
    private T value;

    public Box(T value) { this.value = value; }
    public T get() { return value; }
}

