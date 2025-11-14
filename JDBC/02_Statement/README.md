# Statement

Execute static SQL queries using Statement.

```java
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
int rows = stmt.executeUpdate("UPDATE users SET active = true");
```

For details, see: **[StatementExample.java](StatementExample.java)**

