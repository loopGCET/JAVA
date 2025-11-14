# HTTP Client (Java 11+)

Modern HTTP client introduced in Java 11.

```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
```

For details, see: **[HTTPClientExample.java](HTTPClientExample.java)**

