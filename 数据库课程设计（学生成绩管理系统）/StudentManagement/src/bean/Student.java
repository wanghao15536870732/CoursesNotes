package bean;

public class Student {
    private String Sno; //学号
    private String Sname;  //姓名
    protected String Ssex;  //性别
    private int Sage;  //年龄
    private String Sbirthday;  //出生日期
    private String Sdept;  //所在院系
    private String Sdomin; //专业
    private String Sclass;  //所在班级
    protected String Spassword; //密码
    private String Stel;
    private String Semil;

    public String getStel() {
        return Stel;
    }

    public void setStel(String stel) {
        Stel = stel;
    }

    public String getSemil() {
        return Semil;
    }

    public void setSemil(String semil) {
        Semil = semil;
    }

    public Student(String sno, String spassword) {
        Sno = sno;
        Spassword = spassword;
    }

    public Student() {
        Sno = "";
        Sname = "";
        Ssex = "";
        Sage = 0;
        Sbirthday = "";
        Sdept = "";
        Sdomin = "";
        Sclass = "";
        Spassword = "";
    }

    public Student(String no, String name, String sex, int age, String dept, String domin,
                   String sclass,String Stel,String Semil) {
        Sno = no;
        Sname = name;
        Ssex = sex;
        Sage = age;
        Sdept = dept;
        Sdomin = domin;
        Sclass = sclass;
        this.Stel = Stel;
        this.Semil = Semil;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public int getSage() {
        return Sage;
    }

    public void setSage(int sage) {
        Sage = sage;
    }

    public String getSbirthday() {
        return Sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        Sbirthday = sbirthday;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getSdomin() {
        return Sdomin;
    }

    public void setSdomin(String sdomin) {
        Sdomin = sdomin;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSpassword() {
        return Spassword;
    }

    public void setSpassword(String spassword) {
        Spassword = spassword;
    }
}
