package com.model;

import com.common.AppConfig;

import java.sql.*;

public class MyConnection {
    public static Connection connection = null;


    public void testDrive() throws ClassNotFoundException {
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {
        this.testDrive();
        try {

            connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
            System.out.println("ket noi thanh cong");
        } catch (SQLException throwables) {
            throw new SQLException(throwables.getMessage());
        }
        return connection;

    }


    public PreparedStatement prepare(String sql) {
        try {
            //cho phép con trỏ resultset chạy từ bản ghi đầu tiên đến bản ghi cuối cùng
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }

    public PreparedStatement preparedUpdate(String sql) {
        try {
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection is closed");
        }
    }


}
