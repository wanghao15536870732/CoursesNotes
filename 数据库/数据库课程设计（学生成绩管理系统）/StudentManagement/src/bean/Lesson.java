package bean;

public class Lesson {
    private String Lname; //课程名
    private int Lweek; //上课周
    private int Ltime; //上课时间
    private String Lposition;

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public int getLweek() {
        return Lweek;
    }

    public void setLweek(int lweek) {
        Lweek = lweek;
    }

    public int getLtime() {
        return Ltime;
    }

    public void setLtime(int ltime) {
        Ltime = ltime;
    }

    public String getLposition() {
        return Lposition;
    }

    public void setLposition(String lposition) {
        Lposition = lposition;
    }
}
