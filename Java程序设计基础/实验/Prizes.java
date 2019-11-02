package newTest;

import java.util.Scanner;

public class Prizes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入兑奖编号：");
        String input = scanner.next();
        while (!input.equals("exit")){
            switch (input){
                case "1":
                    System.out.println("恭喜你，得到奖一等奖");
                    break;
                case "2":
                    System.out.println("恭喜你，得到二等奖" );
                    break;
                case "3":
                    System.out.println("恭喜你，得到奖品三等奖");
                    break;
                default:
                    System.out.println("没有奖品给你！");
                    break;
            }
            System.out.print("请输入兑奖编号：");
            input = scanner.next();
        }
        System.out.println("推出兑奖系统");
    }
}
