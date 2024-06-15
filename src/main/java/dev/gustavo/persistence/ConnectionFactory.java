package dev.gustavo.persistence;

import java.sql.*;

public class ConnectionFactory {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    private static Connection createConnection() throws SQLException {
        if (connection == null) connection = DriverManager.getConnection("jdbc:sqlite:Database.db");
        return connection;
    }

    public static PreparedStatement createPrepareStatement(String sql){
        try {
            preparedStatement = createConnection().prepareStatement(sql);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static Statement createStatement(){
        try {
            statement = createConnection().createStatement();
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return statement;
    }
}
