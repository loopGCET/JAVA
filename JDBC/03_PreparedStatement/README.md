# PreparedStatement

Execute parameterized queries safely (prevents SQL injection).

```java
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, userId);
ResultSet rs = pstmt.executeQuery();
```

For details, see: **[PreparedStatementExample.java](PreparedStatementExample.java)**

