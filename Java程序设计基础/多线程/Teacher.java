package cn.edu.nuc;

public class Teacher extends Thread {
    private Thread student;
    private boolean noneStudent = true;
    private Object object = new Object();

    public void setData(Thread student,boolean noneStudent){ //构造方法
        synchronized (object){   //使用同一个object,synchronized修饰语句块
            this.student = student;
            this.noneStudent = noneStudent;
        }
    }

    @Override
    public void run() {
        while (true){
            synchronized (object){
                if(!noneStudent){
                    for(int i = 0;i < 3;i ++){
                        try {
                            Thread.sleep(1000);
                            System.out.println("上课");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(this.getName() + "非常生气！");
                    student.interrupt();
                    noneStudent = true;
                }
            }
        }
    }
}
