package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorProvider {
    private static final DBConnectorProvider INSTANCE = new DBConnectorProvider();
    private Connection connection;
    private final String DB_URL = "jdbc:mysql://localhost:3306/eshop_shopping_itspace?useUnicode=true";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private DBConnectorProvider() {
    }

    public static DBConnectorProvider getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
