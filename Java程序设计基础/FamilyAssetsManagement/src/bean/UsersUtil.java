package bean;

import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersUtil {

    Connection con = DBHelper.getConnect();

    //添加用户方法
    public boolean addData(Users u){
        try {
            String sql = "insert into users (username,password) values (?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getPassword());
            int result = statement.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //检查用户名和密码的正确性
    public boolean checkData(Users u){
        try{
            String sql = "select * from users where username=? and password=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, u.getUsername().trim());
            preparedStatement.setString(2, u.getPassword().trim());
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    //查询所有用户
    public String[][] getAllUses(){
        String[][] result = new String[0][];
        ResultSet resultSet = null;
        int count = 0;
        try {
            String sql = "select * from users;";
            PreparedStatement statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count ++;
            }
            result = new String[count][6];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = resultSet.getString(3);
                result[count][3] = resultSet.getString(4);
                result[count][4] = resultSet.getDate(5).toString();
                result[count][5] = resultSet.getString(6);
                count ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
