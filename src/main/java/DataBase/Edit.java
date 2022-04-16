package DataBase;

import java.sql.*;

public class Edit {
    private static Statement stmt;

    public Edit(Connection connection) throws SQLException {
        stmt = connection.createStatement();
    }

    public ResultSet executeQuery(String request) throws SQLException {
        return stmt.executeQuery(request);
    }

    public boolean executeUpdate(String request) {
        try {
            stmt.executeUpdate(request);
            return true;
        } catch (SQLException e) {
        return false;
        }
    }

    public void close() throws SQLException {
        stmt.close();
    }
}