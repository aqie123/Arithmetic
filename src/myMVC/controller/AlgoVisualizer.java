package myMVC.controller;

import myMVC.model.FractalData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 40;

    private FractalData data;

    // 视图层
    private AlgoFrame frame;

    // todo 设置自定义变量

    // 传入最大深度,保持窗口不变
    public AlgoVisualizer(int maxDepth){
        // todo 初始化数据
        data = new FractalData(maxDepth);
        int screenWidth = (int)Math.pow(2,maxDepth);
        int screenHeight = (int)Math.pow(2,maxDepth);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Vicsek Gractal 分形图绘制",
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
       setData(data.getDepth());
    }

    // 设置要绘制的数据,添加点击坐标
    private void setData(int depth){
        if(depth >= 0){
            data.setDepth(depth);
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent event){
            System.out.println("Key released:" + event);
            if(event.getKeyChar() >= '0' && event.getKeyChar() <= '9'){
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent event){

        }
    }
}
