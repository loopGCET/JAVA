import java.io.*;
import java.net.*;

/**
 * Socket Programming Examples
 */
public class SocketProgrammingExample {

    public static void main(String[] args) {
        System.out.println("═══ SOCKET PROGRAMMING EXAMPLES ═══\n");

        socketConcepts();
        clientServerPattern();

        System.out.println("\n═══ Complete! ===");
    }

    static void socketConcepts() {
        System.out.println("--- Socket Concepts ---");
        System.out.println("Socket: Endpoint for network communication\n");

        System.out.println("Key classes:");
        System.out.println("  - Socket       : Client-side socket");
        System.out.println("  - ServerSocket : Server-side socket");
        System.out.println("  - InetAddress  : IP address representation\n");

        System.out.println("Communication flow:");
        System.out.println("  1. Server creates ServerSocket and listens");
        System.out.println("  2. Client creates Socket and connects");
        System.out.println("  3. Both exchange data via streams");
        System.out.println("  4. Close connections");
        System.out.println();
    }

    static void clientServerPattern() {
        System.out.println("--- Client-Server Pattern ---\n");

        System.out.println("Server code:");
        System.out.println("  ServerSocket serverSocket = new ServerSocket(8080);");
        System.out.println("  System.out.println(\"Server listening on port 8080\");");
        System.out.println("  ");
        System.out.println("  Socket clientSocket = serverSocket.accept();");
        System.out.println("  System.out.println(\"Client connected\");");
        System.out.println("  ");
        System.out.println("  BufferedReader in = new BufferedReader(");
        System.out.println("      new InputStreamReader(clientSocket.getInputStream()));");
        System.out.println("  PrintWriter out = new PrintWriter(");
        System.out.println("      clientSocket.getOutputStream(), true);");
        System.out.println("  ");
        System.out.println("  String message = in.readLine();");
        System.out.println("  out.println(\"Echo: \" + message);\n");

        System.out.println("Client code:");
        System.out.println("  Socket socket = new Socket(\"localhost\", 8080);");
        System.out.println("  ");
        System.out.println("  PrintWriter out = new PrintWriter(");
        System.out.println("      socket.getOutputStream(), true);");
        System.out.println("  BufferedReader in = new BufferedReader(");
        System.out.println("      new InputStreamReader(socket.getInputStream()));");
        System.out.println("  ");
        System.out.println("  out.println(\"Hello Server\");");
        System.out.println("  String response = in.readLine();");
        System.out.println("  System.out.println(\"Server says: \" + response);");
        System.out.println();
    }
}

