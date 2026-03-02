package module7_2.Datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/currencydb?user=appuser&password=1234");
                System.out.println("Database connection successful.");
            }
            catch (SQLException e){
                System.out.println("Database connection failed.");
                e.printStackTrace();
            }
            return  connection;
        }
        else  {
            return connection;
        }
    }

    public static void closeConnection() {
        try {
            getConnection().close();
            System.out.println("Connection closed.");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
