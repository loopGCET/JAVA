import java.io.*;

public class ByteStreamsExample {

    public static void main(String[] args) {
        System.out.println("═══ BYTE STREAMS EXAMPLES ═══\n");

        writeBytes();
        readBytes();
        copyFile();

        System.out.println("\n═══ Complete! ═══");
    }

    static void writeBytes() {
        System.out.println("--- Writing Bytes ---");
        try (FileOutputStream fos = new FileOutputStream("bytes.bin")) {
            byte[] data = {10, 20, 30, 40, 50};
            fos.write(data);
            System.out.println("Wrote " + data.length + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void readBytes() {
        System.out.println("--- Reading Bytes ---");
        try (FileInputStream fis = new FileInputStream("bytes.bin")) {
            int byteData;
            System.out.print("Bytes: ");
            while ((byteData = fis.read()) != -1) {
                System.out.print(byteData + " ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    static void copyFile() {
        System.out.println("--- Copying File ---");
        try (FileInputStream fis = new FileInputStream("bytes.bin");
             FileOutputStream fos = new FileOutputStream("bytes_copy.bin")) {

            int byteData;
            int count = 0;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
                count++;
            }
            System.out.println("Copied " + count + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

