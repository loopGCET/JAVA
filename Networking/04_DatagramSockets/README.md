# Datagram Sockets (UDP)

Connectionless communication using UDP protocol.

```java
// Sender
DatagramSocket socket = new DatagramSocket();
byte[] buffer = message.getBytes();
DatagramPacket packet = new DatagramPacket(buffer, buffer.length, 
    InetAddress.getByName("localhost"), 9876);
socket.send(packet);

// Receiver
DatagramSocket socket = new DatagramSocket(9876);
byte[] buffer = new byte[1024];
DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
socket.receive(packet);
```

For details, see: **[DatagramSocketsExample.java](DatagramSocketsExample.java)**

