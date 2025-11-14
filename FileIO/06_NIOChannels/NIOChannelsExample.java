import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class NIOChannelsExample {

    public static void main(String[] args) {
        System.out.println("═══ NIO CHANNELS EXAMPLES ═══\n");

        channelExample();

        System.out.println("\n═══ Complete! ===");
    }

    static void channelExample() {
        System.out.println("--- Channel Example ---");
        try {
            Path path = Paths.get("channel_test.txt");

            // Write using channel
            try (FileChannel channel = FileChannel.open(path,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

                ByteBuffer buffer = ByteBuffer.allocate(48);
                buffer.put("Hello NIO Channels!".getBytes());
                buffer.flip();
                channel.write(buffer);
                System.out.println("Written using channel");
            }

            // Read using channel
            try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocate(48);
                int bytesRead = channel.read(buffer);
                System.out.println("Read " + bytesRead + " bytes");
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                System.out.println();
            }

            Files.deleteIfExists(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

