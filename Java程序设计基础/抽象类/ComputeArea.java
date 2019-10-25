package cn.edu.nuc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ComputeArea {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the name of class：");
        String className = scanner.next();
        while (className != null) {
            System.out.println("the name of the class is :" + className);
            Class c = Class.forName(className);

            // 创建一个跟类className相关联的class类对象
            Shape obj = (Shape) c.newInstance();
            Field[] fs = c.getDeclaredFields();// 返回该类的所有成员变量
            Method[] ms = c.getDeclaredMethods();// 返回该类的所有方法(不包括构造方法)

            for (Field f : fs) {
                System.out.println(f.toString());
            }

            for (Method m : ms) {
                System.out.println(m.toString());
            }

            for (Field f : fs) {   //遍历整个成员变量
                System.out.println("please input value of " + f.getName() + ":");
                float value = scanner.nextFloat();
                String methodName = Field2Method.toString(f.getName());
                Method m = c.getDeclaredMethod(methodName, float.class);
                m.invoke(obj, value);
            }

            System.out.println("the area is :" + obj.ColArea());
            System.out.println("please input the name of class：");

            if (scanner.hasNext()) {
                className = scanner.next();
            } else {
                className = null;
            }
        }
    }
}