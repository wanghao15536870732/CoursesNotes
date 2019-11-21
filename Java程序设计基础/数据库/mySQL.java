package mySQL;

import java.sql.*;

public class mySQL {
    public static void main(String[] args) {
        Connection connection = null;
        Statement sql;
        ResultSet resultSet;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
        String uri = "jdbc:mysql://localhost:3306/student?&useSSL=true&characterEncoding=utf-8&serverTimezone=GMT";
        String user = "root";
        String password = "212033";
        try{
            connection = DriverManager.getConnection(uri,user,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            sql = connection.createStatement();
            resultSet = sql.executeQuery("SELECT * FROM student");
            while (resultSet.next()){
                String number = resultSet.getString(1);
                String name = resultSet.getString(2);
                String sex = resultSet.getString(3);
                String age = resultSet.getString(4);
                String dept = resultSet.getString(5);
                System.out.printf("%s\t",number);
                System.out.printf("%s\t",name);
                System.out.printf("%s\t",sex);
                System.out.printf("%s\t",age);
                System.out.printf("%s\n",dept);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
