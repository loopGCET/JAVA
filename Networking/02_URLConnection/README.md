# URL Connection

Work with URLs and HTTP connections.

```java
URL url = new URL("https://api.example.com/data");
URLConnection connection = url.openConnection();
BufferedReader in = new BufferedReader(
    new InputStreamReader(connection.getInputStream()));
```

For details, see: **[URLConnectionExample.java](URLConnectionExample.java)**

