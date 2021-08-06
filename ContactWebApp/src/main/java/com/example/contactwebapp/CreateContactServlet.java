package com.example.contactwebapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/create-contact-servlet")
public class CreateContactServlet extends HttpServlet {
    private static final String createContact = "WEB-INF/create-contact.jsp";
    private static final String success = "WEB-INF/operation-succesful.jsp";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/db_jss?useSSL=false";
    private static final String user = "dbuser";
    private static final String password = "dbuser123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(createContact).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String tel = req.getParameter("number");
        Contact contact = new Contact(name, tel);
        ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, user, password);
        resp.getWriter().println(name + " " + tel);

        Connection cnn = connectionFactory.openConnection();
        contact.addContact(contact, cnn);
        connectionFactory.closeConnection(cnn);
        req.getRequestDispatcher(success).forward(req, resp);
    }
}


//    public static void addContact(Contact contact, Connection conn) {
//        String query = "insert into contacts (name, tel_number)" +
//                "values('" + contact.getName() + "', '" + contact.getNumber() + "')";
//        Statement statement = null;
//        if (conn != null) {
//            try {
//                System.out.println("Inserting row");
//                statement = conn.createStatement();
//                statement.executeUpdate(query);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
// String querya = "insert into student (first_name, last_name, email)" +
//        // "values('test', 'test', 'test@test')";
//
//        Statement statement = null;
//        if (conn != null){
//            try {
//                System.out.println("Inserting row");
//                String query = "insert into word_freq (word, freq) " +
//                        "values('"+ s + "', " + freq + ")";
//                //String query = "UPDATE student set first_name='updateTest' where id=2";
//                statement = conn.createStatement();
//                statement.executeUpdate(query);
//            }catch (Exception e){
//                e.printStackTrace();
//            }


//
//    Connection conn = dbConnection();
//        conn.setAutoCommit(false);
//        wordFreq.forEach((k, v) -> updateDb(conn, k, v));
//        try {
//        conn.commit();
//    } catch (SQLException throwables) {
//        throwables.printStackTrace();
//    }
//    connectionClose(conn);
//
//}
//
//
//
//    public static void updateDb(Connection conn, String s, int freq){
//        PreparedStatement statement;
//        if (conn != null){
//            try {
//                System.out.println("Executing update");
//                String query = "Select * from word_freq where word=?";
//                statement = conn.prepareStatement(query);
//                statement.setString(1, s);
//
//                ResultSet rs = statement.executeQuery();
//                if (!rs.next()){
//                    insertRow(conn, s, freq);
//                }
//                else{
//                    if (true){
//                        System.out.println(rs.getString("word") + " " + rs.getInt("freq") + "->" + rs.getInt("freq") + freq);
//                        updateRow(conn, s, rs.getInt("freq") + freq);
//                    }
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    public static  void updateRow(Connection conn, String s, int freq){
//        PreparedStatement statement;
//        if (conn != null){
//            try {
//                System.out.println("Updating row");
//                String query = "UPDATE word_freq set freq=? where word=?";
//                //String query = "UPDATE student set first_name='updateTest' where id=2";
//                statement = conn.prepareStatement(query);
//                statement.setInt(1, freq);
//                statement.setString(2, s);
//                statement.executeUpdate();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void insertRow(Connection conn, String s, int freq){
//        // String querya = "insert into student (first_name, last_name, email)" +
//        // "values('test', 'test', 'test@test')";
//
//        Statement statement = null;
//        if (conn != null){
//            try {
//                System.out.println("Inserting row");
//                String query = "insert into word_freq (word, freq) " +
//                        "values('"+ s + "', " + freq + ")";
//                //String query = "UPDATE student set first_name='updateTest' where id=2";
//                statement = conn.createStatement();
//                statement.executeUpdate(query);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

