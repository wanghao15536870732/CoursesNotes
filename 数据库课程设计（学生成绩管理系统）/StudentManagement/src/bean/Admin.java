package bean;

public class Admin {
    private String Mno;  //管理员号
    private String Mname;  //姓名
    private String Msex;  //性别
    private String Mage;  //年龄
    private String Mpassword;  //密码
    private String Mtel;  //电话号码

    public Admin(String no,String password) {
        Mno = no;
        Mpassword = password;
    }

    public Admin() {
        Mno = "";
        Mname = "";
        Msex = "";
        Mage = "";
        Mpassword = "";
        Mtel = "";
    }

    public String getMno() {
        return Mno;
    }

    public void setMno(String mno) {
        Mno = mno;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getMsex() {
        return Msex;
    }

    public void setMsex(String msex) {
        Msex = msex;
    }

    public String getMage() {
        return Mage;
    }

    public void setMage(String mage) {
        Mage = mage;
    }

    public String getMpassword() {
        return Mpassword;
    }

    public void setMpassword(String mpassword) {
        Mpassword = mpassword;
    }

    public String getMtel() {
        return Mtel;
    }

    public void setMtel(String mtel) {
        Mtel = mtel;
    }

    public Admin(String no,String name,String sex,String age,String password,String tel) {
        Mno = no;
        Mname = name;
        Msex = sex;
        Mage = age;
        Mpassword = password;
        Mtel = tel;
    }
}
