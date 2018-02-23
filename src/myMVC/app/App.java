package myMVC.app;

import arithmetic.InsertionSort;
import myMVC.controller.AlgoVisualizer;
import myMVC.model.QuickSortData;
import myMVC.model.SelectionSortData;

public class App {
    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 800;
        int N = 100;
        // 默认
        // AlgoVisualizer vis = new AlgoVisualizer(screenWidth, screenHeight, N);

        // 快速排序( 针对近乎有序数组)
        /*AlgoVisualizer vis = new AlgoVisualizer(screenWidth, screenHeight, N,
                QuickSortData.Type.NearlyOrdered);*/

        // 快排，近乎一致数组
        AlgoVisualizer vis = new AlgoVisualizer(screenWidth, screenHeight, N,
                QuickSortData.Type.Identical);
    }
}
