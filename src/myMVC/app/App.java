package myMVC.app;

import myMVC.controller.AlgoVisualizer;
import myMVC.controller.MonteCarioExperiment;

public class App {
    public static void main(String[] args) {
        // simulateViaInterface();
        simulateViaCommandLine();
    }

    private static void simulateViaInterface() {
        int screenWidth = 800;
        int screenHeight = 800;
        int N = 20000;
        AlgoVisualizer vis = new AlgoVisualizer(screenWidth,screenHeight,N);
    }

    private static void simulateViaCommandLine(){
        int squareSide = 800;
        int N = 100000000;
        MonteCarioExperiment mce = new MonteCarioExperiment(squareSide,N);
        mce.setOutputInterval(100000);
        mce.run();
    }
}
