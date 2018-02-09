package myMVC.model;

/**
 *  生成要排序的数组
 */
public class SelectionSortData {
    private int[] numbers;

    // 有序的索引, [0,orderIndex) 有序的
    public int orderIndex = -1;

    // 当前找到的最小值索引
    public int currentMinIndex = -1;

    // 当前正在比较的元素索引
    public int currentCompareIndex = -1;
    public SelectionSortData(int N, int randomBound){
        numbers = new int[N];
        for(int i = 0;i < N;i++){
            numbers[i] = (int)(Math.random()*randomBound) + 1;  // [0,1)
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
