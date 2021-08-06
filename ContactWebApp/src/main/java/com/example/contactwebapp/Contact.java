package com.example.contactwebapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private String number;
    private int id;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String number, int id) {
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public Contact( int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public void addContact(Contact contact, Connection conn) {
        String query = "insert into contacts (name, tel_number)" +
                "values('" + contact.getName() + "', '" + contact.getNumber() + "')";
        Statement statement = null;
        if (conn != null) {
            try {
                System.out.println("Inserting row");
                statement = conn.createStatement();
                statement.executeUpdate(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contact> searchContact(Contact contact, Connection cnn) {
        PreparedStatement statement;
        String query ="";
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        if (cnn != null) {
            try {
                System.out.println("Searching...");
                if (contact.getName() != null){
                    query = "Select * from contacts where name=?";
                    statement = cnn.prepareStatement(query);
                    statement.setString(1, name);

                }
                else {
                    query = "Select * from contacts where id=?";
                    statement = cnn.prepareStatement(query);
                    statement.setInt(1, contact.getId());
                }


                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    contacts.add(new Contact(rs.getString("name"), rs.getString("tel_number"), rs.getInt("id")));
                }
                return contacts;
//                    if (true){
//                        System.out.println(rs.getString("word") + " " + rs.getInt("freq") + "->" + rs.getInt("freq") + freq);
//                        updateRow(conn, s, rs.getInt("freq") + freq);
//                    }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
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

    public void updateContact(Contact contact, Connection connection) {
        PreparedStatement statement;
        if (connection != null) {
            try {
                System.out.println("Updating contact");
                String query = "UPDATE contacts" +
                        " set name=?, tel_number=? " +
                        "where id=?";
                statement = connection.prepareStatement(query);
                statement.setString(1, contact.getName());

                statement.setString(2, contact.getNumber());
                statement.setInt(3, contact.getId());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

}
