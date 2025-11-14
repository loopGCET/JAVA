# Java Networking

> Network communication in Java

[![Java](https://img.shields.io/badge/Java-1.0%2B-blue.svg)](https://www.oracle.com/java/)

## Overview

Java provides comprehensive APIs for network programming.

## Topics

1. Socket Programming - TCP client-server communication
2. URL Connection - Working with URLs
3. HTTP Client - Modern HTTP client (Java 11+)
4. Datagram Sockets - UDP communication

## Example

```java
// TCP Socket
Socket socket = new Socket("localhost", 8080);
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
out.println("Hello Server!");

// HTTP Client (Java 11+)
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com"))
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

**Status:** ✅ Complete

