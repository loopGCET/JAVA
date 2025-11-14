import java.io.*;
import java.net.*;

/**
 * URL Connection Examples
 */
public class URLConnectionExample {

    public static void main(String[] args) {
        System.out.println("═══ URL CONNECTION EXAMPLES ═══\n");

        urlConcepts();
        readingFromURL();
        httpURLConnection();

        System.out.println("\n═══ Complete! ===");
    }

    static void urlConcepts() {
        System.out.println("--- URL Concepts ---");
        System.out.println("URL components:");
        System.out.println("  protocol://host:port/path?query#fragment\n");

        System.out.println("Example:");
        System.out.println("  https://api.example.com:443/users?id=123#section\n");

        System.out.println("Key classes:");
        System.out.println("  - URL           : Represents a URL");
        System.out.println("  - URLConnection : Connection to URL");
        System.out.println("  - HttpURLConnection : HTTP-specific operations");
        System.out.println();
    }

    static void readingFromURL() {
        System.out.println("--- Reading from URL ---\n");

        System.out.println("Basic pattern:");
        System.out.println("  URL url = new URL(\"https://example.com\");");
        System.out.println("  URLConnection connection = url.openConnection();");
        System.out.println("  ");
        System.out.println("  BufferedReader in = new BufferedReader(");
        System.out.println("      new InputStreamReader(connection.getInputStream()));");
        System.out.println("  ");
        System.out.println("  String line;");
        System.out.println("  while ((line = in.readLine()) != null) {");
        System.out.println("      System.out.println(line);");
        System.out.println("  }");
        System.out.println();
    }

    static void httpURLConnection() {
        System.out.println("--- HttpURLConnection ---\n");

        System.out.println("GET request:");
        System.out.println("  URL url = new URL(\"https://api.example.com/data\");");
        System.out.println("  HttpURLConnection conn = (HttpURLConnection) url.openConnection();");
        System.out.println("  conn.setRequestMethod(\"GET\");");
        System.out.println("  ");
        System.out.println("  int responseCode = conn.getResponseCode();");
        System.out.println("  if (responseCode == 200) {");
        System.out.println("      // Read response");
        System.out.println("  }\n");

        System.out.println("POST request:");
        System.out.println("  conn.setRequestMethod(\"POST\");");
        System.out.println("  conn.setDoOutput(true);");
        System.out.println("  ");
        System.out.println("  OutputStream os = conn.getOutputStream();");
        System.out.println("  os.write(postData.getBytes());");
        System.out.println();
    }
}

