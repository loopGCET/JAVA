import java.sql.*;

/**
 * ResultSet Examples
 * Demonstrates processing query results
 */
public class ResultSetExample {

    public static void main(String[] args) {
        System.out.println("═══ RESULTSET EXAMPLES ═══\n");

        resultSetConcepts();
        navigatingResultSet();
        retrievingData();

        System.out.println("\n═══ Complete! ===");
    }

    static void resultSetConcepts() {
        System.out.println("--- ResultSet Concepts ---");
        System.out.println("ResultSet contains query results\n");

        System.out.println("Key methods:");
        System.out.println("  - next()        : Move to next row");
        System.out.println("  - getInt(col)   : Get integer value");
        System.out.println("  - getString(col): Get string value");
        System.out.println("  - getDouble(col): Get double value");
        System.out.println("  - getDate(col)  : Get date value");
        System.out.println();
    }

    static void navigatingResultSet() {
        System.out.println("--- Navigating ResultSet ---\n");

        System.out.println("Basic iteration:");
        System.out.println("  ResultSet rs = stmt.executeQuery(\"SELECT * FROM users\");");
        System.out.println("  while (rs.next()) {");
        System.out.println("      // Process each row");
        System.out.println("  }\n");

        System.out.println("Scrollable ResultSet:");
        System.out.println("  Statement stmt = conn.createStatement(");
        System.out.println("      ResultSet.TYPE_SCROLL_INSENSITIVE,");
        System.out.println("      ResultSet.CONCUR_READ_ONLY);");
        System.out.println("  ResultSet rs = stmt.executeQuery(sql);");
        System.out.println("  rs.first();    // Go to first row");
        System.out.println("  rs.last();     // Go to last row");
        System.out.println("  rs.absolute(5);// Go to row 5");
        System.out.println();
    }

    static void retrievingData() {
        System.out.println("--- Retrieving Data ---\n");

        System.out.println("By column name:");
        System.out.println("  int id = rs.getInt(\"id\");");
        System.out.println("  String name = rs.getString(\"name\");");
        System.out.println("  double salary = rs.getDouble(\"salary\");\n");

        System.out.println("By column index (1-based):");
        System.out.println("  int id = rs.getInt(1);");
        System.out.println("  String name = rs.getString(2);");
        System.out.println("  double salary = rs.getDouble(3);\n");

        System.out.println("Metadata:");
        System.out.println("  ResultSetMetaData metaData = rs.getMetaData();");
        System.out.println("  int columnCount = metaData.getColumnCount();");
        System.out.println("  String columnName = metaData.getColumnName(1);");
        System.out.println();
    }
}

