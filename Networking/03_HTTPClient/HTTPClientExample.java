import java.net.http.*;
import java.net.*;

/**
 * HTTP Client Examples (Java 11+)
 */
public class HTTPClientExample {
    
    public static void main(String[] args) {
        System.out.println("═══ HTTP CLIENT EXAMPLES ═══\n");
        
        httpClientConcepts();
        syncRequests();
        asyncRequests();
        
        System.out.println("\n═══ Complete! ===");
    }
    
    static void httpClientConcepts() {
        System.out.println("--- HTTP Client Concepts ---");
        System.out.println("Modern HTTP client (Java 11+)\n");
        
        System.out.println("Features:");
        System.out.println("  - Supports HTTP/1.1 and HTTP/2");
        System.out.println("  - Synchronous and asynchronous requests");
        System.out.println("  - Builder pattern for requests");
        System.out.println("  - Automatic redirect handling");
        System.out.println("  - Better API than URLConnection");
        System.out.println();
    }
    
    static void syncRequests() {
        System.out.println("--- Synchronous Requests ---\n");
        
        System.out.println("GET request:");
        System.out.println("  HttpClient client = HttpClient.newHttpClient();");
        System.out.println("  ");
        System.out.println("  HttpRequest request = HttpRequest.newBuilder()");
        System.out.println("      .uri(URI.create(\"https://api.example.com/users\"))");
        System.out.println("      .build();");
        System.out.println("  ");
        System.out.println("  HttpResponse<String> response = client.send(request,");
        System.out.println("      HttpResponse.BodyHandlers.ofString());");
        System.out.println("  ");
        System.out.println("  System.out.println(response.body());\n");
        
        System.out.println("POST request:");
        System.out.println("  HttpRequest request = HttpRequest.newBuilder()");
        System.out.println("      .uri(URI.create(\"https://api.example.com/users\"))");
        System.out.println("      .header(\"Content-Type\", \"application/json\")");
        System.out.println("      .POST(HttpRequest.BodyPublishers.ofString(jsonData))");
        System.out.println("      .build();");
        System.out.println();
    }
    
    static void asyncRequests() {
        System.out.println("--- Asynchronous Requests ---\n");
        
        System.out.println("Async pattern:");
        System.out.println("  HttpClient client = HttpClient.newHttpClient();");
        System.out.println("  ");
        System.out.println("  HttpRequest request = HttpRequest.newBuilder()");
        System.out.println("      .uri(URI.create(\"https://api.example.com/data\"))");
        System.out.println("      .build();");
        System.out.println("  ");
        System.out.println("  client.sendAsync(request, HttpResponse.BodyHandlers.ofString())");
        System.out.println("      .thenApply(HttpResponse::body)");
        System.out.println("      .thenAccept(System.out::println)");
        System.out.println("      .join();");
        System.out.println();
    }
}

