package storeHome;

public class Solution {
    public static void main(String[] args) {
        StoreHome storeHome = new StoreHome();  //新建公共缓冲区
        Producer producer = new Producer("生产者",storeHome);
        Consumer consumer = new Consumer("消费者",storeHome);
        Thread producerThread = new Thread(producer);  //生产者线程
        Thread consumerThread = new Thread(consumer);  //消费者线程
        producerThread.start();
        consumerThread.start();
    }
}
