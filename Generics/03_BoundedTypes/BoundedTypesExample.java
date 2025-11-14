public class BoundedTypesExample {

    public static void main(String[] args) {
        System.out.println("═══ BOUNDED TYPES EXAMPLES ===\n");

        NumberBox<Integer> intBox = new NumberBox<>(42);
        System.out.println("Integer box: " + intBox.get());

        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        System.out.println("Double box: " + doubleBox.get());

        System.out.println("\n═══ Complete! ═══");
    }
}

// T must be Number or subclass
class NumberBox<T extends Number> {
    private T value;

    public NumberBox(T value) { this.value = value; }
    public T get() { return value; }
    public double doubleValue() { return value.doubleValue(); }
}

