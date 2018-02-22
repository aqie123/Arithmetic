package arithmetic;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    private MergeSort(){}

    // [1 ... mid] [mid+1,....n]
    private static void merge(Comparable[] arr,int l,int mid,int r){
        Comparable[] aux = Arrays.copyOfRange(arr,l,r+1);
        // 初始化，i指向左半部分的起始索引位置l；
        // j指向右半部分起始索引位置mid+1
        int i = l,j = mid + 1;
        for(int k = l;k <= r;k++){
            if(i > mid){    // 左半部分处理完
                arr[k] = aux[j - l];
                j++;
            }else if (j > r){   // 右半部分元素处理完
                arr[k] = aux[i - l];
                i++;
            }else if( aux[i-l].compareTo(aux[j-l]) < 0 ){  // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i-l]; i ++;
            }else {     // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    // 递归使用归并排序,对arr[l...r]的范围进行排序
    private static void sort(Comparable[] arr, int l, int r, int depth){
        System.out.print(repeatCharacters('-',depth * 2));
        System.out.println("Deal with [ " + l + " , " + r + " ]");
        if(l >= r){
            return;
        }
        int mid = (l + r) / 2;
        sort(arr,l,mid,depth + 1);
        sort(arr,mid + 1,r,depth + 1);
        merge(arr,l,mid,r);
    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr, 0, n-1, 0);
    }

    private static String repeatCharacters(char character,int length){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0;i < length;i++){
            sb.append(character);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[8];
        for(int i = 0;i < 8;i++){
            arr[i] = new Integer(8 - i);
        }
        MergeSort.sort(arr);
    }
}
