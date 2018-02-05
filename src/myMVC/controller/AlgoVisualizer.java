package myMVC.controller;

import myMVC.model.Circle;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;


public class AlgoVisualizer {
    // 定义延长时间
    private static int DELAY = 40;
    // 数据
    private int[] money;
    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量

    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // todo 初始化数据
        money = new int[100];
        for (int i = 0;i<money.length;i++){
            money[i] = 100;     // 每个人初始一百元
        }

        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("如何面对这个残酷的世界!", screenWidth, screenHeight);
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
        while (true){
            // 内置排序算法
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);
            for(int k = 0;k<50;k++){        // 加快循环次数
                for (int i = 0;i<money.length;i++){
                    // 每个人不能欠钱
                    // if(money[i] > 0){
                        // 第i个人给第j个人随机钱
                        int j = (int)(Math.random() * money.length);
                        money[i] -= 1;
                        money[j] += 1;
                    // }
                }
            }

        }
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
