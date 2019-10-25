package cn.edu.nuc;

public class Student extends Thread {

    @Override
    public void run() {
        try {
            System.out.println(this.getName() + "正在睡觉，不听课");
            Thread.sleep(1000 * 60 * 60);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println(this.getName() + "被老师叫醒了");
        }
        System.out.println(this.getName() + "正在听课");
    }
}
