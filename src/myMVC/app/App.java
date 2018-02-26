package myMVC.app;

import myMVC.controller.AlgoVisualizer;

public class App {
    public static void main(String[] args) {
        // 绘制地图
        int N = 20;
        int M = 20;
        int MineNumber = 50;
        AlgoVisualizer vis = new AlgoVisualizer(N,M,MineNumber);
    }
}
