package Junit;

import mySwing.entity.AlgoFrame;

import java.awt.*;

public class Test {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome",500,500);
        });
    }
}
