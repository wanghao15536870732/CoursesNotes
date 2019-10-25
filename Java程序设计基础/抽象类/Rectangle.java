package cn.edu.nuc;

public class Rectangle extends Shape {

    private float width;
    private float height;

    public Rectangle(){

    }

    public Rectangle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    float ColArea() {  //重写父类Shape的抽象方法
        return width * height;  //计算矩形的面积
    }
}
