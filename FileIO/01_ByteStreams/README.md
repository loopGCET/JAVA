# Byte Streams

Byte streams handle binary data (8-bit bytes).

## Key Classes
- **InputStream** - Read bytes
- **OutputStream** - Write bytes
- **FileInputStream/FileOutputStream** - File I/O

## Example
```java
try (FileInputStream fis = new FileInputStream("input.bin");
     FileOutputStream fos = new FileOutputStream("output.bin")) {
    
    int byteData;
    while ((byteData = fis.read()) != -1) {
        fos.write(byteData);
    }
}
```

For details, see: **[ByteStreamsExample.java](ByteStreamsExample.java)**

