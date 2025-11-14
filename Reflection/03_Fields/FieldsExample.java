import java.lang.reflect.*;

public class FieldsExample {

    public static void main(String[] args) {
        System.out.println("═══ FIELDS REFLECTION EXAMPLES ═══\n");

        listingFields();
        accessingFields();
        modifyingPrivateFields();

        System.out.println("\n═══ Complete! ═══");
    }

    static void listingFields() {
        System.out.println("--- Listing Fields ---");

        Class<?> clazz = Person.class;
        Field[] fields = clazz.getDeclaredFields();

        System.out.println("Fields in Person class:");
        for (Field field : fields) {
            System.out.println("  " + field.getName() +
                             " - Type: " + field.getType().getSimpleName() +
                             " - Modifiers: " + Modifier.toString(field.getModifiers()));
        }
        System.out.println();
    }

    static void accessingFields() {
        System.out.println("--- Accessing Fields ---");

        try {
            Person person = new Person("Alice", 25);
            Class<?> clazz = person.getClass();

            // Access public field
            Field nameField = clazz.getDeclaredField("name");
            Object nameValue = nameField.get(person);
            System.out.println("Name: " + nameValue);

            // Access private field (need setAccessible)
            Field ageField = clazz.getDeclaredField("age");
            ageField.setAccessible(true);
            Object ageValue = ageField.get(person);
            System.out.println("Age: " + ageValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void modifyingPrivateFields() {
        System.out.println("--- Modifying Private Fields ---");

        try {
            Person person = new Person("Bob", 30);
            Class<?> clazz = person.getClass();

            System.out.println("Original: " + person);

            // Modify private age field
            Field ageField = clazz.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(person, 35);

            System.out.println("After modification: " + person);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

class Person {
    public String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

