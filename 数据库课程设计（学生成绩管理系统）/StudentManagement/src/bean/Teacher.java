package bean;

public class Teacher {
    private String Tno;  //教师编号
    private String Tname;  //姓名
    private String Tsex;  //性别
    private String Tdept; //系别
    private String Tdegree;  //学历
    private String Ttitle;  //职称
    private String Tduty; //职务

    public String getTemail() {
        return Temail;
    }

    public void setTemail(String temail) {
        Temail = temail;
    }

    private String Ttel;  //电话
    private String Tage;  //年龄
    private String Temail;
    private String Tpassword;

    public Teacher(String tno, String tpassword) {
        Tno = tno;
        Tpassword = tpassword;
    }

    public Teacher() {
        this.Tno = "";
        this.Tname = "";
        this.Tsex = "";
        this.Tdept = "";
        this.Tdegree = "";
        this.Ttitle = "";
        this.Tduty = "";
        this.Ttel = "";
        this.Tage = "";
        this.Tpassword = "";
    }

    public Teacher(String Tno, String Tname, String Tsex, String Tdept, String Tdegree,
                   String Ttitle, String Tduty, String Ttel, String Tage, String Tpassword) {
        this.Tno = Tno;
        this.Tname = Tname;
        this.Tsex = Tsex;
        this.Tdept = Tdept;
        this.Tdegree = Tdegree;
        this.Ttitle = Ttitle;
        this.Tduty = Tduty;
        this.Ttel = Ttel;
        this.Tage = Tage;
        this.Tpassword = Tpassword;
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTsex() {
        return Tsex;
    }

    public void setTsex(String tsex) {
        Tsex = tsex;
    }

    public String getTdept() {
        return Tdept;
    }

    public void setTdept(String tdept) {
        Tdept = tdept;
    }

    public String getTdegree() {
        return Tdegree;
    }

    public void setTdegree(String tdegree) {
        Tdegree = tdegree;
    }

    public String getTtitle() {
        return Ttitle;
    }

    public void setTtitle(String ttitle) {
        Ttitle = ttitle;
    }

    public String getTduty() {
        return Tduty;
    }

    public void setTduty(String tduty) {
        Tduty = tduty;
    }

    public String getTtel() {
        return Ttel;
    }

    public void setTtel(String ttel) {
        Ttel = ttel;
    }

    public String getTage() {
        return Tage;
    }

    public void setTage(String tage) {
        Tage = tage;
    }

    public String getTpassword() {
        return Tpassword;
    }

    public void setTpassword(String tpassword) {
        Tpassword = tpassword;
    }
}
