package arithmetic;

import java.util.Arrays;

/**
 * 冒泡排序: (相邻数值交换)
 */
public class BubbleSort {
    public static int[] sort(int[] arr){
        int length = arr.length;
        for (int i = 0;i < length - 1; i++){
            boolean flag = false;
            for(int j = 0;j<length - 1 - i;j++){
                // 从小到大
                if(arr[j] > arr[j+1]){
                    Tools.exchange(arr,j,j+1);
                    flag = true;
                }
            }
            if(!flag){      // 未发生交换
                return arr;
            }
        }
        return arr;
    }

}
