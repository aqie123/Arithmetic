package myMVC.controller;

import myMVC.model.Circle;
import myMVC.view.AlgoFrame;
import mySwing.tools.AlgoVisHelper;

import java.awt.*;


public class AlgoVisualizer {
    // 数据 存储所有circle信息
    private Circle[] circles;
    // 视图层
    private AlgoFrame frame;

    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // 初始化小球数组
        Circle[] circles = new Circle[N];
        int R = 50;
        for(int i = 0;i<N;i++){
            int x = (int) Math.random()*(screenWidth-2*R) + R;
            int y = (int) Math.random()*(screenHeight-2*R) + R;
            int vx = (int)(Math.random()*11)-5;
            int vy = (int)(Math.random()*11)-5;
            circles[i] = new Circle(x,y,R,vx,vy);
        }

        // 初始化frame
        EventQueue.invokeLater(() -> {
            // 注意 ： 初始化类成员变量
            frame = new AlgoFrame("Welcome",screenWidth,screenHeight);
            new Thread(()->{
                while(true){
                    // 绘制数据
                    frame.render(circles);
                    AlgoVisHelper.pause(20);

                    // 更新数据
                    for(Circle circle : circles){
                        circle.move(0,0,screenWidth,screenHeight);
                    }
                }
            }).start();
        });
    }
}
