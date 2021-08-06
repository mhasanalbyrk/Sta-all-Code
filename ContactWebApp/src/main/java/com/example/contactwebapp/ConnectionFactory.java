package com.example.contactwebapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String jdbcUrl;
    private String user;
    private String password;

    public ConnectionFactory(String jdbcUrl, String user, String password) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
    }

    public Connection openConnection() {
        try {
            System.out.println("Connecting to db..");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection successful");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        System.out.println("Closing connection");
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conn is closed");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


//    public static Connection dbConnection() {
//        String jdbcUrl = "jdbc:mysql://localhost:3306/db_jss?useSSL=false";
//        String user = "dbuser";
//        String password = "dbuser123";
//
//        try {
//            System.out.println("Connecting to db..");
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
//            System.out.println("Connection successfull");
//            return connection;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void connectionClose(Connection connection) {
//        System.out.println("Closing connection");
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println("Conn is closed");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}
