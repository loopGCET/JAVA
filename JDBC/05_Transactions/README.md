# Transactions

Manage database transactions with commit and rollback.

```java
conn.setAutoCommit(false);
try {
    // Execute multiple queries
    stmt.executeUpdate("INSERT INTO accounts ...");
    stmt.executeUpdate("UPDATE balance ...");
    
    conn.commit(); // Commit if all successful
} catch (SQLException e) {
    conn.rollback(); // Rollback on error
}
```

For details, see: **[TransactionsExample.java](TransactionsExample.java)**

