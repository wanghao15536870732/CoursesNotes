package cn.edu.nuc;

public class Triangle extends Shape {

    private float width;
    private float height;

    public Triangle(){

    }

    public Triangle(float width, float height) {
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
        return 0.5f * width * height;  //计算三角形的面积
    }
}
