# ResultSet

Process query results from database.

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
}
```

For details, see: **[ResultSetExample.java](ResultSetExample.java)**

