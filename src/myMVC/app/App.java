package myMVC.app;

import myMVC.controller.AlgoVisualizer;

public class App {
    public static void main(String[] args) {
        // 模拟方块
        String fileName = "src/resources/boston_09.txt";
        AlgoVisualizer vis = new AlgoVisualizer(fileName);
    }
}
