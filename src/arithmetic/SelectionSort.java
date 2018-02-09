package arithmetic;

/**
 * 选择排序 : 指定位置依次和后面互换
 */
public class SelectionSort {
    public static int[] sort(int[] arr){
        int N = arr.length;
        for (int i = 0;i < N - 1;i++){
            int min = i;
            for(int j = i + 1;j < N;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            Tools.exchange(arr,i,min);
        }
        return arr;
    }


}
