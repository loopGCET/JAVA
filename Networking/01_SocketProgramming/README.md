# Socket Programming

TCP client-server communication using sockets.

## Server
```java
ServerSocket serverSocket = new ServerSocket(8080);
Socket clientSocket = serverSocket.accept();
```

## Client
```java
Socket socket = new Socket("localhost", 8080);
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
```

For details, see: **[SocketProgrammingExample.java](SocketProgrammingExample.java)**

