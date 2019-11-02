package storeHome;

public class Producer implements Runnable {

    private String producerName;  //生产者名字
    private StoreHome storeHome;  //仓库，即缓冲区

    public Producer(String producerName,StoreHome storeHome){
        this.producerName = producerName;
        this.storeHome = storeHome;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    @Override
    public void run() {
        int i = 0;  //从0号产品开始
        while (true){
            i ++;
            Product product = new Product(i);  //实例化产品
            storeHome.push(product);  //将产品放到缓冲区上
            //输出生产者生产产品的位置
            System.out.print(getProducerName() + "在位置" + storeHome.getTop());
            System.out.println("生产了" + product);  //输出生产的产品信息
            //缓冲区剩余的产品个数
            System.out.println("缓冲区还有" + storeHome.getTop() + "个产品");
            try{
                Thread.sleep(2000);  //线程延迟2000ms
            }catch (InterruptedException e){
                return;
            }
        }
    }
}
