package day5.PreparedStatement;

import java.sql.*;


public class PreparedStatementEx {

    public static void main(String[] args) {
        Connection conn = dbConnection();
        //select(conn);
        //insert(conn);
        update(conn);
        connectionClose(conn);
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

    public static void update(Connection connection){
        Statement statement;
        if (connection != null){
            try{
                System.out.println("Executing update");
                String query = "UPDATE student set first_name='updateTest' where id=2";
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public static void insert(Connection connection){
        Statement statement;
        if (connection != null){
            try {
                System.out.println("Executing insert");
                String query = "insert into student (first_name, last_name, email)" +
                        "values('test', 'test', 'test@test')";
                statement = connection.createStatement();
                statement.execute(query);

            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public static void select(Connection conn){
        PreparedStatement preparedStatement = null;
        if (conn != null){
            try {
                System.out.println("Executing select");
                String query = "Select * from student where id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, 1);

                ResultSet rs = preparedStatement.executeQuery();
               while (rs.next()){
                    System.out.println(rs.getInt("id") + rs.getString("first_name") +
                            rs.getString("last_name") + rs.getString("email"));
                }


            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
