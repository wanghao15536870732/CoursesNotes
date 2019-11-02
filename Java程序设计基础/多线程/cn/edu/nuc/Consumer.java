package cn.edu.nuc;

public class Consumer extends Thread{

    private MyData available;
    private MyData product;
    private Object object;

    Consumer(MyData available,MyData product,Object object){
        this.available = available;
        this.product = product;
        this.object = object;
    }

    private void get(){
        synchronized (object){
            while (available.getData() == -1){
                try{
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            available.setData(-1);
            System.out.println("Consumer gets: " + product.getData());
            object.notifyAll();
        }
    }

    @Override
    public void run() {
        for(int i = 1;i <= 6;i ++){
            get();
        }
    }
}
