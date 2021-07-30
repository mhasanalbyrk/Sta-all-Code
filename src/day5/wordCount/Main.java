package day5.wordCount;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        HashMap<String, Integer> wordFreq = new HashMap<String, Integer>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string:");
        String in = input.nextLine();
        String[] words = in.split(" ");

        for (String s: words) {
            if (wordFreq.get(s) == null){
                wordFreq.put(s, 1);
            }
            else{
                int c = wordFreq.get(s);
                wordFreq.put(s, ++c);
            }
        }
        System.out.println(wordFreq.toString());


        Connection conn = dbConnection();
        conn.setAutoCommit(false);
        wordFreq.forEach((k, v) -> updateDb(conn, k, v));
        try {
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connectionClose(conn);

    }



    public static void updateDb(Connection conn, String s, int freq){
        PreparedStatement statement;
        if (conn != null){
            try {
                System.out.println("Executing update");
                String query = "Select * from word_freq where word=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, s);

                ResultSet rs = statement.executeQuery();
                if (!rs.next()){
                    insertRow(conn, s, freq);
                }
                else{
                    if (true){
                        System.out.println(rs.getString("word") + " " + rs.getInt("freq") + "->" + rs.getInt("freq") + freq);
                        updateRow(conn, s, rs.getInt("freq") + freq);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static  void updateRow(Connection conn, String s, int freq){
        PreparedStatement statement;
        if (conn != null){
            try {
                System.out.println("Updating row");
                String query = "UPDATE word_freq set freq=? where word=?";
                //String query = "UPDATE student set first_name='updateTest' where id=2";
                statement = conn.prepareStatement(query);
                statement.setInt(1, freq);
                statement.setString(2, s);
                statement.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void insertRow(Connection conn, String s, int freq){
       // String querya = "insert into student (first_name, last_name, email)" +
               // "values('test', 'test', 'test@test')";

        Statement statement = null;
        if (conn != null){
            try {
                System.out.println("Inserting row");
                String query = "insert into word_freq (word, freq) " +
                        "values('"+ s + "', " + freq + ")";
                //String query = "UPDATE student set first_name='updateTest' where id=2";
                statement = conn.createStatement();
                statement.executeUpdate(query);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public  static  void  connectionClose(Connection connection){
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


    public static Connection dbConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_jss?useSSL=false";
        String user = "dbuser";
        String password = "dbuser123";

        try {
            System.out.println("Connecting to db..");
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection successfull");
            return connection;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
