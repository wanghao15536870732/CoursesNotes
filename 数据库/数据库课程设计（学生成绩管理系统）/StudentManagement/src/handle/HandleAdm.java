package handle;

import bean.Admin;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandleAdm {

    private static Connection con;
    HandleSQL handleSQL;

    private static String managerSQL = "select Mno,Mname,Msex,Mage,Mtel,Memail,Mpassword " +
            "from manager " + "where Mno=?";
    private static String updateSQL = "update manager " + "set Mname=?,Msex=?,Mage=?,Mtel=?,Memail=? " + "where Mno=?;";
    private static String setpasswordSQL = "update manager " + "set Mpassword=? " + "where Mno=?;";
    private static String oldpasswordSQL = "select Mpassword " + "from manager " + "where Mno=?;";
    private static String trueSQL = "update gradesystem " + "set Mgrade=1;";
    private static String falseSQL = "update gradesystem " + "set Mgrade=0;";

    /*
     * -1:用户名不存在(用户名输入不正确)
     * 0:用户密码错误
     * 1:用户名密码均正确
     */
    public HandleAdm() {
        con = DBHelper.getConnect();
    }

    public static boolean UpdateManager(String Mno, String Mname, String Msex, String Mage, String Memail, String Mtel) {
        String data[] = showManager(Mno);
        try {
            while (data != null) {
                PreparedStatement statement = con.prepareStatement(updateSQL);
                statement.setString(1, Mname);
                statement.setString(2, Msex);
                statement.setString(3, Mage);
                statement.setString(4, Mtel);
                statement.setString(5, Memail);
                statement.setString(6, Mno);
                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] showManager(String Mno) {
        String[] result = new String[0];
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = con.prepareStatement(managerSQL);
            statement.setString(1, Mno);
            result = new String[7];
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result[0] = resultSet.getString(1);
                result[1] = resultSet.getString(2);
                result[2] = resultSet.getString(3);
                result[3] = resultSet.getString(4);
                result[4] = resultSet.getString(5);
                result[5] = resultSet.getString(6);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int checkAdn(Admin admin) throws SQLException {
        handleSQL = new HandleSQL(con);

        //查找管理员表里面所有  管理员号为用户输入的用户名的管理员,并添加到列表里
        ArrayList<Admin> admins = handleSQL.findAdnBySQL("select Mno,Mpassword from manager where Mno='" + admin.getMno() + "'");

        if (admins.size() < 1)//该用户名在管理员表里面不存在
            return -1;

        if (admin.getMpassword().equals(admins.get(0).getMpassword())) //用户名存在,且密码匹配正确
            return 1;
        else     //用户名存在,但密码不匹配
            return 0;
    }

    public static boolean SetPasswordManager(String Mno, String Mpassword) {
        try {
            PreparedStatement statement = con.prepareStatement(setpasswordSQL);
            statement.setString(1, Mpassword);
            statement.setString(2, Mno);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String OldPasswordManager(String Mno) {
        String password = null;
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = con.prepareStatement(oldpasswordSQL);
            statement.setString(1, Mno);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }


    public static boolean trueMGrade() {
        try {
            PreparedStatement statement = con.prepareStatement(trueSQL);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean falseMGrade() {
        try {
            PreparedStatement statement = con.prepareStatement(falseSQL);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
