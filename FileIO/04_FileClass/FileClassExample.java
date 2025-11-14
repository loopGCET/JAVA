import java.io.*;

public class FileClassExample {

    public static void main(String[] args) {
        System.out.println("═══ FILE CLASS EXAMPLES ═══\n");

        fileInfo();
        directoryOperations();

        System.out.println("\n═══ Complete! ═══");
    }

    static void fileInfo() {
        System.out.println("--- File Information ---");
        File file = new File("text.txt");

        System.out.println("Exists: " + file.exists());
        System.out.println("Is file: " + file.isFile());
        System.out.println("Is directory: " + file.isDirectory());
        System.out.println("Can read: " + file.canRead());
        System.out.println("Can write: " + file.canWrite());
        if (file.exists()) {
            System.out.println("Size: " + file.length() + " bytes");
        }
        System.out.println();
    }

    static void directoryOperations() {
        System.out.println("--- Directory Operations ---");
        File dir = new File("testdir");

        if (dir.mkdir()) {
            System.out.println("Directory created: " + dir.getName());
        }

        System.out.println("Is directory: " + dir.isDirectory());

        if (dir.delete()) {
            System.out.println("Directory deleted");
        }
        System.out.println();
    }
}

