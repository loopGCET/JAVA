# Paths & Files (NIO.2)

Modern file API introduced in Java 7.

```java
Path path = Paths.get("file.txt");
List<String> lines = Files.readAllLines(path);
Files.write(path, "Hello".getBytes());
```

For details, see: **[PathsFilesExample.java](PathsFilesExample.java)**

