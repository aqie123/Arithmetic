package test;

import javax.swing.*;
import java.awt.*;

public class Hello {
    public static void main(String[] args) {
        // 事件纷发线程
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("First");
            frame.setSize(500,400);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}
