package storeHome;

public class StoreHome {  //仓库类，相当于公共缓冲区
    private int bottom = 0;  //队尾
    private int top = 0;  //队首
    //仓库大小，公共缓冲区总共可以存放6件产品
    private Product[] products = new Product[6];

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    /**
     * 生产产品
     */
    public synchronized void push(Product product){
        if(top == products.length){  //如果仓库已满，等待消费
            notify();  //通知消费者消费产品
            try{
                System.out.println("仓库已满，等待消费者消费...");
                wait();
            }catch (InterruptedException e){
                System.out.println("由于其他原因暂停生产...");
            }
        }
        //仓库未满，将生产的产品放入缓冲区
        products[top] = product;
        //仓库中的产品数量加+1
        top ++;
    }

    /**
     * 消费产品
     */
    public synchronized Product pop(){
        Product product = null;
        while (top == bottom){ //仓库已空，无法消费产品
            notify(); //通知生产者生产产品
            try{
                System.out.println("仓库以空，正在等待生产...");
                wait();
            }catch (InterruptedException e){
                System.out.println("由于其他原因暂停消费...");
            }
        }
        //仓库未空，等待消费
        top --;  //top向下走
        product = products[top]; //取出产品
        products[top] = null;  //消耗产品
        return product;  //返回取出的产品
    }
}
