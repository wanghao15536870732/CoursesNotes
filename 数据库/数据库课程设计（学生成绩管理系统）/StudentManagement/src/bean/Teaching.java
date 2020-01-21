package bean;

public class Teaching {
    private String Tno,Cno,Cposition,Cweek,Ctime1,Ctime2,Cyear,Cterm;

    public Teaching(String tno, String cno, String cposition, String cweek, String ctime1, String ctime2, String cyear, String cterm) {
        Tno = tno;
        Cno = cno;
        Cposition = cposition;
        Cweek = cweek;
        Ctime1 = ctime1;
        Ctime2 = ctime2;
        Cyear = cyear;
        Cterm = cterm;
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getCposition() {
        return Cposition;
    }

    public void setCposition(String cposition) {
        Cposition = cposition;
    }

    public String getCweek() {
        return Cweek;
    }

    public void setCweek(String cweek) {
        Cweek = cweek;
    }

    public String getCtime1() {
        return Ctime1;
    }

    public void setCtime1(String ctime1) {
        Ctime1 = ctime1;
    }

    public String getCtime2() {
        return Ctime2;
    }

    public void setCtime2(String ctime2) {
        Ctime2 = ctime2;
    }

    public String getCyear() {
        return Cyear;
    }

    public void setCyear(String cyear) {
        Cyear = cyear;
    }

    public String getCterm() {
        return Cterm;
    }

    public void setCterm(String cterm) {
        Cterm = cterm;
    }
}
