package myMVC.controller;

import myMVC.model.CircleData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 40;

    private CircleData data;

    // 视图层
    private AlgoFrame frame;

    // todo 设置自定义变量

    public AlgoVisualizer(int screenWidth,int screenHeight){
        // todo 初始化数据
        int R = Math.min(screenWidth,screenHeight)/2 - 2;
        data = new CircleData(screenWidth/2, screenHeight/2,R,R/2,2);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer",
                    screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 编写自动求解过程
    private void run(){
       setData();
    }

    // 设置要绘制的数据,添加点击坐标
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
