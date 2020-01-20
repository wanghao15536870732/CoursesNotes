package handle;

import bean.Admin;
import bean.Student;
import bean.Teacher;

import java.sql.*;
import java.util.ArrayList;

public class HandleSQL {
    private static Connection con = null;
    private Statement smt = null;
    private static PreparedStatement preparedsmt;
    private static ResultSet rs;

    private String CourseInfoSQL = "select Cname,Cweek,Cposition,Ctime1,Ctime2,Tname,Cperiod,Ccredit,Cattribute " +
            "from student,classcourse,teaching,teacher,course " +
            "where Sno = ? and Sclass=CLno and classcourse.Cno=teaching.Cno " +
            "and teaching.Tno=teacher.Tno and teaching.Cno=course.Cno ";

    //构造方法
    public HandleSQL(Connection con) {
        this.con = con;
    }

    public ArrayList<Student> findStuBySQL(String sql) throws SQLException {
        if (smt == null)
            smt = con.createStatement();
        ArrayList<Student> students = new ArrayList<Student>();
        ResultSet rs = smt.executeQuery(sql); //查询结果集
        while (rs.next()) {
            Student student = new Student();
            student.setSno(rs.getString("Sno"));
            student.setSpassword(rs.getString("Spassword"));
            students.add(student);
        }
        return students;
    }

    public ArrayList<Teacher> findTeaBySQL(String sql) throws SQLException {
        if (smt == null)
            smt = con.createStatement();
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        ResultSet rs = smt.executeQuery(sql); //查询结果集
        while (rs.next()) {
            Teacher teacher = new Teacher();
            teacher.setTno(rs.getString("Tno"));
            teacher.setTpassword(rs.getString("Tpassword"));
            teachers.add(teacher);
        }
        return teachers;
    }

    public ArrayList<Admin> findAdnBySQL(String sql) throws SQLException {
        if (smt == null)
            smt = con.createStatement();
        ArrayList<Admin> admins = new ArrayList<Admin>();
        ResultSet rs = smt.executeQuery(sql); //查询结果集
        while (rs.next()) {
            Admin admin = new Admin();
            admin.setMno(rs.getString("Mno"));
            admin.setMpassword(rs.getString("Mpassword"));
            admins.add(admin);
        }
        return admins;
    }

