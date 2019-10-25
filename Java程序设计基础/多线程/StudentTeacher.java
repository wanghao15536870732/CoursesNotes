package cn.edu.nuc;

public class StudentTeacher {
    public static void main(String[] args) throws InterruptedException {
        Student student;

        Teacher teacher = new Teacher();
        teacher.setName("熊老师");
        teacher.start();

        for(int i = 0;i < 10;i ++){
            student = new Student();
            student.setName("王浩" + i);
            student.start();
            teacher.setData(student,false);
            Thread.sleep(1000 * 5);
        }
    }
}
