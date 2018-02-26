package arithmetic;

public class Shuffle {
    /**
     * 每一个元素与随机位置进行交换
     * @param arr 需要 乱序的数组
     * @param n   乱序数组总个数
     *  结论 : 有偏的
     */
    public static void shuffle(int [] arr,int n){
        for(int i = 0;i < n;i++){
            int x = (int)(Math.random() * n);
            swap(arr,i,x);
        }
    }

    /**
     * 只循环m次，每次只将这些类和其他随机位置交换
     * @param arr 需要 乱序的数组
     * @param n   乱序数组总个数
     * @param m   雷的个数
     * 结论 ：更加有偏
     */
    public static void shuffle(int [] arr,int n,int m){

        for(int i = 0;i < m;i++){
            int x = (int)(Math.random() * n);
            swap(arr,i,x);
        }
    }

    // 正序插入
    public static void fisherYates(int [] arr,int n){
        // 每次都是从[i,n-1) 取元素
        for(int i = 0;i < n;i++){
            int x = (int)(Math.random() * (n - i)) + i;
            swap(arr,i,x);
        }
    }

    // 倒序插入
    public static void fisherYates2(int [] arr,int n){
        // 每次都是从[0,i+1) 取元素
        for(int i = n-1;i >=0;i--){
            int x = (int)(Math.random() * (i+1));
            swap(arr,i,x);
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