    public String[][] getCosInfoBySQL(String Sno, String Cyear, String Cterm) {
        String[][] result = new String[0][];
        if (Cyear.equals("") && Cterm.equals("")) {
            return result;
        }
        if (!Cyear.equals(""))
            CourseInfoSQL += " and classcourse.Cyear = '" + Cyear + "' ";
        if (!Cterm.equals(""))
            CourseInfoSQL += " and classcourse.Cterm = '" + Cterm + "' ";
        int count = 0;
        try {
            preparedsmt = con.prepareStatement(CourseInfoSQL);
            preparedsmt.setString(1, Sno);
            rs = preparedsmt.executeQuery();
            while (rs.next())
                count++;
            result = new String[count][9];
            rs = preparedsmt.executeQuery();
            count = 0;
            while (rs.next()) {
                result[count][0] = rs.getString(1);
                result[count][1] = rs.getString(2);
                result[count][2] = rs.getString(3);
                result[count][3] = rs.getString(4);
                result[count][4] = rs.getString(5);
                result[count][5] = rs.getString(6);
                result[count][6] = rs.getString(7);
                result[count][7] = rs.getString(8);
                result[count][8] = rs.getString(9);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //修改教师号为Tno的老师的基本信息
    public static boolean SetTeaInfo(String newname, String newsex, String newage, String newdept,
                                     String newdegree, String newtitle, String newduty, String newtel,
                                     String newemail, String tno) {
        String UpdateTeaPwd = "update teacher set Tname = ?,Tsex=?,Tage=?,Tdept=?,Tdegree=?," +
                "Ttitle=?,Tduty=?,Ttel=?,Temail=? where Tno = ?";
        int result;
        try {
            preparedsmt = con.prepareStatement(UpdateTeaPwd);
            preparedsmt.setString(1, newname);
            preparedsmt.setString(2, newsex);
            preparedsmt.setString(3, newage);
            preparedsmt.setString(4, newdept);
            preparedsmt.setString(5, newdegree);
            preparedsmt.setString(6, newtitle);
            preparedsmt.setString(7, newduty);
            preparedsmt.setString(8, newtel);
            preparedsmt.setString(9, newemail);
            preparedsmt.setString(10, tno);
            result = preparedsmt.executeUpdate();
            if (result > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找教师号为Tno的教师基本信息
    public static Teacher getTeaInfoBySQL(String Tno) {
        Teacher teacher = new Teacher();
        String TeaInfoSQL = "select * from teacher where Tno = ?";
        try {
            preparedsmt = con.prepareStatement(TeaInfoSQL);
            preparedsmt.setString(1,Tno);
            rs= preparedsmt.executeQuery();
            while(rs.next()) {
                teacher.setTno(rs.getString(1));
                teacher.setTname(rs.getString(2));
                teacher.setTsex(rs.getString(3));
                teacher.setTage(rs.getString(4));
                teacher.setTdept(rs.getString(5));
                teacher.setTdegree(rs.getString(6));
                teacher.setTtitle(rs.getString(7));
                teacher.setTduty(rs.getString(8));
                teacher.setTtel(rs.getString(9));
                teacher.setTemail(rs.getString(10));
                teacher.setTpassword(rs.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    //修改教师号为Tno的老师的密码
    public static boolean Setpwd(String newpwd,String tno){
        String UpdateTeaPwd = "update teacher set Tpassword = ? where Tno = ?";
        int result;
        try {
            preparedsmt = con.prepareStatement(UpdateTeaPwd);
            preparedsmt.setString(1,newpwd);
            preparedsmt.setString(2,tno);
            result = preparedsmt.executeUpdate();
            if(result>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找所有的课程名并返回
    public static String[] getAllCourseName() {
        String selectCourseNameSQL = "select Cname from course";
        String[] coursenames = new String[0];
        int count = 0;
        try{
            preparedsmt = con.prepareStatement(selectCourseNameSQL);
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                count ++;
            }
            coursenames = new String[count+1];
            coursenames[0] = "请选择课程";
            count = 1;
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                coursenames[count] = rs.getString(1);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursenames;
    }

    //查找课程名为Cname的课程号并返回
    public static String getCno(String Cname){
        String selectCnoSQL = "select Cno from course where Cname=?; " ;
        String result = null ;
        try{
            preparedsmt = con.prepareStatement(selectCnoSQL);
            preparedsmt.setString(1,Cname);
            rs= preparedsmt.executeQuery();
            while (rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //根据Sno,Cno,Grade信息录入到成绩表里
    public static  boolean InsertMessage(String Sno,String Cno,Double Grade){
        String insertSQL = "insert into csgrade (Sno,Cno,Grade) " + "values (?,?,?);";
        try{
            preparedsmt = con.prepareStatement(insertSQL);
            preparedsmt.setString(1,Sno);
            preparedsmt.setString(2,Cno);
            preparedsmt.setDouble(3,Grade);
            int result = preparedsmt.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找教师号为Tno的所教的所有课程并返回
    public static String[] getAllTeachingCourse(String Tno) {
        String selectCourseSQL = "select Cname " +
                "from teaching,course " +
                "where Tno=? and teaching.Cno=course.Cno";
        String[] courselist = new String[0];
        int count = 0;
        try {
            preparedsmt = con.prepareStatement(selectCourseSQL);
            preparedsmt.setString(1, Tno);
            rs = preparedsmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            courselist = new String[count + 1];
            courselist[0] = "请选择课程";
            count = 1;
            rs = preparedsmt.executeQuery();
            while (rs.next()) {
                courselist[count] = rs.getString(1);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courselist;
    }

    //查找课程号为Cno的考试信息并返回
    public static String[][] getExamMessage(String Cno) {
        String selectExamMessage = "select Cname,Eposition,Etime,Endtime,Eclno "+
                "from exam,course "+
                "where Ecno=? and Ecno=Cno;";
        String[][] exammessage = new String[0][];
        int count = 0;
        try{
            preparedsmt = con.prepareStatement(selectExamMessage);
            System.out.println(Cno);
            preparedsmt.setString(1, Cno);
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                count ++;
            }
            exammessage = new String[count][5];
            count = 0;
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                exammessage[count][0] = rs.getString(1);
                exammessage[count][1] = rs.getString(2);
                exammessage[count][2] = rs.getString(3);
                exammessage[count][3] = rs.getString(4);
                exammessage[count][4] = rs.getString(5);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exammessage;
    }

    //查找教师号为Tno的所教的所有班C级号并返回
    public static String[] getAllTeachingCLno(String Tno) {
        String selectLnoSQL = "select distinct Clno " +
                "from teaching,classcourse " +
                "where Tno=? and teaching.Cno=classcourse.Cno";
        String[] classlist = new String[0];
        int count = 0;
        try{
            preparedsmt = con.prepareStatement(selectLnoSQL);
            preparedsmt.setString(1, Tno);
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                count ++;
            }
            classlist = new String[count+1];
            classlist[0] = "请选择班级";
            count = 1;
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                classlist[count] = rs.getString(1);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classlist;
    }

    //查找某个班级的所有学生成绩
    public static String[][] getClassGrade(String sClass,String cName,String selectClassGradeSQL) {
        String[][] result = new String[0][];
        int count = 0;
        try{
            preparedsmt = con.prepareStatement(selectClassGradeSQL);
            preparedsmt.setString(1,sClass);
            preparedsmt.setString(2,cName);
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                count ++;
            }
            result = new String[count][6];
            count = 0;
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                result[count][0] = rs.getString(1);
                result[count][1] = rs.getString(2);
                result[count][2] = rs.getString(3);
                result[count][3] = rs.getString(4);
                result[count][4] = rs.getString(5);
                result[count][5] = rs.getString(6);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //判断学号为Sno的学生在student表里面存不存在
    public static boolean JudgeStu(String Sno){
        ArrayList<String> snos = new ArrayList<>();
        String selectStu = "select Sno from student where Sno=?;";
        try {
            preparedsmt = con.prepareStatement(selectStu);
            preparedsmt.setString(1,Sno);
            rs = preparedsmt.executeQuery();
            while(rs.next())
            {
                String sno = rs.getString(1);
                snos.add(sno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(snos.size()<1)
            return false;
        else
            return true;
    }

    //查找学号为Sno的学生的所有课程成绩
    public static String[][] getStuGrade(String sno) {
        String selectStuGradeSQL = "select student.Sno,student.Sname,course.Cno,course.Cname,csgrade.Grade,course.Ccredit " +
                "from Student,Course,Csgrade " +
                "where Student.Sno=Csgrade.Sno and " +
                "Csgrade.Cno=Course.Cno and " +
                "Student.Sno=?;";
        String[][] result = new String[0][];
        int count = 0;
        try{
            preparedsmt = con.prepareStatement(selectStuGradeSQL);
            preparedsmt.setString(1,sno);
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                count ++;
            }
            result = new String[count][6];
            count = 0;
            rs = preparedsmt.executeQuery();
            while (rs.next()){
                result[count][0] = rs.getString(1);
                result[count][1] = rs.getString(2);
                result[count][2] = rs.getString(3);
                result[count][3] = rs.getString(4);
                result[count][4] = rs.getString(5);
                result[count][5] = rs.getString(6);
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //查找姓名为Sname的学生的学号并返回
    public static String getSno(String Sname) {
        String sno = "";
        String selectSnoSQL = "select Sno from student where Sname = ? ;";
        try {
            preparedsmt = con.prepareStatement(selectSnoSQL);
            preparedsmt.setString(1,Sname);
            rs = preparedsmt.executeQuery();
            while(rs.next())
            {
                sno = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sno;
    }

    //根据Sno,Cno修改成绩
    public static  boolean UpdateGrade(Double grade,String Sno,String Cno){
        String updategradeSQL = "update csgrade " +
                "set Grade =? " +
                "where Sno =? and Cno =?;";
        try{
            preparedsmt = con.prepareStatement(updategradeSQL);
            preparedsmt.setDouble(1,grade);
            preparedsmt.setString(2,Sno);
            preparedsmt.setString(3,Cno);
            int result = preparedsmt.executeUpdate();
            if(result > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找学号为Sno的学生姓名并返回
    public static String getStuName(String Sno) {
        String stuname = "";
        String selectStuNameSQL = "select Sname from student where Sno = ? ;";
        try {
            preparedsmt = con.prepareStatement(selectStuNameSQL);
            preparedsmt.setString(1,Sno);
            rs = preparedsmt.executeQuery();
            while(rs.next())
            {
                stuname = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuname;
    }

    //查找教师号为Tno的教师姓名并返回
    public static String getTeaName(String Tno) {
        String teaname = "";
        String selectTeaNameSQL = "select Tname from teacher where Tno = ? ;";
        try {
            preparedsmt = con.prepareStatement(selectTeaNameSQL);
            preparedsmt.setString(1,Tno);
            rs = preparedsmt.executeQuery();
            while(rs.next())
            {
                teaname = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teaname;
    }

    //查找管理员编号为Mno的管理员姓名并返回
    public static String getAdnName(String Mno) {
        String adnname = "";
        String selectAdnNameSQL = "select Mname from manager where Mno = ? ;";
        try {
            preparedsmt = con.prepareStatement(selectAdnNameSQL);
            preparedsmt.setString(1,Mno);
            rs = preparedsmt.executeQuery();
            while(rs.next())
            {
                adnname = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adnname;
    }

    //返回某班级某课程的平均成绩
    public static String getClassAVG(String sClass,String Cname,String selectAVGGrade) {
        String result = null;
        try {
            preparedsmt = con.prepareStatement(selectAVGGrade);
            preparedsmt.setString(1,sClass);
            preparedsmt.setString(2,Cname);
            rs = preparedsmt.executeQuery();
            while(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
