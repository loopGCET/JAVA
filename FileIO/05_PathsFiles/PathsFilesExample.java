import java.nio.file.*;
import java.util.*;

public class PathsFilesExample {

    public static void main(String[] args) {
        System.out.println("═══ PATHS & FILES EXAMPLES ═══\n");

        pathOperations();
        fileOperations();

        System.out.println("\n═══ Complete! ═══");
    }

    static void pathOperations() {
        System.out.println("--- Path Operations ---");
        Path path = Paths.get("example.txt");

        System.out.println("File name: " + path.getFileName());
        System.out.println("Parent: " + path.getParent());
        System.out.println("Absolute path: " + path.toAbsolutePath());
        System.out.println();
    }

    static void fileOperations() {
        System.out.println("--- File Operations ---");
        try {
            Path path = Paths.get("nio_test.txt");

            // Write
            Files.write(path, Arrays.asList("Line 1", "Line 2", "Line 3"));
            System.out.println("File written");

            // Read
            List<String> lines = Files.readAllLines(path);
            System.out.println("Lines read: " + lines.size());
            lines.forEach(line -> System.out.println("  " + line));

            // Delete
            Files.deleteIfExists(path);
            System.out.println("File deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

