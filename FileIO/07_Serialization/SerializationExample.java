import java.io.*;

public class SerializationExample {

    public static void main(String[] args) {
        System.out.println("═══ SERIALIZATION EXAMPLES ═══\n");

        serializeObject();
        deserializeObject();

        System.out.println("\n═══ Complete! ═══");
    }

    static void serializeObject() {
        System.out.println("--- Serializing Object ---");
        Person person = new Person("Alice", 25);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("person.ser"))) {
            oos.writeObject(person);
            System.out.println("Person serialized: " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void deserializeObject() {
        System.out.println("--- Deserializing Object ---");

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("person.ser"))) {
            Person person = (Person) ois.readObject();
            System.out.println("Person deserialized: " + person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

