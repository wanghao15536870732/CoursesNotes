package cn.edu.nuc;

public class Producer extends Thread{

    private MyData available;
    private MyData product;
    private Object object;

    Producer(MyData available,MyData product,Object object){
        this.available = available;
        this.product = product;
        this.object = object;
    }

    private void put(int i){
        synchronized (object){
            while (available.getData() == 1){
                try{
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            product.setData(i);
            available.setData(1);
            System.out.println("Producer producers: " + product.getData());
            object.notifyAll();
        }
    }

    @Override
    public void run() {
        for(int i = 1;i <= 6;i ++){
            put(i);
        }
    }
}
