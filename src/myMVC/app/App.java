package myMVC.app;

import myMVC.controller.AlgoVisualizer;

public class App {
    public static void main(String[] args) {
        // 分形图
        int maxDepth = 9;
        int side = 300;
        AlgoVisualizer vis = new AlgoVisualizer(maxDepth,side);
    }
}
