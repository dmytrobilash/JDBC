import java.sql.*;

public class ConnectionFactory {
    private static final String dbHost = "localhost:3306";
    private static final String dbName = "java2022lb2";
    private static final String dbUser = "root";
    private static final String dbPassword = "";
    private static final String dbUrl = "jdbc:mysql://" + dbHost + "/" + dbName;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Can't get mysql driver");
        }
        Connection connection = DriverManager.getConnection(dbUrl, dbUser,
                dbPassword);
        connection.setAutoCommit(true);
        validate(connection);
        return connection;
    }

    private static void validate(Connection connection) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        if (!meta.getTables(null, null, "Owners", new String[]{"TABLE"}).next()) {
            throw new SQLException("Table branch not found");
        }
        if (!meta.getTables(null, null, "Cars", new String[]{"TABLE"}).next()) {
            throw new SQLException("Table departments not found");
        }
    }
}

