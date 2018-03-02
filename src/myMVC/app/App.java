package myMVC.app;

import myMVC.controller.AlgoVisualizer;

public class App {
    public static void main(String[] args) {
        // 分形图
        int maxDepth = 9;
        double splitAngle = 60.0;
        int sceneWidth = 800;
        int sceneHeight = 800;
        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth,sceneHeight,maxDepth,splitAngle);
    }
}
