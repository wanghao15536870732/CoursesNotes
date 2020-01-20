package handle;

import bean.Student;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandleStu {
    private static String searchSQL = "select student.* ,class.CLdept,class.CLmajor from student,class " +
            "where student.Sclass = class.CLno and " +
            "student.Sno=?";

    private static String updateInfoSQL = "update student set Sname=?,Ssex=?,Sage=?,Stel=?,Semail=? where Sno=?";

    private static String updatePass = "update student set Spassword=? " +
            "where Sno=?;";

    private static String getAllStuSQL = "select * from student where Sclass=?;";
    private static String getStuBySnoSQL = "select * from student where Sno=?;";
    private static String InsertStudentSQL = "insert into student(Sno,Sname,Ssex,Sage,Sclass,Spassword,Stel,Semail) " +
            "value(?,?,?,?,?,?,?,?);";
    private static Connection connection;
    private HandleSQL handleSQL;
    private static PreparedStatement statement;

    /*
     * -1:用户名不存在(用户名输入不正确)
     * 0:用户密码错误
     * 1:用户名密码均正确
     */
    public HandleStu() {
        connection = DBHelper.getConnect();
    }

    public Connection getCon() {
        return connection;
    }

    public int checkStu(Student student) throws SQLException {
        handleSQL = new HandleSQL(connection);

        //查找学生表里面所有  学号为用户输入的用户名的学生 , 并添加到列表里
        ArrayList<Student> students = handleSQL.findStuBySQL(
                "select Sno,Spassword from student where Sno='"
                        + student.getSno() + "'"
        );
        if (students.size() < 1) //该用户名在学生表里面不存在
            return -1;
        if (student.getSpassword().equals(students.get(0).getSpassword())) //用户名存在,且密码匹配正确
            return 1;
        else     //用户名存在,但密码不匹配
            return 0;
    }

    private static Student getStu(PreparedStatement statement) {
        try {
            Student student = new Student();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student.setSno(resultSet.getString(1));
                student.setSname(resultSet.getString(2));
                student.setSsex(resultSet.getString(3));
                student.setSage(resultSet.getInt(4));
                student.setSclass(resultSet.getString(5));
                student.setSpassword(resultSet.getString(6));
                student.setStel(resultSet.getString(7));
                student.setSemil(resultSet.getString(8));
                student.setSdept(resultSet.getString(9));
                student.setSdomin(resultSet.getString(10));
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Student getStuInfo(String sno) {
        try {
            statement = connection.prepareStatement(searchSQL);
            statement.setString(1, sno);
            Student student = getStu(statement);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[][] getAll(PreparedStatement statement) {
        String[][] result = new String[0][];
        ResultSet resultSet = null;
        int count = 0;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            result = new String[count][8];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(6);
                result[count][2] = resultSet.getString(2);
                result[count][3] = resultSet.getString(3);
                result[count][4] = resultSet.getString(4);
                result[count][5] = resultSet.getString(5);
                result[count][6] = resultSet.getString(7);
                result[count][7] = resultSet.getString(8);
                count++;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[][] getAllStudent(String CLno) {
        String[][] result = new String[0][];
        try {
            statement = connection.prepareStatement(getAllStuSQL);
            statement.setString(1, CLno);
            result = getAll(statement);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[][] getStudentBySno(String Sno) {
        String[][] result = new String[0][];
        try {
            statement = connection.prepareStatement(getStuBySnoSQL);
            statement.setString(1, Sno);
            result = getAll(statement);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static boolean updateStuInfo(Student student) {
        try {
            statement = connection.prepareStatement(updateInfoSQL);
            statement.setString(1, student.getSname());
            statement.setString(2, student.getSsex());
            statement.setInt(3, student.getSage());
            statement.setString(4, student.getStel());
            statement.setString(5, student.getSemil());
            statement.setString(6, student.getSno());
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateStuPass(String Sno, String password) {
        try {
            statement = connection.prepareStatement(updatePass);
            statement.setString(1, password);
            statement.setString(2, Sno);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean InsertStudent(String Sno, String Sname, String Ssex, String Sage,
                                        String Sclass, String Spassword, String Stel, String Semail) {
        try {
            PreparedStatement statement = connection.prepareStatement(InsertStudentSQL);
            statement.setString(1, Sno);
            statement.setString(2, Sname);
            statement.setString(3, Ssex);
            statement.setString(4, Sage);
            statement.setString(5, Sclass);
            statement.setString(6, Spassword);
            statement.setString(7, Stel);
            statement.setString(8, Semail);
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
