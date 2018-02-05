package myMVC.controller;

import myMVC.model.Circle;
import myMVC.view.AlgoFrame;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.awt.*;

@Repository
public class MyVisualizer extends AlgoVisualizer {
    @Resource
    private AlgoFrame myFrame;
    @Resource
    private Circle[] circles;
    // 是否开启动画
    private boolean isAnimated = true;

    public MyVisualizer(int screenWidth,int screenHeight,int N){
        _initData(screenWidth,screenHeight,N);
        _initFrame(screenWidth,screenHeight);
    }
    private void _initFrame(int screenWidth, int screenHeight) {
        EventQueue.invokeLater(() -> {
            // 注意 ： 初始化类成员变量
            myFrame.addKeyListener(new AlgoKeyListener());
            myFrame.addMouseListener(new AlgoMouseListener());
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

    public void run(int screenWidth, int screenHeight) {

    }
}
