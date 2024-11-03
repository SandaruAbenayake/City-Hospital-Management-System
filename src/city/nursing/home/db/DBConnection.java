
package city.nursing.home.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/hos";
    private static final String USER = "root"; // username
    private static final String PASSWORD = "1234"; // DataBase password

    // Create a static instance of DBConnection (Singleton)
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());

        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (instance != null && instance.getConnection() != null) {
            try {
                instance.getConnection().close();
                System.out.println("Database connection closed successfully!");
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
