package myMVC.controller;

import myMVC.model.Circle;
import myMVC.model.MonteCarloPiData;

import java.awt.*;

public class MonteCarioExperiment {
    private int squareSide;
    private int N;
    private int outputInterval = 100;

    public MonteCarioExperiment(int squareSide,int N){
        if(squareSide <= 0 || N <= 0){
            throw new IllegalArgumentException("squareSide and N must large than zero!");
        }
        this.squareSide = squareSide;
        this.N = N;
    }
    public void setOutputInterval(int interval){
        if(interval < 0){
            throw new IllegalArgumentException("interval must be large than zero");
        }
        this.outputInterval = interval;
    }

    public void run(){
        Circle circle = new Circle(squareSide/2,squareSide/2,squareSide/2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for (int i = 0; i< N;i++){
            if(i % outputInterval == 0){
                System.out.println(data.simulatePI());
            }
            int x = (int)(Math.random() * squareSide);
            int y = (int)(Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }
}
