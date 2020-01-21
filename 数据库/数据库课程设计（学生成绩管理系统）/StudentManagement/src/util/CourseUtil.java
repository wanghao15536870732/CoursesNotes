package util;
import bean.Lesson;
import bean.Teaching;
import java.sql.*;
import java.util.ArrayList;
public class CourseUtil {
    private static Connection connect;
    private static PreparedStatement statement = null;
    private static String insertSQL = "insert into course (Cno,Cname,Cperiod,Ccredit,Cattribute) " +
            "values (?,?,?,?,?);";

    private static String insertTeachingSQL = "insert into teaching (Tno,Cno,Cposition,Cweek,Ctime1,Ctime2,Cyear,Cterm) " +
            "values (?,?,?,?,?,?,?,?);";

    private static String searchNotArrSQL = "select * from course " +
            "where course.Cno not in (select teaching.Cno from teaching);";

    private static String searchArrSQL = "select * from course " +
            "where course.Cno in (select teaching.Cno from teaching);";

    private static String courseTableSQL = "select course.Cname,lesson.Lweek,lesson.Ltime,lesson.Lposition " +
            "from course,lesson " +
            "where course.Cno=lesson.Lcno;";

    private static String searchArrByDept = "select distinct course.*,class.CLdept,classcourse.Cyear,classcourse.Cterm " +
            "from course,class,classcourse " +
            "where course.Cno = classcourse.Cno and " +
            "class.CLno = classcourse.CLno and " +
            "class.CLdept=? and course.Cno in (select teaching.Cno from teaching);";

    private static String searchNotArrByDept = "select distinct course.*,class.CLdept,classcourse.Cyear,classcourse.Cterm " +
            "from course,class,classcourse " +
            "where course.Cno = classcourse.Cno and " +
            "class.CLno = classcourse.CLno and " +
            "class.CLdept=? and course.Cno not in (select teaching.Cno from teaching);";

    private static String searchArrByMajor = "select distinct course.*,class.CLmajor,classcourse.Cyear,classcourse.Cterm " +
            "from course,class,classcourse " +
            "where course.Cno = classcourse.Cno and " +
            "class.CLno = classcourse.CLno and " +
            "class.CLdept=? and class.CLmajor=? and course.Cno in (select teaching.Cno from teaching);";

    private static String searchNotArrByMajor = "select distinct course.*,class.CLmajor,classcourse.Cyear,classcourse.Cterm " +
            "from course,class,classcourse " +
            "where course.Cno = classcourse.Cno and " +
            "class.CLno = classcourse.CLno and " +
            "class.CLdept=? and class.CLmajor=? and course.Cno not in (select teaching.Cno from teaching);";

    private static String deleteNotArrCourse = "delete from course where Cno=? ";
    private static String deleteArrCourse = "delete from teaching where teaching.Cno=?";

    static {
        connect = DBHelper.getConnect();
    }

    //添加课程
    public static boolean InsertCourse(String Con, String Cname, int Cperiod, float Ccredit, String Cattribute) {
        try {
            statement = connect.prepareStatement(insertSQL);
            statement.setString(1, Con);
            statement.setString(2, Cname);
            statement.setInt(3, Cperiod);
            statement.setFloat(4, Ccredit);
            statement.setString(5, Cattribute);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查询课程
    public static String[][] getCourses(boolean arr){
        String[][] result = new String[0][];
        ResultSet resultSet  = null;
        int count = 0;
        try{
            if (arr){
                statement = connect.prepareStatement(searchArrSQL);
            }else {
                statement = connect.prepareStatement(searchNotArrSQL);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count ++;
            }
            result = new String[count][5];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = String.valueOf(resultSet.getInt(3));
                result[count][3] = String.valueOf(resultSet.getFloat(4));
                result[count][4] = resultSet.getString(5);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //通过学院查询课程
    public static String[][] getCourseByDept(String dept,boolean arr){
        String[][] result = new String[0][];
        ResultSet resultSet  = null;
        int count = 0;
        try{
            if (arr){
                statement = connect.prepareStatement(searchArrByDept);
            }else {
                statement = connect.prepareStatement(searchNotArrByDept);
            }
            statement.setString(1,dept);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count ++;
            }
            result = new String[count][8];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = String.valueOf(resultSet.getInt(3));
                result[count][3] = String.valueOf(resultSet.getDouble(4));
                result[count][4] = resultSet.getString(5);
                result[count][5] = resultSet.getString(6);
                result[count][6] = resultSet.getString(7);
                result[count][7] = resultSet.getString(8);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //通过学院和专业查询课程
    public static String[][] getCourseByMajor(String dept,String major,boolean arr){
        String[][] result = new String[0][];
        ResultSet resultSet  = null;
        int count = 0;
        try{
            if (arr){
                statement = connect.prepareStatement(searchArrByMajor);
            }else {
                statement = connect.prepareStatement(searchNotArrByMajor);
            }
            statement.setString(1,dept);
            statement.setString(2,major);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count ++;
            }
            result = new String[count][8];
            count = 0;
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result[count][0] = resultSet.getString(1);
                result[count][1] = resultSet.getString(2);
                result[count][2] = String.valueOf(resultSet.getInt(3));
                result[count][3] = String.valueOf(resultSet.getDouble(4));
                result[count][4] = resultSet.getString(5);
                result[count][5] = resultSet.getString(6);
                result[count][6] = resultSet.getString(7);
                result[count][7] = resultSet.getString(8);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //查询课程 构成课表
    public static ArrayList<Lesson> getCourseTable() {
        ArrayList<Lesson> lessonList = new ArrayList();
        try {
            PreparedStatement statement = connect.prepareStatement(courseTableSQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setLname(resultSet.getString(1));
                lesson.setLweek(resultSet.getInt(2));
                lesson.setLtime(resultSet.getInt(3));
                lesson.setLposition(resultSet.getString(4));
                lessonList.add(lesson);
            }
            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonList;
    }

    //添加课程安排
    public static boolean insertCourse(Teaching teaching) {
        try {
            PreparedStatement statement = connect.prepareStatement(insertTeachingSQL);
            statement.setString(1, teaching.getTno());
            statement.setString(2, teaching.getCno());
            statement.setString(3, teaching.getCposition());
            statement.setString(4, teaching.getCweek());
            statement.setString(5, teaching.getCtime1());
            statement.setString(6, teaching.getCtime2());
            statement.setString(7, teaching.getCyear());
            statement.setString(8, teaching.getCterm());
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteCourse(String Cno,boolean arr){
        try {
            if(arr){
                statement = connect.prepareStatement(deleteArrCourse);
            }else {
                statement = connect.prepareStatement(deleteNotArrCourse);
            }
            statement.setString(1,Cno);
            int result = statement.executeUpdate();
            if (result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
