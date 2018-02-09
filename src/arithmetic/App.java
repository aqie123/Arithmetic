package arithmetic;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[] arr = {4,3,0,5,8,6};
        // 冒泡排序
        arr = BubbleSort.sort(arr);
        // 选择排序
        // arr = SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
