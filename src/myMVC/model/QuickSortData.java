package myMVC.model;

import java.util.Arrays;

public class QuickSortData {
    public enum Type{
        Default,
        NearlyOrdered,      // 近乎有序
        Identical           // 几乎一致数据
    }
    private int[] numbers;
    public int l, r;
    public boolean[] fixedPivots;   // 是否当过标定点
    public int curPivot;    // 当前处理的标定点
    public int curElement;  // 当前处理的元素

    // 保证之前排序编译通过
    public int orderIndex = -1;
    public int currentIndex = -1;
    public int currentMinIndex = -1;
    public int currentCompareIndex = -1;

    // 双路快排
    public int curL,curR;

    public QuickSortData(int N,int randomBound){
        this(N, randomBound, Type.Default);
    }
    public QuickSortData(int N, int randomBound,Type dataType){

        numbers = new int[N];
        fixedPivots = new boolean[N];

        /*for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*randomBound) + 1;
            fixedPivots[i] = false;
        }*/
        // 生成一致的数组
        int lBound = 1;
        int rBound = randomBound;
        if(dataType == Type.Identical){
            int avgNumber = (lBound + rBound) / 2;
            lBound = avgNumber;
            rBound = avgNumber;
        }
        for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*(rBound-lBound+1)) + lBound;
            fixedPivots[i] = false;
        }

        // 生成近乎有序数组
        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01*N);
            for(int i = 0 ; i < swapTime; i ++){
                int a = (int)(Math.random()*N);
                int b = (int)(Math.random()*N);
                swap(a, b);
            }
        }

    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        }
        return numbers[index];
    }

    public void swap(int i, int j) {
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        }
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
