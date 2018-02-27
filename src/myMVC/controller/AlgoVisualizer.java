package myMVC.controller;

import myMVC.model.GameData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 5;

    private GameData data;

    // 视图层
    private AlgoFrame frame;

    // todo 设置自定义变量
    private static int blockSide = 80;  // 每个单元格边长

    public AlgoVisualizer(String filename){
        // todo 初始化数据
        data = new GameData(filename);
        int screenWidth = data.getM() * blockSide;
        int screenHeight = data.getN() * blockSide;
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver",
                    screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
       setData();
    }

    // 设置要绘制的数据
    private void setData(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent event){
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent event){
        }
    }
}
