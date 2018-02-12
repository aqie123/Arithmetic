package myMVC.app;

import arithmetic.InsertionSort;
import myMVC.controller.AlgoVisualizer;
import myMVC.model.SelectionSortData;

public class App {
    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 800;
        int N = 100;
        // 选择排序可视化
        select(screenWidth, screenHeight, N);

        // 插入排序可视化
        // insert(screenWidth, screenHeight, N);
    }

    private static void insert(int screenWidth, int screenHeight, int n) {
        AlgoVisualizer vis = new AlgoVisualizer(screenWidth,screenHeight, n,
                SelectionSortData.Type.NearlyOrdered);
    }

    private static void select(int screenWidth, int screenHeight, int n) {
        AlgoVisualizer vis = new AlgoVisualizer(screenWidth,screenHeight, n);
    }
}
