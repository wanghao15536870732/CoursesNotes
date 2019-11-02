package newTest;
import java.util.Scanner;
public class Array {
    public static void main(String[] args) {
        int [][]array = new int[4][5];
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入数组的各个元素：");
        //给数组赋值
        for(int i = 0;i < 4;i ++){
            for(int j = 0;j < 5;j ++){
                array[i][j] = scanner.nextInt();
            }
        }
        //输出数组
        System.out.println("你输入的数组为：");
        for(int i = 0;i < 4;i ++){
            for(int j = 0;j < 5;j ++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
