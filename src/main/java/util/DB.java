package util;

import java.sql.*;

import java.sql.SQLException;

/**
 * Administrator
 * Created by tbpwang
 * 2016/7/9.
 */
public class DB {
    public static final String URL = "jdbc:mysql:///db_health?user=root&password=system";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");//com.mysql.cj.jdbc
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    public static void close(ResultSet set, PreparedStatement statement, Connection connection) {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
