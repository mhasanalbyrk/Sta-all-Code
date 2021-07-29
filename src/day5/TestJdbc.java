package day5;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_jss?useSSL=false";
        String user = "dbuser";
        String password = "dbuser123";

        try {
            System.out.println("Connecting to db..");
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection successfull");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
