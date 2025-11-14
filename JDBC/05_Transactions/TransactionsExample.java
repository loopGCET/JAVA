import java.sql.*;

/**
 * Transaction Management Examples
 */
public class TransactionsExample {

    public static void main(String[] args) {
        System.out.println("═══ TRANSACTIONS EXAMPLES ═══\n");

        transactionConcepts();
        transactionPattern();
        isolationLevels();

        System.out.println("\n═══ Complete! ===");
    }

    static void transactionConcepts() {
        System.out.println("--- Transaction Concepts ---");
        System.out.println("ACID Properties:");
        System.out.println("  - Atomicity   : All or nothing");
        System.out.println("  - Consistency : Valid state transitions");
        System.out.println("  - Isolation   : Concurrent transactions don't interfere");
        System.out.println("  - Durability  : Committed changes persist\n");

        System.out.println("Transaction methods:");
        System.out.println("  - setAutoCommit(false) : Start transaction");
        System.out.println("  - commit()             : Save changes");
        System.out.println("  - rollback()           : Undo changes");
        System.out.println();
    }

    static void transactionPattern() {
        System.out.println("--- Transaction Pattern ---\n");

        System.out.println("try (Connection conn = DriverManager.getConnection(url)) {");
        System.out.println("    conn.setAutoCommit(false);");
        System.out.println("    ");
        System.out.println("    try {");
        System.out.println("        // Execute multiple queries");
        System.out.println("        stmt.executeUpdate(\"INSERT INTO orders ...\");");
        System.out.println("        stmt.executeUpdate(\"UPDATE inventory ...\");");
        System.out.println("        ");
        System.out.println("        conn.commit(); // All successful");
        System.out.println("    } catch (SQLException e) {");
        System.out.println("        conn.rollback(); // Error occurred");
        System.out.println("        throw e;");
        System.out.println("    }");
        System.out.println("}\n");

        System.out.println("Example: Bank Transfer");
        System.out.println("  1. Debit from account A");
        System.out.println("  2. Credit to account B");
        System.out.println("  3. Commit if both successful");
        System.out.println("  4. Rollback if any fails");
        System.out.println();
    }

    static void isolationLevels() {
        System.out.println("--- Isolation Levels ---\n");

        System.out.println("conn.setTransactionIsolation(level);\n");

        System.out.println("Levels:");
        System.out.println("  1. READ_UNCOMMITTED : Dirty reads possible");
        System.out.println("  2. READ_COMMITTED   : No dirty reads");
        System.out.println("  3. REPEATABLE_READ  : Consistent reads");
        System.out.println("  4. SERIALIZABLE     : Full isolation (slowest)");
        System.out.println();
    }
}

