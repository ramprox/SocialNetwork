package ru.ramil.SocialNetwork;

import java.sql.*;

public class MainApp {
    private static final String DB = "jdbc:mysql://localhost:3306/social_network";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection dbConnection;

    public static void main(String[] args) {
        try {
            dbConnection = getConnection();
            String simpleProc = "CALL LIST_USERS_LIKED_USER(5);";
            Statement statement = dbConnection.createStatement();
            statement.execute(simpleProc);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            closeConnection();
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if(dbConnection == null) {
            dbConnection = DriverManager.getConnection(DB, USER, PASSWORD);
        }
        return dbConnection;
    }

    public static void closeConnection() {
        try {
            if(dbConnection != null) {
                dbConnection.close();
            }
        } catch(SQLException ex) {

        } finally {
            dbConnection = null;
        }
    }
}
