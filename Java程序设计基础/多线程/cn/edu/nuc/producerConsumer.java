package cn.edu.nuc;

public class producerConsumer {
    public static void main(String[] args) {
       MyData available = new MyData(-1);
       MyData product = new MyData(-1);
       Object object = new Object();
       Producer producer = new Producer(available,product,object);
       Consumer consumer = new Consumer(available,product,object);
       producer.start();
       consumer.start();
    }
}
