package myMVC.controller;

import myMVC.model.Circle;
import myMVC.model.MonteCarloPiData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 40;
    // 数据 存储所有circle信息
    private MonteCarloPiData data;
    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量
    private int N;

    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // todo 初始化数据
        if(screenHeight != screenWidth){
            throw new IllegalArgumentException("This Demo must be run in square ");
        }
        this.N = N;
        Circle circle = new Circle(screenWidth/2, screenHeight/2, screenWidth/2);
        data = new MonteCarloPiData(circle);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", screenWidth, screenHeight);
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
        for(int i = 0;i< N;i++){
            if(i % 100 == 0) {
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                System.out.println(data.simulatePI());
            }
            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());
            data.addPoint(new Point(x, y));
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
