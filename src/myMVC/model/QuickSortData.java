package myMVC.model;

public class QuickSortData {
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

    public QuickSortData(int N, int randomBound){

        numbers = new int[N];
        fixedPivots = new boolean[N];

        for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*randomBound) + 1;
            fixedPivots[i] = false;
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
