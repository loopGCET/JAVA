import java.io.*;

public class BufferedIOExample {

    public static void main(String[] args) {
        System.out.println("═══ BUFFERED I/O EXAMPLES ═══\n");

        bufferedWriting();
        bufferedReading();

        System.out.println("\n═══ Complete! ═══");
    }

    static void bufferedWriting() {
        System.out.println("--- Buffered Writing ---");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("buffered.txt"))) {
            writer.write("Line 1");
            writer.newLine();
            writer.write("Line 2");
            writer.newLine();
            writer.write("Line 3");
            System.out.println("Written 3 lines");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void bufferedReading() {
        System.out.println("--- Buffered Reading ---");
        try (BufferedReader reader = new BufferedReader(new FileReader("buffered.txt"))) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNum++ + ": " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

