package myMVC.controller;

import myMVC.model.Circle;
import myMVC.view.AlgoFrame;
import mySwing.tools.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    // 数据 存储所有circle信息
    private Circle[] circles;
    // 视图层
    private AlgoFrame frame;
    // 是否开启动画
    private boolean isAnimated = true;

    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // 初始化小球数组数据
        _initData(screenWidth, screenHeight, N);

        // 初始化视图frame
        _initFrame(screenWidth, screenHeight);
    }

    private void _initFrame(int screenWidth, int screenHeight) {
        EventQueue.invokeLater(() -> {
            // 注意 ： 初始化类成员变量
            frame = new AlgoFrame("Welcome",screenWidth,screenHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(()->{
                run(screenWidth, screenHeight);
            }).start();
        });
    }

    private void _initData(int screenWidth, int screenHeight, int N) {
        circles = new Circle[N];
        int R = 50;
        for(int i = 0;i<N;i++){
            int x = (int) Math.random()*(screenWidth-2*R) + R;
            int y = (int) Math.random()*(screenHeight-2*R) + R;
            int vx = (int)(Math.random()*15)-5;
            int vy = (int)(Math.random()*15)-5;
            circles[i] = new Circle(x,y,R,vx,vy);
        }
    }

    // 小球运动逻辑
    private void run(int screenWidth, int screenHeight) {
        while(true){
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            // 更新数据
            if(isAnimated){
                for(Circle circle : circles){
                    circle.move(0,0,screenWidth,screenHeight);
                }
            }
        }
    }

    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() == ' '){
                isAnimated = !isAnimated;
            }
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            // 获取menuBar相应高度
            e.translatePoint(0,-(frame.getBounds().height -frame.getCanvasHeight()));
            for (Circle circle : circles){
                if(circle.contain(e.getPoint())){
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }
}
