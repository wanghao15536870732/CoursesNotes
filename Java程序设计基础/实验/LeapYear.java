package newTest;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个年份：");
        long year = scanner.nextLong();
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            System.out.println(year + "是闰年！");
        }else {
            System.out.println(year + "不是闰年！");
        }
    }
}
