package myMVC.app;

import arithmetic.InsertionSort;
import myMVC.controller.AlgoVisualizer;
import myMVC.model.SelectionSortData;

public class App {
    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 800;
        int N = 100;
        // 归并排序
        AlgoVisualizer vis = new AlgoVisualizer(screenWidth, screenHeight, N);
    }
}
