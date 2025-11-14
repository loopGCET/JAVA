import java.sql.*;

/**
 * PreparedStatement Examples
 * Demonstrates parameterized queries
 */
public class PreparedStatementExample {

    public static void main(String[] args) {
        System.out.println("═══ PREPARED STATEMENT EXAMPLES ═══\n");

        preparedStatementConcepts();
        parameterTypes();
        sqlInjectionPrevention();

        System.out.println("\n═══ Complete! ===");
    }

    static void preparedStatementConcepts() {
        System.out.println("--- PreparedStatement Concepts ---");
        System.out.println("PreparedStatement is used for parameterized queries\n");

        System.out.println("Benefits:");
        System.out.println("  1. Prevents SQL injection");
        System.out.println("  2. Better performance (pre-compiled)");
        System.out.println("  3. Cleaner code");
        System.out.println();
    }

    static void parameterTypes() {
        System.out.println("--- Parameter Types ---\n");

        System.out.println("Setting parameters:");
        System.out.println("  pstmt.setInt(1, 42);");
        System.out.println("  pstmt.setString(2, \"Alice\");");
        System.out.println("  pstmt.setDouble(3, 99.99);");
        System.out.println("  pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));");
        System.out.println("  pstmt.setBoolean(5, true);\n");

        System.out.println("Example query:");
        System.out.println("  String sql = \"INSERT INTO users (id, name, age) VALUES (?, ?, ?)\";");
        System.out.println("  PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println("  pstmt.setInt(1, 1);");
        System.out.println("  pstmt.setString(2, \"Alice\");");
        System.out.println("  pstmt.setInt(3, 25);");
        System.out.println("  pstmt.executeUpdate();");
        System.out.println();
    }

    static void sqlInjectionPrevention() {
        System.out.println("--- SQL Injection Prevention ---\n");

        System.out.println("❌ Vulnerable (Statement):");
        System.out.println("  String sql = \"SELECT * FROM users WHERE name = '\" + userInput + \"'\";");
        System.out.println("  // If userInput = \"' OR '1'='1\", returns all users!\n");

        System.out.println("✅ Safe (PreparedStatement):");
        System.out.println("  String sql = \"SELECT * FROM users WHERE name = ?\";");
        System.out.println("  PreparedStatement pstmt = conn.prepareStatement(sql);");
        System.out.println("  pstmt.setString(1, userInput);");
        System.out.println("  // Input is properly escaped");
        System.out.println();
    }
}

