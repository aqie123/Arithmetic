package arithmetic;

/**
 * 插入排序：插入数据和之前数据比较
 *  对位置 i 处的元素进行排序时，[0,i-1]上的元素一定是已经有序的了
 */
public class InsertionSort {        // 优化后
    private InsertionSort(){}

    public static void sort(Comparable[] arr){
        int n = arr.length;
        for(int i = 0;i < n;i++){
            // 寻找元素arr[i] 合适的插入位置
            Comparable e = arr[i];
            int j = i;
            for (;j > 0 && arr[j - 1].compareTo(e) > 0;j--){
                arr[j] = arr[j - 1];
                arr[j] = e;
            }
        }
    }
}
