package myMVC.controller;

import myMVC.model.Circle;
import myMVC.model.SelectionSortData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 10;
    // 数据
    private SelectionSortData data;
    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量


    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // todo 初始化数据
        data = new SelectionSortData(N,screenHeight);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // todo 编写自己的动画逻辑
    private void run() {
        frame.render(data);     // 加载数据
        AlgoVisHelper.pause(DELAY);
        for(int i = 0;i < data.N()-1;i++){
            int minIndex = i;
            for(int j = i + 1;j < data.N();j++){
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                if(data.get(j) < data.get(minIndex)){
                    minIndex = j;
                    /*frame.render(data);
                    AlgoVisHelper.pause(DELAY);*/
                }
            }
            data.swap(i,minIndex);
            frame.render(data);
            AlgoVisHelper.pause(DELAY);
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            // todo 键盘释放事件
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
           // todo 鼠标按压事件
        }
    }
}
