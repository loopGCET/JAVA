/**
 * Connection Pooling Concepts
 */
public class ConnectionPoolingExample {

    public static void main(String[] args) {
        System.out.println("═══ CONNECTION POOLING EXAMPLES ═══\n");

        poolingConcepts();
        hikariCPExample();
        benefits();

        System.out.println("\n═══ Complete! ===");
    }

    static void poolingConcepts() {
        System.out.println("--- Connection Pooling Concepts ---");
        System.out.println("Instead of creating new connections each time:");
        System.out.println("  1. Create a pool of connections at startup");
        System.out.println("  2. Reuse connections from the pool");
        System.out.println("  3. Return connections to pool after use");
        System.out.println("  4. Much faster than creating new connections\n");

        System.out.println("Configuration parameters:");
        System.out.println("  - minimumIdle     : Minimum connections in pool");
        System.out.println("  - maximumPoolSize : Maximum connections");
        System.out.println("  - connectionTimeout : Max wait time");
        System.out.println();
    }

    static void hikariCPExample() {
        System.out.println("--- HikariCP Example ---\n");

        System.out.println("Setup (requires HikariCP library):");
        System.out.println("  HikariConfig config = new HikariConfig();");
        System.out.println("  config.setJdbcUrl(\"jdbc:mysql://localhost:3306/mydb\");");
        System.out.println("  config.setUsername(\"user\");");
        System.out.println("  config.setPassword(\"password\");");
        System.out.println("  config.setMaximumPoolSize(10);");
        System.out.println("  ");
        System.out.println("  HikariDataSource dataSource = new HikariDataSource(config);");
        System.out.println("  Connection conn = dataSource.getConnection();\n");

        System.out.println("Usage:");
        System.out.println("  try (Connection conn = dataSource.getConnection()) {");
        System.out.println("      // Use connection");
        System.out.println("  } // Automatically returned to pool");
        System.out.println();
    }

    static void benefits() {
        System.out.println("--- Benefits ---");
        System.out.println("✅ Much faster than creating new connections");
        System.out.println("✅ Reduced database load");
        System.out.println("✅ Better resource management");
        System.out.println("✅ Configurable pool size");
        System.out.println("✅ Connection validation and cleanup");
        System.out.println("\nBest Practice: Always use connection pooling in production!");
        System.out.println();
    }
}

