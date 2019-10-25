package cn.edu.nuc;

public class Square extends Shape {

    private float side;

    public Square(){

    }

    public Square(float side) {
        this.side = side;
    }

    public void setSide(float side) {
        this.side = side;
    }

    @Override
    float ColArea() {  //重写父类Shape的抽象方法
        return side * side;  //计算正方形的面积
    }
}
