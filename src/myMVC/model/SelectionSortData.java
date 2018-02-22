package myMVC.model;

import java.util.Arrays;

/**
 *  生成要排序的数组
 */
public class SelectionSortData implements SortData{
    private int[] numbers;

    // 枚举型变量测试 近乎有序数组插入排序
    public  enum Type{
        Default,
        NearlyOrdered
    }

    // 有序的索引, [0,orderIndex) 有序的,前闭后开 (插入)
    public int orderIndex = -1;
    public int currentIndex = -1;

    // 当前找到的最小值索引 (选择)
    public int currentMinIndex = -1;

    // 当前正在比较的元素索引
    public int currentCompareIndex = -1;

    public SelectionSortData(int N, int randomBound){
        this(N,randomBound,Type.Default);
    }

    // 选择排序,插入排序通用的排序数据
    public SelectionSortData(int N,int randomBound,Type dataType){
        numbers = new int[N];
        for(int i = 0;i < N;i++){
            numbers[i] = (int)(Math.random()*randomBound) + 1;  // [0,1)
        }
        if (dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.02 * N);
            for (int i = 0;i < swapTime;i++){
                int a = (int)(Math.random() * N);
                int b = (int)(Math.random() * N);
                swap(a,b);
            }
        }
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if(index < 0 || index >= numbers.length){
            throw new IllegalArgumentException("Invalid index to access sort Data");
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
