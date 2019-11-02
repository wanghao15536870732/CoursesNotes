package newTest;

public class Course {
    private String courseName;  //课程名
    private int courseId; //编号
    private int coursePreId;  //先修课号

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCoursePreId() {
        return coursePreId;
    }

    public void setCoursePreId(int coursePreId) {
        this.coursePreId = coursePreId;
    }

    @Override
    public String toString() {
        return "课程名：" + getCourseName() + "," +
                "编号：" + getCourseId() + "," +
                "先修课号：" + getCoursePreId() ;
    }
}
