package DataBase;

import java.sql.*;

public class Connector {
    private static volatile Connector instance;
    private boolean flagConnection = false;
    private Connection connection;

    private Connector() {
    }

    public boolean connection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,
                    user, password);
            flagConnection = true;
            return true;
        } catch (SQLException | ClassNotFoundException sqlException) {
            flagConnection = false;
            return false;
        }
    }

    public boolean connectionBreak() throws SQLException {
        if (flagConnection) {
            this.connection.close();
            this.connection = null;
            flagConnection = false;
            return true;
        }
        return false;
    }

    public static Connector getInstance() {
        if (instance == null)
            synchronized (Connector.class) {
                if (instance == null)
                    instance = new Connector();
            }
        return instance;
    }

    public boolean isFlagConnection() {
        return flagConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
