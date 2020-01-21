package handle;

import bean.Teacher;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandleTea {
    private static Connection con;
    HandleSQL handleSQL;

    private static String InsertSQL="insert into teacher(Tno,Tname,Tsex,Tage,Tdept,Tdegree,Ttitle,Tduty,Ttel,Temail,Tpassword) "+
            "value(?,?,?,?,?,?,?,?,?,?,?);";

    private static String SelectTeacherSQL =
            "select Tno,Tname,Tsex,Tage,Tdept,Tdegree,Ttitle,Tduty,Ttel,Temail " +
                    "from Teacher " +
                    "where Tno=?;";
    private static String UpdateSQL="update teacher "+
            "set Tname=?,Tsex=?,Tage=?,Tdept=?,Ttel=?,Temail=? "+
            "where Tno=?;";
    private static String DeleteSQL="delete from teacher where Tno=?;";
    private static String InitializeSQL="update teacher "+"set Tpassword='123456' "+"where Tno=?;";

    /*
     * -1:用户名不存在(用户名输入不正确)
     * 0:用户密码错误
     * 1:用户名密码均正确
     */
    public HandleTea() {
        con = DBHelper.getConnect();
    }
    public Connection getCon() {
        return con;
    }

    public int checkTea(Teacher teacher) throws SQLException {
        handleSQL = new HandleSQL(con);

        //查找教师表里面所有  教师号为用户输入的用户名的教师,并添加到列表里
        ArrayList<Teacher> teachers = handleSQL.findTeaBySQL("select Tno,Tpassword from teacher where Tno='"+teacher.getTno()+"'");

        if(teachers.size()<1)//该用户名在教师表里面不存在
            return -1;

        if(teacher.getTpassword().equals(teachers.get(0).getTpassword())) //用户名存在,且密码匹配正确
            return 1;
        else     //用户名存在,但密码不匹配
            return 0;
    }

    public static boolean InsertTeacher(String Tno,String Tname,String Tsex,String Tage,String Tdept,
                                        String Tdegree,String Ttitle,String Tduty,String Ttel,String Temail,String Tpassword) {
        try{
            PreparedStatement statement = con.prepareStatement(InsertSQL);
            statement.setString(1,Tno);
            statement.setString(2,Tname);
            statement.setString(3,Tsex);
            statement.setString(4,Tage);
            statement.setString(5,Tdept);
            statement.setString(6,Tdegree);
            statement.setString(7,Ttitle);
            statement.setString(8,Tduty);
            statement.setString(9,Ttel);
            statement.setString(10,Temail);
            statement.setString(11,Tpassword);
            int result = statement.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[][] SelectTeacher(String Tno){
        String[][] result = new String[0][];
        ResultSet resultSet  = null;
        int count = 0;
        try{
            PreparedStatement statement = con.prepareStatement(SelectTeacherSQL);
            statement.setString(1,Tno);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count ++;
            }
            result = new String[count][11];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = resultSet.getString(3);
                result[count][3] = String.valueOf(resultSet.getInt(4));
                result[count][4] = resultSet.getString(5);
                result[count][5] = resultSet.getString(6);
                result[count][6] = resultSet.getString(7);
                result[count][7] = resultSet.getString(8);
                result[count][8] = resultSet.getString(9);
                result[count][9] = resultSet.getString(10);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean UpdateTeacher(String Tno,String Tname,String Tsex,String Tage,String Tdept, String Ttel,String Temail) {
        try{
            PreparedStatement statement = con.prepareStatement(UpdateSQL);
            statement.setString(1,Tname);
            statement.setString(2,Tsex);
            statement.setString(3,Tage);
            statement.setString(4,Tdept);
            statement.setString(5,Ttel);
            statement.setString(6,Temail);
            statement.setString(7,Tno);
            int result = statement.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean DeleteTeacher(String Tno) {
        try {
            PreparedStatement statement = con.prepareStatement(DeleteSQL);
            statement.setString(1,Tno);
            int result = statement.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean InitializeTeacher(String Tno) {
        try {
            PreparedStatement statement = con.prepareStatement(InitializeSQL);
            statement.setString(1,Tno);
            int result = statement.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
