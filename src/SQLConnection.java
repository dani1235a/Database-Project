import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private final static String SQL_URL =
            "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BookStore;integratedSecurity=true";

    private Connection myConnection;


    public void startConnection(){
        myConnection = null;
        try {
            myConnection = DriverManager.getConnection(SQL_URL);
        } catch (SQLException e) {
            System.out.println("Error connecting");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return myConnection;
    }

}
