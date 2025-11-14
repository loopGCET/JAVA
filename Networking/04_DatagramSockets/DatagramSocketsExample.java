import java.net.*;

/**
 * Datagram Sockets (UDP) Examples
 */
public class DatagramSocketsExample {

    public static void main(String[] args) {
        System.out.println("═══ DATAGRAM SOCKETS EXAMPLES ═══\n");

        udpConcepts();
        tcpVsUdp();
        datagramPattern();

        System.out.println("\n═══ Complete! ===");
    }

    static void udpConcepts() {
        System.out.println("--- UDP Concepts ---");
        System.out.println("UDP (User Datagram Protocol):");
        System.out.println("  - Connectionless protocol");
        System.out.println("  - No guarantee of delivery");
        System.out.println("  - No guarantee of order");
        System.out.println("  - Faster than TCP");
        System.out.println("  - Lower overhead\n");

        System.out.println("Use cases:");
        System.out.println("  - Video/audio streaming");
        System.out.println("  - Gaming");
        System.out.println("  - DNS queries");
        System.out.println("  - Real-time applications");
        System.out.println();
    }

    static void tcpVsUdp() {
        System.out.println("--- TCP vs UDP ---\n");

        System.out.println("TCP:");
        System.out.println("  ✅ Connection-oriented");
        System.out.println("  ✅ Reliable delivery");
        System.out.println("  ✅ Ordered packets");
        System.out.println("  ❌ Higher overhead");
        System.out.println("  ❌ Slower\n");

        System.out.println("UDP:");
        System.out.println("  ✅ Connectionless");
        System.out.println("  ✅ Fast");
        System.out.println("  ✅ Lower overhead");
        System.out.println("  ❌ No delivery guarantee");
        System.out.println("  ❌ Packets may arrive out of order");
        System.out.println();
    }

    static void datagramPattern() {
        System.out.println("--- Datagram Pattern ---\n");

        System.out.println("Sender:");
        System.out.println("  DatagramSocket socket = new DatagramSocket();");
        System.out.println("  ");
        System.out.println("  String message = \"Hello UDP\";");
        System.out.println("  byte[] buffer = message.getBytes();");
        System.out.println("  ");
        System.out.println("  InetAddress address = InetAddress.getByName(\"localhost\");");
        System.out.println("  DatagramPacket packet = new DatagramPacket(");
        System.out.println("      buffer, buffer.length, address, 9876);");
        System.out.println("  ");
        System.out.println("  socket.send(packet);\n");

        System.out.println("Receiver:");
        System.out.println("  DatagramSocket socket = new DatagramSocket(9876);");
        System.out.println("  ");
        System.out.println("  byte[] buffer = new byte[1024];");
        System.out.println("  DatagramPacket packet = new DatagramPacket(");
        System.out.println("      buffer, buffer.length);");
        System.out.println("  ");
        System.out.println("  socket.receive(packet); // Blocks until data received");
        System.out.println("  ");
        System.out.println("  String message = new String(packet.getData(), ");
        System.out.println("      0, packet.getLength());");
        System.out.println("  System.out.println(\"Received: \" + message);");
        System.out.println();
    }
}

