package myMVC.app;

import myMVC.controller.AlgoVisualizer;

public class App {
    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 800;
        int N = 10;
        AlgoVisualizer vis = new AlgoVisualizer(screenWidth,screenHeight,N);
    }
}
