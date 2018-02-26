package testArithmetic;

import arithmetic.Shuffle;

// shuffle实验
public class ShuffleExp {
    private int N;
    private int n,m;
    /**
     *
     * @param N  总实验次数
     * @param n  小n个格子雷区
     * @param m  m 个雷
     */
    public ShuffleExp(int N,int n,int m){
        if(N <= 0){
            throw new IllegalArgumentException("N must be larger than zero!");
        }
        if(n < m){
            throw new IllegalArgumentException("n must be larger than m");
        }
        this.N = N;
        this.m = m;
        this.n = n;
    }

    // 进行N次实验模拟
    public void run(){
        // 统计每个雷区 雷出现的频次
        int  frequency[] = new int[n];
        // 在n这块空间运行
        int arr[] = new int[n];
        for(int i = 0;i < N;i++){
            reset(arr);
            // Shuffle.shuffle(arr,n);
            // Shuffle.shuffle(arr,n,m);
            Shuffle.fisherYates2(arr,n);
            for(int j = 0;j < n;j++){
                frequency[j] += arr[j];     // arr[j] = 1 表示有雷
            }
        }
        // 输出每个雷区出现雷的频率
        for(int i = 0;i < n;i++){
            System.out.println(i + " : " + (double)frequency[i]/N);
        }
    }

    // 初始化数据
    private void reset(int[] arr){
        // 前m个元素都是雷
        for(int i = 0;i < m;i++){
            arr[i] = 1;
        }
        for(int i = m;i < n;i++){
            arr[i] = 0;
        }
    }

    // 理想情况 每个格子有雷概率 m/n
    public static void main(String[] args) {
        int N = 10000000;
        int n = 10;
        int m = 5;
        ShuffleExp shuffleExp = new ShuffleExp(N,n,m);
        shuffleExp.run();
    }
}
