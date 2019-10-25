package cn.edu.nuc;

public class Field2Method {
    public static String toString(String fieldName){
        String methodName = "" ;
        methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }
}
