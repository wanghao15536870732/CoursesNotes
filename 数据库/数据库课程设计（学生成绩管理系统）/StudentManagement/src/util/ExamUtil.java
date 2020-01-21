package util;

import java.sql.*;

public class ExamUtil {
    private static Connection connect;
    private static PreparedStatement statement = null;
    private static  String[][] result;

    static {
        connect = DBHelper.getConnect();
    }

    private static String insertExamSQL = "insert into exam(Eno,eclno,Ecno,Eposition,Etime,Endtime)" +
            "values (?,?,?,?,?,?);";

    private static String getExamSQL = "select exam.Eclno,exam.Ecno,course.Cname,exam.Eposition,exam.Etime,exam.Endtime " +
            "from student,exam,course " +
            "where student.Sclass = exam.Eclno and " +
            "exam.Ecno = course.Cno and " +
            "student.Sno=?;";

    public static String[][] getMyExam(String Sno) {
        result = new String[0][];
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connect.prepareStatement(getExamSQL);
            statement.setString(1, Sno);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            result = new String[count][6];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = resultSet.getString(3);
                result[count][3] = resultSet.getString(4);
                result[count][4] = resultSet.getString(5);
                result[count][5] = resultSet.getString(6);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean InsertExam(String Eno, String ECLno, String ECno, String EPosition, Date Etime, Date EndTime) {
        try {
            statement = connect.prepareStatement(insertExamSQL);
            statement.setString(1, Eno);
            statement.setString(2, ECLno);
            statement.setString(3, ECno);
            statement.setString(4, EPosition);
            statement.setDate(5, Etime);
            statement.setDate(6, EndTime);
            System.out.println(Etime);
            System.out.println(EPosition);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String selectByClassSQL = "select distinct Eno,ECLno,CLmajor,ECno,Cname,Eposition,Etime,Endtime " +
            "from exam,class,course,classcourse " +
            "where exam.EClno = class.CLno and " +
            "exam.Ecno = course.Cno and classcourse.Cno = course.Cno and " +
            "Eclno=? and classcourse.Cyear=? and classcourse.Cterm=?;";

    private static String selectBySubjectSQL = "select Eno,ECLno,CLmajor,ECno,Cname,Eposition,Etime,Endtime " +
            "from exam,class,course " +
            "where exam.EClno = class.CLno and " +
            "exam.Ecno = course.Cno and " +
            "ECLno=? and Cno=?;";

    public static String[][] getExamByClass(String eClass,String eYear,String eTerm) {
        result = new String[0][];
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connect.prepareStatement(selectByClassSQL);
            statement.setString(1, eClass);
            statement.setString(2, eYear);
            statement.setString(3, eTerm);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            result = new String[count][9];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = resultSet.getString(3);
                result[count][3] = resultSet.getString(4);
                result[count][4] = resultSet.getString(5);
                result[count][5] = resultSet.getString(6);
                result[count][6] = resultSet.getString(7);
                result[count][7] = resultSet.getString(8);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[][] getExamBySubject(String eSubject,String CLass) {
        String[][] result = new String[0][];
        ResultSet resultSet = null;
        int count = 0;
        try {
            PreparedStatement statement = connect.prepareStatement(selectBySubjectSQL);
            statement.setString(1, CLass);
            statement.setString(2, ClassUtil.getCourseByCname(eSubject));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            result = new String[count][9];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = resultSet.getString(3);
                result[count][3] = resultSet.getString(4);
                result[count][4] = resultSet.getString(5);
                result[count][5] = resultSet.getString(6);
                result[count][6] = resultSet.getString(7);
                result[count][7] = resultSet.getString(8);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
