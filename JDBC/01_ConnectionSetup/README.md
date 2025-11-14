# JDBC Connection Setup

Establish database connections using JDBC.

## Basic Connection

```java
String url = "jdbc:mysql://localhost:3306/mydb";
String user = "username";
String password = "password";

Connection conn = DriverManager.getConnection(url, user, password);
```

## Connection Steps

1. Load JDBC driver (automatic in JDBC 4.0+)
2. Get connection using DriverManager
3. Execute queries
4. Close resources

For details, see: **[ConnectionSetupExample.java](ConnectionSetupExample.java)**

**Note:** Examples use H2 in-memory database for demonstration (no external DB required)

