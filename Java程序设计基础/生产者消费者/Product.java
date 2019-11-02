package storeHome;

public class Product {  //产品类
    private int productId = 0; //产品Id

    public Product(int productId){
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {  //覆写默认方法toString()
        return "" + productId;
    }
}
