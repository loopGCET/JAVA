# Java JDBC

> Java Database Connectivity

[![Java](https://img.shields.io/badge/Java-1.1%2B-blue.svg)](https://www.oracle.com/java/)

## Overview

JDBC API allows Java applications to interact with databases.

## Topics

1. Connection Setup - Establishing database connections
2. Statement - Executing SQL queries
3. PreparedStatement - Parameterized queries
4. ResultSet - Processing query results
5. Transactions - Managing database transactions
6. Connection Pooling - Efficient connection management

## Example

```java
Connection conn = DriverManager.getConnection(url, user, password);
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId);
ResultSet rs = pstmt.executeQuery();
```

**Status:** ✅ Complete

