# Buffered I/O

Buffered streams improve performance by reducing I/O operations.

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}
```

For details, see: **[BufferedIOExample.java](BufferedIOExample.java)**

