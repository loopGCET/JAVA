import java.io.*;

public class CharacterStreamsExample {

    public static void main(String[] args) {
        System.out.println("═══ CHARACTER STREAMS EXAMPLES ═══\n");

        writeText();
        readText();

        System.out.println("\n═══ Complete! ═══");
    }

    static void writeText() {
        System.out.println("--- Writing Text ---");
        try (FileWriter writer = new FileWriter("text.txt")) {
            writer.write("Hello, World!\n");
            writer.write("Java File I/O");
            System.out.println("Text written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void readText() {
        System.out.println("--- Reading Text ---");
        try (FileReader reader = new FileReader("text.txt")) {
            int charData;
            while ((charData = reader.read()) != -1) {
                System.out.print((char) charData);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

