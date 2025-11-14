import java.sql.*;

/**
 * JDBC Statement Examples
 * Demonstrates Statement usage concepts
 */
public class StatementExample {

    public static void main(String[] args) {
        System.out.println("═══ JDBC STATEMENT EXAMPLES ═══\n");

        statementConcepts();
        queryTypes();

        System.out.println("\n═══ Complete! ===");
    }

    static void statementConcepts() {
        System.out.println("--- Statement Concepts ---");
        System.out.println("Statement is used for static SQL queries\n");

        System.out.println("Creating Statement:");
        System.out.println("  Statement stmt = conn.createStatement();\n");

        System.out.println("Common methods:");
        System.out.println("  - executeQuery(sql)  : For SELECT queries");
        System.out.println("  - executeUpdate(sql) : For INSERT/UPDATE/DELETE");
        System.out.println("  - execute(sql)       : For any SQL statement");
        System.out.println();
    }

    static void queryTypes() {
        System.out.println("--- Query Types ---\n");

        System.out.println("SELECT query:");
        System.out.println("  ResultSet rs = stmt.executeQuery(\"SELECT * FROM users\");");
        System.out.println("  while (rs.next()) {");
        System.out.println("      String name = rs.getString(\"name\");");
        System.out.println("  }\n");

        System.out.println("INSERT query:");
        System.out.println("  int rows = stmt.executeUpdate(");
        System.out.println("      \"INSERT INTO users VALUES (1, 'Alice')\");\n");

        System.out.println("UPDATE query:");
        System.out.println("  int rows = stmt.executeUpdate(");
        System.out.println("      \"UPDATE users SET active = true WHERE id = 1\");\n");

        System.out.println("DELETE query:");
        System.out.println("  int rows = stmt.executeUpdate(");
        System.out.println("      \"DELETE FROM users WHERE id = 1\");");
        System.out.println();
    }
}

