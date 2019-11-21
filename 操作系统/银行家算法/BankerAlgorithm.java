package myBank;

public class BankerAlgorithm {
    static final int n = 5; //进程的数目
    static final int m = 3; //最大需求量

    static int[] available = {3,3,2}; //现有可利用资源，详见课本P121

    static int[][] max = {
            {7,5,3},
            {3,2,2},
            {9,0,2},
            {2,2,2},
            {4,3,3}
    }; //最大需求矩阵，详见课本P122

    static int[][] allocation = {
            {0,1,0},
            {2,0,0},
            {3,0,2},
            {2,1,1},
            {0,0,2}
    }; //已分配的资源

    static int[][] need = {
            {7,4,3},
            {1,2,2},
            {6,0,0},
            {0,1,1},
            {4,3,1}
    }; //需求矩阵

    static int[] request = new int[m]; //请求资源矩阵

    /**
     * 安全算法检查，判断进程分配后系统是否处于安全状态
     * @return 如果安全 返回 true,否则返回 false
     */
    private static boolean checkSecurityAlgorithm(){
        System.out.println("----安全算法检查----");
        int[] work = new int[m];  //工作量向量

        //安全算法开始时，work = available
        for (int i = 0; i < work.length; i++) {
            work[i] = available[i];
        }

        //finish向量 表示系统是否有足够的资源分配给进程
        boolean[] finish = new boolean[n];
        for (int j = 0; j < finish.length; j++) {
            finish[j] = false;
        }

        for (int i = 0; i < n; i++) {
            //满足条件:
            // ① Finish[i] = false;
            // ② Need[i,j] <= Work[j];
            if(compare(need[i],work) && !finish[i]){
                System.out.println("进程" + i + "获得资源，可顺利执行");
                System.out.printf("%-18s","work:");
                printOneMa(work);
                System.out.printf("%-18s","need:");
                printOneMa(need[i]);
                System.out.printf("%-18s","allocation:");
                printOneMa(allocation[i]);
                work = oneMatrixAdd(work,allocation[i]);
                System.out.printf("%-18s","work + allocation:");
                printOneMa(work);
                System.out.println();
                // Finish[i] = true;
                finish[i] = true;
                // go to step2 ,返回重新开始,由于最后i要++,所以设为-1
                if (i == n - 1) {
                    i = -1;
                }
            }
        }

        int count = 0;  //统计finish为true的个数
        for (int i = 0; i < finish.length; i++) {
            if (finish[i]) { //Finish[i] == true
                count++;
            }
        }
        return count == n;
    }

    /**
     *
     * @param request 请求向量
     * @param i 进程的编号
     */
    private static void bankerAlgorithm(int request[],int i){
        //打印当前的request,need
        System.out.println("进程" + i + "请求资源，开始执行银行家算法");
        System.out.print("request：");
        printOneMa(request);
        System.out.print("need" + i + "：");
        printOneMa(need[i]);
        //首先满足条件 Request < Need
        if (compare(request,need[i])){
            System.out.println("满足条件①：request <= need");
            System.out.print("request：");
            printOneMa(request);
            System.out.print("available" + i + "：");
            printOneMa(available);
            if(compare(request,available)){
                System.out.println("满足条件②：request <= available");
                //步骤③：修改Available、Allocation和Need向量
                //修改Available = 修改Available - Request[i];
                available = oneMatrixSub(available,request);
                //修改Need[i] = Need[i] - Request[i];
                need[i] = oneMatrixSub(need[i],request);
                //修改Allocation[i] = Allocation[i] - Request[i];
                allocation[i] = oneMatrixAdd(allocation[i],request);
                //步骤④：进行安全检查算法
                if(checkSecurityAlgorithm()){
                    System.out.println("就可以找到一个安全序列,系统是安全的,请允许求,将资源分配给" + i + "进程");
                    System.out.print("当前的available：");
                    printOneMa(available);
                }else {
                    System.out.println("此时系统处于不安全状态，不允许资源分配给" + i + "进程");
                    //将数据恢复到变化之前
                    available = oneMatrixAdd(available,request);
                    need[i] = oneMatrixAdd(need[i],request);
                    allocation[i] = oneMatrixSub(allocation[i],request);
                    System.out.println("进程" + i + "处于等待状态");
                    System.out.print("当前的available：");
                    printOneMa(available);
                }
            }else {
                System.out.println("Request > Available");
                System.out.println("当前系统尚无足够资源，进程" + i + "处于等待状态");
            }
        }else {
            System.out.println("Request > Need");
            System.out.println("进程" + i + "申请的资源已经超出其需求资源");
        }
    }

    /**
     * 一维向量减法
     * @param first 一维向量
     * @param second 一维向量
     * @return 一维向量
     */
    private static int[] oneMatrixSub(int[] first,int[] second){
        int[] result = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] - second[i];
        }
        return result;
    }

    /**
     * 一维向量加法
     * @param first 一维向量
     * @param second 一维向量
     * @return 一维向量
     */
    private static int[] oneMatrixAdd(int[] first,int[] second){
        int[] result = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] + second[i];
        }
        return result;
    }

    /**
     * 判断一个向量对应位置的数是否都大于第二个向量
     * @param first 第一个向量
     * @param second 第二个向量
     * @return 第一个小于第二个 返回 true ,否则 返回f alse
     */
    private static boolean compare(int[] first,int[] second){
        int count = 0;
        for (int i = 0; i < first.length; i++) {
            if(first[i] <= second[i]){
                count ++;
            }
        }
        return count == first.length;
    }

    /**
     * 打印一维向量
     * @param temp 一维向量
     */
    private static void printOneMa(int[] temp) {
        for (int value : temp) {
            System.out.print("	");
            System.out.print(value);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println("判断t0时刻系统的安全性");
        if (checkSecurityAlgorithm()){
            System.out.println("当前t0时刻是安全的");
        }else {
            System.out.println("当前t0时刻是不安全的");
        }
        //P1请求资源,Request(1,0,2);
        System.out.println("***************************************************************");
        request[0] = 1;
        request[1] = 0;
        request[2] = 2;
        bankerAlgorithm(request,1);

        //P4请求资源,Request(3,3,0);
        System.out.println("***************************************************************");
        request[0] = 3;
        request[1] = 3;
        request[2] = 0;
        bankerAlgorithm(request,4);

        //P0请求资源,Request(0,2,0);
        System.out.println("***************************************************************");
        request[0] = 0;
        request[1] = 2;
        request[2] = 0;
        bankerAlgorithm(request,0);

        //P0请求资源,Request(0,1,0);
        System.out.println("***************************************************************");
        request[0] = 0;
        request[1] = 1;
        request[2] = 0;
        bankerAlgorithm(request,0);
    }
}
