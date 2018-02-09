package arithmetic;

public class Tools {
    public static void exchange(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
